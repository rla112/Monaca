package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ReceivedMessage;
import com.dw.Monaca.service.impl.ReceivedMessageServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.POST })
public class ReceivedMessageController {

	private final ReceivedMessageServiceImpl receivedMessageServiceImpl;

	@Autowired
	public ReceivedMessageController(ReceivedMessageServiceImpl receivedMessageServiceImpl) {
		this.receivedMessageServiceImpl = receivedMessageServiceImpl;
	}

	// 현재 로그인한 유저가 받은 쪽지 목록 조회
	@GetMapping("/api/receivedMessage/list")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	public ResponseEntity<ResponseDto<List<ReceivedMessage>>> getReceivedMessages() {
		return new ResponseEntity<>(receivedMessageServiceImpl.getReceivedMessages(), HttpStatus.OK);
	}

	// 현재 로그인한 유저가 받은 특정 쪽지 ID로 쪽지 조회
	@GetMapping("/api/receivedMessage/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	public ResponseEntity<ResponseDto<ReceivedMessage>> getReceivedMessageById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(receivedMessageServiceImpl.getReceivedMessageById(id), HttpStatus.OK);
	}

	// 현재 로그인한 유저가 받은 모든 쪽지 삭제
	@PutMapping("/api/receivedMessage/delete/all")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	public ResponseEntity<ResponseDto<List<ReceivedMessage>>> deleteAllReceivedMessages() {
		return new ResponseEntity<>(receivedMessageServiceImpl.deleteAllReceivedMessages(), HttpStatus.OK);
	}

	// 현재 로그인한 유저가 받은 특정 쪽지 삭제
	@PutMapping("/api/receivedMessage/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	public ResponseEntity<ResponseDto<ReceivedMessage>> deleteReceivedMessageByReceivedMessageId(
			@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(receivedMessageServiceImpl.deleteReceivedMessageByReceivedMessageId(id),
				HttpStatus.OK);
	}
}
