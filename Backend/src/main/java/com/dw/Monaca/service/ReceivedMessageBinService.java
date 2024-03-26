package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ReceivedMessageBin;


public interface ReceivedMessageBinService {

	// 현재 로그인한 유저의 ReceivedMessageBin 목록 조회
	ResponseDto<List<ReceivedMessageBin>> getReceivedMessageBins();
	
	// 현재 로그인한 유저의 특정 ReceivedMessageBin ID로 ReceivedMessageBin 조회
	ResponseDto<ReceivedMessageBin> getReceivedMessageBinByReceivedMessageBinId(Long receivedMessageBinId);
	
	// 현재 로그인한 유저의 ReceivedMessageBin 전체 복구
	ResponseDto<String> restoreAllReceivedMessageBins();
	
	// 현재 로그인한 유저의 ReceivedMessageBin ID로 특정 ReceivedMessageBin 복구
	ResponseDto<String> restoreReceivedMessageBinByReceivedMessageBinId(Long receivedMessageBinId);
	
	// 현재 로그인한 유저의 ReceivedMessageBin 전체 삭제
	ResponseDto<String> deleteAllReceivedMessageBins();
	
	// 현재 로그인한 유저의 ReceivedMessageBin ID로 특정 ReceivedMessageBin 삭제
	ResponseDto<String> deleteReceivedMessageBinByReceivedMessageBinId(Long receivedMessageBinId);
	
	// 일정기간(30일)이 지난 ReceivedMessage를 조회하여 삭제
	ResponseDto<String> deleteReceivedMessageBinOlderThan30Days();

	
}
