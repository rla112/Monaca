package com.dw.Monaca.service;

import com.dw.Monaca.dto.MessageDto;
import com.dw.Monaca.dto.ResponseDto;

// 쪽지 기능
public interface MessageService {
	
//	// 탭 메뉴 활용하여 대상을 선택 해 쪽지 발송 가능
//	// 새로운 답변 수신 시 new 또는 카운트로 알림 표시 (새로고침 필)

	// 특정 수신자에게 쪽지 전송
	ResponseDto<Void> sendMessage(MessageDto messageDto);
//
//    // 특정 쪽지를 읽음으로 표시
//    void markMessageAsRead(Long messageId);


}
