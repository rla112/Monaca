package com.dw.Monaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.LectureCategory;

public interface LectureCategoryRepository extends JpaRepository<LectureCategory, Long>{


	// LectureCategory명으로 LectureCategory 찾기
	LectureCategory findByCategoryName(String categoryName);
	

}
