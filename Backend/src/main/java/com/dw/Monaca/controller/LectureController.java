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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.LectureDto;
import com.dw.Monaca.dto.LectureListDto;
import com.dw.Monaca.dto.ProfessorDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.service.impl.LectureServiceImpl;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
public class LectureController {

	private final LectureServiceImpl lectureServiceImpl;

	@Autowired
	public LectureController(LectureServiceImpl lectureServiceImpl) {
		this.lectureServiceImpl = lectureServiceImpl;
	}

	// 모든 Lecture 불러오기 / OK
	@GetMapping("/lecture/all")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> getAllLecture() {
		return new ResponseEntity<>(lectureServiceImpl.getAllLecture(), HttpStatus.OK);
	}

	// Lecture 카테고리 별 불러오기 / OK
	@GetMapping("/lecture/lectureCategory/{categoryName}")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> getAllLectureByCategoryName(
			@PathVariable(name = "categoryName") String categoryName) {
		return new ResponseEntity<>(lectureServiceImpl.getAllLectureByCategoryName(categoryName), HttpStatus.OK);
	}

	// professor 별 불러오기 / OK
	@GetMapping("/lecture/professor/{professorName}")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> getAllLectureByProfessor(
			@PathVariable(name = "professorName") String professorName) {
		return new ResponseEntity<>(lectureServiceImpl.getAllLectureByProfessor(professorName), HttpStatus.OK);
	}

	// 유료강의만 불러오기 / OK
	@GetMapping("/lecture/paid")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> getAllLectureByPaidLectures() {
		return new ResponseEntity<>(lectureServiceImpl.getAllLectureByPaidLectures(), HttpStatus.OK);
	}

	// 무료 강의만 불러오기 / OK
	@GetMapping("/lecture/free")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> getAllLectureByFreeLectures() {
		return new ResponseEntity<>(lectureServiceImpl.getAllLectureByFreeLectures(), HttpStatus.OK);
	}

	// LecureID로 특정 강의 불러오기 / OK
	@GetMapping("/lecture/id/{id}")
	public ResponseEntity<ResponseDto<LectureDto>> getLectureById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(lectureServiceImpl.getLectureById(id), HttpStatus.OK);
	}

	// Lecture 업로드 / OK
	@PostMapping("/lecture")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<Lecture>> createLecture(@RequestBody @Valid LectureDto lectureDto) {
		return new ResponseEntity<>(lectureServiceImpl.createLecture(lectureDto), HttpStatus.OK);
	}

	// LectrueID로 특정 강의 삭제 / OK
	@DeleteMapping("/lecture/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<String>> deleteLectureById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(lectureServiceImpl.deleteLectureById(id), HttpStatus.OK);
	}

	
	// LectureID로 특정 강의의 교수 불러오기
	@GetMapping("/lecture/professorInfo/{id}")
	public ResponseEntity<ResponseDto<ProfessorDto>> getProfessorById(@PathVariable Long id){
		return new ResponseEntity<>(lectureServiceImpl.getProfessorById(id),HttpStatus.OK);
	}
	
	// LectureCategory로 특정 교수 리스트 불러오기
	@GetMapping("/lecture/professor/list/{lectureCategoryName}")
	public ResponseEntity<ResponseDto<List<ProfessorDto>>> getProfessorByLectureCategoryName(@PathVariable String lectureCategoryName){
	return new ResponseEntity<>(lectureServiceImpl.getProfessorByLectureCategoryName(lectureCategoryName),HttpStatus.OK);		
		}
	
}
