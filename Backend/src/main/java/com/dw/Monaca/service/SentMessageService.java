package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.SentMessageDto;
import com.dw.Monaca.dto.SentMessageListDto;

public interface SentMessageService {

	// 현재 로그인한 유저가 보낸 쪽지 목록 조회
	ResponseDto<List<SentMessageListDto>> getSentMessages();
	
	// 현재 로그인한 유저가 보낸 특정 쪽지 ID로 쪽지 조회
	ResponseDto<SentMessageDto> getSentMessageById(Long sentMessageId);
	
	// 현재 로그인한 유저가 보낸 모든 쪽지 삭제
	ResponseDto<String> deleteAllSentMessages();
	
	// 현재 로그인한 유저가 보낸 특정 쪽지 삭제
	ResponseDto<String> deleteSentMessageBySentMessageId(Long sentMessageId);
	
	
}
