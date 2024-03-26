package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.MessageDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.ReceivedMessage;
import com.dw.Monaca.model.SentMessage;
import com.dw.Monaca.repository.ReceivedMessageRepository;
import com.dw.Monaca.repository.SentMessageRepository;
import com.dw.Monaca.service.MessageService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	    private final UserRepository userRepository;
	    private final SentMessageRepository sentMessageRepository;
	    private final ReceivedMessageRepository receivedMessageRepository;
	    
	    @Autowired
	    public MessageServiceImpl(UserRepository userRepository,
	                              SentMessageRepository sentMessageRepository, 
	                              ReceivedMessageRepository receivedMessageRepository) {
	        this.userRepository = userRepository;
	        this.sentMessageRepository = sentMessageRepository;
	        this.receivedMessageRepository = receivedMessageRepository;
	    }
	    
	    private User getAuthenticatedUser() {
		    String currentLoginId = SecurityUtil.getCurrentLoginId()
		        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
		    return userRepository.findByLoginId(currentLoginId)
		        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
		}
	    

	
	   // 전송함
	   // 특정 수신자에게 쪽지 전송
	    @Override
	    public ResponseDto<Void> sendMessage(MessageDto messageDto) {
	    	
	    	// 현재 로그인한 사용자의 loginId 가져오기
	        User sender = getAuthenticatedUser();
	        Optional<User> receiverOptional = userRepository.findByLoginId(messageDto.getReceiver());

	        User receiver = receiverOptional.get();
	        
	    if (sender == null) {
	             throw new InvalidRequestException("Invalid User", "쪽지 작성 권한이 없습니다.");
	     }
	    // 먼저 SentMessage 객체를 생성하고 저장
	    SentMessage sentMessage = new SentMessage();
        sentMessage.setCreateAt(LocalDateTime.now());
        sentMessage.setSender(sender);
        sentMessage.setReceiver(receiver);
        sentMessage.setText(messageDto.getText());
        sentMessage.setTitle(messageDto.getTitle());

        sentMessageRepository.save(sentMessage); // sentMessageRepository에 저장

		
		// 같은 내용의 ReceivedMessage 객체를 생성하고 저장
        ReceivedMessage receivedMessage = new ReceivedMessage(sentMessage);
        receivedMessage.setReceiver(receiver); // ReceivedMessage의 receiver를 다시 설정하는 코드임..
        //만약 SentMessage의 receiver와 ReceivedMessage의 receiver가 항상 동일하다면, 필요 없는 코드.. 삭제할지 고민
        receivedMessageRepository.save(receivedMessage); // receiver의 receivedMessageRepository에 저장
		
		 return new ResponseDto<>(ResultCode.SUCCESS.name(), null, "쪽지 전송이 완료되었습니다.");
	}

	}
