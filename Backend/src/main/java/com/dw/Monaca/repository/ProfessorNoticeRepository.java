package com.dw.Monaca.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dw.Monaca.model.Lecture;
import com.dw.Monaca.model.ProfessorNotice;

public interface ProfessorNoticeRepository extends JpaRepository<ProfessorNotice, Long>{

	// Lecture를 이용해 관련된 ProfessorNotice를 찾는다.
	List<ProfessorNotice> findByLecture(Lecture lectures);

	List<ProfessorNotice> findAllByCreateAtBeforeAndNewStatusIsTrue(LocalDateTime threshold);

}
