package com.dw.Monaca.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.SentMessageBin;

public interface SentMessageBinRepository extends JpaRepository<SentMessageBin, Long>{

	Optional<SentMessageBin> findByid(Long id);

	List<SentMessageBin> findBySentMessageSender(User sender);

	List<SentMessageBin> findAllByCreateAtBefore(LocalDateTime threshold);


}
