package com.dw.Monaca.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.dw.Monaca.dto.CandyPointDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.UserCandyPointSumDto;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.CandyPoint;

public interface CandyPointService {


	// CandyPoint 생성
	ResponseDto<CandyPoint> createCandyPoint(CandyPointDto candyPointDto);
	
	// 모든 사용자의 CandyPoint 기록 조회
	ResponseDto<List<CandyPoint>> getAllCandyPoint();
	
	// 현재 로그인한 유저의 CandyPoint 기록 조회
	ResponseDto<List<CandyPoint>> getCandyPointByUser();
	
	// 현재 로그인한 유저의 특정 날짜에 해당하는 CandyPoint 기록 조회
	ResponseDto<List<CandyPoint>>  getCandyPointsByUserAndDate(LocalDate timeStamp);

	// 현재 로그인한 사용자의 총 CandyPoint 합계 조회
	ResponseDto<Integer> getTotalCandyPointsByUser();
	
	// 모든 사용자의 총 CandyPoint 합계  조회
	ResponseDto<List<UserCandyPointSumDto>> getTotalCandyPointsByAllUser();
	
//	// CandyPoint 삭제 
//	ResponseDto<String> deleteByCandyId(Long id);
}
