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

import com.dw.Monaca.dto.MaterialDto;
import com.dw.Monaca.dto.MaterialListDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.service.impl.MaterialServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class MaterialController {

	private final MaterialServiceImpl materialServiceImpl;

	@Autowired
	public MaterialController(MaterialServiceImpl materialServiceImpl) {
		this.materialServiceImpl = materialServiceImpl;
	}

	// 전체 강의 자료 불러오기(관리자용) / OK
	@GetMapping("/api/material/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<MaterialListDto>>> getAllMaterial() {
		return new ResponseEntity<>(materialServiceImpl.getAllMaterial(), HttpStatus.OK);
	}

	// 강의 별 강의 자료 불러오기(is_reservation false만) / OK
	@GetMapping("/api/material/lectureName/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<MaterialListDto>>> getMaterialByLecture(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(materialServiceImpl.getMaterialByLecture(lectureName), HttpStatus.OK);
	}

	// 강의 별 강의 자료 불러오기(is_reservation ture만 가능) / OK
	@GetMapping("/api/material/reservation/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<List<MaterialListDto>>> getReservationMaterialByLectureForProfessor(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(materialServiceImpl.getReservationMaterialByLectureForProfessor(lectureName),
				HttpStatus.OK);
	}

	// 강의 별 강의 자료 불러오기(is_reservation ture, false 모두 가능) / OK
	@GetMapping("api/material/all/{lectureName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<List<MaterialListDto>>> getMaterialByLectureForProfessor(
			@PathVariable(name = "lectureName") String lectureName) {
		return new ResponseEntity<>(materialServiceImpl.getMaterialByLectureForProfessor(lectureName), HttpStatus.OK);
	}

	// MaterialID로 특정 강의자료 불러오기 / OK
	@GetMapping("api/material/id/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<MaterialDto>> getMaterialById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(materialServiceImpl.getMaterialById(id), HttpStatus.OK);
	}

	// 강의 자료 임시 업로드(초기엔 isReservation이 ture임. 강의 자료를 다 완성하고 난 뒤에 정식 업로드를 해야함. 초기엔
	// 임시저장) / OK
	@PostMapping("api/material")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<MaterialDto>> reservationMaterial(@RequestBody @Valid MaterialDto materialDto) {
		return new ResponseEntity<>(materialServiceImpl.reservationMaterial(materialDto), HttpStatus.OK);
	}

	// 강의자료 업로드(작성자인 교수와 관리자만 가능, isReservation을 false로 바꿔 정식 업로드) / OK
	@PutMapping("api/material/upload/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<MaterialDto>> uploadMaterial(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(materialServiceImpl.uploadMaterial(id), HttpStatus.OK);
	}

	// 강의 자료 수정(작성자인 교수와 관리자만 가능, 임시저장으로 업데이트) / OK
	@PutMapping("api/material/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<MaterialDto>> updateMaterial(@PathVariable(name = "id") Long id,
			@RequestBody MaterialDto updateMaterial) {
		return new ResponseEntity<>(materialServiceImpl.updateMaterial(id, updateMaterial), HttpStatus.OK);
	}

	// 강의 자료 삭제(작성자인 교수와 관리자만 가능)
	@DeleteMapping("api/material/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<String>> deleteMaterial(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(materialServiceImpl.deleteMaterial(id), HttpStatus.OK);
	}
}
