package com.dw.Monaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findByReceiver(User receiver);
	List<Message> findBySender(User sender);
}
