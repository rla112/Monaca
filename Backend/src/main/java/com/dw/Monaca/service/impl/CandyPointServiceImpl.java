package com.dw.Monaca.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.CandyPointDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.UserCandyPointSumDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Candy;
import com.dw.Monaca.model.CandyPoint;
import com.dw.Monaca.model.CandyPointItem;
import com.dw.Monaca.repository.CandyPointItemRepository;
import com.dw.Monaca.repository.CandyPointRepository;
import com.dw.Monaca.repository.CandyRepository;
import com.dw.Monaca.repository.RatingRepository;
import com.dw.Monaca.service.CandyPointService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CandyPointServiceImpl implements CandyPointService {

	private final CandyPointRepository candyPointRepository;
    private final CandyRepository candyRepository;
    private final UserRepository userRepository;
    private final CandyPointItemRepository candyPointItemRepository;
    private final RatingRepository ratingRepository;
    
   
    @Autowired
	public CandyPointServiceImpl(CandyPointRepository candyPointRepository, CandyRepository candyRepository,
			UserRepository userRepository, CandyPointItemRepository candyPointItemRepository,
			RatingRepository ratingRepository) {
		super();
		this.candyPointRepository = candyPointRepository;
		this.candyRepository = candyRepository;
		this.userRepository = userRepository;
		this.candyPointItemRepository = candyPointItemRepository;
		this.ratingRepository = ratingRepository;
	}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
	// CandyPoint 생성
	@Override
	public ResponseDto<CandyPoint> createCandyPoint(CandyPointDto candyPointDto) {
		User user = getAuthenticatedUser();
		Candy candy = candyRepository.findByCandyName(candyPointDto.getCandyName());
		Optional<CandyPointItem> candyPointItemOptional = candyPointItemRepository.findByItemName(candyPointDto.getCandyPointItem());
		CandyPointItem candyPointItem = candyPointItemOptional.get();
		
		CandyPoint candyPoint = new CandyPoint();
		candyPoint.setTimeStamp(LocalDateTime.now());
		candyPoint.setUser(user);
		candyPoint.setCandy(candy);
		candyPoint.setPoint(candyPointDto.getPoint());
		candyPoint.setCandyPointItem(candyPointItem);
		candyPoint.setDescription(candyPointDto.getDescription());
		
		candyPointRepository.save(candyPoint);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),candyPoint,ResultCode.SUCCESS.getMsg());
	}

	// 모든 사용자의 CandyPoint 기록 조회
	@Override
	public ResponseDto<List<CandyPoint>> getAllCandyPoint() {
		getAuthenticatedUser();
		List<CandyPoint> candyPoints = candyPointRepository.findAll();
		if(candyPoints.isEmpty()) {
			throw new InvalidRequestException("CandyPoint Empty", "CandyPoint 기록이 없습니다.");
		}
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),candyPoints,ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 유저의 CandyPoint 기록 조회
	@Override
	public ResponseDto<List<CandyPoint>> getCandyPointByUser() {
		User user = getAuthenticatedUser();
		List<CandyPoint> candyPoints = candyPointRepository.findByUser(user);
		
		if(candyPoints.isEmpty()) {
			throw new InvalidRequestException("CandyPoint Empty", "현재 사용자의 CandyPoint 기록이 존재하지 않습니다.");
		}
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),candyPoints,ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 유저의 특정 날짜에 해당하는 CandyPoint 기록 조회
	@Override
	public ResponseDto<List<CandyPoint>> getCandyPointsByUserAndDate(LocalDate timeStamp) {
	    User user = getAuthenticatedUser();
	    // 특정 날짜의 시작 시간과 끝 시간 사이에 생성된 CandyPoint를 찾습니다.
	    // 입력받은 날짜(timeStamp)의 시작 시간을 구함.
	    LocalDateTime startOfDay = timeStamp.atStartOfDay();
	    // 입력받은 날짜의 다음 날의 시작 시간을 구함. 이는 입력받은 날짜의 종료 시간과 같음. 
	    LocalDateTime endOfDay = timeStamp.plusDays(1).atStartOfDay();
	    // CandyPoint의 user 필드가 user이고, timeStamp 필드가 startOfDay와 endOfDay 사이인 CandyPoint를 찾음.
	    List<CandyPoint> candyPoints = candyPointRepository.findByUserAndTimeStampBetween(user, startOfDay, endOfDay);
	    if(candyPoints.isEmpty()) {
	        throw new InvalidRequestException("CandyPoint Empty", "선택한 날짜의 CandyPoint 기록이 없습니다.");
	    }
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), candyPoints, ResultCode.SUCCESS.getMsg());
	}
	
	// 현재 로그인한 사용자의 총 CandyPoint 합계 조회
	@Override
	public ResponseDto<Integer> getTotalCandyPointsByUser() {
		User user = getAuthenticatedUser();
		// User 객체를 파라미터로 받아 해당 사용자의 모든 CandyPoint의 point 필드 값을 합한 결과를 반환함.
		 Integer totalPoints = candyPointRepository.sumPointsByUser(user);
		   return new ResponseDto<>(ResultCode.SUCCESS.name(), totalPoints, ResultCode.SUCCESS.getMsg());

	}

	// 모든 사용자의 총 CandyPoint 합계  조회
	@Override
	public ResponseDto<List<UserCandyPointSumDto>> getTotalCandyPointsByAllUser() {
	    getAuthenticatedUser();
	    // sumPointsByAllUsers() 메서드를 호출하여 모든 사용자의 총 CandyPoint를 계산함.
	    List<UserCandyPointSumDto> totalPoints = candyPointRepository.sumPointsByAllUsers();
	    // 결과 코드(ResultCode.SUCCESS.name()), 데이터(totalPoints),결과 메시지(ResultCode.SUCCESS.getMsg())
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), totalPoints, ResultCode.SUCCESS.getMsg());
	}

}
