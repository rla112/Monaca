package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ProfessorNoticeDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.ProfessorNotice;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.repository.ProfessorNoticeRepository;
import com.dw.Monaca.service.ProfessorNoticeService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ProfessorNoticeServiceImpl implements ProfessorNoticeService {

	private final ProfessorNoticeRepository professorNoticeRepository;
	private final UserRepository userRepository;
	private final LectureRepository lectureRepository;
	
	@Autowired
	public ProfessorNoticeServiceImpl(ProfessorNoticeRepository professorNoticeRepository,
			UserRepository userRepository, LectureRepository lectureRepository) {
		this.professorNoticeRepository = professorNoticeRepository;
		this.userRepository = userRepository;
		this.lectureRepository = lectureRepository;
	}
	
	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
		// Notice 전체 조회
		@Override
		public ResponseDto<List<ProfessorNotice>> getAllProfessorNotice() {
			List<ProfessorNotice> professorNotice = professorNoticeRepository.findAll();
			if (professorNotice.isEmpty()) {
				throw new InvalidRequestException("ProfessorNotice Empty", "ProfessorNotice가 존재하지 않습니다.");
			}
			return new ResponseDto<>(ResultCode.SUCCESS.name(),  professorNotice, ResultCode.SUCCESS.getMsg());
		}
		
		// Notice ID로 조회
		public ResponseDto<ProfessorNotice> getProfessorNoticeById(Long id){
		    Optional<ProfessorNotice> professorNoticeOptional = professorNoticeRepository.findById(id);
		    
		    if(professorNoticeOptional.isPresent()) {
		    	ProfessorNotice professorNotice =  professorNoticeOptional.get();
		        return new ResponseDto<>(ResultCode.SUCCESS.name(),  professorNotice, ResultCode.SUCCESS.getMsg());
		    } else {
		        throw new InvalidRequestException("ProfessorNotice Not Found", "해당 ID에 대한 ProfessorNotice가 존재하지 않습니다.");
		    }
		}
		
		
		// Notice Lecture별로 조회
		@Override
		public ResponseDto<List<ProfessorNotice>> getAllProfessorNoticeByLecture(String lecture) {
			Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lecture);
			Lecture lectures = lectureOptional.get();
			List<ProfessorNotice> professorNotice = professorNoticeRepository.findByLecture(lectures);
			if (professorNotice.isEmpty()) {
				throw new InvalidRequestException("ProfessorNotice Empty", "질의응답이 존재하지 않습니다.");
			}
			List<ProfessorNotice> professorNotices = new ArrayList<>();
			for (ProfessorNotice notice : professorNotice) {
				if (!notice.getLecture().equals(lectures)) {
					throw new InvalidRequestException("Lecture", "ProfessorNotice 목록과 강의 정보가 일치하지 않습니다.");
				} else {
					professorNotices.add(notice);
				}
			}
			return new ResponseDto<>(ResultCode.SUCCESS.name(), professorNotices, ResultCode.SUCCESS.getMsg());
		}
		
		
		// Notice 등록
		@Override
		public ResponseDto<ProfessorNotice> createProfessorNotice(ProfessorNoticeDto professorNoticeDto) {
			User author = getAuthenticatedUser();
			if (author == null) {
				throw new InvalidRequestException("Invalid User", "글쓰기 권한이 없습니다.");
			}
			String lectureName = professorNoticeDto.getLecture();
			Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
			
			if(lectureOptional.isEmpty()) {
				throw new InvalidRequestException("Lecture Empty","해당 강의명을 가진 Lecture가 존재하지 않습니다.");
			}
			Lecture lecture = lectureOptional.get();
			
			// Notice객체 생성
			ProfessorNotice professorNotice = new ProfessorNotice();
			professorNotice.setCreateAt(LocalDateTime.now());
			professorNotice.setAuthor(author);
			professorNotice.setLecture(lecture);
			professorNotice.setTitle(professorNoticeDto.getTitle());
			professorNotice.setText(professorNoticeDto.getText());
			professorNoticeRepository.save(professorNotice);
			return new ResponseDto<>(ResultCode.SUCCESS.name(), professorNotice, "ProfessorNotice 등록이 완료되었습니다.");
		}

		// Notice 수정
		public ResponseDto<ProfessorNotice> updateProfessorNotice(ProfessorNotice updateProfessorNotice, Long id){
		    Optional<ProfessorNotice> professorNoticeOptional = professorNoticeRepository.findById(id);
		    User author = getAuthenticatedUser();
		    
		    if (professorNoticeOptional.isEmpty()) {
		        throw new InvalidRequestException(Long.toString(id), "해당 ProfessorNoticeId가 존재하지 않습니다.");
		    }
		    ProfessorNotice professorNotice = professorNoticeOptional.get();
		    if (!author.equals(professorNotice.getAuthor())) {
		        throw new InvalidRequestException("Unauthorized", "ProfessorNotice 작성자가 현재 로그인 한 작성자와 일치하지 않습니다.");
		    }
		    // 수정할 필드들을 업데이트
		    professorNotice.setTitle(updateProfessorNotice.getTitle());
		    professorNotice.setText(updateProfessorNotice.getText());

		    // 수정된 Notice 객체를 저장
		    ProfessorNotice savedProfessorNotice = professorNoticeRepository.save(professorNotice);

		    // 응답 객체를 생성하여 반환
		    return new ResponseDto<>(ResultCode.SUCCESS.name(), savedProfessorNotice, "ProfessorNotice가 성공적으로 수정되었습니다.");
		}
		
		// Notice 삭제
		@Override
		public ResponseDto<ProfessorNotice> deleteProfessorNotice(Long  id) {
		    User author = getAuthenticatedUser();
		    Optional<ProfessorNotice>professorNoticeOptional = professorNoticeRepository.findById(id);

		    if (professorNoticeOptional.isEmpty()) {
		        throw new InvalidRequestException(Long.toString(id), "해당 ProfessorNoticeId가 존재하지 않습니다.");
		    }
		    ProfessorNotice adminNotice = professorNoticeOptional.get();
		    if (!author.equals(adminNotice.getAuthor())) {
		        throw new InvalidRequestException("Unauthorized", "ProfessorNotice 작성자가 현재 로그인 한 작성자와 일치하지 않습니다.");
		    }

		    // Notice를 삭제
		    professorNoticeRepository.deleteById(id);

		    return new ResponseDto<>(ResultCode.SUCCESS.name(), adminNotice, "ProfessorNotice 삭제가 완료되었습니다.");
		}

		// 새로운 Notice는 알림표시, 7일이 지나면 알림표시 삭제
		@Scheduled(cron = "0 0 0 * * ?")  // 매일 자정에 실행
		@Override
		public ResponseDto<String> updateStatuProfessorNotice() {
		LocalDateTime threshold = LocalDateTime.now().minusDays(7);

		List<ProfessorNotice> oldProfessorNotices = professorNoticeRepository.findAllByCreateAtBeforeAndNewStatusIsTrue(threshold);

		if (oldProfessorNotices.isEmpty()) {
		throw new InvalidRequestException("ProfessorNotice Empty", "7일 이상 지난 ProfessorNotice가 존재하지 않습니다.");
		}

		for (ProfessorNotice professorNotice : oldProfessorNotices) {
		professorNotice.setNewStatus(false);
		professorNoticeRepository.save(professorNotice);
		}

		return new ResponseDto<>("SUCCESS", null, "7일 이상 지난 ProfessorNotice의 newStatus가 변경되었습니다.");
		}
	
}
