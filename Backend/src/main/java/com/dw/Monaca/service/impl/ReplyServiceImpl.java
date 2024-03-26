package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ReplyDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.QandA;
import com.dw.Monaca.model.Reply;
import com.dw.Monaca.repository.QandARepository;
import com.dw.Monaca.repository.ReplyRepository;
import com.dw.Monaca.service.ReplyService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {
	private final ReplyRepository replyRepository;
	private final QandARepository qandaRepository;
	private final UserRepository userRepository;

	@Autowired
	public ReplyServiceImpl(ReplyRepository replyRepository, QandARepository qandaRepository,
			UserRepository userRepository) {
		this.replyRepository = replyRepository;
		this.qandaRepository = qandaRepository;
		this.userRepository = userRepository;
	}


	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	
	// 모든 Reply 불러오기
	public ResponseDto<List<Reply>> getAllReply() {
		List<Reply> replies = replyRepository.findAll();
		if (replies.isEmpty()) {
			throw new InvalidRequestException("Reply Empty", "답변이 존재하지 않습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), replies, ResultCode.SUCCESS.getMsg());
	}

	// QandA별 Reply( Q&A 답변 ) 불러오기
	public ResponseDto<List<Reply>> getAllReplyByQandA(Long qandaId){
	    Optional<QandA> qandaOptional = qandaRepository.findById(qandaId);

	    QandA qanda = qandaOptional.orElseThrow(() ->
	            new InvalidRequestException("QandA Not Found", "해당 ID에 대한 질의응답이 존재하지 않습니다."));

	    List<Reply> replies = replyRepository.findByQandA(qanda);

	    if (replies.isEmpty()) {
	        throw new InvalidRequestException("Reply Empty", "답변이 존재하지 않습니다.");
	    }

	    return new ResponseDto<List<Reply>>(ResultCode.SUCCESS.name(), replies, ResultCode.SUCCESS.getMsg());
	}

	// Reply( Q&A 답변 ) 작성
	@Override
	public ResponseDto<Reply> createReply(ReplyDto replyDto, Long qandaId) {
		User author = getAuthenticatedUser();
		
		if (author == null) {
			throw new InvalidRequestException("Invalid User", "글쓰기 권한이 없습니다.");
		}
		
		   // QandA 객체 찾기
	    QandA qanda = qandaRepository.findById(qandaId)
	            .orElseThrow(() -> new InvalidRequestException("QandA Not Found", "해당 ID에 대한 질의응답이 존재하지 않습니다."));
		
	    // Repy객체 생성
		Reply replied = new Reply();
		replied.setCreateAt(LocalDateTime.now());
	    replied.setQandA(qanda);
		replied.setTitle(replyDto.getTitle());
		replied.setText(replyDto.getText());
		replied.setReplyAuthor(author);
		
		// QandA  상태 변경
		qanda.markAsRead();  // 'newStatus'를 false로 설정
		qandaRepository.save(qanda);

		
		replyRepository.save(replied);
		return new ResponseDto<>(ResultCode.SUCCESS.name(),null, "답변등록이 완료되었습니다.");
	
	}

//	// Reply( Q&A 답변 ) 수정
//	@Override
//	public ResponseDto<Reply> updateReply(Reply updateReply, Long Id){
//		Optional<Reply> replyOptional = replyRepository.findById(Id);
//		
//		String currentLoginId = SecurityUtil.getCurrentLoginId().orElseThrow(() -> 
//        new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
//		User author = userRepository.findByLoginId(currentLoginId);
//		
//		 if (replyOptional.isEmpty()) {
//		        throw new InvalidRequestException("Reply Empty", "답변이 존재하지 않습니다.");
//		    }
//		 
//		 Reply reply = replyOptional.get();
//		    if (!author.equals(reply.getReplyAuthor())) {
//		        throw new InvalidRequestException("Unauthorized", "Reply 작성자가 현재 로그인 한 작성자와 일치하지 않습니다.");
//		    }
//		    
//		 reply.setTitle(updateReply.getTitle());
//		 reply.setText(updateReply.getText());
//		 
//		 Reply savedReply = replyRepository.save(reply);
//		    
//		return new ResponseDto<>(ResultCode.SUCCESS.name(),savedReply,"답변이 성공적으로 수정되었습니다.");
//		
//	}
	
	// Reply( Q&A 답변 ) 삭제
	@Override
	public ResponseDto<Long> deleteReply(Long id) {
		 User author = getAuthenticatedUser();
		 Optional<Reply> replyOptional = replyRepository.findById(id);
		 
		 if (replyOptional.isEmpty()) {
		        throw new InvalidRequestException("Reply Empty", "답변이 존재하지 않습니다.");
		    }
		 
		 Reply reply = replyOptional.get();
		    if (!author.equals(reply.getReplyAuthor())) {
		        throw new InvalidRequestException("Unauthorized", "Reply 작성자가 현재 로그인 한 작성자와 일치하지 않습니다.");
		    }
		 
		 replyRepository.deleteById(id);
		 
		return new ResponseDto<>(ResultCode.SUCCESS.name(),id,"답변 삭제가 완료되었습니다.");
	}
	
	
	// Reply가 생성되고 7일이상 지난 Reply는 NewStatus(flase)로 변경(New 상태표시 해제)
	@Scheduled(cron = "0 0 0 * * ?")  // 매일 자정에 실행
	@Override
	public ResponseDto<String> updateStatusReplies() {
	    LocalDateTime threshold = LocalDateTime.now().minusDays(7);

	    List<Reply> oldReplies = replyRepository.findAllByCreateAtBeforeAndNewStatusIsTrue(threshold);

	    if (oldReplies.isEmpty()) {
	        throw new InvalidRequestException("Reply Empty", "7일 이상 지난 Reply가 존재하지 않습니다.");
	    }

	    for (Reply reply : oldReplies) {
	        reply.setNewStatus(false);
	        replyRepository.save(reply);
	    }

	    return new ResponseDto<>("SUCCESS", null, "7일 이상 지난 Reply의 newStatus가 변경되었습니다.");
	}
}