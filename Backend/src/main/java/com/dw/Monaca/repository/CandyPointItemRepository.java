package com.dw.Monaca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.CandyPointItem;

public interface CandyPointItemRepository extends JpaRepository<CandyPointItem, Long>{

	// CandyPointItem 명으로 CandyPointItem 찾기
	Optional<CandyPointItem> findByItemName(String itemName);

}
