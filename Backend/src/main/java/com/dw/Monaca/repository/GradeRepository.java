package com.dw.Monaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

	Grade findByGradeName(String gradeName);

}
