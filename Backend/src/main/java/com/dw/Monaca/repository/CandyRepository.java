package com.dw.Monaca.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.Candy;

public interface CandyRepository extends JpaRepository<Candy, Long>{

	Optional<Candy> findById(Long id);

	// CandyName으로 Candy 찾기
	Candy findByCandyName(String candyName);

	
}
