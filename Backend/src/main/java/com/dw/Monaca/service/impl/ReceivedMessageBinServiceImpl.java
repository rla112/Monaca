package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.ReceivedMessage;
import com.dw.Monaca.model.ReceivedMessageBin;
import com.dw.Monaca.repository.ReceivedMessageBinRepository;
import com.dw.Monaca.repository.ReceivedMessageRepository;
import com.dw.Monaca.service.ReceivedMessageBinService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReceivedMessageBinServiceImpl implements ReceivedMessageBinService{

	private final ReceivedMessageBinRepository receivedMessageBinRepository;
	private final ReceivedMessageRepository receivedMessageRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public ReceivedMessageBinServiceImpl(ReceivedMessageBinRepository receivedMessageBinRepository,
			ReceivedMessageRepository receivedMessageRepository, UserRepository userRepository) {
		super();
		this.receivedMessageBinRepository = receivedMessageBinRepository;
		this.receivedMessageRepository = receivedMessageRepository;
		this.userRepository = userRepository;
	}

	
	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	// 현재 로그인한 유저의 ReceivedMessageBin 목록 조회
	@Override
	public ResponseDto<List<ReceivedMessageBin>> getReceivedMessageBins() {
		User receiver = getAuthenticatedUser();
		List<ReceivedMessageBin> receivedMessageBins = receivedMessageBinRepository.findAll();

		if (receivedMessageBins.isEmpty()) {
			throw new InvalidRequestException("ReceivedMessageBin Empty", "삭제한 쪽지가 존재하지 않습니다.");
		}
		//for문으로 안에서 일일이 대조하는 대신 Stream API를 이용하여 코드를 간결화 시키고 필터링된 리스트를 만듦 
		//for문으로 안에서 일일이 대조하는 대신 Stream API를 이용하여 코드를 간결화 시키고 필터링된 리스트를 만듦 
		List<ReceivedMessageBin> currentUserReceivedMessageBins = receivedMessageBins.stream()
		    .filter(bin -> bin.getReceivedMessage().getReceiver().equals(receiver))
		    .collect(Collectors.toList());


		if (currentUserReceivedMessageBins.isEmpty()) {
			throw new InvalidRequestException("No ReceivedMessageBin for Current User", "현재 유저의 ReceivedMessageBin이 비어있습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), currentUserReceivedMessageBins, ResultCode.SUCCESS.getMsg());
	}


	// 현재 로그인한 유저의 특정 ReceivedMessageBin ID로 ReceivedMessageBin 조회
	@Override
	public ResponseDto<ReceivedMessageBin> getReceivedMessageBinByReceivedMessageBinId(Long id) {
		 Optional<ReceivedMessageBin> receivedMessageBinOptional = receivedMessageBinRepository.findByid(id);
		 User receiver = getAuthenticatedUser();

		    if(receivedMessageBinOptional.isPresent()) {
		        ReceivedMessageBin receivedMessageBin = receivedMessageBinOptional.get();

		        // 받는 사람이 현재 로그인한 사용자인지 확인
		        if (!receivedMessageBin.getReceivedMessage().getReceiver().equals(receiver)) {
		            throw new InvalidRequestException("Unauthorized", "해당 ReceivedMessageBin에 대한 권한이 없습니다.");
		        }

		        return new ResponseDto<>(ResultCode.SUCCESS.name(), receivedMessageBin, ResultCode.SUCCESS.getMsg());
		    } else {
		        throw new InvalidRequestException("ReceivedMessageBin Not Found", "해당 ID에 대한 ReceivedMessageBin이 존재하지 않습니다.");
		    }
	}

	// 현재 로그인한 유저의 ReceivedMessageBin 전체 복구
	@Override
	public ResponseDto<String> restoreAllReceivedMessageBins() {
		User receiver = getAuthenticatedUser();
		List<ReceivedMessageBin> receivedMessageBins = receivedMessageBinRepository.findByReceivedMessageReceiver(receiver);

		    if (receivedMessageBins.isEmpty()) {
		        throw new InvalidRequestException("ReceivedMessageBin Empty", "해당 ReceivedMessageBin이 존재하지 않습니다.");
		    }

		    for (ReceivedMessageBin receivedMessageBin : receivedMessageBins) {
		        ReceivedMessage receivedMessage = receivedMessageBin.getReceivedMessage();

		        // 받는 사람이 현재 로그인한 사용자인지 확인
		        if (!receivedMessage.getReceiver().equals(receiver)) {
		            throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
		        }

		        // is_hide를 false로 설정
		        receivedMessage.setIs_hide(false);
		        receivedMessageRepository.save(receivedMessage);

		        // ReceivedMessageBin에서 삭제
		        receivedMessageBinRepository.delete(receivedMessageBin);
		    }

		    return new ResponseDto<>("SUCCESS", null, "모든 쪽지가 복구되었습니다.");
	}

	// 현재 로그인한 유저의 ReceivedMessageBin ID로 특정 ReceivedMessageBin 복구
	@Override
	public ResponseDto<String> restoreReceivedMessageBinByReceivedMessageBinId(Long id) {
		User receiver = getAuthenticatedUser();
		    Optional<ReceivedMessageBin> receivedMessageBinOptional = receivedMessageBinRepository.findById(id);

		    if (receivedMessageBinOptional.isEmpty()) {
		        throw new InvalidRequestException("ReceivedMessageBin Empty", "해당 ReceivedMessageBin이 존재하지 않습니다.");
		    }
		    
		    ReceivedMessageBin receivedMessageBin = receivedMessageBinOptional.get();
		    ReceivedMessage receivedMessage = receivedMessageBin.getReceivedMessage();

		    // 받는 사람이 현재 로그인한 사용자인지 확인
		    if (!receivedMessage.getReceiver().equals(receiver)) {
		        throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
		    }
		    
		    // is_hide를 false로 설정
		    receivedMessage.setIs_hide(false);
		    receivedMessageRepository.save(receivedMessage);

		    // ReceivedMessageBin에서 삭제
		    receivedMessageBinRepository.deleteById(id);

		    return new ResponseDto<>("SUCCESS", null, "선택한 쪽지가 복구되었습니다.");
	}

	// 현재 로그인한 유저의 ReceivedMessageBin 전체 삭제
	@Override
	public ResponseDto<String> deleteAllReceivedMessageBins() {
		User receiver = getAuthenticatedUser();
		List<ReceivedMessageBin> receivedMessageBins = receivedMessageBinRepository.findByReceivedMessageReceiver(receiver);

	    if (receivedMessageBins.isEmpty()) {
	        throw new InvalidRequestException("ReceivedMessageBin Empty", "해당 ReceivedMessageBin이 존재하지 않습니다.");
	    }

	    for (ReceivedMessageBin receivedMessageBin : receivedMessageBins) {
	        ReceivedMessage receivedMessage = receivedMessageBin.getReceivedMessage();

	        // 받은 사람이 현재 로그인한 사용자인지 확인
	        if (!receivedMessage.getReceiver().equals(receiver)) {
	            throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
	        }

	        // receivedMessage에서 삭제
	        receivedMessageRepository.delete(receivedMessage);

	        // receivedMessageBin에서 삭제
	        receivedMessageBinRepository.delete(receivedMessageBin);
	    }

	    return new ResponseDto<>("SUCCESS", null, "모든 쪽지가 삭제되었습니다.");
	}

	// 현재 로그인한 유저의 ReceivedMessageBin ID로 특정 ReceivedMessageBin 삭제
	@Override
	public ResponseDto<String> deleteReceivedMessageBinByReceivedMessageBinId(Long id) {
		User receiver = getAuthenticatedUser();
		    Optional<ReceivedMessageBin> receivedMessageBinOptional = receivedMessageBinRepository.findById(id);

		    if (receivedMessageBinOptional.isEmpty()) {
		        throw new InvalidRequestException("ReceivedMessageBin Empty", "해당 ReceivedMessageBin이 존재하지 않습니다.");
		    }
		    
		    ReceivedMessageBin receivedMessageBin = receivedMessageBinOptional.get();
		    ReceivedMessage receivedMessage = receivedMessageBin.getReceivedMessage();

		    // 받은 사람이 현재 로그인한 사용자인지 확인
		    if (!receivedMessage.getReceiver().equals(receiver)) {
		        throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
		    }
		    
		    receivedMessageRepository.delete(receivedMessage);

		    // ReceivedMessageBin에서 삭제
		    receivedMessageBinRepository.deleteById(id);

		    return new ResponseDto<>("SUCCESS", null, "선택한 쪽지가 삭제되었습니다.");
	}

	// 일정기간(30일)이 지난 ReceivedMessage를 조회하여 삭제
	// 시스템 자체에서 자동적으로 주기적으로 실행되는 배치 작업으로 처리( @Scheduled 사용)
	@Scheduled(cron = "0 0 0 * * ?")  // 매일 자정에 실행
	@Override
	public ResponseDto<String> deleteReceivedMessageBinOlderThan30Days() {
		 // LocalDateTime.now().minusDays(30)를 통해 현재 시간에서 30일 이전의 시간을 계산 
		LocalDateTime threshold = LocalDateTime.now().minusDays(30);

		// receivedMessageBinRepository.findAllByCreateAtBefore(threshold);를 통해 30일 이상 지난 ReceivedMessageBin을 찾아옴
	    List<ReceivedMessageBin> oldMessageBins = receivedMessageBinRepository.findAllByCreateAtBefore(threshold);

	    if (oldMessageBins.isEmpty()) {
	        throw new InvalidRequestException("ReceivedMessageBin Empty", "30일 이상 지난 ReceivedMessageBin이 존재하지 않습니다.");
	    }

	    for (ReceivedMessageBin messageBin : oldMessageBins) {
	        ReceivedMessage receivedMessage = messageBin.getReceivedMessage();
	        // ReceivedMessageBin의 ReceivedMessage를 receivedMessageRepository.delete(receivedMessage);를 통해 삭제
	        receivedMessageRepository.delete(receivedMessage);
	        // ReceivedMessageBin 자체를 receivedMessageBinRepository.delete(messageBin);를 통해 삭제
	        receivedMessageBinRepository.delete(messageBin);
	    }

	    return new ResponseDto<>("SUCCESS", null, "30일 이상 지난 ReceivedMessageBin이 삭제되었습니다.");
	}


}
