package com.dw.Monaca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.AdminNoticeDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.AdminNotice;
import com.dw.Monaca.repository.AdminNoticeRepository;
import com.dw.Monaca.service.AdminNoticeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminNoticeServiceImpl implements AdminNoticeService{

	private final AdminNoticeRepository adminNoticeRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public AdminNoticeServiceImpl(AdminNoticeRepository adminNoticeRepository, UserRepository userRepository) {
		this.adminNoticeRepository = adminNoticeRepository;
		this.userRepository = userRepository;
	}
	
	
	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	
	// Notice 전체 조회
	@Override
	public ResponseDto<List<AdminNotice>> getAllAdminNotice() {
		List<AdminNotice> adminNotice = adminNoticeRepository.findAll();
		if (adminNotice.isEmpty()) {
			throw new InvalidRequestException("AdminNotice Empty", "AdminNotice가 존재하지 않습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(), adminNotice, ResultCode.SUCCESS.getMsg());
	}
	
	// Notice ID로 조회
	public ResponseDto<AdminNotice> getAdminNoticeById(Long id){
	    Optional<AdminNotice> adminNoticeOptional = adminNoticeRepository.findById(id);
	    
	    if(adminNoticeOptional.isPresent()) {
	    	AdminNotice adminNotice = adminNoticeOptional.get();
	        return new ResponseDto<>(ResultCode.SUCCESS.name(), adminNotice, ResultCode.SUCCESS.getMsg());
	    } else {
	        throw new InvalidRequestException("AdminNotice Not Found", "해당 ID에 대한 AdminNotice가 존재하지 않습니다.");
	    }
	}
	
	// Notice 등록
	@Override
	public ResponseDto<AdminNotice> createAdminNotice(AdminNoticeDto adminNoticeDto) {
		User author = getAuthenticatedUser();
		if (author == null) {
			throw new InvalidRequestException("Invalid User", "글쓰기 권한이 없습니다.");
		}
		// Notice객체 생성
		AdminNotice adminNotice = new AdminNotice();
		adminNotice.setCreateAt(LocalDateTime.now());
		adminNotice.setAuthor(author);
		adminNotice.setTitle(adminNoticeDto.getTitle());
		adminNotice.setText(adminNoticeDto.getText());
		adminNoticeRepository.save(adminNotice);
		return new ResponseDto<>(ResultCode.SUCCESS.name(), adminNotice, "AdminNotice 등록이 완료되었습니다.");
	}

	// Notice 수정
	public ResponseDto<AdminNotice> updateAdminNotice(AdminNoticeDto updateAdminNotice, Long adminNoticeId){
	    Optional<AdminNotice> adminNoticeOptional = adminNoticeRepository.findById(adminNoticeId);
	    
	    User author = getAuthenticatedUser();
	    
	    if (adminNoticeOptional.isEmpty()) {
	        throw new InvalidRequestException(Long.toString(adminNoticeId), "해당 AdminNoticeId가 존재하지 않습니다.");
	    }
	    AdminNotice adminNotice = adminNoticeOptional.get();
	    if (!author.equals(adminNotice.getAuthor())) {
	        throw new InvalidRequestException("Unauthorized", "AdminNoticeId 작성자가 현재 로그인 한 작성자와 일치하지 않습니다.");
	    }
	    // 수정할 필드들을 업데이트
	    adminNotice.setTitle(updateAdminNotice.getTitle());
	    adminNotice.setText(updateAdminNotice.getText());

	    // 수정된 Notice 객체를 저장
	    AdminNotice savedAdminNotice = adminNoticeRepository.save(adminNotice);

	    // 응답 객체를 생성하여 반환
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), savedAdminNotice, "AdminNotice가 성공적으로 수정되었습니다.");
	}
	
	// Notice 삭제
	@Override
	public ResponseDto<AdminNotice> deleteAdminNoticeById(Long id) {
	    User author = getAuthenticatedUser();
	    Optional<AdminNotice>adminNoticeOptional = adminNoticeRepository.findById(id);

	    if (adminNoticeOptional.isEmpty()) {
	        throw new InvalidRequestException(Long.toString(id), "해당 AdminNoticeId가 존재하지 않습니다.");
	    }
	    AdminNotice adminNotice = adminNoticeOptional.get();
	    if (!author.equals(adminNotice.getAuthor())) {
	        throw new InvalidRequestException("Unauthorized", "AdminNoticeId 작성자가 현재 로그인 한 작성자와 일치하지 않습니다.");
	    }

	    // Notice를 삭제
	    adminNoticeRepository.deleteById(id);

	    return new ResponseDto<>(ResultCode.SUCCESS.name(), null, "AdminNotice 삭제가 완료되었습니다.");
	}
	
	// 새로운 Notice는 알림표시, 7일이 지나면 알림표시 삭제 // NewStatus 상태 변경 x => 시간남으면 여쭤보기
		@Scheduled(cron = "0 0 0 * * ?")  // 매일 자정에 실행
		@Override
		public ResponseDto<String> updateStatuAdminNotice() {
		    LocalDateTime threshold = LocalDateTime.now().minusDays(7);

		    List<AdminNotice> oldAdminNotices = adminNoticeRepository.findAllByCreateAtBeforeAndNewStatusIsTrue(threshold);

		    if (oldAdminNotices.isEmpty()) {
		        throw new InvalidRequestException("AdminNotice Empty", "7일 이상 지난 AdminNotice가 존재하지 않습니다.");
		    }

		    for (AdminNotice adminNotice : oldAdminNotices) {
		        System.out.println("Before save: " + adminNotice);
		        AdminNotice savedAdminNotice = adminNoticeRepository.save(adminNotice);
		        System.out.println("After save: " + savedAdminNotice);
		    }
		    return new ResponseDto<>("SUCCESS", null, "7일 이상 지난 AdminNotice의 newStatus가 변경되었습니다.");
		}
}
