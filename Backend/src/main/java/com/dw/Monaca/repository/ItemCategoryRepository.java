package com.dw.Monaca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.ItemCategory;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

	// CategoryName으로 ItemCategory 찾기
	Optional<ItemCategory> findByCategoryName(String itemShopCategory);

}
