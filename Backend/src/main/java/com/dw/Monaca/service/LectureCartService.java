package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.LectureCartDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.LectureCart;

public interface LectureCartService {

	
	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item(Lecture) 전부 조회
	ResponseDto<List<LectureCartDto>> getAllLectureCart();
	
	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item(Lecture)을 ID로 조회
	ResponseDto<LectureCartDto> getLectureCartById(Long id);
	
	// 현재 로그인한 User의 cart에 Item(Lecture) 담기
	ResponseDto<Long> createLectureCart(LectureCartDto lectureCartDto);
	
	// 현재 로그인한 User가 장바구니에 담아놓은 Item(Lecture)를 결제( ClassRoom에는 복사되어 생성 )
	ResponseDto<Long> purchaseLectureCart(Long lectureCartId);

	// 현재 로그인한 User의 cart에 담아놓은 Item(lecture) 전부 삭제
	ResponseDto<String> deleteAllLectureCart();
	
	// 현재 로그인한 User의 cart에 담아놓은 Item(lecture) ID로 선택하여 삭제
	ResponseDto<String> deleteLectureCartById(Long id);
	
}