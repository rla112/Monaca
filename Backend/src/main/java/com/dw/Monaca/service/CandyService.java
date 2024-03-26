package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Candy;

public interface CandyService {

	// 모든 candy 불러오기
	ResponseDto<List<Candy>> getAllCandies();
	
	// candyID로 candy 불러오기 
	ResponseDto<Candy> getCandyById(Long id);

	// candy 저장하기
	ResponseDto<Candy> saveCandy(Candy candy);

	// candy 수정하기
	ResponseDto<Candy> updateCandy(Candy updateCandy, Long id);

	// candy 삭제하기
	ResponseDto<String> deleteCandyById(Long id);
	

}
