package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.ClassRoom;
import com.dw.Monaca.model.Lecture;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

	List<ClassRoom> findByUser(User user);

	Optional<ClassRoom> findById(Long id);

	List<ClassRoom> findByLecture(Lecture lecture);

	boolean existsByUserAndLecture(User user, Lecture lecture);

	// 해당하는 강의가 포함된 classRoom의 갯수를 셈( 강의를 듣는 사용자의 수 )
	int countByLecture(Lecture lecture);

}
