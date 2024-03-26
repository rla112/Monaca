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

import com.dw.Monaca.dto.QandADto;
import com.dw.Monaca.dto.QandAListDto;
import com.dw.Monaca.dto.QandAWritingDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.QandA;
import com.dw.Monaca.service.impl.QandAServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class QandAController {
	private final QandAServiceImpl qandaServiceImpl;

	@Autowired
	public QandAController(QandAServiceImpl qandaServiceImpl) {
		this.qandaServiceImpl = qandaServiceImpl;
	}

	// 모든 유저의 Q&A / OK
	@GetMapping("/api/qanda/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<QandAListDto>>> getAllQandA() {
		return new ResponseEntity<>(qandaServiceImpl.getAllQandA(), HttpStatus.OK);
		// qandaDto 필요
	}

	// 각 강의의 수강생 Q&A 목록 불러오기 / OK
	@GetMapping("/api/qanda/lectureName/{lectureName}")
	public ResponseEntity<ResponseDto<List<QandAListDto>>> getAllQandAByLecture(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(qandaServiceImpl.getAllQandAByLecture(lectureName), HttpStatus.OK);
	}

	// 현재 사용자의 Q&A 목록 불러오기 / OK
	@GetMapping("/api/myqanda")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<ResponseDto<List<QandAListDto>>> getAllQandAByUser() {
		return new ResponseEntity<>(qandaServiceImpl.getAllQandAByUser(), HttpStatus.OK);
	}

	// QandA ID를 이용하여 특정 QandA 불러오기 / OK
	@GetMapping("/api/qanda/id/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<ResponseDto<QandADto>> getQandAById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(qandaServiceImpl.getQandAById(id), HttpStatus.OK);
	}

	// Q&A 등록 / OK
	@PostMapping("/api/qanda")
	@PreAuthorize("hasAnyRole('USER','ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<QandADto>> createQandA(@RequestBody @Valid QandAWritingDto qandaDto) {
		return new ResponseEntity<>(qandaServiceImpl.createQandA(qandaDto), HttpStatus.CREATED);
	}

	// Q&A 수정 (Reply가 등록되기 전까지만 가능!!) / OK
	@PutMapping("/api/qanda/update/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<ResponseDto<QandADto>> updateQandA(@RequestBody @Valid QandA updateQandA,
			@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(qandaServiceImpl.updateQandA(updateQandA, id), HttpStatus.OK);
	}

	// Q&A 삭제 ( 작성자와 관리자만 가능 ) / OK
	@DeleteMapping("/api/qanda/delete/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<ResponseDto<String>> deleteQandA(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(qandaServiceImpl.deleteQandA(id), HttpStatus.OK);
	}
}
