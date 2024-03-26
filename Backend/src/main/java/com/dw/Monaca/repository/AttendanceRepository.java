package com.dw.Monaca.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

// 출석 이력과 관련된 사용자 정의 추가
	// 사용자를 기반으로 출석 정보를 가져오기
	List<Attendance> findByUser(User user);

	// 해당 유저의 출석을 모두 불러온다 (출석 확인)
	List <Attendance> getAttendanceByUser(User user);

	// 특정한 날짜에 해당하는 출석 이력을 가져오는 쿼리
	List<Attendance> findByTimeStamp(LocalDateTime timeStamp);

    // 특정 로그인 ID와 날짜에 해당하는 출석 이력을 가져오는 쿼리
	List<Attendance> findByUserIdAndTimeStampBetween(User user, LocalDateTime startTime, LocalDateTime endTime);

    // 해당 로그인 ID에 대한 출석 이력과 관련된 캔디 정보를 함께 가져오는 쿼리
//	Optional<Attendance> findOneWithCandyByAttendance(String attendance);
	
	// 모든 사용자의 날짜에 해당하는 출석 이력을 가져옴
	List<Attendance> findByTimeStampBetween(LocalDateTime startTime, LocalDateTime endTime);

	Optional<Attendance> findByUserAndDate(User user, LocalDate date);

	

}
