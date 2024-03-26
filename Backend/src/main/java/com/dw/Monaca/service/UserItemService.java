package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.UserItem;

// 유저의 옷장
public interface UserItemService {
// 기능 구현 예정
	
	// 유저가 가지고 있는 모든 아이템 불러오기
	ResponseDto<List<UserItem>> getAllUserItem();
	
	// 유저가 가지고 있는 아이템 카테고리별로 불러오기
	ResponseDto<List<UserItem>> getUserItemByItemCategory(String itemShopCategoryName);
	
	
	// 유저가 장착중인 아이템엔 "장착 중" 이라는 문구 표시 (클릭을 막음) 
	ResponseDto<UserItem> wearUserItem(Long id);
	
	
	// 유저가 현재 장착중인 아이템을 장착 해제
	ResponseDto<UserItem> takeOffUserItem(Long id);

	// 업로드만 사용 (수정 삭제는 불필요, 유저가 구매한 아이템이나 획득한 아이템만)
}
