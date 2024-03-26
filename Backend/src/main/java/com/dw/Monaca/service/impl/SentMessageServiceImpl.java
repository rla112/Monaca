package com.dw.Monaca.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.dto.SentMessageDto;
import com.dw.Monaca.dto.SentMessageListDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.SentMessageBin;
import com.dw.Monaca.model.SentMessage;
import com.dw.Monaca.repository.SentMessageBinRepository;
import com.dw.Monaca.repository.SentMessageRepository;
import com.dw.Monaca.service.SentMessageService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SentMessageServiceImpl implements SentMessageService{

	private final SentMessageRepository sentMessageRepository;
	private final SentMessageBinRepository sentMessageBinRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public SentMessageServiceImpl(SentMessageRepository sentMessageRepository,
			SentMessageBinRepository sentMessageBinRepository, UserRepository userRepository) {
		super();
		this.sentMessageRepository = sentMessageRepository;
		this.sentMessageBinRepository = sentMessageBinRepository;
		this.userRepository = userRepository;
	}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	// 현재 로그인한 유저가 보낸 쪽지 목록 조회
	@Override
	public ResponseDto<List<SentMessageListDto>> getSentMessages() {
	    User sender = getAuthenticatedUser();
	    List<SentMessage> sentMessages = sentMessageRepository.findBySender(sender);

	    if (sentMessages.isEmpty()) {
	        throw new InvalidRequestException("SentMessage Empty", "SentMessage가 존재하지 않습니다.");
	    }
	    
	    List<SentMessageListDto> sentMessageListDtos = new ArrayList<>();
	    sentMessages.stream().forEach(data -> {
	    SentMessageListDto sentMessageListDto = new SentMessageListDto();
	    sentMessageListDto.setSender(data.getSender().getLoginId());
	    sentMessageListDto.setReceiver(data.getReceiver().getLoginId());
	    sentMessageListDto.setTitle(data.getTitle());
	    sentMessageListDto.setCreateAt(data.getCreateAt().toString());
	    sentMessageListDto.setIs_hide(data.isIs_hide());
	    sentMessageListDtos.add(sentMessageListDto);
	    });

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), sentMessageListDtos, ResultCode.SUCCESS.getMsg());
	}


	// 현재 로그인한 유저가 보낸 특정 쪽지 ID로 쪽지 조회
	@Override
	public ResponseDto<SentMessageDto> getSentMessageById(Long id) {
	    Optional<SentMessage> sentMessageOptional = sentMessageRepository.findById(id);
	    User sender = getAuthenticatedUser();
	    if(sentMessageOptional.isEmpty()) {
	    	throw new InvalidRequestException("SentMessage Not Found", "해당 ID에 대한 SentMessage가 존재하지 않습니다.");
	    }
	    
	    SentMessage sentMessage = sentMessageOptional.get();

	        // 보낸 사람이 현재 로그인한 사용자인지 확인
	        if (!sentMessage.getSender().equals(sender)) {
	            throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
	        }

	        SentMessageDto sentMessageDto = new SentMessageDto();
	        sentMessageDto.setSender(sentMessage.getSender().getLoginId());
	        sentMessageDto.setReceiver(sentMessage.getReceiver().getLoginId());
	        sentMessageDto.setTitle(sentMessage.getTitle());
	        sentMessageDto.setText(sentMessage.getText());
	        sentMessageDto.setCreateAt(sentMessage.getCreateAt().toString());
	        sentMessageDto.setIs_hide(sentMessage.isIs_hide());
	        
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), sentMessageDto, ResultCode.SUCCESS.getMsg());

	}

	// 현재 로그인한 유저가 보낸 모든 쪽지 삭제
	@Override
	public ResponseDto<String> deleteAllSentMessages() {
	    User sender = getAuthenticatedUser();
	    List<SentMessage> sentMessages = sentMessageRepository.findBySender(sender);


	    if (sentMessages.isEmpty()) {
	        throw new InvalidRequestException("SentMessage Empty", "해당 쪽지가 존재하지 않습니다.");
	    }

	    for (SentMessage sentMessage : sentMessages) {
	    	if (!sentMessage.isIs_hide()) {
				sentMessage.setIs_hide(true);
				sentMessageRepository.save(sentMessage);

				SentMessageBin sentMessageBin = new SentMessageBin();
				sentMessageBin.setSentMessage(sentMessage);
				sentMessageBinRepository.save(sentMessageBin);

			} else {
				throw new InvalidRequestException("is_hide", "이미 삭제된 쪽지 입니다.");
			}
	    }
		return new ResponseDto<>("SUCCESS", null, "해당 쪽지가 삭제되었습니다.");
	}



	// 현재 로그인한 유저가 보낸 특정 쪽지 삭제
	@Override
	public ResponseDto<String> deleteSentMessageBySentMessageId(Long id) {
	    Optional<SentMessage> optionalSentMessage = sentMessageRepository.findById(id);
	    User sender = getAuthenticatedUser();
	    if (optionalSentMessage.isEmpty()) {
	        throw new InvalidRequestException(Long.toString(id), "해당 쪽지가 존재하지 않습니다.");
	    }
	    SentMessage sentMessage = optionalSentMessage.get();

	    if (!sentMessage.getSender().equals(sender)) {
	        throw new InvalidRequestException("loginId", "삭제하려는 유저의 ID와 쪽지를 쓴 유저가 일치하지 않습니다.");
	    } else {
	        if (!sentMessage.isIs_hide()) {
	            sentMessage.setIs_hide(true);
	            sentMessageRepository.save(sentMessage);

	            SentMessageBin sentMessageBin = new SentMessageBin();
	            sentMessageBin.setSentMessage(sentMessage);
	            sentMessageBinRepository.save(sentMessageBin);

	        } else {
	            throw new InvalidRequestException("is_hide", "이미 삭제된 쪽지 입니다.");
	        }

	        return new ResponseDto<>("SUCCESS", null, "해당 쪽지가 삭제되었습니다.");
	    }
	}

}
