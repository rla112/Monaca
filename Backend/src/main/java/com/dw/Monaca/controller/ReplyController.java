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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ReplyDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Reply;
import com.dw.Monaca.service.impl.ReplyServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ReplyController {
	private final ReplyServiceImpl replyServiceImpl;

	@Autowired
	public ReplyController(ReplyServiceImpl replyServiceImpl) {
		this.replyServiceImpl = replyServiceImpl;
	}

	// 모든 Reply 불러오기
	@GetMapping("/api/reply/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<Reply>>> getAllReply() {
		return new ResponseEntity<>(replyServiceImpl.getAllReply(), HttpStatus.OK);
	}

	// QandA별 Reply( Q&A 답변 ) 불러오기
	@GetMapping("/api/reply/{qandaId}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<ResponseDto<List<Reply>>> getAllReplyByQandA(@PathVariable(name = "qandaId") Long qandaId) {
		return new ResponseEntity<>(replyServiceImpl.getAllReplyByQandA(qandaId), HttpStatus.OK);
	}

	// Reply( Q&A 답변 ) 작성
	@PostMapping("/api/reply/{qandaId}")
	@PreAuthorize("hasAnyRole('PROFESSOR','ADMIN')")
	public ResponseEntity<ResponseDto<Reply>> createReply(@RequestBody @Valid ReplyDto replyDto,
			@PathVariable(name = "qandaId") Long qandaId) {
		return new ResponseEntity<>(replyServiceImpl.createReply(replyDto, qandaId), HttpStatus.CREATED);
	}

//	// Reply( Q&A 답변 ) 수정
//	@PutMapping("/api/{id}/updateReply")
//	@PreAuthorize("hasAnyRole('PROFESSOR','ADMIN')")
//	public ResponseEntity<ResponseDto<Reply>> updateReply(@RequestBody @Valid Reply updateReply, @PathVariable Long Id){
//		return new ResponseEntity<>(replyServiceImpl.updateReply(updateReply, Id),HttpStatus.OK);
//	}
//	
	// Reply( Q&A 답변 ) 삭제
	@DeleteMapping("/api/reply/delete/{id}")
	@PreAuthorize("hasAnyRole('PROFESSOR','ADMIN')")
	public ResponseEntity<ResponseDto<Long>> deleteReply(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(replyServiceImpl.deleteReply(id), HttpStatus.OK);
	}
}
