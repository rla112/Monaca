package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Characters;

public interface CharactersService {

	
	// 전체 캐릭터 가져오기
	ResponseDto<List<Characters>> getAllCharacters();
	
	// CharacterID로 캐릭터 가져오기
	ResponseDto<Characters> getCharactersById(Long id);

	// 캐릭터 생성
	ResponseDto<Characters> createCharacters(Characters characters);
	
	// 캐릭터 삭제
	ResponseDto<String> deleteChracters(Long id);
	
	
}
