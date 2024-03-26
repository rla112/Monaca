package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.LectureHistory;

public interface LectureHistoryRepository extends JpaRepository<LectureHistory, Long> {

	List<LectureHistory> findByUser(User user);

	Optional<LectureHistory> findByLecture(Lecture lecture);

}
