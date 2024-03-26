package com.dw.Monaca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Characters;
import com.dw.Monaca.repository.CharactersRepository;
import com.dw.Monaca.service.CharactersService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CharactersServiceImpl implements CharactersService{

	private CharactersRepository charactersRepository;
	private UserRepository userRepository;
	
	
	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	// 전체 캐릭터 가져오기
	@Override
	public ResponseDto<List<Characters>> getAllCharacters() {
		getAuthenticatedUser();
		List<Characters> characters = charactersRepository.findAll();
		if(characters.isEmpty()) {
			return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"존재하는 캐릭터가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(),characters,ResultCode.SUCCESS.getMsg());
	}
	
	// CharacterID로 캐릭터 가져오기
	@Override
	public ResponseDto<Characters> getCharactersById(Long id) {
		getAuthenticatedUser();
		Optional<Characters> charactersOptional = charactersRepository.findById(id);
		if(charactersOptional.isEmpty()) {
			throw new InvalidRequestException("character Not Found","해당 캐릭터를 찾을 수 없습니다.");
		}
		Characters characters = charactersOptional.get();
		return new ResponseDto<>(ResultCode.SUCCESS.name(),characters,ResultCode.SUCCESS.getMsg());
	}
	
	// 캐릭터 생성
	@Override
	public ResponseDto<Characters> createCharacters(Characters characters) {
		getAuthenticatedUser();
		
		Characters avatar = new Characters();
		avatar.setCharacterName(characters.getCharacterName());
		avatar.setSpecies(characters.getSpecies());
		avatar.setImage(characters.getImage());
		charactersRepository.save(avatar);
		return new ResponseDto<>(ResultCode.SUCCESS.name(),avatar,ResultCode.SUCCESS.getMsg());
		
	}

	// 캐릭터 삭제
	@Override
	public ResponseDto<String> deleteChracters(Long id) {
		getAuthenticatedUser();
		
		Optional<Characters>charactersOptional = charactersRepository.findById(id);
		if(charactersOptional.isEmpty()) {
			throw new InvalidRequestException("Character Not Found","해당 캐릭터가 존재하지 않습니다.");
		}
		
		charactersRepository.deleteById(id);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"Character 삭제가 완료되었습니다.");
	}

}
