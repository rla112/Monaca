package com.dw.Monaca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.MessageBin;
import com.dw.Monaca.repository.MessageBinRepository;
import com.dw.Monaca.service.MessageBinService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MessageBinServiceImpl implements MessageBinService {

	private final MessageBinRepository messageBinRepository;

	@Autowired
	public MessageBinServiceImpl(MessageBinRepository messageBinRepository) {
		this.messageBinRepository = messageBinRepository;
	}

	public ResponseDto<List<MessageBin>> getAllMessageBin() {

		String currentLoginId = SecurityUtil.getCurrentLoginId().get(); // Get current user's login ID
		List<MessageBin> allMessageBins = messageBinRepository.findAll();

		if (allMessageBins.isEmpty()) {
			throw new InvalidRequestException("MessageBin Empty", "삭제한 쪽지가 존재하지 않습니다.");
		}

		List<MessageBin> currentUserMessageBins = new ArrayList<>();
		for (MessageBin messageBin : allMessageBins) {
			if (messageBin.getMessage().getSender().getLoginId().equals(currentLoginId)) {
				currentUserMessageBins.add(messageBin);
			}
//	    	else{ 
//	    		// !messagebin.getMessage().getUser.getLoginId().equals(SecurityUtil.getCurrentLoginId().get()))
//				throw new InvalidRequestException("loginId", "접속하려는 유저 본인의 쪽지함이 아닙니다.");
//			}
		}
		if (currentUserMessageBins.isEmpty()) {
			throw new InvalidRequestException("No MessageBin for Current User", "현재 유저의 쪽지함이 비어있습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), currentUserMessageBins, ResultCode.SUCCESS.getMsg());
	}

}