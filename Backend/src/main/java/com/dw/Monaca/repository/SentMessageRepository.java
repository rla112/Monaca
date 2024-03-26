package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.SentMessage;

public interface SentMessageRepository extends JpaRepository<SentMessage, Long>{

	// 현재 로그인한 유저(currentLoginId) = User sender = sentMessage.getSender(sender) 이용해
	// 현재 로그인한 유저의 객체로 SentMessage List를 불러온다.
	List<SentMessage> findBySender(User sender);

	// sentMessageId를 이용해 sentMessage 찾기
	Optional<SentMessage> findById(Long id);




}
