package com.dw.Monaca.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.LectureCategoryDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.model.LectureCategory;
import com.dw.Monaca.repository.LectureCategoryRepository;
import com.dw.Monaca.service.LectureCategoryService;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class LectureCategoryServiceImpl implements LectureCategoryService {

	private final LectureCategoryRepository lectureCategoryRepository;

	@Autowired
	public LectureCategoryServiceImpl(LectureCategoryRepository lectureCategoryRepository) {
		super();
		this.lectureCategoryRepository = lectureCategoryRepository;
	}

	// 모든 lectureCategory 불러오기
	@Override
	public ResponseDto<List<LectureCategoryDto>> getAllLectureCategory() {
	    List<LectureCategory> lectureCategoryList = lectureCategoryRepository.findAll();
	    
	    if (lectureCategoryList.isEmpty()) {
	        throw new InvalidRequestException("LectureCategory Empty", "강의 카테고리가 존재하지 않습니다");
	    }

	    List<LectureCategoryDto> lectureCategoryDto = lectureCategoryList.stream()
	        .map(category -> {
	            LectureCategoryDto dto = new LectureCategoryDto();
	            dto.setId(category.getId());
	            dto.setCategoryName(category.getCategoryName());
	            dto.setCategoryDescription1(category.getCategoryDescription1());
	            dto.setCategoryDescription2(category.getCategoryDescription2());
	            return dto;
	        })
	        .collect(Collectors.toList());
	    
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), lectureCategoryDto, ResultCode.SUCCESS.getMsg());
	}

	
	
	
}
