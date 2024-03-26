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

import com.dw.Monaca.dto.AdminNoticeDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.AdminNotice;
import com.dw.Monaca.service.impl.AdminNoticeServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class AdminNoticeController {

	private final AdminNoticeServiceImpl adminNoticeServiceImpl;

	@Autowired
	public AdminNoticeController(AdminNoticeServiceImpl adminNoticeServiceImpl) {
		this.adminNoticeServiceImpl = adminNoticeServiceImpl;
	}

	// Notice 전체 조회 / OK
	@GetMapping("/api/adminNotice/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<AdminNotice>>> getAllAdminNotice() {
		return new ResponseEntity<>(adminNoticeServiceImpl.getAllAdminNotice(), HttpStatus.OK);
	}

	// Notice ID로 조회 / OK
	@GetMapping("/api/adminNotice/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<AdminNotice>> getAdminNoticeById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(adminNoticeServiceImpl.getAdminNoticeById(id), HttpStatus.OK);
	}

	// Notice 등록 / OK
	@PostMapping("/api/adminNotice")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<AdminNotice>> createAdminNotice(
			@RequestBody @Valid AdminNoticeDto adminNoticeDto) {
		return new ResponseEntity<>(adminNoticeServiceImpl.createAdminNotice(adminNoticeDto), HttpStatus.CREATED);
	}

	// Notice 수정 / OK
	@PutMapping("/api/adminNotice/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<AdminNotice>> updateAdminNotice(AdminNoticeDto updateAdminNotice,
			@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(adminNoticeServiceImpl.updateAdminNotice(updateAdminNotice, id), HttpStatus.OK);
	}

	// Notice 삭제 /OK
	@DeleteMapping("/api/adminNotice/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<AdminNotice>> deleteAdminNoticeById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(adminNoticeServiceImpl.deleteAdminNoticeById(id), HttpStatus.OK);
	}
}
