package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.UserLectureGrade;

public interface UserLectureGradeRepository extends JpaRepository<UserLectureGrade, Long>{

	// User로 UserLectureGrade 목록 불러오기
	List<UserLectureGrade> findByUser(User user);

	// User 와 Lecture정보로 UserLectureGrades 정보 불러오기
	Optional<UserLectureGrade> findByUserAndLecture(User user, Lecture lecture);
	
	
}
