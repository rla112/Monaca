package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.QandADto;
import com.dw.Monaca.dto.QandAListDto;
import com.dw.Monaca.dto.QandAWritingDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.QandA;

// QandA 게시판
public interface QandAService {
	// 기능구현 예정

	// 게시글 삭제
	// 답변이 달리기 전까지만 게시글 수정가능(수정시 수정 된 게시글 알림(새로고침 필))
	// 갯수제한 없이 무한 답변 가능
	// 내가 아닌 다른사람이 쓴 게시글도 전체 조회 가능
	// 나의 방에서는 내가 쓴 게시글 만 조회 가능
	// 답변 완료 시 답변 완료라고 표시 (새로고침 필)

	// Q&A 등록
	ResponseDto<QandADto> createQandA(QandAWritingDto qandaDto);

	//전체 유저의 Q&A 목록 불러오기
	ResponseDto<List<QandAListDto>> getAllQandA();

	// Lecture별 Q&A 목록 불러오기 
	ResponseDto<List<QandAListDto>> getAllQandAByLecture(String lecture);
	
	// 현재 로그인한 유저의 Q&A 목록
	ResponseDto<List<QandAListDto>> getAllQandAByUser();
	
	//QandA ID를 이용하여 특정 QandA 불러오기
	ResponseDto<QandADto> getQandAById(Long qandaId);
	
	// Q&A 수정 (Reply가 등록되기 전까지만 가능!!)
	ResponseDto<QandADto> updateQandA(QandA updateQandA, Long id);
	
	// Q&A 삭제 ( 작성자와 관리자만 가능 )
	ResponseDto<String> deleteQandA(Long qandaId);
	

}