package com.dw.Monaca.jwtauthority.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Lecture;

public interface UserRepository extends JpaRepository<User, Long> {
	@EntityGraph(attributePaths = "authorities")
	Optional<User> findOneWithAuthoritiesByLoginId(String loginId);

	@EntityGraph(attributePaths = "authorities")
	List<User> findAll();
//	User findByLoginId(String login_Id);

	@EntityGraph(attributePaths = "lectures")
	Optional<User> findOneWithLecturesByLoginId(String loginId);

	@EntityGraph(attributePaths = "wishLecture")
	Optional<User> findOneWithWishlistByLoginId(String loginId);

	Optional<User> findByLoginId(String loginId);

	// 현재 로그인한 유저의 로그인 아이디로 user 정보 삭제
	void deleteByLoginId(String currentLoginId);

	// professor 이름으로 user 찾기
	Optional<User> findByName(String professorName);

	// ProfessorIntro와 ProfessorResumn이 Null이 아닌 유저들을 찾기
	List<User> findByProfessorIntroIsNotNullAndProfessorResumeIsNotNull();

	List<User> findByLectureContaining(Lecture lecture);

}
