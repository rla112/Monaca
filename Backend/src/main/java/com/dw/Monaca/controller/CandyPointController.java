package com.dw.Monaca.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.CandyPointDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.UserCandyPointSumDto;
import com.dw.Monaca.model.CandyPoint;
import com.dw.Monaca.service.impl.CandyPointServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class CandyPointController {

	private final CandyPointServiceImpl candyPointServiceImpl;

	@Autowired
	public CandyPointController(CandyPointServiceImpl candyPointServiceImpl) {
		this.candyPointServiceImpl = candyPointServiceImpl;
	}

	// CandyPoint 생성
	@PostMapping("api/candyPoint")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<CandyPoint>> createCandyPoint(@RequestBody @Valid CandyPointDto candyPointDto) {
		return new ResponseEntity<>(candyPointServiceImpl.createCandyPoint(candyPointDto), HttpStatus.OK);
	}

	// 모든 사용자의 CandyPoint 기록 조회
	@GetMapping("api/candyPoint/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<CandyPoint>>> getAllCandyPoint() {
		return new ResponseEntity<>(candyPointServiceImpl.getAllCandyPoint(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 CandyPoint 기록 조회
	@GetMapping("api/candyPoint/my")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<CandyPoint>>> getCandyPointByUser() {
		return new ResponseEntity<>(candyPointServiceImpl.getCandyPointByUser(), HttpStatus.OK);
	}

	// 현재 로그인한 유저의 특정날짜에 해당하는 CandyPoint 기록 조회
	@GetMapping("api/candyPoint/{timeStamp}") // timeSamp => yyyy-MM-dd
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<CandyPoint>>> getCandyPointsByUserAndDate(
			@PathVariable(name = "timeStamp") LocalDate timeStamp) {
		return new ResponseEntity<>(candyPointServiceImpl.getCandyPointsByUserAndDate(timeStamp), HttpStatus.OK);
	}

	// 현재 로그인한 사용자의 총 CandyPoint 합계 조회
	@GetMapping("api/candyPoint/total")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<Integer>> getTotalCandyPointsByUser() {
		return new ResponseEntity<>(candyPointServiceImpl.getTotalCandyPointsByUser(), HttpStatus.OK);
	}

	// 모든 사용자의 총 CandyPoint 합계 조회
	@GetMapping("api/candyPoint/total/all")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<UserCandyPointSumDto>>> getTotalCandyPointsByAllUser() {
		return new ResponseEntity<>(candyPointServiceImpl.getTotalCandyPointsByAllUser(), HttpStatus.OK);
	}

}
