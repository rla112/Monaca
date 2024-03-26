package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.LectureCategory;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

//	// Lecture명으로 Lecture 찾기
//	Lecture findByLectureName(String lectureName);
	
	// Lecture명으로 lecture 찾기
	Optional<Lecture> findByLectureName(String lectureName);
	
	
	// LectureCategory로 Lecture 찾기
	List<Lecture> findByLectureCategory(LectureCategory lectureCategories);

	// Professor로 Lecture 찾기
	List<Lecture> findByProfessor(User professors);

	// price 기준으로 0원보다 높은 유료 Lecture List 찾기
	List<Lecture> findByPriceGreaterThan(int price);
   
	// price가 0원인 무료 Lecture List 찾기
	List<Lecture> findByPrice(int price);

	// 강의 카테고리를 인자로 받아, 해당 카테고리에서 강의를 진행하는 교수들의 리스트를 반환. 여기서 'DISTINCT' 키워드는 중복된 교수를 제거하는 역할
	@Query("SELECT DISTINCT l.professor FROM Lecture l WHERE l.lectureCategory = :category")
	List<User> findDistinctProfessorsByLectureCategory(@Param("category") LectureCategory category);



	//	@EntityGraph(attributePaths = "users")
//	Optional<Lecture> findOneWithLecturesByUsername(String username);
//
//	@EntityGraph(attributePaths = "wishlist")
//	Optional<Lecture> findOneWithWishlistByUsername(String username);


	
	
}
