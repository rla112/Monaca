package com.dw.Monaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Exam;
import com.dw.Monaca.model.Examination;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

	// Exam을 이용해 examination을 불러옴
	List<Examination> findByExam(Exam exam);
	
	// User를 이용해 examination을 불러옴
	List<Examination> findByUser(User user);

	// User와 Exam를 이용해 examination을 불러옴
	List<Examination> findByUserAndExam(User user, Exam exam);

	
}
