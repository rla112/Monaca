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

import com.dw.Monaca.dto.ProfessorNoticeDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ProfessorNotice;
import com.dw.Monaca.service.impl.ProfessorNoticeServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class ProfessorNoticeController {
	private final ProfessorNoticeServiceImpl professorNoticeServiceImpl;

	@Autowired
	public ProfessorNoticeController(ProfessorNoticeServiceImpl professorNoticeServiceImpl) {
		this.professorNoticeServiceImpl = professorNoticeServiceImpl;
	}

	// Notice 전체 조회 /OK
	@GetMapping("api/professorNotice/list")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<ProfessorNotice>>> getAllProfessorNotice() {
		return new ResponseEntity<>(professorNoticeServiceImpl.getAllProfessorNotice(), HttpStatus.OK);
	}

	// Notice ID로 조회
	@GetMapping("api/professorNotice/id/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<ProfessorNotice>> getProfessorNoticeById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(professorNoticeServiceImpl.getProfessorNoticeById(id), HttpStatus.OK);
	}

	// Notice Lecture별로 조회
	@GetMapping("api/professorNotice/lectureName/{lecture}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<ProfessorNotice>>> getAllProfessorNoticeByLecture(
			@PathVariable(name = "lecture") String lecture) {
		return new ResponseEntity<>(professorNoticeServiceImpl.getAllProfessorNoticeByLecture(lecture), HttpStatus.OK);
	}

	// Notice 등록 // 서버에 저장은 되나 응답메세지가 오질 않음.(Could not get response Error: aborted)
	@PostMapping("api/professorNotice")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<ProfessorNotice>> createProfessorNotice(
			@RequestBody @Valid ProfessorNoticeDto professorNoticeDto) {
		return new ResponseEntity<>(professorNoticeServiceImpl.createProfessorNotice(professorNoticeDto),
				HttpStatus.CREATED);
	}

	// Notice 수정
	@PutMapping("api/professorNotice/update/{id}")
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	public ResponseEntity<ResponseDto<ProfessorNotice>> updateProfessorNotice(ProfessorNotice updateProfessorNotice,
			@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(professorNoticeServiceImpl.updateProfessorNotice(updateProfessorNotice, id),
				HttpStatus.OK);
	}

	// Notice 삭제
	@DeleteMapping("api/professorNotice/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<ProfessorNotice>> deleteProfessorNotice(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(professorNoticeServiceImpl.deleteProfessorNotice(id), HttpStatus.OK);
	}

}
