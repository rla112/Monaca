package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.LectureHistoryDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.service.impl.LectureHistoryServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
public class LectureHistoryController {

	public LectureHistoryServiceImpl lectureHistoryServiceImpl;

	@Autowired
	public LectureHistoryController(LectureHistoryServiceImpl lectureHistoryServiceImpl) {
		this.lectureHistoryServiceImpl = lectureHistoryServiceImpl;
	}

	// 현재 로그인한 유저의 LectureHistory를 전체 조회 / OK
	@GetMapping("api/lectureHistory/all")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<LectureHistoryDto>>> getAllLectureHistory() {
		return new ResponseEntity<>(lectureHistoryServiceImpl.getAllLectureHistory(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 LectureHistory ID로 조회 / OK
	@GetMapping("api/lectureHistory/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<LectureHistoryDto>> getLectureHistoryById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(lectureHistoryServiceImpl.getLectureHistoryById(id), HttpStatus.OK);
	}

}
