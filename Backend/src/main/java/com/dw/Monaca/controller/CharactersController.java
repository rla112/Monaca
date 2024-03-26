package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Characters;
import com.dw.Monaca.service.impl.CharactersServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class CharactersController {

	private final CharactersServiceImpl charactersServiceImpl;

	@Autowired
	public CharactersController(CharactersServiceImpl charactersServiceImpl) {
		this.charactersServiceImpl = charactersServiceImpl;
	}

	// 전체 캐릭터 가져오기
	@GetMapping("api/characters/all")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<Characters>>> getAllCharacters() {
		return new ResponseEntity<>(charactersServiceImpl.getAllCharacters(), HttpStatus.OK);
	}

	// CharacterID로 캐릭터 가져오기
	@GetMapping("api/characters/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<Characters>> getCharactersById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(charactersServiceImpl.getCharactersById(id), HttpStatus.OK);
	}

	// 캐릭터 생성
	@PostMapping("api/character")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Characters>> createCharacters(@RequestBody Characters characters) {
		return new ResponseEntity<>(charactersServiceImpl.createCharacters(characters), HttpStatus.OK);
	}

	// 캐릭터 삭제
	// Notice 삭제
	@DeleteMapping("api/character/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<String>> deleteChracters(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(charactersServiceImpl.deleteChracters(id), HttpStatus.OK);
	}

}
