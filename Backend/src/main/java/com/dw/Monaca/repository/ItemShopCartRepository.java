package com.dw.Monaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.ItemShopCart;

public interface ItemShopCartRepository extends JpaRepository<ItemShopCart, Long> {

	// 유저를 이용하여 ItemShopCart 목록 불러오기
	List<ItemShopCart> findByUser(User user);

}
