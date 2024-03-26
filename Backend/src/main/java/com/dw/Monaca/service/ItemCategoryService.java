package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ItemCategory;

public interface ItemCategoryService {

	
	// 전체 ItemCategory 불러오기
	ResponseDto<List<ItemCategory>> getAllCategory();
	
	// ItemCategory 생성
	ResponseDto<ItemCategory> createItemCategory(ItemCategory itemCategory);
	
	// ItemCategory 수정
	ResponseDto<ItemCategory> updateItemCategory(Long id, ItemCategory updateItemCategory);
	
	// ItemCategory 삭제
	ResponseDto<ItemCategory> deleteItemCategory(Long id);
}
