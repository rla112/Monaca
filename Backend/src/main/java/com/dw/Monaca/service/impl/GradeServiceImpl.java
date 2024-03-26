package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Candy;
import com.dw.Monaca.model.CandyPoint;
import com.dw.Monaca.model.CandyPointItem;
import com.dw.Monaca.model.Grade;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.UserLectureGrade;
import com.dw.Monaca.repository.CandyPointRepository;
import com.dw.Monaca.repository.CandyRepository;
import com.dw.Monaca.repository.GradeRepository;
import com.dw.Monaca.repository.LectureRepository;
import com.dw.Monaca.repository.UserLectureGradeRepository;
import com.dw.Monaca.service.ExaminationService;
import com.dw.Monaca.service.GradeService;
import com.dw.Monaca.repository.CandyPointItemRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{

	private final GradeRepository gradeRepository;
	private final UserRepository userRepository;
	private final LectureRepository lectureRepository;
	private final UserLectureGradeRepository userLectureGradeRepository;
	private final ExaminationService examinationService;
	private final CandyPointRepository candyPointRepository;
	private final CandyPointItemRepository candyPointItemRepository;
	private final CandyRepository candyRepository;
	
	
	@Autowired
	public GradeServiceImpl(GradeRepository gradeRepository, UserRepository userRepository,
			LectureRepository lectureRepository, UserLectureGradeRepository userLectureGradeRepository,
			ExaminationService examinationService, CandyPointRepository candyPointRepository,
			CandyPointItemRepository candyPointItemRepository, CandyRepository candyRepository) {
		super();
		this.gradeRepository = gradeRepository;
		this.userRepository = userRepository;
		this.lectureRepository = lectureRepository;
		this.userLectureGradeRepository = userLectureGradeRepository;
		this.examinationService = examinationService;
		this.candyPointRepository = candyPointRepository;
		this.candyPointItemRepository = candyPointItemRepository;
		this.candyRepository = candyRepository;
	}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
	public ResponseDto<Grade> createGrade(Grade grade) {
		getAuthenticatedUser();
		
		Grade grades = new Grade();
		grades.setGradeName(grade.getGradeName());
		grades.setMinScore(grade.getMinScore());
		grades.setMaxScore(grade.getMaxScore());
		
		Grade savedGrade = gradeRepository.save(grades);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),savedGrade,ResultCode.SUCCESS.getMsg());
	}

	@Override
	public ResponseDto<Grade> updateGrade(Long id, Grade updateGrade) {
		getAuthenticatedUser();
		Optional<Grade> gradeOptional = gradeRepository.findById(id);
		
		if(gradeOptional.isEmpty()) {
			throw new InvalidRequestException("Grade Empty", "해당 Grade가 존재하지 않습니다.");
		}
		
		Grade grade = gradeOptional.get();
		
		grade.setGradeName(updateGrade.getGradeName());
		grade.setMinScore(updateGrade.getMinScore());
		grade.setMaxScore(updateGrade.getMaxScore());
		
		Grade savedGrade = gradeRepository.save(grade);
		return new ResponseDto<>(ResultCode.SUCCESS.name(),savedGrade,ResultCode.SUCCESS.getMsg());
	}

	@Override
	public ResponseDto<String> deleteGradeById(Long id) {
		getAuthenticatedUser();
		
		Optional<Grade> gradeOptional = gradeRepository.findById(id);
		
		if(gradeOptional.isEmpty()) {
			throw new InvalidRequestException("Grade Empty", "해당 Grade가 존재하지 않습니다.");
		}
		Grade grade = gradeOptional.get();
		
		 gradeRepository.delete(grade);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"Grade 삭제가 완료 되었습니다.");
	}

	@Override
	public ResponseDto<List<Grade>> getAllGrade() {
		getAuthenticatedUser();
		List<Grade> grades = gradeRepository.findAll();
		
		if(grades.isEmpty()) {
			throw new InvalidRequestException("Grade Empty", "Grade가 존재하지 않습니다.");
		}
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),grades,ResultCode.SUCCESS.getMsg());
	}

	//  현재 인증된 사용자와 주어진 강의 이름을 기반으로 시험 점수를 계산하고, 그 점수에 따른 등급을 찾아 UserLectureGrade 객체를 생성하고 저장
	@Override
	public ResponseDto<Grade> calculateAndAssignGrade(String lectureName) {
	    User user = getAuthenticatedUser();
	    Lecture lecture = lectureRepository.findByLectureName(lectureName)
	        .orElseThrow(() -> new InvalidRequestException("Lecture Not Found", "해당 강의를 찾을 수 없습니다."));
	 

	    // 시험 점수 계산
	    int score = examinationService.calculateScore(user, lecture);
        // Grade 기준에 따라 등급 결정
        List<Grade> grades = gradeRepository.findAll();
        // grades.stream(): grades 리스트를 스트림으로 변환.(스트림은 데이터의 시퀀스를 나타내며, 이를 통해 데이터를 더 효율적으로 처리할 수 있음.)
        Grade userGrade = grades.stream()
        		// score가 grade의 최소 점수와 최대 점수 사이에 있는 grade만을 선택함. 이를 통해 학생의 점수에 해당하는 등급을 찾음.
        	    .filter(grade -> score >= grade.getMinScore() && score <= grade.getMaxScore())
        	    // findFirst 메소드는 스트림의 첫 번째 요소를 선택함.,
        	    // filter 메소드를 통해 선택된 요소 중 첫 번째 요소, 즉 학생의 점수에 해당하는 등급 중 가장 높은 등급을 선택함.
        	    .findFirst()
        	    // orElseThrow 메소드는 스트림이 비어 있는 경우, 즉 학생의 점수에 해당하는 등급이 없는 경우 주어진 예외를 발생
        	    .orElseThrow(() -> new IllegalArgumentException("No suitable grade found"));
        Grade grade = gradeRepository.findByGradeName(userGrade.getGradeName());
    
        // UserLectureGrade 찾기 (없으면 예외 발생)
        UserLectureGrade userLectureGrade = userLectureGradeRepository.findByUserAndLecture(user, lecture)
            .orElseThrow(() -> new InvalidRequestException("UserLectureGrade Not Found", "해당 UserLectureGrade를 찾을 수 없습니다."));

        // Grade 및 Score 설정
        userLectureGrade.setGrade(grade);
        userLectureGrade.setScore(score);

        // UserLectureGrade 저장
        UserLectureGrade savedUserLectureGrade = userLectureGradeRepository.save(userLectureGrade);
        
        // 저장된 UserLectureGrade가 null인 경우 예외를 던짐
        if (savedUserLectureGrade == null) {
          throw new InvalidRequestException("Failed to save UserLectureGrade","시험 성적을 등급으로 환산하여 저장하지 못했습니다.");
        }
        // 등급이 'A'인 경우
        if (userGrade.getGradeName().equals("A")) {
        	// CandyPointItem 객체 생성 또는 찾기
        	CandyPointItem candyPointItem = candyPointItemRepository.findByItemName("Grade")
        	    .orElseGet(() -> {
        	        CandyPointItem newItem = new CandyPointItem("Grade");
        	        return candyPointItemRepository.save(newItem);
        	    });

        	// 모든 Candy를 데이터베이스에서 가져옴
    		List<Candy> allCandies = candyRepository.findAll();

    		// allCandies 리스트가 비어있지 않은 경우에만 랜덤 Candy를 선택
    		Candy randomCandy = null;
    		if (!allCandies.isEmpty()) {
    		    int randomIndex = new Random().nextInt(allCandies.size());
    		    randomCandy = allCandies.get(randomIndex);
    		}
        	
        	// CandyPoint 객체 생성 및 설정
        	CandyPoint candyPoint = new CandyPoint();
        	candyPoint.setUser(user);
        	candyPoint.setCandy(randomCandy);  // 랜덤으로 선택한 Candy 설정
        	candyPoint.setCandyPointItem(candyPointItem);  // CandyPointItem을 'Grade'로 설정
        	candyPoint.setPoint(1);  // 포인트를 1로 설정
        	candyPoint.setTimeStamp(LocalDateTime.now());  // 현재 시간으로 타임스탬프 설정

        	// CandyPoint 객체 저장
        	candyPointRepository.save(candyPoint);

//        	// 저장된 CandyPoint가 null인 경우 예외를 던짐
//        	if (savedCandyPoint == null) {
//        	    throw new InvalidRequestException("Failed to save CandyPoint","등급 'A'에 해당하는 CandyPoint를 생성하여 저장하지 못했습니다.");
//        	}
      }
        return new ResponseDto<>(ResultCode.SUCCESS.name(), null, "등급 계산이 성공적으로 완료되었습니다.");
			}
}