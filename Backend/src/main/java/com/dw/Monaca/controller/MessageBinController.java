package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.MessageBin;
import com.dw.Monaca.service.impl.MessageBinServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET })
public class MessageBinController {
	private final MessageBinServiceImpl messageBinServiceImpl;

	@Autowired
	public MessageBinController(MessageBinServiceImpl messagebinServiceImpl) {
		this.messageBinServiceImpl = messagebinServiceImpl;
	}

	@GetMapping("/api/messagebin")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<MessageBin>>> getAllMessageBin() {
		return new ResponseEntity<>(messageBinServiceImpl.getAllMessageBin(), HttpStatus.OK);
	}

}
