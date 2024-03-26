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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ReceivedMessageBin;
import com.dw.Monaca.service.impl.ReceivedMessageBinServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.POST })
public class ReceivedMessageBinController {

	private final ReceivedMessageBinServiceImpl receivedMessageBinServiceImpl;

	@Autowired
	public ReceivedMessageBinController(ReceivedMessageBinServiceImpl receivedMessageBinServiceImpl) {
		this.receivedMessageBinServiceImpl = receivedMessageBinServiceImpl;
	}

	// 현재 로그인한 유저의 ReceivedMessageBin 목록 조회
	@GetMapping("/api/receivedMessageBin/list")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<List<ReceivedMessageBin>>> getReceivedMessageBins() {
		return new ResponseEntity<>(receivedMessageBinServiceImpl.getReceivedMessageBins(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 특정 ReceivedMessageBin ID로 ReceivedMessageBin 조회
	@GetMapping("/api/receivedMessageBin/{receivedMessageBinId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<ReceivedMessageBin>> getReceivedMessageBinByReceivedMessageBinId(
			@PathVariable(name = "receivedMessageBinId") Long receivedMessageBinId) {
		return new ResponseEntity<>(
				receivedMessageBinServiceImpl.getReceivedMessageBinByReceivedMessageBinId(receivedMessageBinId),
				HttpStatus.OK);
	}

	// 현재 로그인한 유저의 ReceivedMessageBin 전체 복구
	@PutMapping("/api/receivedMessageBin/restore/all")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> restoreAllReceivedMessageBins() {
		return new ResponseEntity<>(receivedMessageBinServiceImpl.restoreAllReceivedMessageBins(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 ReceivedMessageBin ID로 특정 ReceivedMessageBin 복구
	@PutMapping("/api/receivedMessageBin/restore/{receivedMessageBinId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> restoreReceivedMessageBinByReceivedMessageBinId(
			@PathVariable(name = "receivedMessageBinId") Long receivedMessageBinId) {
		return new ResponseEntity<>(
				receivedMessageBinServiceImpl.restoreReceivedMessageBinByReceivedMessageBinId(receivedMessageBinId),
				HttpStatus.OK);
	}

	// 현재 로그인한 유저의 ReceivedMessageBin 전체 삭제
	@DeleteMapping("/api/receivedMessageBin/delete/all")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteAllReceivedMessageBins() {
		return new ResponseEntity<>(receivedMessageBinServiceImpl.deleteAllReceivedMessageBins(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 ReceivedMessageBin ID로 특정 ReceivedMessageBin 삭제
	@DeleteMapping("/api/receivedMessageBin/delete/{receivedMessageBinId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteReceivedMessageBinByReceivedMessageBinId(
			@PathVariable(name = "receivedMessageBinId") Long receivedMessageBinId) {
		return new ResponseEntity<>(
				receivedMessageBinServiceImpl.deleteReceivedMessageBinByReceivedMessageBinId(receivedMessageBinId),
				HttpStatus.OK);
	}

	// 일정기간(30일)이 지난 ReceivedMessage를 조회하여 삭제
	@PreAuthorize("hasAnyRole('ADMIN')") // 관리자가 수동으로 삭제를 원할 때는 관리자 권한으로 API를 호출할 수 있도록
	@DeleteMapping("/api/receivedMessageBin/delete/oldReceivedMessage")
	ResponseEntity<ResponseDto<String>> deleteReceivedMessageBinOlderThan30Days() {
		return new ResponseEntity<>(receivedMessageBinServiceImpl.deleteReceivedMessageBinOlderThan30Days(),
				HttpStatus.OK);
	}

}
