package com.dw.Monaca.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.ReceivedMessageBin;

public interface ReceivedMessageBinRepository extends JpaRepository<ReceivedMessageBin, Long> {

	Optional<ReceivedMessageBin> findByid(Long id);

	List<ReceivedMessageBin> findByReceivedMessageReceiver(User receiver);
	
	List<ReceivedMessageBin> findAllByCreateAtBefore(LocalDateTime threshold);

}
