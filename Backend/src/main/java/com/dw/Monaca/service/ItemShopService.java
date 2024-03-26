package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ItemShop;

// 아이템 상점
public interface ItemShopService {

	// 기능 구현 예정
	
	// 전체 아이템 불러오기
	ResponseDto<List<ItemShop>> getAllItemShop();
	
	// 카테고리별 아이템 불러오기 (탭 사용)
	ResponseDto<List<ItemShop>> getItemShopByItemCategory(String ItemShopCategory);
	
	// 아이템 업로드
	ResponseDto<ItemShop> createItemShop(ItemShop itemShop);
	
	// 아이템 수정
	ResponseDto<ItemShop> updateItemShop(Long id, ItemShop updateItemShop);

	// 아이템 삭제
	ResponseDto<String> deleteItemShop(Long id);
	

	// CRUD 모두 사용 (한정 아이템,기간제 아이템 삭제 용이)
}
