package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Grade;
import com.dw.Monaca.service.impl.GradeServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class GradeController {

	private final GradeServiceImpl gradeServiceImpl;

	@Autowired
	public GradeController(GradeServiceImpl gradeServiceImpl) {
		this.gradeServiceImpl = gradeServiceImpl;
	}

	// Grade를 생성
	@PostMapping("/api/grade")
	@PreAuthorize("hasAnyRole('ADMIN')")
	ResponseEntity<ResponseDto<Grade>> createGrade(@RequestBody @Valid Grade grade) {
		return new ResponseEntity<>(gradeServiceImpl.createGrade(grade), HttpStatus.OK);
	}

	// Grade를 수정
	@PutMapping("/api/grade/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	ResponseEntity<ResponseDto<Grade>> updateGrade(@PathVariable(name = "id") Long id,
			@RequestBody @Valid Grade updateGrade) {
		return new ResponseEntity<>(gradeServiceImpl.updateGrade(id, updateGrade), HttpStatus.OK);
	}

	// Grade를 삭제
	@DeleteMapping("/api/examination/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	ResponseEntity<ResponseDto<String>> deleteGradeById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(gradeServiceImpl.deleteGradeById(id), HttpStatus.OK);
	}

	// Grade를 불러오기
	@GetMapping("/api/grade")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<Grade>>> getAllGrade() {
		return new ResponseEntity<>(gradeServiceImpl.getAllGrade(), HttpStatus.OK);
	}

	// Grade에 미리 gradedName과 standard 정보가 있어야 이를 바탕으로 계산 후 등급이 결정 됨.
	// Examination의 isCorrect결과를 바탕으로 총점 계산, 그 계산을 바탕으로 총점에 따른 등급 결정
	@PostMapping("/api/grade/calculate/{lectureName}")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<ResponseDto<Grade>> calculateAndAssignGrade(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(gradeServiceImpl.calculateAndAssignGrade(lectureName), HttpStatus.OK);
	}
}
