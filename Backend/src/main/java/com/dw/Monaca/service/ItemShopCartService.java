package com.dw.Monaca.service;

import java.util.List;

import com.dw.Monaca.dto.ItemShopCartDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ItemShopCart;

public interface ItemShopCartService {
	
	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item 전부 조회
	ResponseDto<List<ItemShopCart>> getAllItemShopCart();
	
	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item을 ID로 조회
	ResponseDto<ItemShopCart> getItemShopCartById(Long id);
	
	// 현재 로그인한 User의 cart에 Item 담기
	ResponseDto<Long> createItemShopCart(ItemShopCartDto itemShopCartDto);
	
	// 현재 로그인한 User가 장바구니에 담아놓은 Item을 결제(candyPoitn사용)( UserItem에는 복사되어 생성 )
	ResponseDto<Long> purchaseItemShopCart(Long id);

	// 현재 로그인한 User의 cart에 담아놓은 Item 전부 삭제
	ResponseDto<String> deleteAllItemShopCart();
	
	// 현재 로그인한 User의 cart에 담아놓은 Item ID로 선택하여 삭제
	ResponseDto<String> deleteItemShopCartById(Long id);
}
