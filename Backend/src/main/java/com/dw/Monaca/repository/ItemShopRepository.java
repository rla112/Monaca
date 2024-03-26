package com.dw.Monaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.ItemCategory;
import com.dw.Monaca.model.ItemShop;

public interface ItemShopRepository extends JpaRepository<ItemShop, Long> {

	// ItemCategory를 이용하여 ItemShop 찾기
	List<ItemShop> findByItemCategory(ItemCategory itemCategory);

}
