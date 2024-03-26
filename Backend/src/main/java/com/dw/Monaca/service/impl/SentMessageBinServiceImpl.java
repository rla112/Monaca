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
import com.dw.Monaca.model.SentMessage;
import com.dw.Monaca.model.SentMessageBin;
import com.dw.Monaca.repository.SentMessageBinRepository;
import com.dw.Monaca.repository.SentMessageRepository;
import com.dw.Monaca.service.SentMessageBinService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SentMessageBinServiceImpl implements SentMessageBinService{

	private final SentMessageBinRepository sentMessageBinRepository;
	private final SentMessageRepository sentMessageRepository;
	private final UserRepository userRepository;
	
	
	@Autowired
	public SentMessageBinServiceImpl(SentMessageBinRepository sentMessageBinRepository,
			SentMessageRepository sentMessageRepository, UserRepository userRepository) {
		super();
		this.sentMessageBinRepository = sentMessageBinRepository;
		this.sentMessageRepository = sentMessageRepository;
		this.userRepository = userRepository;
	}


	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	
	// 현재 로그인한 유저의 SentMessageBin 목록 조회
	@Override
	public ResponseDto<List<SentMessageBin>> getSentMessageBins() {
		List<SentMessageBin> sentMessageBins = sentMessageBinRepository.findAll();

		User sender = getAuthenticatedUser();
		
		if (sentMessageBins.isEmpty()) {
			throw new InvalidRequestException("SentMessageBin Empty", "삭제한 쪽지가 존재하지 않습니다.");
		}

//		List<SentMessageBin> currentUserSentMessageBins = new ArrayList<>();
//		for (SentMessageBin sentMessageBin : sentMessageBins) {
//			if (sentMessageBin.getSentMessage().getSender().getLoginId().equals(currentLoginId)) {
//				currentUserSentMessageBins.add(sentMessageBin);
//			}
//	    	else{ 
//	    		 //!sentMessagebin.getSentMessage().getSender.getLoginId().equals(SecurityUtil.getCurrentLoginId().get()))
//				throw new InvalidRequestException("loginId", "접속하려는 유저 본인의 SentMessageBin이 아닙니다.");
//			}
//		}
		
		//  for loop에서 리스트의 모든 요소를 순회하며 검사하며 InvalidRequestException을 발생시키는 것보다 Stream API를 이용하여
		// 코드를 간결화 시키고 필터링된 리스트를 만듦 
		List<SentMessageBin> currentUserSentMessageBins = sentMessageBins.stream()
			    .filter(bin -> bin.getSentMessage().getSender().getLoginId().equals(sender))
			    .collect(Collectors.toList());

		if (currentUserSentMessageBins.isEmpty()) {
			throw new InvalidRequestException("No SentMessageBin for Current User", "현재 유저의 SentMessageBin이 비어있습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), currentUserSentMessageBins, ResultCode.SUCCESS.getMsg());
	}


	// 현재 로그인한 유저의 특정 SentMessageBin ID로 SentMessageBin 조회
	@Override
	public ResponseDto<SentMessageBin> getSentMessageBinBySentMessageBinId(Long id) {
		 Optional<SentMessageBin> sentMessageBinOptional = sentMessageBinRepository.findByid(id);
		    String currentLoginId = SecurityUtil.getCurrentLoginId().get();

		    if(sentMessageBinOptional.isPresent()) {
		        SentMessageBin sentMessageBin = sentMessageBinOptional.get();

		        // 보낸 사람이 현재 로그인한 사용자인지 확인
		        if (!sentMessageBin.getSentMessage().getSender().getLoginId().equals(currentLoginId)) {
		            throw new InvalidRequestException("Unauthorized", "해당 SentMessageBin에 대한 권한이 없습니다.");
		        }

		        return new ResponseDto<>(ResultCode.SUCCESS.name(), sentMessageBin, ResultCode.SUCCESS.getMsg());
		    } else {
		        throw new InvalidRequestException("SentMessageBin Not Found", "해당 ID에 대한 SentMessageBin이 존재하지 않습니다.");
		    }
	}

	
	// 현재 로그인한 유저의 SentMessageBin 전체 복구
	@Override
	public ResponseDto<String> restoreAllSentMessageBins() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId().get();
	    User sender = getAuthenticatedUser();
	    List<SentMessageBin> sentMessageBins = sentMessageBinRepository.findBySentMessageSender(sender);

	    if (sentMessageBins.isEmpty()) {
	        throw new InvalidRequestException("SentMessageBin Empty", "해당 SentMessageBin이 존재하지 않습니다.");
	    }

	    for (SentMessageBin sentMessageBin : sentMessageBins) {
	        SentMessage sentMessage = sentMessageBin.getSentMessage();

	        // 보낸 사람이 현재 로그인한 사용자인지 확인
	        if (!sentMessage.getSender().getLoginId().equals(currentLoginId)) {
	            throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
	        }

	        // is_hide를 false로 설정
	        sentMessage.setIs_hide(false);
	        sentMessageRepository.save(sentMessage);

	        // SentMessageBin에서 삭제
	        sentMessageBinRepository.delete(sentMessageBin);
	    }

	    return new ResponseDto<>("SUCCESS", null, "모든 쪽지가 복구되었습니다.");
	}

	
	// 현재 로그인한 유저의 SentMessageBin ID로 특정 SentMessageBin 복구
	@Override
	public ResponseDto<String> restoreSentMessageBinBySentMessageBinId(Long id) {
	    String currentLoginId = SecurityUtil.getCurrentLoginId().get();
	    Optional<SentMessageBin> sentMessageBinOptional = sentMessageBinRepository.findById(id);

	    if (sentMessageBinOptional.isEmpty()) {
	        throw new InvalidRequestException("SentMessageBin Empty", "해당 SentMessageBin이 존재하지 않습니다.");
	    }
	    
	    SentMessageBin sentMessageBin = sentMessageBinOptional.get();
	    SentMessage sentMessage = sentMessageBin.getSentMessage();

	    // 보낸 사람이 현재 로그인한 사용자인지 확인
	    if (!sentMessage.getSender().getLoginId().equals(currentLoginId)) {
	        throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
	    }
	    
	    // is_hide를 false로 설정
	    sentMessage.setIs_hide(false);
	    sentMessageRepository.save(sentMessage);

	    // SentMessageBin에서 삭제
	    sentMessageBinRepository.deleteById(id);

	    return new ResponseDto<>("SUCCESS", null, "선택한 쪽지가 복구되었습니다.");
	}


	// 현재 로그인한 유저의 SentMessageBin 전체 삭제
	@Override
	public ResponseDto<String> deleteAllSentMessageBins() {
	    User sender = getAuthenticatedUser();
	    List<SentMessageBin> sentMessageBins = sentMessageBinRepository.findBySentMessageSender(sender);

		    if (sentMessageBins.isEmpty()) {
		        throw new InvalidRequestException("SentMessageBin Empty", "해당 SentMessageBin이 존재하지 않습니다.");
		    }

		    for (SentMessageBin sentMessageBin : sentMessageBins) {
		        SentMessage sentMessage = sentMessageBin.getSentMessage();

		        // 보낸 사람이 현재 로그인한 사용자인지 확인
		        if (!sentMessage.getSender().getLoginId().equals(sender)) {
		            throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
		        }

		        // sentMessage에서 삭제
		        sentMessageRepository.delete(sentMessage);

		        // SentMessageBin에서 삭제
		        sentMessageBinRepository.delete(sentMessageBin);
		    }

		    return new ResponseDto<>("SUCCESS", null, "모든 쪽지가 삭제되었습니다.");
	}

	
	// 현재 로그인한 유저의 SentMessageBin ID로 특정 SentMessageBin 삭제
	@Override
	public ResponseDto<String> deleteSentMessageBinBySentMessageBinId(Long id) {
		 String currentLoginId = SecurityUtil.getCurrentLoginId().get();
		    Optional<SentMessageBin> sentMessageBinOptional = sentMessageBinRepository.findById(id);

		    if (sentMessageBinOptional.isEmpty()) {
		        throw new InvalidRequestException("SentMessageBin Empty", "해당 SentMessageBin이 존재하지 않습니다.");
		    }
		    
		    SentMessageBin sentMessageBin = sentMessageBinOptional.get();
		    SentMessage sentMessage = sentMessageBin.getSentMessage();

		    // 보낸 사람이 현재 로그인한 사용자인지 확인
		    if (!sentMessage.getSender().getLoginId().equals(currentLoginId)) {
		        throw new InvalidRequestException("Unauthorized", "해당 쪽지에 대한 권한이 없습니다.");
		    }
		    
		    sentMessageRepository.delete(sentMessage);

		    // SentMessageBin에서 삭제
		    sentMessageBinRepository.deleteById(id);

		    return new ResponseDto<>("SUCCESS", null, "선택한 쪽지가 삭제되었습니다.");
	}

	
	// 일정기간(30일)이 지난 SentMessage를 조회하여 삭제
	// 시스템 자체에서 자동적으로 주기적으로 실행되는 배치 작업으로 처리( @Scheduled 사용)
	@Scheduled(cron = "0 0 0 * * ?")  // 매일 자정에 실행
	@Override
	public ResponseDto<String> deleteSentMessageBinOlderThan30Days() {
	    // LocalDateTime.now().minusDays(30)를 통해 현재 시간에서 30일 이전의 시간을 계산 
		LocalDateTime threshold = LocalDateTime.now().minusDays(30);

		// sentMessageBinRepository.findAllByCreateAtBefore(threshold);를 통해 30일 이상 지난 SentMessageBin을 찾아옴
	    List<SentMessageBin> oldMessageBins = sentMessageBinRepository.findAllByCreateAtBefore(threshold);

	    if (oldMessageBins.isEmpty()) {
	        throw new InvalidRequestException("SentMessageBin Empty", "30일 이상 지난 SentMessageBin이 존재하지 않습니다.");
	    }

	    for (SentMessageBin messageBin : oldMessageBins) {
	        SentMessage sentMessage = messageBin.getSentMessage();
	        // SentMessageBin의 SentMessage를 sentMessageRepository.delete(sentMessage);를 통해 삭제
	        sentMessageRepository.delete(sentMessage);
	        // SentMessageBin 자체를 sentMessageBinRepository.delete(messageBin);를 통해 삭제
	        sentMessageBinRepository.delete(messageBin);
	    }

	    return new ResponseDto<>("SUCCESS", null, "30일 이상 지난 SentMessageBin이 삭제되었습니다.");
	}


}
