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

import com.dw.Monaca.dto.LectureCartDto;
import com.dw.Monaca.dto.ResponseDto;
//import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.service.impl.LectureCartServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class LectureCartController {

	private final LectureCartServiceImpl lectureCartServiceImpl;

	@Autowired
	public LectureCartController(LectureCartServiceImpl lectureCartServiceImpl) {
		this.lectureCartServiceImpl = lectureCartServiceImpl;
	}

	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item(Lecture) 전부 조회 / OK
	@GetMapping("/api/lectureCart/all")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<List<LectureCartDto>>> getAllLectureCart() {
		return new ResponseEntity<>(lectureCartServiceImpl.getAllLectureCart(), HttpStatus.OK);
	}

	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item(Lecture)을 ID로 조회 / OK
	@GetMapping("/api/lectureCart/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<LectureCartDto>> getLectureCartById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(lectureCartServiceImpl.getLectureCartById(id), HttpStatus.OK);
	}

	// 현재 로그인한 User의 cart에 Item(Lecture) 담기 / OK
	@PostMapping("/api/lectureCart")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<Long>> createLectureCart(@RequestBody LectureCartDto lectureCartDto) {
		System.out.println(lectureCartDto.toString());
		return new ResponseEntity<>(lectureCartServiceImpl.createLectureCart(lectureCartDto), HttpStatus.OK);
	}

	// 현재 로그인한 User가 장바구니에 담아놓은 Item(Lecture)를 결제( ClassRoom에는 복사되어 생성 ) / OK =>
	// classRoom은 확인 필요
	@PostMapping("/api/lectureCart/purchase/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<Long>> purchaseLectureCart(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(lectureCartServiceImpl.purchaseLectureCart(id), HttpStatus.OK);

	}

	// 현재 로그인한 User의 cart에 담아놓은 Item(lecture) 전부 삭제
	@DeleteMapping("/api/lectureCart/delete/all")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteAllLectureCart() {
		return new ResponseEntity<>(lectureCartServiceImpl.deleteAllLectureCart(), HttpStatus.OK);
	}

	// 현재 로그인한 User의 cart에 담아놓은 Item(lecture) ID로 선택하여 삭제 / OK
	@DeleteMapping("/api/lectureCart/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteLectureCartById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(lectureCartServiceImpl.deleteLectureCartById(id), HttpStatus.OK);
	}

}
