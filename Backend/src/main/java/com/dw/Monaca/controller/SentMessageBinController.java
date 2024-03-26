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
import com.dw.Monaca.model.SentMessageBin;
import com.dw.Monaca.service.impl.SentMessageBinServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.POST })
public class SentMessageBinController {

	private final SentMessageBinServiceImpl sentMessageBinServiceImpl;

	@Autowired
	public SentMessageBinController(SentMessageBinServiceImpl sentMessageBinServiceImpl) {
		this.sentMessageBinServiceImpl = sentMessageBinServiceImpl;
	}

	// 현재 로그인한 유저의 SentMessageBin 목록 조회
	@GetMapping("/api/sentMessageBin/list")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<List<SentMessageBin>>> getSentMessageBins() {
		return new ResponseEntity<>(sentMessageBinServiceImpl.getSentMessageBins(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 특정 SentMessageBin ID로 SentMessageBin 조회
	@GetMapping("/api/sentMessageBin/{sentMessageBinId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<SentMessageBin>> getSentMessageBinBySentMessageBinId(
			@PathVariable(name = "sentMessageBinId") Long sentMessageBinId) {
		return new ResponseEntity<>(sentMessageBinServiceImpl.getSentMessageBinBySentMessageBinId(sentMessageBinId),
				HttpStatus.OK);
	}

	// 현재 로그인한 유저의 SentMessageBin 전체 복구
	@PutMapping("/api/sentMessageBin/restore/all")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> restoreAllSentMessageBins() {
		return new ResponseEntity<>(sentMessageBinServiceImpl.restoreAllSentMessageBins(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 SentMessageBin ID로 특정 SentMessageBin 복구
	@PutMapping("/api/sentMessageBin/restore/{sentMessageBinId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> restoreSentMessageBinBySentMessageBinId(
			@PathVariable(name = "sentMessageBinId") Long sentMessageBinId) {
		return new ResponseEntity<>(sentMessageBinServiceImpl.restoreSentMessageBinBySentMessageBinId(sentMessageBinId),
				HttpStatus.OK);
	}

	// 현재 로그인한 유저의 SentMessageBin 전체 삭제
	@DeleteMapping("/api/sentMessageBin/delete/all")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteAllSentMessageBins() {
		return new ResponseEntity<>(sentMessageBinServiceImpl.deleteAllSentMessageBins(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 SentMessageBin ID로 특정 SentMessageBin 삭제
	@DeleteMapping("/api/sentMessageBin/delete/{sentMessageBinId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteSentMessageBinBySentMessageBinId(
			@PathVariable(name = "sentMessageBinId") Long sentMessageBinId) {
		return new ResponseEntity<>(sentMessageBinServiceImpl.deleteSentMessageBinBySentMessageBinId(sentMessageBinId),
				HttpStatus.OK);
	}

	// 일정기간(30일)이 지난 SentMessage를 조회하여 삭제
	@DeleteMapping("/api/sentMessageBin/delete/oldSentMessage")
	@PreAuthorize("hasAnyRole('ADMIN')") // 관리자가 수동으로 삭제를 원할 때는 관리자 권한으로 API를 호출할 수 있도록
	ResponseEntity<ResponseDto<String>> deleteSentMessageBinOlderThan30Days() {
		return new ResponseEntity<>(sentMessageBinServiceImpl.deleteSentMessageBinOlderThan30Days(), HttpStatus.OK);
	}
}
