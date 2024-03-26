package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dw.Monaca.dto.LectureCategoryDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.service.impl.LectureCategoryServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class LectureCategoryController {

	private final LectureCategoryServiceImpl lectureCategoryServiceImpl;

	@Autowired
	public LectureCategoryController(LectureCategoryServiceImpl lectureCategoryServiceImpl) {
		this.lectureCategoryServiceImpl = lectureCategoryServiceImpl;
	}
	
	@GetMapping("/api/lectureCategory/all")
	public ResponseEntity<ResponseDto<List<LectureCategoryDto>>> getAllLectureCategory(){
		return new ResponseEntity<>(lectureCategoryServiceImpl.getAllLectureCategory(),HttpStatus.OK);
	}
	
}
