package com.dw.Monaca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.Message;
import com.dw.Monaca.model.ReceivedMessage;
import com.dw.Monaca.model.ReceivedMessageBin;
import com.dw.Monaca.repository.MessageRepository;
import com.dw.Monaca.repository.ReceivedMessageBinRepository;
import com.dw.Monaca.repository.ReceivedMessageRepository;
import com.dw.Monaca.service.ReceivedMessageService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReceivedMessageServiceImpl implements ReceivedMessageService{
	
	private final ReceivedMessageRepository receivedMessageRepository;
	private final ReceivedMessageBinRepository receivedMessageBinRepository;
	private final UserRepository userRepository;
	private final MessageRepository messageRepository;
	
	@Autowired
	public ReceivedMessageServiceImpl(ReceivedMessageRepository receivedMessageRepository,
			ReceivedMessageBinRepository receivedMessageBinRepository, UserRepository userRepository,
			MessageRepository messageRepository) {
		super();
		this.receivedMessageRepository = receivedMessageRepository;
		this.receivedMessageBinRepository = receivedMessageBinRepository;
		this.userRepository = userRepository;
		this.messageRepository = messageRepository;
	}


	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	
	// 현재 로그인한 유저가 받은 쪽지 목록 조회
	@Override
	public ResponseDto<List<ReceivedMessage>> getReceivedMessages() {
		User receiver = getAuthenticatedUser();
		List<ReceivedMessage> receivedMessages = receivedMessageRepository.findByReceiver(receiver);

		    if (receivedMessages.isEmpty()) {
		        throw new InvalidRequestException("ReceivedMessage Empty", "ReceivedMessage가 존재하지 않습니다.");
		    }

		    return new ResponseDto<>(ResultCode.SUCCESS.name(), receivedMessages, ResultCode.SUCCESS.getMsg());
	}

	// 현재 로그인한 유저가 받은 특정 쪽지 ID로 쪽지 조회
	@Override
	public ResponseDto<ReceivedMessage> getReceivedMessageById(Long id) {
	    Optional<ReceivedMessage> receivedMessageOptional = receivedMessageRepository.findById(id);
	    User receiver = getAuthenticatedUser();

	    if(receivedMessageOptional.isPresent()) {
	        ReceivedMessage receivedMessage = receivedMessageOptional.get();

	        // 보낸 사람이 현재 로그인한 사용자인지 확인
	        if (!receivedMessage.getReceiver().equals(receiver)) {
	            throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
	        }

	        // 메시지를 읽었으므로 markAsRead메소드를 이용해'readStatus' 필드를 true로 변경
	        Message message = receivedMessage.getMessage();
	        message.markAsRead();
	        messageRepository.save(message);

	        return new ResponseDto<>(ResultCode.SUCCESS.name(), receivedMessage, ResultCode.SUCCESS.getMsg());
	    } else {
	        throw new InvalidRequestException("ReceivedMessage Not Found", "해당 ID에 대한 ReceivedMessage가 존재하지 않습니다.");
	    }
	}

	// 현재 로그인한 유저가 받은 모든 쪽지 삭제
	@Override
	public ResponseDto<List<ReceivedMessage>> deleteAllReceivedMessages() {
		 User receiver = getAuthenticatedUser();
		 List<ReceivedMessage> receivedMessages = receivedMessageRepository.findByReceiver(receiver);


	    if (receivedMessages.isEmpty()) {
	        throw new InvalidRequestException("ReceivedMessage Empty", "해당 쪽지가 존재하지 않습니다.");
	    }

	    for (ReceivedMessage receivedMessage : receivedMessages) {
	    	if (!receivedMessage.isIs_hide()) {
				receivedMessage.setIs_hide(true);
				receivedMessageRepository.save(receivedMessage);

				ReceivedMessageBin receivedMessageBin = new ReceivedMessageBin();
				receivedMessageBin.setReceivedMessage(receivedMessage);
				receivedMessageBinRepository.save(receivedMessageBin);

			} else {
				throw new InvalidRequestException("is_hide", "이미 삭제된 쪽지 입니다.");
			}
	    }
		return new ResponseDto<>("SUCCESS", null, "해당 쪽지가 삭제되었습니다.");
	}

	// 현재 로그인한 유저가 받은 특정 쪽지 삭제
	@Override
	public ResponseDto<ReceivedMessage> deleteReceivedMessageByReceivedMessageId(Long id) {
		Optional<ReceivedMessage> optionalReceivedMessage = receivedMessageRepository.findById(id);
		 User receiver = getAuthenticatedUser();

		if (optionalReceivedMessage.isEmpty()) {
			throw new InvalidRequestException(Long.toString(id), "해당 쪽지가 존재하지 않습니다.");
		}
		ReceivedMessage message = optionalReceivedMessage.get();

		if (!message.getReceiver().equals(receiver)) {
			throw new InvalidRequestException("loginId", "삭제하려는 유저와 쪽지를 받은 유저가 일치하지 않습니다.");

		} else {
			if (!message.isIs_hide()) {
				message.setIs_hide(true);
				receivedMessageRepository.save(message);

				ReceivedMessageBin receivedMessageBin = new ReceivedMessageBin();
				receivedMessageBin.setReceivedMessage(message);
				receivedMessageBinRepository.save(receivedMessageBin);

			} else {
				throw new InvalidRequestException("is_hide", "이미 삭제된 쪽지 입니다.");
			}

			return new ResponseDto<>("SUCCESS", null, "해당 쪽지가 삭제되었습니다.");
		}
	}

}
