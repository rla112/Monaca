package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.Attendance;

import com.dw.Monaca.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })
public class AttendanceController {

	private final AttendanceService attendanceService;

	@Autowired
	public AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	// 특정 사용자의 출석 정보 저장
	@PostMapping("/api/attendance")
	public ResponseDto<Attendance> saveAttendance(@RequestBody @Valid Attendance attendance) {
		return attendanceService.saveAttendance(attendance);
	}

	// 모든 사용자의 출석 목록을 가져오기
	@GetMapping("/api/adrate/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAllAttendance() {
		return ResponseEntity.ok(attendanceService.getAllAttendance());
	}

	// 모든 유저의 연도별 출석목록을 가져오기
	// adrate = AttendanceRate = 출석율
	@GetMapping("/api/adrate/year/{year}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByTimeStampOfYear(
			@PathVariable(name = "year") int year) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByTimeStampOfYear(year));
	}

	// 모든 유저의 월별 출석목록을 가져오기
	@GetMapping("/api/adrate/year/{year}/month/{month}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByTimeStampOfMonth(
			@PathVariable(name = "year") int year, @PathVariable(name = "month") int month) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByTimeStampOfMonth(year, month));
	}

	// 모든 유저의 주간별 출석목록을 가져오기
	@GetMapping("/api/adrate/year/{year}/month/{week}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByTimeStampOfWeek(
			@PathVariable(name = "year") int year, @PathVariable(name = "month") int month,
			@PathVariable(name = "week") int week) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByTimeStampOfWeek(year, month, week));
	}

	// 모든 유저의 일별 출석목록을 가져오기
	@GetMapping("/api/adrate/year/{year}/month/{month}/day/{day}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByTimeStampOfDay(
			@PathVariable(name = "year") int year, @PathVariable(name = "month") int month,
			@PathVariable(name = "day") int day) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByTimeStampOfDay(year, month, day));
	}

	// 현재 로그인 한 유저의 출석 목록을 가져오기
	@GetMapping("/api/myadrate")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByUser() {
		return ResponseEntity.ok(attendanceService.getAttendanceByUser());
	}

	// 현재 로그인한 유저의 연도별 출석목록을 가져오기
	@GetMapping("/api/myadrate/year/{year}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByUserIdAndTimeStampOfYear(
			@PathVariable(name = "year") int year) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByUserIdAndTimeStampOfYear(year));
	}

	// 특정 유저의 월별 출석목록을 가져오기
	@GetMapping("/api/myadrate/year/{year}/month/{month}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByUserIdAndTimeStampOfMonth(
			@PathVariable(name = "year") int year, @PathVariable(name = "month") int month) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByUserIdAndTimeStampOfMonth(year, month));
	}

	// 특정 유저의 주간별 출석목록을 가져오기
	@GetMapping("api/myadrate/year/{year}/month/{month}/week/{week}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByUserIdAndTimeStampOfWeek(
			@PathVariable(name = "year") int year, @PathVariable(name = "month") int month,
			@PathVariable(name = "week") int week) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByUserIdAndTimeStampOfWeek(year, month, week));
	}

	// 특정 유저의 일별 출석목록을 가져오기
	@GetMapping("api/myadrate/year/{year}/month/{month}/day/{day}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByUserIdAndTimeStampOfDay(
			@PathVariable(name = "year") int year, @PathVariable(name = "month") int month,
			@PathVariable(name = "weeek") int day) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByUserIdAndTimeStampOfDay(year, month, day));
	}

	// 특정 유저의 LoginId로 출석 목록을 가져오기
	@GetMapping("/api/adrate/{loginId}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByLoginId(
			@PathVariable(name = "loginId") String loginId) {
		return ResponseEntity.ok(attendanceService.getAttendanceByLoginId(loginId));
	}

	// 특정 유저의 LoginId로 년도별 출석 리스트 조회
	@GetMapping("api/adrate/{loginId}/year/{year}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByLoginIdIdAndTimeStampOfYear(
			@PathVariable(name = "loginId") String loginId, @PathVariable(name = "year") int year) {
		// 날짜 형식에 따라 변환 필요
		return ResponseEntity.ok(attendanceService.getAttendanceByLoginIdIdAndTimeStampOfYear(loginId, year));
	}

	// 특정 유저의 LoginId로 월별 출석 리스트 조회
	@GetMapping("api/adrate/{loginId}/year/{year}/month/{month}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByLoginIdIdAndTimeStampOfMonth(
			@PathVariable(name = "loginId") String loginId, @PathVariable(name = "year") int year,
			@PathVariable(name = "month") int month) {
		return ResponseEntity.ok(attendanceService.getAttendanceByLoginIdIdAndTimeStampOfMonth(loginId, year, month));
	}

	// 특정 유저의 LoginId로 주별 출석 리스트 조회
	@GetMapping("api/adrate/{loginId}/year/{year}/month/{month}/week/{week}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByLoginIdIdAndTimeStampOfWeek(
			@PathVariable(name = "loginId") String loginId, @PathVariable(name = "year") int year,
			@PathVariable(name = "month") int month, @PathVariable(name = "week") int week) {
		return ResponseEntity
				.ok(attendanceService.getAttendanceByLoginIdIdAndTimeStampOfWeek(loginId, year, month, week));
	}

	// 특정 유저의 LoginId로 일별 출석 리스트 조회
	@GetMapping("api/adrate/{loginId}/year/{year}/month/{month}/day/{day}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	ResponseEntity<ResponseDto<List<Attendance>>> getAttendanceByLoginIdIdAndTimeStampOfDay(
			@PathVariable(name = "loginId") String loginId, @PathVariable(name = "year") int year,
			@PathVariable(name = "month") int month, @PathVariable(name = "day") int day) {
		return ResponseEntity
				.ok(attendanceService.getAttendanceByLoginIdIdAndTimeStampOfDay(loginId, year, month, day));
	}
}
