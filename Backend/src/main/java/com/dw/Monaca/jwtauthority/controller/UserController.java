package com.dw.Monaca.jwtauthority.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.LectureListDto;
import com.dw.Monaca.dto.ProfessorDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.UserAuthorityDto;
import com.dw.Monaca.dto.UserDeleteRequestDto;
import com.dw.Monaca.jwtauthority.dto.UserDto;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.service.UserService;
import com.dw.Monaca.model.Rating;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 유저(일반 학생회원) 가입하기 / OK
	@PostMapping("/signup/user")
	public ResponseEntity<ResponseDto<UserDto>> signupForUser(@RequestBody @Valid UserDto userDto) {
		return new ResponseEntity<>(userService.signupForUser(userDto), HttpStatus.OK);
	}

	// 유저(교수) 가입하기 / OK
	@PostMapping("/signup/professor")
	public ResponseEntity<ResponseDto<User>> signupForProfessor(@RequestBody @Valid ProfessorDto professorDto) {
		return new ResponseEntity<>(userService.signupForProfessor(professorDto), HttpStatus.OK);
	}

//	권한이 'ROLE_ADMIN'이나 'ROLE_PROFESSOR'를 가지고 있지 않은 유저를 찾기
	@GetMapping("/users/non-admin-or-professor")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<UserDto>>> findUsersWithRoleUserOnly() {
		return new ResponseEntity<>(userService.findUsersWithRoleUserOnly(), HttpStatus.OK);
	}

	// 교수가입유저 불러오기 / OK
	@GetMapping("/apply/professor")
	public ResponseEntity<ResponseDto<List<ProfessorDto>>> getUserByProfessorIntro() {
		return new ResponseEntity<>(userService.getUserByProfessorIntro(), HttpStatus.OK);
	}

	// 유저에게 교수권한 부여하기 / OK
	@PutMapping("/user/authority/{loginId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<UserDto>> addAuthenticateByUser(@PathVariable(name = "loginId") String loginId) {
		return ResponseEntity.ok(userService.addAuthenticateByUser(loginId));
	}

	// LoginId를 이용해 특정 유저 정보 받아오기(관리자용) / OK
	@GetMapping("/user/{loginId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<UserDto>> getUserInfo(@PathVariable(name = "loginId") String loginId) {
		return new ResponseEntity<>(userService.getUserWithAuthorities(loginId), HttpStatus.OK);
	}
	
	// LoginId를 이용해 유저 정보 일부분을 받아오기(lectureInfo용)
	@GetMapping("/part-of-user/{loginId}")
	public ResponseEntity<ResponseDto<ProfessorDto>> getPartOfUserInfo(@PathVariable (name = "loginId") String loginId){
		return new ResponseEntity<>(userService.getPartOfUserInfo(loginId),HttpStatus.OK);
	}
	// 현재 로그인한 유저의 권한 조회하기 / OK
	@GetMapping("/user/authority")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<UserDto>> getCurrentUserWithAuthorities() {
		return new ResponseEntity<>(userService.getCurrentUserWithAuthorities(), HttpStatus.OK);
	}

	// 모든 유저의 권한 조회하기 / OK
	@GetMapping("/user/authority/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<UserAuthorityDto>>> getAuthorities() {
		return new ResponseEntity<>(userService.getAuthorities(), HttpStatus.OK);
	}

	// 회원 탈퇴(본인이 직접) 기능 / OK
	@DeleteMapping("/user/delete/self")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<ResponseDto<String>> deleteByUserForSelf(
			@RequestBody @Valid UserDeleteRequestDto userDeleteDto) {
		return new ResponseEntity<>(userService.deleteByUserForSelf(userDeleteDto), HttpStatus.OK);
	}

	// 회원 박탈(관리자가 진행) / OK
	@DeleteMapping("/user/delete/loginId/{loginId}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<String>> deleteByUser(@PathVariable(name = "loginId") String loginId) {
		return new ResponseEntity<>(userService.deleteByUser(loginId), HttpStatus.OK);
	}

	// 유저별로 가입날짜 조회하기 / OK
	@GetMapping("/user/joinDate/{loginId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<String>> getJoinDateByLoginId(@PathVariable(name = "loginId") String loginId) {
		return new ResponseEntity<>(userService.getJoinDateByLoginId(loginId), HttpStatus.OK);
	}

	// 모든 유저 가입날짜 조회하기 / OK
	@GetMapping("/user/joinDate/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Map<String, LocalDateTime>>> getJoinDateForAllUsers() {
		return new ResponseEntity<>(userService.getJoinDateForAllUsers(), HttpStatus.OK);
	}

	// 현재 로그인한 사용자 가입한 날 이후 현재 날짜 기준으로 며칠이 지났는지 조회하기 /OK
	@GetMapping("/user/joinDays")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<Long>> getDaysJoinedForSelf() {
		return new ResponseEntity<>(userService.getDaysJoinedForSelf(), HttpStatus.OK);
	}

	// 유저별로 가입한 날 이후 현재 날짜 기준으로 며칠이 지났는지 조회하기(유저의 가입일과 현재 날짜 사이의 일수를 계산) / OK
	@GetMapping("/daysSince/{loginId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<Long>> getDaysSinceJoined(@PathVariable(name = "loginId") String loginId) {
		return ResponseEntity.ok(userService.getDaysSinceJoined(loginId));
	}

	// 모든 유저 가입한 날 이후 현재 날짜 기준으로 며칠이 지났는지 조회하기(유저의 가입일과 현재 날짜 사이의 일수를 계산) / OK
	@GetMapping("/daysSince/all")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ResponseDto<Map<String, Long>>> getDaysSinceJoinedForAllUsers() {
		return ResponseEntity.ok(userService.getDaysSinceJoinedForAllUsers());
	}

	// 현재 로그인한 사용자의 user_rating 조회 / OK
	@GetMapping("/user/ratings")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<ResponseDto<Set<Rating>>> getCurrentUserRatings() {
		return new ResponseEntity<>(userService.getCurrentUserRatings(), HttpStatus.OK);
	}

	// 모든 사용자의 user_rating 조회 / OK
	@GetMapping("/user/ratings/all")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Map<String, Set<Rating>>>> getAllUsersRatings() {
		return new ResponseEntity<>(userService.getAllUsersRatings(), HttpStatus.OK);
	}

	// 특정 loginId를 가진 사용자의 user_rating 조회 / OK
	@GetMapping("/user/{loginId}/ratings")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Set<Rating>>> getUserRatingsByLoginId(
			@PathVariable(name = "loginId") String loginId) {
		return new ResponseEntity<>(userService.getUserRatingsByLoginId(loginId), HttpStatus.OK);
	}

	// 찜 목록 보기 / OK
	@GetMapping("/wishLecture/list")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> getWishLectures() {
		return new ResponseEntity<>(userService.getWishLectures(), HttpStatus.OK);
	}

	// 강의 찜하기 / OK
	@PostMapping("/saveWishLecture/{lectureId}")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> saveWishLecture(
			@PathVariable(name = "lectureId") Long lectureId) {
		return new ResponseEntity<>(userService.saveWishLecture(lectureId), HttpStatus.OK);
	}

	// 강의 찜 해제하기 / OK
	@DeleteMapping("/removeWishLecture/{lectureId}")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<ResponseDto<String>> removeWishLecture(@PathVariable(name = "lectureId") Long lectureId) {
		return new ResponseEntity<>(userService.removeWishLecture(lectureId), HttpStatus.OK);
	}

	// 현재 로그인한 교수가 가르치는 강의 조회하기 / OK
	@GetMapping("/teaching")
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> getTeachingLecturesByCurrentProfessor() {
		return new ResponseEntity<>(userService.getTeachingLecturesByCurrentProfessor(), HttpStatus.OK);
	}

	// 특정 교수 이름으로 강의 조회하기 / OK
	@GetMapping("/teaching/professorName/{professorName}")
	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<ResponseDto<List<LectureListDto>>> getTeachingLecturesByProfessorName(
			@PathVariable(name = "professorName") String professorName) {
		return new ResponseEntity<>(userService.getTeachingLecturesByProfessorName(professorName), HttpStatus.OK);
	}

//	// 모든 교수와 그들이 가르치는 강의 조회하기 /
//	@GetMapping("/api/teaching/professors/lectures")
//	@PreAuthorize("hasAnyRole('USER')")
//    public ResponseEntity<ResponseDto<Map<String, List<String>>>> getAllProfessorsAndTheirLectures(){
//    	return new ResponseEntity<>(userService.getAllProfessorsAndTheirLectures(),HttpStatus.OK);
//    }
}
