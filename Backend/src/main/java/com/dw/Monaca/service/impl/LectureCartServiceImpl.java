package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.LectureCartDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.ClassRoom;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.LectureCart;
import com.dw.Monaca.repository.ClassRoomRepository;
import com.dw.Monaca.repository.LectureCartRepository;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.service.LectureCartService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LectureCartServiceImpl implements LectureCartService{

	private final LectureCartRepository lectureCartRepository;
	private final LectureRepository lectureRepository;
	private final ClassRoomRepository classRoomRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public LectureCartServiceImpl(LectureCartRepository lectureCartRepository, LectureRepository lectureRepository,
			ClassRoomRepository classRoomRepository, UserRepository userRepository) {
		this.lectureCartRepository = lectureCartRepository;
		this.lectureRepository = lectureRepository;
		this.classRoomRepository = classRoomRepository;
		this.userRepository = userRepository;
	}


	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}


	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item(Lecture) 전부 조회
	@Override
	public ResponseDto<List<LectureCartDto>> getAllLectureCart() {
		User user = getAuthenticatedUser();
		List<LectureCart> lectureCart = lectureCartRepository.findByUser(user);
		if(lectureCart.isEmpty()) {
			// 사용자 입장에서는 장바구니가 비어있을 때 서버에서 오류 메시지를 보내는 것이 아니라,
			// 장바구니가 비어있다는 메시지를 보내는 것이 더 자연스러울 수 있기 때문에
			// InvalidRequstException 대신 비어있는 장바구니, 장바구니가 비어있다는 메세지를 반환.
			return new ResponseDto<>(ResultCode.SUCCESS.name(), null, "장바구니가 비어 있습니다.");
		}
		List<LectureCartDto> lectureCartDtos = new ArrayList<>();
		lectureCart.stream().forEach(data -> {
			 Lecture lecture = lectureRepository.findById(data.getLecture().getId()).orElse(null);
		      if (lecture == null) {
		    	  throw new InvalidRequestException("Lecture Empty","해당 강의 정보가 존재하지 않습니다.");
		      }
			LectureCartDto lectureCartDto = new LectureCartDto();
			lectureCartDto.setId(data.getId());
			lectureCartDto.setUser(data.getUser().getLoginId());
			lectureCartDto.setLectureId(data.getLecture().getId());
			lectureCartDtos.add(lectureCartDto);
		});
		
		
			return new ResponseDto<>(ResultCode.SUCCESS.name(),lectureCartDtos,ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item(Lecture)을 ID로 조회
	@Override
	public ResponseDto<LectureCartDto> getLectureCartById(Long id) {
	    Optional<LectureCart> lectureCartOptional = lectureCartRepository.findById(id);
	    User user = getAuthenticatedUser();
	    if(lectureCartOptional.isPresent()) {

	        LectureCart lectureCart = lectureCartOptional.get();

	        if(!lectureCart.getUser().equals(user)) {
	            throw new InvalidRequestException("Unauthorized", "해당 장바구니에 대한 권한이 없습니다.");
	        }

	        LectureCartDto lectureCartDto = new LectureCartDto();
			lectureCartDto.setId(lectureCart.getId());
			lectureCartDto.setUser(lectureCart.getUser().getLoginId());
			lectureCartDto.setLectureId(lectureCart.getLecture().getId());
			
	        return new ResponseDto<>(ResultCode.SUCCESS.name(),lectureCartDto,ResultCode.SUCCESS.getMsg());

	    } else {

	        throw new InvalidRequestException("Not Found", "해당 ID를 가진 장바구니 아이템이 존재하지 않습니다.");
	    }

	}

	// 현재 로그인한 User의 cart에 Item(Lecture) 담기
	@Override
	public ResponseDto<Long> createLectureCart(LectureCartDto lectureCartDto) {
	    User user = getAuthenticatedUser(); // 현재 인증된 사용자를 가져옴
	    Lecture lecture = lectureRepository.findById(lectureCartDto.getLectureId())
	        .orElseThrow(() -> new InvalidRequestException("Lecture Not Found", "강의를 찾을 수 없습니다."));

	    // 이미 구매한 강의인지 확인
	    boolean alreadyPurchased = classRoomRepository.existsByUserAndLecture(user, lecture);
	    if (alreadyPurchased) {
	        throw new InvalidRequestException("Already Purchased", "이미 구매한 강의입니다.");
	    }
	    
	 // 이미 장바구니에 담긴 강의인지 확인
	    boolean alreadyInCart = lectureCartRepository.existsByUserAndLecture(user, lecture);
	    if (alreadyInCart) {
	        throw new InvalidRequestException("Already In Cart", "이미 장바구니에 담긴 강의입니다.");
	    }
	    
	    LectureCart lectureCart = new LectureCart();
	    lectureCart.setUser(user);
	    lectureCart.setLecture(lecture);

	    LectureCart savedLectureCart = lectureCartRepository.save(lectureCart);

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), savedLectureCart.getId(), "강의가 장바구니에 추가되었습니다.");
	}

	
	
	// 결제 성공 시 LectureCart -> ClassRoom 저장
	// LectureCart -> ClassRoom 복사
	private ClassRoom convertToClassRoom(LectureCart lectureCart, User user) {
	    ClassRoom classRoom = new ClassRoom();
	    classRoom.setUser(user);
	    classRoom.setLecture(lectureCart.getLecture());
	    classRoom.setProgressRate(0); // 진도율 초기화
	    classRoom.setViewingRecord(0); // 시청 기록 초기화
	    classRoom.setRecentViewing(LocalDateTime.now()); // 최근 시청 기록은 현재 시간으로 설정
	    return classRoom; 
	}

	// 결제 성공 시 LectureCart -> ClassRoom 저장
	public ResponseDto<Long> purchaseLectureCart(Long id) {
	    User user = getAuthenticatedUser(); // 현재 인증된 사용자를 가져옴
	    LectureCart lectureCart = lectureCartRepository.findById(id)
	        .orElseThrow(() -> new InvalidRequestException("LectureCart Not Found", "장바구니 항목을 찾을 수 없습니다."));
	    
	    ClassRoom classRoom = convertToClassRoom(lectureCart, user);
	    ClassRoom savedClassRoom = classRoomRepository.save(classRoom);
	    
	    // 결제 후 LectureCart에서 삭제
	    lectureCartRepository.delete(lectureCart);

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), savedClassRoom.getId(), "강의가 구매되었습니다.");
	}

	
	
	// 현재 로그인한 User의 cart에 담아놓은 Item(lecture) 전부 삭제
	@Override
	public ResponseDto<String> deleteAllLectureCart() {
		User user = getAuthenticatedUser();
		List<LectureCart> carts = lectureCartRepository.findByUser(user);
		
		if(carts.isEmpty()) {
			throw new InvalidRequestException("LectureCart Empty", "장바구니에 강의가 존재하지 않습니다.");
		}

		lectureCartRepository.deleteAll(carts);
		return new ResponseDto<>(ResultCode.SUCCESS.name(),"Deleted","장바구니가 비워졌습니다.");	
	}

	// 현재 로그인한 User의 cart에 담아놓은 Item(lecture) ID로 선택하여 삭제
	@Override
	public ResponseDto<String> deleteLectureCartById(Long id) {
	    User user = getAuthenticatedUser();
	    LectureCart cart = lectureCartRepository.findById(id)
	        .orElseThrow(() -> new InvalidRequestException("LectureCart Not Found", "장바구니 항목을 찾을 수 없습니다."));

	    if (!cart.getUser().equals(user)) {
	        throw new InvalidRequestException("Unauthorized", "해당 장바구니 항목에 대한 권한이 없습니다.");
	    }

	    Long lectureId = cart.getLecture().getId();
	    
	    lectureCartRepository.deleteById(id);
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureId.toString(), "장바구니 항목이 삭제되었습니다.");
	}


}
