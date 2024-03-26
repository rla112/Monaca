package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ReceivedMessage;

public interface ReceivedMessageService {

	// 현재 로그인한 유저가 받은 쪽지 목록 조회
	ResponseDto<List<ReceivedMessage>> getReceivedMessages();
	
	// 현재 로그인한 유저가 받은 특정 쪽지 ID로 쪽지 조회
	ResponseDto<ReceivedMessage> getReceivedMessageById(Long receivedMessageId);
	
	// 현재 로그인한 유저가 받은 모든 쪽지 삭제
	ResponseDto<List<ReceivedMessage>> deleteAllReceivedMessages();
	
	// 현재 로그인한 유저가 받은 특정 쪽지 삭제
	ResponseDto<ReceivedMessage> deleteReceivedMessageByReceivedMessageId(Long receivedMessageId);
	
}
