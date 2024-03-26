
package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ExamDto;
import com.dw.Monaca.dto.ExamStudentDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Exam;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.repository.ExamRepository;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.service.ExamService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
	
	private final ExamRepository examRepository;
	private final LectureRepository lectureRepository;
	private final UserRepository userRepository;


	@Autowired
	public ExamServiceImpl(ExamRepository examRepository, LectureRepository lectureRepository,
			UserRepository userRepository) {
		super();
		this.examRepository = examRepository;
		this.lectureRepository = lectureRepository;
		this.userRepository = userRepository;
	}

	//  Exam 객체를 ExamStudentDto로 변환하는 메소드
	public ExamStudentDto convertToExamStudentDto(Exam exam) {
	    return new ExamStudentDto(
	        exam.getLecture().getLectureName(),
	        exam.getExamTitle(),
	        exam.getExamText(),
	        exam.getExamImage(),
	        exam.getMultipleChoice() // 보기(선지)
	    );
	}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}


	// 전체 Exam 가져오기
	@Override
	public ResponseDto<List<Exam>> getAllExam() {
		getAuthenticatedUser();
		List<Exam> exam = examRepository.findAll();
		if(exam.isEmpty()) {
			
			throw new InvalidRequestException("Exam Empty", "Exam이 존재하지 않습니다.");
			
		}
		
			return new ResponseDto<>(ResultCode.SUCCESS.name(),exam,ResultCode.SUCCESS.getMsg());
	}

	// ExamID로 특정 Exam만 가져오기
	@Override
	public ResponseDto<Exam> getExamByExamId(Long examId) {
		getAuthenticatedUser();
		Optional<Exam> examOptional = examRepository.findById(examId);
		
		if(examOptional.isEmpty()) {	
			throw new InvalidRequestException("Exam Empty", "Exam이 존재하지 않습니다.");
		}
		
		Exam exam = examOptional.get();
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),exam,ResultCode.SUCCESS.getMsg());
	}

	// 특정 Lecture별 Exam만 가져오기
	@Override
	public ResponseDto<List<Exam>> getExamByLectureName(String lectureName) {
	    getAuthenticatedUser();
	    Lecture lecture = lectureRepository.findByLectureName(lectureName)
	        .orElseThrow(() -> new InvalidRequestException("Lecture Not Found", "해당 강의를 찾을 수 없습니다."));
	    List<Exam> exams = examRepository.findAllByLecture(lecture);
	    
	    if(exams.isEmpty()) {
	        throw new InvalidRequestException("Exams Empty", "Exam이 존재하지 않습니다.");
	    }
	    
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), exams, ResultCode.SUCCESS.getMsg());
	}

	
	// 학생이 시험 볼 때 Exam 가져오기
	@Override
	public ResponseDto<List<ExamStudentDto>> getExamByLectureNameForStudent(String lectureName) {
	    getAuthenticatedUser();
		Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
		Lecture lecture = lectureOptional.get();
	    List<Exam> exams = examRepository.findAllByLecture(lecture);
	    if(!exams.isEmpty()) {
	    	throw new InvalidRequestException("Exams Empty", "Exam이 존재하지 않습니다.");
	    }


//	    학생이 시험 정보를 조회할 때 답안 정보가 노출되지 않도록 할 수 있도록 함.
	    // 조회한 시험 정보들을 ExamStudentDto로 변환(Exam 객체를 ExamStudentDto로 변환)하여 반환함.
	    List<ExamStudentDto> examStudentDtos = exams.stream()
	    	// stream과 map을 이용하여 각 Exam 객체를 convertToExamStudentDto 메소드를 통해 ExamStudentDto로 변환하고, 
	    	//이를 다시 리스트로 모음.
	        .map(this::convertToExamStudentDto)
	        .collect(Collectors.toList());

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), examStudentDtos, ResultCode.SUCCESS.getMsg());
	}
	
	// Exam 생성
	@Override
	public ResponseDto<Exam> addExam (ExamDto examDto) {
		
		Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(examDto.getLectureName());
	    Lecture lecture = lectureOptional.get();
		
		Exam exam = new Exam();
	    exam.setCreateAt(LocalDateTime.now());
	    exam.setLecture(lecture);
	    exam.setExamTitle(examDto.getExamTitle());
	    exam.setExamText(examDto.getExamText());
	    exam.setExamImage(examDto.getExamImage());
	    exam.setMultipleChoice(examDto.getMultipleChoice());
	    exam.setCorrectAnswer(examDto.getCorrectAnswer());
	    
	    Exam savedExam = examRepository.save(exam);

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), savedExam, ResultCode.SUCCESS.getMsg());
	}


	// Exam 수정
	@Override
	public ResponseDto<Exam> updateExam(Long id, ExamDto updateExam) {
		getAuthenticatedUser();
		
		Optional<Exam> examOptional = examRepository.findById(id);
		
		if(examOptional.isEmpty()) {
			throw new InvalidRequestException("Exam Empty", "ExamQuestion이 존재하지 않습니다.");
		}
		
		Exam exam = examOptional.get();
		
		exam.setExamTitle(updateExam.getExamTitle());
		exam.setExamText(updateExam.getExamText());
		exam.setExamImage(updateExam.getExamImage());
		exam.setMultipleChoice(updateExam.getMultipleChoice());
		exam.setCorrectAnswer(updateExam.getCorrectAnswer());
		
		Exam saveExamQuestion = examRepository.save(exam);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),saveExamQuestion,"ExamQuestion이 성공적으로 수정되었습니다.");
	}


	// ExamID로 삭제 
	@Override
	public ResponseDto<String> deleteExamByExamId(Long examId) {
		User user = getAuthenticatedUser();
		Optional<Exam> examOptional = examRepository.findById(examId);		
		
		if(examOptional.isEmpty()) {
			throw new InvalidRequestException("Exam Empty", "ExamQuestion이 존재하지 않습니다.");
		}
		
		Exam exam = examOptional.get();
		
		if(!exam.getLecture().getAuthor().equals(user)) {
			
			throw new InvalidRequestException("Unauthorized", "해당 시험 문제 삭제에 대한 권한이 없습니다.");
		}
		
		 examRepository.delete(exam);

		return new ResponseDto<>(ResultCode.SUCCESS.name(), null, "ExamQuestion 삭제가 완료되었습니다.");
	}


	// 같은 Lecture의 Exam 삭제
	@Override
	public ResponseDto<String> deleteExamByLectureName(String lectureName) {
		getAuthenticatedUser();
		Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
		Lecture lecture = lectureOptional.get();
		List<Exam> exam = examRepository.findAllByLecture(lecture);
		
		if(exam.isEmpty()) {
			
			throw new InvalidRequestException("Exam Empty","Exam이 존재하지 않습니다.");
			
		}

		examRepository.deleteAll(exam);
	
		return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"ExamQuestion 삭제가 완료되었습니다.");
	}



	
}
