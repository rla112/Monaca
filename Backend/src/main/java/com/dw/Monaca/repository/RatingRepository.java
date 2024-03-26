package com.dw.Monaca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{

	Optional<Rating> findByCandyGrade(String candyGradeName);

}
