package com.dw.Monaca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;
import com.dw.Monaca.jwtauthority.util.SecurityUtil;
import com.dw.Monaca.model.ItemCategory;
import com.dw.Monaca.model.ItemShop;
import com.dw.Monaca.repository.ItemCategoryRepository;
import com.dw.Monaca.repository.ItemShopRepository;
import com.dw.Monaca.service.ItemShopService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemShopServiceImpl implements ItemShopService{

	private final ItemShopRepository itemShopRepository;
	private final UserRepository userRepository;
	private final ItemCategoryRepository itemCategoryRepository;

	@Autowired
	public ItemShopServiceImpl(ItemShopRepository itemShopRepository, UserRepository userRepository,
			ItemCategoryRepository itemCategoryRepository) {
		super();
		this.itemShopRepository = itemShopRepository;
		this.userRepository = userRepository;
		this.itemCategoryRepository = itemCategoryRepository;
	}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
	// 전체 아이템 불러오기
	@Override
	public ResponseDto<List<ItemShop>> getAllItemShop() {
		getAuthenticatedUser();
		
		List<ItemShop> itemShops = itemShopRepository.findAll();
		
		if(itemShops.isEmpty()) {
			return new ResponseDto<>(ResultCode.SUCCESS.name(),itemShops,"Item Shop이 비어있습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(),itemShops,ResultCode.SUCCESS.getMsg());
	}

	
	// 카테고리별 아이템 불러오기 (탭 사용)
	@Override
	public ResponseDto<List<ItemShop>> getItemShopByItemCategory(String itemShopCategory) {
		getAuthenticatedUser();
		
		Optional<ItemCategory> itemShopCategoryOptional = itemCategoryRepository.findByCategoryName(itemShopCategory);
		if(itemShopCategoryOptional.isEmpty()) {
			throw new InvalidRequestException("ItemCategory Not Found", "해당 아이템 카테고리를 찾을 수 없습니다.");
		}
		ItemCategory itemCategory =  itemShopCategoryOptional.get();
		List<ItemShop> itemShops = itemShopRepository.findByItemCategory(itemCategory);
		
		if(itemShops.isEmpty()) {
			return new ResponseDto<>(ResultCode.SUCCESS.name(),itemShops,"해당 카테고리의 아이템이 존재하지 않습니다"); 
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(),itemShops,ResultCode.SUCCESS.getMsg());
	}

	
	// 아이템 업로드
	@Override
	public ResponseDto<ItemShop> createItemShop(ItemShop itemShop) {
		
		getAuthenticatedUser();
		
		ItemShop item = new ItemShop();
		item.setItemName(itemShop.getItemName());
		item.setItemCategory(itemShop.getItemCategory());
		item.setImage(itemShop.getImage());
		item.setPrice(itemShop.getPrice());
		itemShopRepository.save(item);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),item,ResultCode.SUCCESS.getMsg());
	}

	// 아이템 수정
	@Override
	public ResponseDto<ItemShop> updateItemShop(Long id, ItemShop updateItemShop) {
		Optional<ItemShop> itemShopOptional = itemShopRepository.findById(id);
		if(itemShopOptional.isEmpty()) {
			throw new InvalidRequestException("ItemShop Not Foud","해당 아이템을 찾을 수 없습니다.");
		}
		ItemShop itemShop = itemShopOptional.get();
		
		itemShop.setItemName(updateItemShop.getItemName());
		itemShop.setItemCategory(updateItemShop.getItemCategory());
		itemShop.setImage(updateItemShop.getImage());
		itemShop.setPrice(updateItemShop.getPrice());
		
		ItemShop savedItemShop = itemShopRepository.save(itemShop);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),savedItemShop,ResultCode.SUCCESS.getMsg());
	}
	
	// 아이템 삭제
	@Override
	public ResponseDto<String> deleteItemShop(Long id) {
		Optional<ItemShop> itemShopOptional = itemShopRepository.findById(id);
		if(itemShopOptional.isEmpty()) {
			throw new InvalidRequestException("ItemShop Not Foud","해당 아이템을 찾을 수 없습니다.");
		}
		itemShopRepository.deleteById(id);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"Item 삭제가 완료되었습니다.");
	}


}
