package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ExaminationDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Exam;
import com.dw.Monaca.model.Examination;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.UserLectureGrade;
import com.dw.Monaca.repository.ExamRepository;
import com.dw.Monaca.repository.ExaminationRepository;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.repository.UserLectureGradeRepository;
import com.dw.Monaca.service.ExaminationService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExaminationServiceImpl implements ExaminationService {
	private final ExaminationRepository examinationRepository;
	private final ExamRepository examRepository;
	private final UserRepository userRepository;
	private final LectureRepository lectureRepository;
	private final UserLectureGradeRepository userLectureGradeRepository;

	
	@Autowired
	public ExaminationServiceImpl(ExaminationRepository examinationRepository, ExamRepository examRepository,
			UserRepository userRepository, LectureRepository lectureRepository,
			UserLectureGradeRepository userLectureGradeRepository) {
		this.examinationRepository = examinationRepository;
		this.examRepository = examRepository;
		this.userRepository = userRepository;
		this.lectureRepository = lectureRepository;
		this.userLectureGradeRepository = userLectureGradeRepository;
	}

	// Examination이 생성되면서 채점(Iscorrect 값 부여)이 되고 그것이 Grade에 바로 적용됨.
	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	// 전체 Examination 불러오기
	@Override
	public ResponseDto<List<Examination>> getAllExamination() {
	
		getAuthenticatedUser();
		List<Examination> examination = examinationRepository.findAll();
		
		if(examination.isEmpty()) {
			
			return new ResponseDto<>(ResultCode.SUCCESS.name(), examination, "현재 제출된 답안지가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), examination, ResultCode.SUCCESS.getMsg());
	}

	// Lecture별 Examination 불러오기
	@Override
	public ResponseDto<List<Examination>> getExaminationByLecture(String lectureName) {
		
		  Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
		    if(!lectureOptional.isPresent()) {
		        throw new InvalidRequestException("Lecture Not Found","해당 강의를 찾을 수 없습니다.");
		    }
		    Lecture lecture = lectureOptional.get();
		    Optional<Exam> examOptional = examRepository.findByLecture(lecture);
		    Exam exam = examOptional.get();
		    
		List<Examination> examinations = examinationRepository.findByExam(exam);
		
		if(examinations.isEmpty()) {
			throw new InvalidRequestException("Examination Empty","해당 lecture에 대한 Examination이 없습니다. ");
		}
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),examinations,ResultCode.SUCCESS.getMsg());
	}

	// User별 Examination 불러오기
	@Override
	public ResponseDto<List<Examination>> getExaminationByUserLoginId(String loginId) {
		Optional<User> userOptional = userRepository.findByLoginId(loginId);
		   if(!userOptional.isPresent()) {
		        throw new InvalidRequestException("User Not Found","해당 사용자를 찾을 수 없습니다.");
		    }
		    User user = userOptional.get();
		List<Examination> examinations = examinationRepository.findByUser(user);
		
		if(examinations.isEmpty()) {
			return new ResponseDto<>(ResultCode.SUCCESS.name(),examinations,"해당 User의 Examination이 존재하지 않습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(),examinations,ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 유저의 Examination 불러오기
	@Override
	public ResponseDto<List<Examination>> getExaminationByUser() {
		User user = getAuthenticatedUser();
		List<Examination> examinations = examinationRepository.findByUser(user);
		
		if(examinations.isEmpty()) {
			return new ResponseDto<>(ResultCode.SUCCESS.name(),examinations,"현재 사용자의 Examination이 존재하지 않습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(),examinations,ResultCode.SUCCESS.getMsg());
	}
	
	// 특정 User의 특정한 Lecture의 Examination 불러오기
	@Override
	public ResponseDto<List<Examination>> getExaminationByLectureForUserLoginId(String loginId, String lectureName) {
		   Optional<User> userOptional = userRepository.findByLoginId(loginId);
		    if(!userOptional.isPresent()) {
		        throw new InvalidRequestException("User Not Found","해당 사용자를 찾을 수 없습니다.");
		    }
		    User user = userOptional.get();

		    Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
		    if(!lectureOptional.isPresent()) {
		        throw new InvalidRequestException("Lecture Not Found","해당 강의를 찾을 수 없습니다.");
		    }
		    Lecture lecture = lectureOptional.get();
		    Optional<Exam> examOptional = examRepository.findByLecture(lecture);
		    Exam exam = examOptional.get();
		    
		    List<Examination> examinations = examinationRepository.findByUserAndExam(user, exam);
		    if(examinations.isEmpty()) {
		        throw new InvalidRequestException("Examination Empty","해당 강의에 대한 Examination이 없습니다.");
		    }

		    return new ResponseDto<>(ResultCode.SUCCESS.name(), examinations, ResultCode.SUCCESS.getMsg());
	}
	
	// 현재 로그인한 유저의 특정한 Lecture의 Examination 불러오기
	@Override
	public ResponseDto<List<Examination>> getExaminationByLectureForUser(String lectureName) {
		
	    User user = getAuthenticatedUser();

	    Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
	    if(!lectureOptional.isPresent()) {
	    	throw new InvalidRequestException("Lecture Not Found","해당 강의를 찾을 수 없습니다.");
	    }
	    Lecture lecture = lectureOptional.get();
	    Optional<Exam> examOptional = examRepository.findByLecture(lecture);
	    
	    Exam exam = examOptional.get();
	    
	    List<Examination> examinations = examinationRepository.findByUserAndExam(user, exam);
	    if(examinations.isEmpty()) {
	        throw new InvalidRequestException("Examination Empty","해당 강의에 대한 Examination이 없습니다.");
	    }

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), examinations, ResultCode.SUCCESS.getMsg());	}

	// examination 생성(학생이 답안지 입력)
	@Transactional
	@Override
	public ResponseDto<Examination> createExamination(Long ExamId,ExaminationDto examinationDto) {
		Optional<Exam> examOptional = examRepository.findById(ExamId);
		if(!examOptional.isPresent()) {
			   throw new InvalidRequestException("Exam Empty","해당 Exam이 존재하지 않습니다.");
		}
		Exam exam = examOptional.get();

		Examination examination = new Examination();
		examination.setDate(LocalDateTime.now());
		examination.setUser(getAuthenticatedUser());
		examination.setExam(exam);
		examination.setAnswerText(examinationDto.getAnswerText());
		Examination savedExamination = examinationRepository.save(examination);

		
		 // Score 계산
	    int score = calculateScore(getAuthenticatedUser(), exam.getLecture());

	    // UserLectureGrade 객체 찾기 (없으면 생성)
	    UserLectureGrade userLectureGrade = userLectureGradeRepository.findByUserAndLecture(getAuthenticatedUser(), exam.getLecture())
	    	    .orElse(new UserLectureGrade(getAuthenticatedUser(), exam.getLecture()));

	    // Score 저장
	    userLectureGrade.setScore(score);
	    userLectureGradeRepository.save(userLectureGrade);
		
	   return new ResponseDto<>(ResultCode.SUCCESS.name(), savedExamination, ResultCode.SUCCESS.getMsg());
	   }

	//  주어진 강의의 모든 시험문항을 찾아, 각 시험문항에 대해 사용자의 Examination 객체를 찾고, 그 중 정답인 것의 개수를 모두 합산하여 점수를 계산
	 public int calculateScore(User user, Lecture lecture) {
	   List<Exam> exams = examRepository.findAllByLecture(lecture);
	   int totalExams = exams.size();
	   int correctExams = 0;

	   for (Exam exam : exams) {
	     List<Examination> examinations = examinationRepository.findByUserAndExam(user, exam);
	     correctExams += (int) examinations.stream().filter(Examination::isCorrect).count();
	    }

	     return (int) ((double) correctExams / totalExams * 100);
	    
	 }


}