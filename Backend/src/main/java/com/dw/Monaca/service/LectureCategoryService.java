package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.LectureCategoryDto;
import com.dw.Monaca.dto.ResponseDto;

public interface LectureCategoryService {

	// 모든 lectureCategory 불러오기
	ResponseDto<List<LectureCategoryDto>> getAllLectureCategory();
	
}
