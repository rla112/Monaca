package com.dw.Monaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>{

	// 강의와 IsReservation의 상태로 Material 목록을 찾음
		List<Material> findByLectureAndIsReservation(Lecture lecture, boolean b);

	// 강의로 Material 목록을 찾음
	List<Material> findByLecture(Lecture lecture);

}
