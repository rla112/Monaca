package com.dw.Monaca.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dw.Monaca.dto.UserCandyPointSumDto;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.CandyPoint;
import com.dw.Monaca.model.CandyPointItem;

public interface CandyPointRepository extends JpaRepository<CandyPoint, Long> {

	// JPQL 쿼리
	// CandyPoint 엔티티에서 user 필드가 특정 사용자와 동일한 모든 엔티티의 point 필드 값을 합(SUM)하는 쿼리임.
	// :user는 JPQL에서 사용되는 파라미터 바인딩으로, 메서드의 User user 파라미터로 전달받은 값을 바인딩함
	@Query("SELECT SUM(point) FROM CandyPoint WHERE user = :user")
	Integer sumPointsByUser(User user);
	// JPQL 쿼리를 실행하고,결과인 point의 합계를 Integer 타입으로 반환합니다.
	// Integer sumPointsByUser(User user):  위 JPQL 쿼리를 실행하고,
	// 결과인 point의 합계를 Integer 타입으로 반환함.
	// 이 메서드는 특정 사용자의 총 CandyPoint를 계산하는데 사용됨.

	
	// 각 사용자의 loginId와 그 사용자의 CandyPoint 합계를 UserCandyPointSumDto 객체로 만들어 리스트로 반환함.
	// CandyPoint 엔티티를 cp라는 별칭으로 조회하고, 각 사용자(cp.user.loginId)의 CandyPoint(cp.point)를 합계(SUM)하여
	// UserCandyPointSumDto 객체를 생성
	@Query("SELECT new com.dw.Monaca.dto.UserCandyPointSumDto(cp.user.loginId, SUM(cp.point)) FROM CandyPoint cp GROUP BY cp.user.loginId")
	List<UserCandyPointSumDto> sumPointsByAllUsers();


	//  특정 사용자의 특정 시간 범위에 생성된 CandyPoint 조회 
	// CandyPoint의 user 필드가 user이고, timeStamp 필드가 start와 end 사이인 CandyPoint를 찾는 쿼리를 자동으로 생성
	List<CandyPoint> findByUserAndTimeStampBetween(User user, LocalDateTime start, LocalDateTime end);

	// User를 이용하여 CandyPoint 목록 불러오기
	List<CandyPoint> findByUser(User user);

	// User와 CandyPointItem을 사용해서 CandyPoint를 세기
	int countByUserAndCandyPointItem(User user, CandyPointItem candyPointItem);


	// User를 이용하여 해당 유저의 모든 CandyPoint 찾기 
	List<CandyPoint> findAllByUser(User user);
	
}
