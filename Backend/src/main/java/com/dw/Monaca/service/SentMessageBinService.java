package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.SentMessageBin;

public interface SentMessageBinService {

	// 현재 로그인한 유저의 SentMessageBin 목록 조회
	ResponseDto<List<SentMessageBin>> getSentMessageBins();
	
	// 현재 로그인한 유저의 특정 SentMessageBin ID로 SentMessageBin 조회
	ResponseDto<SentMessageBin> getSentMessageBinBySentMessageBinId(Long sentMessageBinId);
	
	// 현재 로그인한 유저의 SentMessageBin 전체 복구
	ResponseDto<String> restoreAllSentMessageBins();
	
	// 현재 로그인한 유저의 SentMessageBin ID로 특정 SentMessageBin 복구
	ResponseDto<String> restoreSentMessageBinBySentMessageBinId(Long sentMessageBinId);
	
	// 현재 로그인한 유저의 SentMessageBin 전체 삭제
	ResponseDto<String> deleteAllSentMessageBins();
	
	// 현재 로그인한 유저의 SentMessageBin ID로 특정 SentMessageBin 삭제
	ResponseDto<String> deleteSentMessageBinBySentMessageBinId(Long sentMessageBinId);
	
	// 일정기간(30일)이 지난 SentMessage를 조회하여 삭제
	ResponseDto<String> deleteSentMessageBinOlderThan30Days();
	
	// deleteSentMessageBinOlderThan30Days 메소드의 경우, 메시지를 삭제하는 작업 후에 특별히 반환할 데이터가 없음.
	// 이런 경우, 작업이 성공했다는 것을 나타내는 문자열 메시지만 반환하면 되므로 ResponseDto<String>을 사용




	
}
