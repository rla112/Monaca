package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ProfessorNoticeDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ProfessorNotice;

public interface ProfessorNoticeService {

		// Notice 전체 조회
		ResponseDto<List<ProfessorNotice>> getAllProfessorNotice();
		
		// Notice ID로 조회
		ResponseDto<ProfessorNotice> getProfessorNoticeById(Long id);
		
		// Notice Lecture별로 조회
		ResponseDto<List<ProfessorNotice>> getAllProfessorNoticeByLecture(String lecture);
		
		// Notice 등록
		ResponseDto<ProfessorNotice> createProfessorNotice(ProfessorNoticeDto professorNoticeDto);
		
		// Notice 수정
		ResponseDto<ProfessorNotice> updateProfessorNotice(ProfessorNotice updateProfessorNotice, Long id);
		
		// Notice 삭제
		ResponseDto<ProfessorNotice> deleteProfessorNotice(Long id);

		// 새로운 Notice는 알림표시, 7일이 지나면 알림표시 삭제
		ResponseDto<String> updateStatuProfessorNotice();
	
}
