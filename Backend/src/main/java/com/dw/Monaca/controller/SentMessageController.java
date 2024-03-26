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
import com.dw.Monaca.dto.SentMessageDto;
import com.dw.Monaca.dto.SentMessageListDto;
import com.dw.Monaca.service.impl.SentMessageServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.POST })
public class SentMessageController {

	private final SentMessageServiceImpl sentMessageServiceImpl;
	
	@Autowired
	public SentMessageController(SentMessageServiceImpl sentMessageServiceImpl) {
		this.sentMessageServiceImpl = sentMessageServiceImpl;
	}

	// 현재 로그인한 유저가 보낸 쪽지 목록 조회
	@GetMapping("/api/sentMessage/list")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	public ResponseEntity<ResponseDto<List<SentMessageListDto>>> getSentMessages(){
		return new ResponseEntity<>(sentMessageServiceImpl.getSentMessages(), HttpStatus.OK);
	}
	
	// 현재 로그인한 유저가 보낸 특정 쪽지 ID로 쪽지 조회
	@GetMapping("/api/sentMessage/id/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	public ResponseEntity<ResponseDto<SentMessageDto>> getSentMessageById(@PathVariable (name="id")Long id){
		return new ResponseEntity<>(sentMessageServiceImpl.getSentMessageById(id), HttpStatus.OK);
	}

	// 현재 로그인한 유저가 보낸 모든 쪽지 삭제
	@PutMapping("/api/sentMessage/delete/all")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	public ResponseEntity<ResponseDto<String>> deleteAllSentMessages(){
		return new ResponseEntity<>(sentMessageServiceImpl.deleteAllSentMessages(), HttpStatus.OK);
	}

	// 현재 로그인한 유저가 보낸 특정 쪽지 삭제
	@PutMapping("/api/sentMessage/delete/id/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER','PROFESSOR')")
	public ResponseEntity<ResponseDto<String>> deleteSentMessageBySentMessageId(@PathVariable (name="id")Long id){
		return new ResponseEntity<>(sentMessageServiceImpl.deleteSentMessageBySentMessageId(id), HttpStatus.OK);
	}
}
