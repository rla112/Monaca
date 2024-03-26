package com.dw.Monaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.LectureCart;

public interface LectureCartRepository extends JpaRepository<LectureCart, Long> {

	List<LectureCart> findByUser(User user);
	
	boolean existsByUserAndLecture(User user, Lecture lecture);

}
