package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

//import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.ItemShop;
import com.dw.Monaca.model.UserItem;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
	// 엔티티그래프로 유저테이블을 불러오기
	// 디스코드 유저 레퍼지토리 참고

//	@EntityGraph(attributePaths = "user")
	// User를 이용해 UserItem 목록 불러오기
	List<UserItem> findByUser(User user);

	// User와 ItemShop을 이용해 UserItem 목록 불러오기
	List<UserItem> findByUserAndItemShop(User user,ItemShop itemShop);
	
	// UserItem 엔티티에서 is_wearing 필드가 true인 아이템들을 찾음
	List<UserItem> findByUserAndIsWearingTrue(User user);

	// User와 ItemID를 이용하여 UserItem을 찾음.
	Optional<UserItem> findByIdAndUser(Long id, User user);

}
