package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.QandA;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Lecture;

public interface QandARepository extends JpaRepository<QandA, Long> {

	List<QandA> findByLecture(Lecture lecture);

	List<QandA> findByAuthor(User author);

//	Optional<QandA> findByAuthorAndId(User author, Long Id);

	Optional<QandA> findById(Long qandaId);

}
