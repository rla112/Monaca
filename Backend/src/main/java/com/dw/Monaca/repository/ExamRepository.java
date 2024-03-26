package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.Exam;
import com.dw.Monaca.model.Lecture;

public interface ExamRepository extends JpaRepository<Exam, Long> {

	// examId로 Exam 찾기
	Optional<Exam> findById(Long id);

	List<Exam> findAllByLecture(Lecture lecture);
	
	Optional<Exam>  findByLecture(Lecture lecture);
}
