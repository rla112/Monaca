package com.dw.Monaca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.MessageDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.service.impl.MessageServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.POST })
public class MessageController {

	private final MessageServiceImpl messageServiceImpl;

	@Autowired
	public MessageController(MessageServiceImpl messageServiceImpl) {
		this.messageServiceImpl = messageServiceImpl;
	}

	@PostMapping("/api/message")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<ResponseDto<Void>> sendMessage(@RequestBody MessageDto messageDto) {
		return new ResponseEntity<>(messageServiceImpl.sendMessage(messageDto), HttpStatus.CREATED);
	}

}
