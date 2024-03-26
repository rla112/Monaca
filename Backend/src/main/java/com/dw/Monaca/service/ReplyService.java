package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ReplyDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Reply;

// QandA 답변
public interface ReplyService {

	// 기능 구현 예정
	// 답변 등록
	// 답변 삭제
	// 답변 수정(등록 시에는 답변 등록이라고 뜨고, 수정 시에는 수정 답변 추가 등록(새로고침 필))

	// 모든 Reply 불러오기
	ResponseDto<List<Reply>> getAllReply();
	
	// QandA별 Reply( Q&A 답변 ) 불러오기
	ResponseDto<List<Reply>> getAllReplyByQandA(Long qandaId);

	// Reply( Q&A 답변 ) 작성
	ResponseDto<Reply> createReply(ReplyDto replydto, Long qandaId);

//	// Reply( Q&A 답변 ) 수정
//	public ResponseDto<Reply> updateReply(Reply reply, Long Id);
	
	// Reply( Q&A 답변 ) 삭제
	ResponseDto<Long> deleteReply(Long id);

	ResponseDto<String> updateStatusReplies();
	
	
}