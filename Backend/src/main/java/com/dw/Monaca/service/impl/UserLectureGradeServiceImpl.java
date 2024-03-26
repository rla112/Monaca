package com.dw.Monaca.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.UserLectureGrade;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.repository.UserLectureGradeRepository;
import com.dw.Monaca.service.UserLectureGradeService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserLectureGradeServiceImpl implements UserLectureGradeService {

	private UserLectureGradeRepository userLectureGradeRepository;
	private UserRepository userRepository;
	private LectureRepository lectureRepository;
	
	@Autowired
	public UserLectureGradeServiceImpl(UserLectureGradeRepository userLectureGradeRepository,
			UserRepository userRepository, LectureRepository lectureRepository) {
		this.userLectureGradeRepository = userLectureGradeRepository;
		this.userRepository = userRepository;
		this.lectureRepository = lectureRepository;
	}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
	// 모든 UserLectureGrade를 User별로 불러오기
	@Override
	public ResponseDto<Map<String, List<UserLectureGrade>>> getAllUserLectureGrade() {
	    List<User> users = userRepository.findAll();
	    Map<String, List<UserLectureGrade>> userLectureGradesPerUser = new HashMap<>();
	    for (User user : users) {
	        List<UserLectureGrade> userLectureGrades = userLectureGradeRepository.findByUser(user);
	        userLectureGradesPerUser.put(user.getName(), userLectureGrades);
	    }
	    return new ResponseDto<>(ResultCode.SUCCESS.name(),userLectureGradesPerUser,ResultCode.SUCCESS.getMsg());
	}

	// 특정 Lecture의 모든 UserLectureGrade를 user별로 불러오기
	@Override
	public ResponseDto<Map<String, List<UserLectureGrade>>> getUserLectureGradeByLecture(String lectureName) {
	    List<User> users = userRepository.findAll();
	    Optional<Lecture> lectureOptional = lectureRepository.findByLectureName(lectureName);
	    if(lectureOptional.isEmpty()) {
	    	throw new InvalidRequestException("Lecture Empty", "해당 LectureName을 가진 Lecture을 찾지 못했습니다.");
	    }
	    Lecture lecture = lectureOptional.get();
	    Map<String, List<UserLectureGrade>> userLectureGradesPerUser = new HashMap<>();
	    for (User user : users) {
	        Optional<UserLectureGrade> userLectureGradeOptional = userLectureGradeRepository.findByUserAndLecture(user, lecture);
	        if(userLectureGradeOptional.isPresent()) {
	            if(!userLectureGradesPerUser.containsKey(user.getName())) {
	                userLectureGradesPerUser.put(user.getName(), new ArrayList<>());
	            }
	            userLectureGradesPerUser.get(user.getName()).add(userLectureGradeOptional.get());
	        }
	    }
	    return new ResponseDto<>(ResultCode.SUCCESS.name(),userLectureGradesPerUser,ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 사용자의 특정 Lecture UserLectureGrade 불러오기
	@Override
	public ResponseDto<UserLectureGrade> getUserLectureGradeByLectureForUser(String lectureName) {
	    User user = getAuthenticatedUser();
	    Lecture lecture = lectureRepository.findByLectureName(lectureName)
	        .orElseThrow(() -> new InvalidRequestException("Lecture Empty", "해당 LectureName을 가진 Lecture가 존재하지 않습니다."));
	    Optional<UserLectureGrade> userLectureGradeOptional = userLectureGradeRepository.findByUserAndLecture(user, lecture);
	    
	    if(userLectureGradeOptional.isEmpty()) {
	        throw new InvalidRequestException("UserLectureGrade Empty","해당 사용자의 UserLectureGrade가 존재하지 않습니다.");
	    }
	    
	    UserLectureGrade userLectureGrade = userLectureGradeOptional.get();
	        
	    return new ResponseDto<>(ResultCode.SUCCESS.name(),userLectureGrade,ResultCode.SUCCESS.getMsg());
	}



	// 현재 로그인한 사용자의 UserLectureGrade 모두 불러오기
	@Override
	public ResponseDto<List<UserLectureGrade>> getUserLectureGradeForUser() {
		User user = getAuthenticatedUser();
		List<UserLectureGrade> userLectureGrades = userLectureGradeRepository.findByUser(user);
		if(userLectureGrades.isEmpty()) {
			throw new InvalidRequestException("UserLectureGrade Empty", "해당 유저의 UserLectureGrade를 찾을 수 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(),userLectureGrades,ResultCode.SUCCESS.getMsg());
		
	}
}
