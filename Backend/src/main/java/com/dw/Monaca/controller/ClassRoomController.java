package com.dw.Monaca.controller;

import java.util.List;
import java.util.Map;

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

import com.dw.Monaca.dto.ClassRoomDto;
import com.dw.Monaca.dto.ClassRoomUpdateDto;
import com.dw.Monaca.dto.LectureSummaryDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.StudentProgressDto;
//import com.dw.Monaca.model.ClassRoom;
import com.dw.Monaca.service.impl.ClassRoomServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class ClassRoomController {

	private final ClassRoomServiceImpl classRoomServiceImpl;

	@Autowired
	public ClassRoomController(ClassRoomServiceImpl classRoomServiceImpl) {
		this.classRoomServiceImpl = classRoomServiceImpl;
	}

	// 현재 로그인한 유저의 ClassRoom을 전체 조회 / OK
	@GetMapping("/api/classRoom/all")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<ClassRoomDto>>> getAllClassRoom() {
		return new ResponseEntity<>(classRoomServiceImpl.getAllClassRoom(), HttpStatus.OK);

	}

	// 현재 로그인한 유저의 ClassRoom을 ClassRoom ID로 조회 / OK => recentviewing은 0으로 조회됨
	@GetMapping("/api/classRoom/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<ClassRoomDto>> getClassRoomByClassRoomId(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(classRoomServiceImpl.getClassRoomById(id), HttpStatus.OK);

	}

	// 현재 로그인한 유저의 ClassRoom 삭제 / OK
	@DeleteMapping("/api/classRoom/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<String>> deleteClassRoomByClassRoomId(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(classRoomServiceImpl.deleteClassRoomById(id), HttpStatus.OK);

	}

	// 현재 로그인한 유저의 ClassRoom의 진도율과 최근 시청기록을 업데이트 / OK
	@PutMapping("/api/classRoom/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<ClassRoomDto>> updateClassRoom(@PathVariable(name = "id") Long id,
			@RequestBody ClassRoomUpdateDto classRoomDto) {
		return new ResponseEntity<>(classRoomServiceImpl.updateClassRoom(id, classRoomDto), HttpStatus.OK);

	}

	// 진도율100%로 업데이트 후 classRoom에서는 삭제, lectureHistory에 생성
	@PutMapping("/api/classRoom/complete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<ClassRoomDto>> updateClassRoomAndAddLectureToClassRoom(@PathVariable Long id,
			@RequestBody ClassRoomUpdateDto classRoomUpdateDto) {
		return new ResponseEntity<>(
				classRoomServiceImpl.updateClassRoomAndAddLectureToClassRoom(id, classRoomUpdateDto), HttpStatus.OK);
	}

//	// 현재 로그인한 유저의 진도율(progressRate)이 80%에 도달한 ClassRoom을 LectureHistory로 이동
//	@PostMapping("/api/classRoom/move/{classRoomId}")
//	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
//	public ResponseEntity<ResponseDto<ClassRoom>> createToLectureHistory(@PathVariable Long classRoomId){
//		return new ResponseEntity<>(classRoomServiceImpl.createToLectureHistory(classRoomId),HttpStatus.OK);
//			
//	}
//		

	// LectureHistory에서 선택한 강의를 ClassRoom에 다시 추가 / OK
	@PostMapping("/api/classRoom/add/{lectureHistoryId}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<ClassRoomDto>> addLectureToClassRoom(
			@PathVariable(name = "lectureHistoryId") Long lectureHistoryId) {
		return new ResponseEntity<>(classRoomServiceImpl.addLectureToClassRoom(lectureHistoryId), HttpStatus.OK);

	}

	// 교수(현재 로그인한 사람)가 강의하는 과목의 카테고리별로 학생들의 진도율을 조회하는 기능 / OK
	@GetMapping("/api/progress/students")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	public ResponseEntity<ResponseDto<Map<String, List<StudentProgressDto>>>> getStudentProgressByProfessor() {
		return new ResponseEntity<>(classRoomServiceImpl.getStudentProgressByProfessor(), HttpStatus.OK);
	}

	// 특정 강의를 수강 중인 사람들의 수를 조회
	@GetMapping("/api/lecture/counts/lectureId/{lectureId}")
	public ResponseEntity<ResponseDto<Long>> getParticipantCountByLectureName(
			@PathVariable(name = "lectureId") Long lectureId) {
		return new ResponseEntity<>(classRoomServiceImpl.getParticipantCountByLectureId(lectureId), HttpStatus.OK);
	}

	// 유/무료 강의 전체에서 카테고리별 + 강의명별 로 각 강의를 듣는 사용자의 수가 얼마나 되는지
	@GetMapping("/api/lectureSummary")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<LectureSummaryDto>>> getLectureSummary() {
		return new ResponseEntity<>(classRoomServiceImpl.getLectureSummary(), HttpStatus.OK);
	}

	// 유료 강의 전체에서 카테고리별 + 강의명별 결제가 얼마나 되었는지
	@GetMapping("/api/lectureSummary/paid")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<LectureSummaryDto>>> getLectureSummaryByPaid() {
		return new ResponseEntity<>(classRoomServiceImpl.getLectureSummaryByPaid(), HttpStatus.OK);
	}

	// 무료 강의 전체에서 카테고리별 + 강의명별 각 강의를 듣는 사용자의 수가 얼마나 되는지
	@GetMapping("/api/lectureSummary/free")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<LectureSummaryDto>>> getLectureSummaryByFree() {
		return new ResponseEntity<>(classRoomServiceImpl.getLectureSummaryByFree(), HttpStatus.OK);
	}
}
