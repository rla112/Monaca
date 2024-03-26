package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.AdminNoticeDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.AdminNotice;


//인터페이스의 모든 메서드는 기본적으로 public이다.

public interface AdminNoticeService {

	// Notice 전체 조회
	 ResponseDto<List<AdminNotice>> getAllAdminNotice();
	
	// Notice ID로 조회
	ResponseDto<AdminNotice> getAdminNoticeById(Long id);
	
	// Notice 등록
	 ResponseDto<AdminNotice> createAdminNotice(AdminNoticeDto adminNoticeDto);
	
	// Notice 수정
	 ResponseDto<AdminNotice> updateAdminNotice(AdminNoticeDto updateAdminNotice, Long id);
	
	// Notice 삭제
	 ResponseDto<AdminNotice> deleteAdminNoticeById(Long id);
	
	// 새로운 Notice는 알림표시, 7일이 지나면 알림표시 삭제
	 ResponseDto<String> updateStatuAdminNotice();
	
}
