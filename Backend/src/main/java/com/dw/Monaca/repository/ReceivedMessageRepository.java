package com.dw.Monaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.model.ReceivedMessage;


public interface ReceivedMessageRepository extends JpaRepository<ReceivedMessage, Long>{


	// 현재 로그인한 유저(currentLoginId) = User receiver = receivedMessage.getReceiver(receiver) 이용해
	// 현재 로그인한 유저의 객체로 ReceivedMessage List를 불러온다.
	List<ReceivedMessage> findByReceiver(User receiver);

	// receivedMessageId를 이용해  receivedMessage 찾기
	Optional<ReceivedMessage> findById(Long id);


	


}
