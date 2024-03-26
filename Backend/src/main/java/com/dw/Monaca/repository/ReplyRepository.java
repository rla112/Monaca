package com.dw.Monaca.repository;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.model.QandA;
import com.dw.Monaca.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

	List<Reply> findByQandA(QandA qanda);
	
	List<Reply> findAllByQandAId(Long qandaId);

	
	List<Reply> findAllByCreateAtBeforeAndNewStatusIsTrue(LocalDateTime threshold);

}
