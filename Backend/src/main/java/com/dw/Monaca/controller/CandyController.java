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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Candy;
import com.dw.Monaca.service.impl.CandyServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
//@RequestMapping("/api/candies")
public class CandyController {

	private final CandyServiceImpl candyServiceImpl;

	@Autowired
	public CandyController(CandyServiceImpl candyServiceImpl) {
		this.candyServiceImpl = candyServiceImpl;
	}

	// 모든 candy 불러오기

	@GetMapping("api/candy/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<Candy>>> getAllCandies() {
		return new ResponseEntity<>(candyServiceImpl.getAllCandies(), HttpStatus.OK);
	}

	// candyID로 candy 불러오기
	@GetMapping("/api/candy/{candyId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Candy>> getCandyById(@PathVariable(name = "candyId") Long candyId) {
		return new ResponseEntity<>(candyServiceImpl.getCandyById(candyId), HttpStatus.OK);
	}

	// candy 저장하기
	@PostMapping("api/candy")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Candy>> saveCandy(@RequestBody Candy candy) {
		return new ResponseEntity<>(candyServiceImpl.saveCandy(candy), HttpStatus.OK);
	}

	// candy 수정하기
	@PutMapping("api/candy/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	ResponseEntity<ResponseDto<Candy>> updateCandy(@RequestBody Candy updateCandy, @PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(candyServiceImpl.updateCandy(updateCandy, id), HttpStatus.OK);
	}

	// candy 삭제하기
	@DeleteMapping("api/candy/delete{candyId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<String>> deleteCandyById(@PathVariable(name = "candyId") Long candyId) {
		return new ResponseEntity<>(candyServiceImpl.deleteCandyById(candyId), HttpStatus.OK);
	}

}