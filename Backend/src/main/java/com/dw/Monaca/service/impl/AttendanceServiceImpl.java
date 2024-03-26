package com.dw.Monaca.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Attendance;
import com.dw.Monaca.model.Candy;
import com.dw.Monaca.model.CandyPointItem;
import com.dw.Monaca.model.CandyPoint;
import com.dw.Monaca.repository.AttendanceRepository;
import com.dw.Monaca.repository.CandyPointItemRepository;
import com.dw.Monaca.repository.CandyPointRepository;
import com.dw.Monaca.repository.CandyRepository;
import com.dw.Monaca.service.AttendanceService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceRepository attendanceRepository;
	private final UserRepository userRepository;
	private final CandyPointItemRepository candyPointItemRepository;
	private final CandyPointRepository candyPointRepository;
	private final RatingServiceImpl ratingServiceImpl;
	private final CandyRepository candyRepository;

	@Autowired
	public AttendanceServiceImpl(AttendanceRepository attendanceRepository, UserRepository userRepository,
			CandyPointItemRepository candyPointItemRepository, CandyPointRepository candyPointRepository,
			RatingServiceImpl ratingServiceImpl, CandyRepository candyRepository) {
		super();
		this.attendanceRepository = attendanceRepository;
		this.userRepository = userRepository;
		this.candyPointItemRepository = candyPointItemRepository;
		this.candyPointRepository = candyPointRepository;
		this.ratingServiceImpl = ratingServiceImpl;
		this.candyRepository = candyRepository;
	}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId() // 현 접속자를 가져온다
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	// 현재 로그인한 유저의 출석이력 생성
	@Override
	public ResponseDto<Attendance> saveAttendance(Attendance attendance) {
		// 사용자 ID로 사용자를 조회
		User user = getAuthenticatedUser();
		if (user == null) {
			throw new InvalidRequestException("User not found", "해당 ID의 사용자를 찾을 수 없습니다.");
		}
		
		// 현재 시간을 기준으로 출석 정보 생성
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate currentDate = currentTime.toLocalDate();
		Attendance attendances = new Attendance(null, user, currentDate, currentTime);
		
		// 동일한 날짜의 출석 이력이 있는지 확인
		Optional<Attendance> existingAttendance = attendanceRepository.findByUserAndDate(user, currentDate);

		// 동일한 날짜의 출석 이력이 있다면 예외를 발생시킴
		if (existingAttendance.isPresent()) {
		    throw new InvalidRequestException("Already attended", "오늘 날짜에 이미 출석하였습니다.");
		}
		
		// 생성된 출석 정보 저장
		Attendance savedAttendance = attendanceRepository.save(attendances);
		
		// CandyPoint 생성 및 저장
		CandyPointItem candyPointItem = candyPointItemRepository.findByItemName("Attendance")
		    .orElseGet(() -> {
		        CandyPointItem newItem = new CandyPointItem("Attendance");
		        return candyPointItemRepository.save(newItem);
		    });

		// 모든 Candy를 데이터베이스에서 가져옴
		List<Candy> allCandies = candyRepository.findAll();

		// allCandies 리스트가 비어있지 않은 경우에만 랜덤 Candy를 선택
		Candy randomCandy = null;
		if (!allCandies.isEmpty()) {
		    int randomIndex = new Random().nextInt(allCandies.size());
		    randomCandy = allCandies.get(randomIndex);
		}

		// CandyPoint 객체 생성 및 설정
		CandyPoint candyPoint = new CandyPoint();
		candyPoint.setUser(user);
		candyPoint.setCandy(randomCandy);  // 랜덤으로 선택한 Candy 설정
		candyPoint.setCandyPointItem(candyPointItem);  // CandyPointItem을 'Attendance'로 설정
		candyPoint.setPoint(1);  // 포인트를 1로 설정
		candyPoint.setTimeStamp(LocalDateTime.now());  // 현재 시간으로 타임스탬프 설정

		// CandyPoint 객체 저장
		candyPointRepository.save(candyPoint);

        // 사용자의 출석으로 인해 생성된 CandyPoint 개수 계산
        int attendanceCandyPoints = candyPointRepository.countByUserAndCandyPointItem(user, candyPointItem);

        // 등업 기준 확인 및 등업 처리
        if (attendanceCandyPoints >= 45) {
            // GreenCandy로 등업
            ratingServiceImpl.upgradeToGreenCandy(user);
        } else if (attendanceCandyPoints >= 30) {
            // YellowCandy로 등업
            ratingServiceImpl.upgradeToYellowCandy(user);
        } else if (attendanceCandyPoints >= 15) {
            // OrangeCandy로 등업
            ratingServiceImpl.upgradeToOrangeCandy(user);
        }
//        	// 저장된 CandyPoint가 null인 경우 예외를 던짐
//        	if (savedCandyPoint == null) {
//        	    throw new InvalidRequestException("Failed to save CandyPoint","출석에 해당하는 CandyPoint를 생성하여 저장하지 못했습니다.");
//        	}
//	    CandyPoint candyPoint = new CandyPoint(null, user, null, candyPointItem, 1, "출석 포인트", currentTime);
//	    candyPointRepository.save(candyPoint);
		
		// 저장된 출석 정보를 응답
		return new ResponseDto<>(ResultCode.SUCCESS.name(), savedAttendance, ResultCode.SUCCESS.getMsg());
	}
	
	//모든 출석 이력을 가져오기
	@Override
	public ResponseDto<List<Attendance>> getAllAttendance() {
		// 출석 이력을 리포지토리에서 모두 가져오기
		List<Attendance> attendances = attendanceRepository.findAll();
		// 만약 출석 이력이 비어 있다면, 예외를 발생.
		if (attendances.isEmpty()) {
			throw new InvalidRequestException("Attendance empty", "출석 이력이 존재하지 않습니다.");
		}
		// 출석 이력이 비어 있지 않다면, 성공적인 응답을 생성하여 반환.
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}


// 모든 사용자의 년도별 출석 리스트 조회
	public ResponseDto<List<Attendance>> getAttendanceByTimeStampOfYear(int year) {
		// 년도의 시작 시간 설정 (1월 1일)
		LocalDateTime startTime = LocalDateTime.of(year, 1, 1, 0, 0);
		// 년도의 끝 시간 설정 (12월 31일, 23:59:59)
		LocalDateTime endTime = LocalDateTime.of(year, 12, 31, 23, 59, 59);
		List<Attendance> attendances = attendanceRepository.findByTimeStampBetween(startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 년도의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

	// 모든 사용자의 월별 출석 리스트 조회
	public ResponseDto<List<Attendance>> getAttendanceByTimeStampOfMonth(int year, int month) {
		// 월의 시작 시간 설정
		LocalDateTime startTime = LocalDateTime.of(year, month, 1, 0, 0);
		// 월의 끝 시간 설정 (마지막 날, 23:59:59)
		LocalDateTime endTime = LocalDateTime.of(year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59, 59);
		List<Attendance> attendances = attendanceRepository.findByTimeStampBetween(startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 달의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());

	}

// 모든 사용자의 주별 출석 리스트 조회
	public ResponseDto<List<Attendance>> getAttendanceByTimeStampOfWeek(int year, int month, int week) {
		LocalDateTime startTime = LocalDateTime.of(year, month, 1, 0, 0)
	            .with(TemporalAdjusters.firstDayOfMonth())
	            .plusWeeks(week - 1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

		// 주의 끝 시간 설정 (일요일, 23:59:59)
		LocalDateTime endTime = startTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23)
				.withMinute(59).withSecond(59);
		List<Attendance> attendances = attendanceRepository.findByTimeStampBetween(startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 주의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

	// 모든 사용자의 일별 출석 리스트 조회
	public ResponseDto<List<Attendance>> getAttendanceByTimeStampOfDay(int year, int month, int day) {
		LocalDateTime startTime = LocalDateTime.of(year, month, day, 0, 0);
		LocalDateTime endTime = LocalDateTime.of(year, month, day, 23, 59, 59);
		List<Attendance> attendances = attendanceRepository.findByTimeStampBetween(startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 날짜의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}
	
	// 현재 로그인한 유저의 출석이력을 모두 가져오기
	@Override
	public ResponseDto<List<Attendance>> getAttendanceByUser() {
		User user = getAuthenticatedUser();
		List<Attendance> attendances = attendanceRepository.findByUser(user);
		// 리스트가 비어 있는지 확인하고 그에 따라 처리
		if (attendances.isEmpty()) {
			throw new InvalidRequestException("Attendance not found", "해당 사용자의 출석 이력이 없습니다.");
		}
		// 성공 코드와 출석 목록을 포함한 응답을 반환
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 유저의 년도별 출석 리스트 조회
	@Override
	public ResponseDto<List<Attendance>> getAttendanceByUserIdAndTimeStampOfYear(int year) {
		// 년도의 시작 시간 설정 (1월 1일)
		LocalDateTime startTime = LocalDateTime.of(year, 1, 1, 0, 0);
		// 년도의 끝 시간 설정 (12월 31일, 23:59:59)
		LocalDateTime endTime = LocalDateTime.of(year, 12, 31, 23, 59, 59);
		// 사용자 ID와 날짜로 출석 목록을 가져오기
		User user = getAuthenticatedUser();
		List<Attendance> attendances = attendanceRepository.findByUserIdAndTimeStampBetween(user, startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 년도의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

	
	
	// 현재 로그인한 유저의 월별 출석 리스트 조회
	public ResponseDto<List<Attendance>> getAttendanceByUserIdAndTimeStampOfMonth(int year, int month) {
		// 월의 시작 시간 설정
		LocalDateTime startTime = LocalDateTime.of(year, month, 1, 0, 0);
		// 월의 끝 시간 설정 (마지막 날, 23:59:59)
		LocalDateTime endTime = LocalDateTime.of(year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59, 59);
		User user = getAuthenticatedUser();
		List<Attendance> attendances = attendanceRepository.findByUserIdAndTimeStampBetween(user, startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 달의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());

	}

	// 현재 로그인한 유저의 주별 출석 리스트 조회
	public ResponseDto<List<Attendance>> getAttendanceByUserIdAndTimeStampOfWeek(int year,int month, int week) {
		User user = getAuthenticatedUser();
		// 주의 시작 시간 설정 (월요일)
		LocalDateTime startTime = LocalDateTime.of(year, month, 1, 0, 0)
	            .with(TemporalAdjusters.firstDayOfMonth())
	            .plusWeeks(week - 1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

		// 주의 끝 시간 설정 (일요일, 23:59:59)
		LocalDateTime endTime = startTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23)
				.withMinute(59).withSecond(59);
		List<Attendance> attendances = attendanceRepository.findByUserIdAndTimeStampBetween(user, startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 주의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 유저의 일별 출석 리스트 조회
	public ResponseDto<List<Attendance>> getAttendanceByUserIdAndTimeStampOfDay(int year, int month, int day) {
		User user = getAuthenticatedUser();
		LocalDateTime startTime = LocalDateTime.of(year, month, day, 0, 0);
		LocalDateTime endTime = LocalDateTime.of(year, month, day, 23, 59, 59);
		List<Attendance> attendances = attendanceRepository.findByUserIdAndTimeStampBetween(user, startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 날짜의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}
	


	// 특정 유저의 LoginId로 출석 목록을 가져오기
	@Override
	public ResponseDto<List<Attendance>> getAttendanceByLoginId(String loginId) {
		Optional<User> userOptional = userRepository.findByLoginId(loginId);
		if(userOptional.isEmpty()) {
			throw new InvalidRequestException("User not found", "해당 LoginId의 사용자를 찾을 수 없습니다.");
		}
		User user = userOptional.get();
		List<Attendance> attendances = attendanceRepository.findByUser(user);
		// 리스트가 비어 있는지 확인하고 그에 따라 처리
		if (attendances.isEmpty()) {
			throw new InvalidRequestException("Attendance not found", "해당 사용자의 출석 이력이 없습니다.");
		}
		// 성공 코드와 출석 목록을 포함한 응답을 반환
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

	// 특정 유저의 LoginId로 년도별 출석 리스트 조회
	@Override
	public ResponseDto<List<Attendance>> getAttendanceByLoginIdIdAndTimeStampOfYear(String loginId, int year) {
		Optional<User> userOptional = userRepository.findByLoginId(loginId);
		if(userOptional.isEmpty()) {
			throw new InvalidRequestException("User not found", "해당 LoginId의 사용자를 찾을 수 없습니다.");
		}
		User user = userOptional.get();
		// 년도의 시작 시간 설정 (1월 1일)
		LocalDateTime startTime = LocalDateTime.of(year, 1, 1, 0, 0);
		// 년도의 끝 시간 설정 (12월 31일, 23:59:59)
		LocalDateTime endTime = LocalDateTime.of(year, 12, 31, 23, 59, 59);
		// 사용자 ID와 날짜로 출석 목록을 가져오기
		List<Attendance> attendances = attendanceRepository.findByUserIdAndTimeStampBetween(user, startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 년도의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

	// 특정 유저의 LoginId로 월별 출석 리스트 조회
	@Override
	public ResponseDto<List<Attendance>> getAttendanceByLoginIdIdAndTimeStampOfMonth(String loginId, int year,
			int month) {
		Optional<User> userOptional = userRepository.findByLoginId(loginId);
		if(userOptional.isEmpty()) {
			throw new InvalidRequestException("User not found", "해당 LoginId의 사용자를 찾을 수 없습니다.");
		}
		User user = userOptional.get();
		// 월의 시작 시간 설정
		LocalDateTime startTime = LocalDateTime.of(year, month, 1, 0, 0);
		// 월의 끝 시간 설정 (마지막 날, 23:59:59)
		LocalDateTime endTime = LocalDateTime.of(year, month, YearMonth.of(year, month).lengthOfMonth(), 23, 59, 59);
		List<Attendance> attendances = attendanceRepository.findByUserIdAndTimeStampBetween(user, startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 달의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());

	}

	// 특정 유저의 LoginId로 주별 출석 리스트 조회
	@Override
	public ResponseDto<List<Attendance>> getAttendanceByLoginIdIdAndTimeStampOfWeek(String loginId, int year, int month,
			int week) {
		Optional<User> userOptional = userRepository.findByLoginId(loginId);
		if(userOptional.isEmpty()) {
			throw new InvalidRequestException("User not found", "해당 LoginId의 사용자를 찾을 수 없습니다.");
		}
		User user = userOptional.get();
		// 주의 시작 시간 설정 (월요일)
		LocalDateTime startTime = LocalDateTime.of(year, month, 1, 0, 0)
	            .with(TemporalAdjusters.firstDayOfMonth())
	            .plusWeeks(week - 1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

		// 주의 끝 시간 설정 (일요일, 23:59:59)
		LocalDateTime endTime = startTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23)
				.withMinute(59).withSecond(59);
		List<Attendance> attendances = attendanceRepository.findByUserIdAndTimeStampBetween(user, startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 주의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

	// 특정 유저의 LoginId로 일별 출석 리스트 조회
	@Override
	public ResponseDto<List<Attendance>> getAttendanceByLoginIdIdAndTimeStampOfDay(String loginId, int year, int month,
			int day) {
		Optional<User> userOptional = userRepository.findByLoginId(loginId);
		if(userOptional.isEmpty()) {
			throw new InvalidRequestException("User not found", "해당 LoginId의 사용자를 찾을 수 없습니다.");
		}
		User user = userOptional.get();
		LocalDateTime startTime = LocalDateTime.of(year, month, day, 0, 0);
		LocalDateTime endTime = LocalDateTime.of(year, month, day, 23, 59, 59);
		List<Attendance> attendances = attendanceRepository.findByUserIdAndTimeStampBetween(user, startTime, endTime);
		if (attendances.isEmpty()) {
			 return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, "해당 날짜의 출석 정보가 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), attendances, ResultCode.SUCCESS.getMsg());
	}

}
