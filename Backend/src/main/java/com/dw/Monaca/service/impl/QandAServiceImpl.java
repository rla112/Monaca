package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.QandADto;
import com.dw.Monaca.dto.QandAListDto;
import com.dw.Monaca.dto.QandAWritingDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.QandA;
import com.dw.Monaca.model.Reply;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.repository.QandARepository;
import com.dw.Monaca.repository.ReplyRepository;
import com.dw.Monaca.service.QandAService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class QandAServiceImpl implements QandAService {

	private final QandARepository qandaRepository;
	private final UserRepository userRepository;
	private final LectureRepository lectureRepository;
	private final ReplyRepository replyRepository;

	@Autowired
	public QandAServiceImpl(QandARepository qandaRepository, UserRepository userRepository,
			LectureRepository lectureRepository, ReplyRepository replyRepository) {
		this.qandaRepository = qandaRepository;
		this.userRepository = userRepository;
		this.lectureRepository = lectureRepository;
		this.replyRepository = replyRepository;
	}

	// SecurityUtil을 사용하여 현재 로그인한 사용자의 ID를 가져오고, 이를 바탕으로 사용자 정보를 userRepository로부터 조회
	// 해당 사용자가 존재하지 않으면 예외를 발생
	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
    
	
	// Q&A 등록
	@Override
	public ResponseDto<QandADto> createQandA(QandAWritingDto qandaWritingDto) {
        User author = getAuthenticatedUser();
		Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(qandaWritingDto.getLecture());
		if(lectureOptional.isEmpty()) {
			throw new InvalidRequestException("lecture Empty","해당 강의가 존재하지 않습니다.");
		}
		if (author == null) {
			throw new InvalidRequestException("Invalid User", "글쓰기 권한이 없습니다.");
		}
		
		Lecture lecture = lectureOptional.get();
		
		// QandA객체 생성
		QandA qanda = new QandA();
		qanda.setCreateAt(LocalDateTime.now());
		qanda.setAuthor(author);
		qanda.setLecture(lecture);
		qanda.setTitle(qandaWritingDto.getTitle());
		qanda.setText(qandaWritingDto.getText());
		qanda.setNewStatus(true);  // 'newStatus'를 true로 설정
		qanda.setDisposablePw(qandaWritingDto.getDisposablePw());
		qandaRepository.save(qanda);
		
		
		QandADto qandaDto = new QandADto();
		qandaDto.setAuthor(qanda.getAuthor().getLoginId());
		qandaDto.setLectureName(qanda.getLecture().getLectureName());
		qandaDto.setTitle(qanda.getTitle());
		qandaDto.setText(qanda.getText());
		qandaDto.setNewStatus(qanda.isNewStatus());
		qandaDto.setDusoisablePw(qanda.getDisposablePw());
		qandaDto.setCreateAt(qanda.getCreateAt().toString());
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(), qandaDto, "질문등록이 완료되었습니다.");
		
	}

	
	// 전체 유저의 Q&A 목록 불러오기
	@Override
	public ResponseDto<List<QandAListDto>> getAllQandA() {
        getAuthenticatedUser(); // 인증된 사용자의 정보가 나중에 필요한 경우가 아니라면, User 객체를 변수에 할당할 필요가 없으므로 단순히 getAuthenticatedUser();를 호출함으로써 인증을 확인.
        List<QandA> qandaList = qandaRepository.findAll();
		if (qandaList.isEmpty()) {
			throw new InvalidRequestException("QandA Empty", "질의응답이 존재하지 않습니다.");
		}
		
		List<QandAListDto> qandAListDtos = new ArrayList<>();
		qandaList.stream().forEach(data -> {
			QandAListDto qandAListDto = new QandAListDto();
			qandAListDto.setAuthor(data.getAuthor().getLoginId());
			qandAListDto.setLectureName(data.getLecture().getLectureName());
			qandAListDto.setTitle(data.getTitle());
			qandAListDto.setCreateAt(data.getCreateAt().toString());
			qandAListDto.setNewStatus(data.isNewStatus());
			qandAListDtos.add(qandAListDto);
		
		});
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(), qandAListDtos, ResultCode.SUCCESS.getMsg());
	}

	// Lecture별 수강생 Q&A 목록 불러오기
	public ResponseDto<List<QandAListDto>> getAllQandAByLecture(String lectureName) {
//		getAuthenticatedUser();
        Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
        if(lectureOptional.isEmpty()) {
        	throw new InvalidRequestException("Lecture Epmty","해당 강의가 존재하지 않습니다.");
        }
        	
        Lecture lecture = lectureOptional.get();
        List<QandA> qandaList = qandaRepository.findByLecture(lecture);
//		  if (qandaList.isEmpty()) {
//		        throw new InvalidRequestException("QandA Empty", "질의응답이 존재하지 않습니다.");
//		   }
			List<QandAListDto> qandAListDtos = new ArrayList<>();
			qandaList.stream().forEach(data -> {
				QandAListDto qandAListDto = new QandAListDto();
				qandAListDto.setAuthor(data.getAuthor().getLoginId());
				qandAListDto.setLectureName(data.getLecture().getLectureName());
				qandAListDto.setTitle(data.getTitle());
				qandAListDto.setText(data.getText());
				qandAListDto.setNewStatus(data.isNewStatus());
				qandAListDto.setCreateAt(data.getCreateAt().toString());
				qandAListDtos.add(qandAListDto);
			
			});
		  
		  // findByLecture 메서드가 제대로 구현되어 있다면, 이미 강의에 해당하는 Q&A만을 반환할 것이기 때문에
		  // QandA 객체가 실제로 lectures와 연결된 강의인지 확인하는 로직은 필요 없어서 주석처리!
//		List<QandA> qandas = new ArrayList<>();
//		for (QandA qna : qanda) {
//			if (!qna.getLecture().equals(lectures)) {
//				throw new InvalidRequestException("Lecture", "Q&A 목록과 강의 정보가 일치하지 않습니다.");
//			} else {
//				qandas.add(qna);
//			}
//		}
		  return new ResponseDto<>(ResultCode.SUCCESS.name(), qandAListDtos, ResultCode.SUCCESS.getMsg());
	}

	// 현재 사용자의 Q&A 목록 불러오기
	public ResponseDto<List<QandAListDto>> getAllQandAByUser() {
	    User author = getAuthenticatedUser();
	    List<QandA> qandas = qandaRepository.findByAuthor(author);
	    if (qandas.isEmpty()) {
	        throw new InvalidRequestException("QandA Empty", "질의응답이 존재하지 않습니다.");
	    }

	    List<QandAListDto> qandAListDtos = new ArrayList<>();

	    qandas.stream().forEach(data -> {
	        if (!data.getAuthor().equals(author)) {
	            throw new InvalidRequestException("Author", "현재 사용자의 Q&A 목록이 아닙니다.");
	        }

	        QandAListDto qandAListDto = new QandAListDto();
	        qandAListDto.setAuthor(data.getAuthor().getLoginId());
	        qandAListDto.setLectureName(data.getLecture().getLectureName());
	        qandAListDto.setTitle(data.getTitle());
	        qandAListDto.setNewStatus(data.isNewStatus());
	        qandAListDto.setCreateAt(data.getCreateAt().toString());
	        qandAListDtos.add(qandAListDto);
	    });

	    return new ResponseDto<>(ResultCode.SUCCESS.name(),qandAListDtos, ResultCode.SUCCESS.getMsg());
	}


	
	
	//QandA ID를 이용하여 특정 QandA 불러오기
	public ResponseDto<QandADto> getQandAById(Long id){
        getAuthenticatedUser();
		Optional<QandA> qandaOptional = qandaRepository.findById(id);
	    
	    if(qandaOptional.isEmpty()) {
	    	throw new InvalidRequestException("QandA Not Found", "해당 ID에 대한 질의응답이 존재하지 않습니다.");
	    } 
	    
	    QandA qanda = qandaOptional.get();
	    
	    QandADto qandADto = new QandADto();
	    qandADto.setAuthor(qanda.getAuthor().getLoginId());
	    qandADto.setLectureName(qanda.getLecture().getLectureName());
	    qandADto.setTitle(qanda.getTitle());
	    qandADto.setText(qanda.getText());
	    qandADto.setDusoisablePw(qanda.getDisposablePw());
	    qandADto.setCreateAt(qanda.getCreateAt().toString());
	    qandADto.setNewStatus(qanda.isNewStatus());

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), qandADto, ResultCode.SUCCESS.getMsg());
	    
	}
	    

	// Q&A 수정 (Reply가 등록되기 전까지만 가능!!)
	public ResponseDto<QandADto> updateQandA(QandA updateQandA, Long qandaId){
		List<Reply> existingReply = replyRepository.findAllByQandAId(qandaId);
	    Optional<QandA> qandaOptional = qandaRepository.findById(qandaId);
        User author = getAuthenticatedUser();

	    if (qandaOptional.isEmpty()) {
	        throw new InvalidRequestException(Long.toString(qandaId), "해당 질의응답이 존재하지 않습니다.");
	    }
	    
	    QandA qna = qandaOptional.get();
	    if (!author.equals(qna.getAuthor())) {
	        throw new InvalidRequestException("Unauthorized", "Q&A 작성자가 현재 로그인 한 작성자와 일치하지 않습니다.");
	    }
	    // Reply가 등록되기 전에만 수정 가능하기 때문에 Reply가 있는지 확인.
	    if(!existingReply.isEmpty()) {
	    	throw new InvalidRequestException("Reply existing", "Q&A의 Reply가 존재하여 수정이 불가능합니다.");
	    }

	    // 수정할 필드들을 업데이트
	    qna.setTitle(updateQandA.getTitle());
	    qna.setText(updateQandA.getText());

	    // 수정된 QandA 객체를 저장
	    QandA savedQandA = qandaRepository.save(qna);

	    QandADto qandADto = new QandADto();
	    qandADto.setAuthor(savedQandA.getAuthor().getLoginId());
	    qandADto.setLectureName(savedQandA.getLecture().getLectureName());
	    qandADto.setTitle(savedQandA.getTitle());
	    qandADto.setText(savedQandA.getText());
	    qandADto.setCreateAt(savedQandA.getCreateAt().toString());
	    qandADto.setDusoisablePw(savedQandA.getDisposablePw());
	    qandADto.setNewStatus(savedQandA.isNewStatus());
	    // 응답 객체를 생성하여 반환
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), qandADto, "질의응답이 성공적으로 수정되었습니다.");
	}

	// Q&A 삭제( 작성자와 관리자만 가능 )
	@Override
	public ResponseDto<String> deleteQandA(Long qandaId) {
        User author = getAuthenticatedUser();
	    Optional<QandA> qandaOptional = qandaRepository.findById(qandaId);

	    if (qandaOptional.isEmpty()) {
	        throw new InvalidRequestException(Long.toString(qandaId), "해당 질의응답이 존재하지 않습니다.");
	    }
	    QandA qanda = qandaOptional.get();
	    
	    // 현재 인증된 사용자(user)가 해당 Q&A를 업로드한 작성자와 동일한지를 판별하는 논리 값
	    // 현재 사용자와 Q&A를 업로드한 작성자가 동일한 객체인지 비교하고, 그 결과를 boolean 형태로 반환
	    // 동일한 경우 true, 그렇지 않은 경우 false를 반환
	    boolean isAuthor = author.equals(qanda.getAuthor());
	    
	    // isAdmin은 현재 인증된 author가 'ADMIN' 권한을 가지고 있는지를 판별하는 논리 값. 
	    boolean isAdmin = author.getAuthorities().stream().anyMatch(auth -> auth.getAuthorityName().equals("ADMIN"));

	    // 이 부분은 isAuthor 또는 isAdmin 중 어느 하나라도 true가 아닌 경우,
	    // 즉 현재 사용자가 해당 Q&A를 업로드한 작성자도 아니고 'ADMIN' 권한도 없는 경우에 
	    // InavalidRequestException을 발생시킴. 이 예외는 사용자가 이 자료를 삭제할 권한이 없음을 나타냄.
	    if (!(isAuthor || isAdmin)) {
	        throw new InvalidRequestException("Unauthorized", "Q&A를 삭제할 권한이 없습니다.");
	    }
	    //	    if (!author.equals(qanda.getAuthor())) {
//	        throw new InvalidRequestException("Unauthorized", "Q&A 작성자가 현재 로그인 한 작성자와 일치하지 않습니다.");
//	    }

	    // Q&A에 연결된 Reply들을 삭제
	    List<Reply> replies = replyRepository.findAllByQandAId(qandaId);
	    replyRepository.deleteAll(replies);

	    // Q&A를 삭제
	    qandaRepository.deleteById(qandaId);

	    return new ResponseDto<>("SUCCESS", null, "질의응답 삭제가 완료되었습니다.");
	}
}
