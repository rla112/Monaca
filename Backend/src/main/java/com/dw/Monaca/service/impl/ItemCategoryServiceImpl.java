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
import com.dw.Monaca.repository.ItemCategoryRepository;
import com.dw.Monaca.service.ItemCategoryService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemCategoryServiceImpl implements ItemCategoryService{

	private final ItemCategoryRepository itemCategoryRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public ItemCategoryServiceImpl(ItemCategoryRepository itemCategoryRepository, UserRepository userRepository) {
		super();
		this.itemCategoryRepository = itemCategoryRepository;
		this.userRepository = userRepository;
	}

	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}
	
	// 전체 ItemCategory 불러오기
	@Override
	public ResponseDto<List<ItemCategory>> getAllCategory() {
		getAuthenticatedUser();
		
		List<ItemCategory> itemCategory = itemCategoryRepository.findAll();
		
		if(itemCategory.isEmpty()) {
			throw new InvalidRequestException("ItemCategory Not Found","아이템 카테고리가 존재하지 않습니다.");
		}
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),itemCategory,ResultCode.SUCCESS.getMsg());
	}

	// ItemCategory 생성
	@Override
	public ResponseDto<ItemCategory> createItemCategory(ItemCategory itemCategory) {
		getAuthenticatedUser();
		
		ItemCategory category = new ItemCategory();
		category.setCategoryName(itemCategory.getCategoryName());
		itemCategoryRepository.save(category);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),category,ResultCode.SUCCESS.getMsg());
	}

	// ItemCategory 수정
	@Override
	public ResponseDto<ItemCategory> updateItemCategory(Long id, ItemCategory updateItemCategory) {
		Optional<ItemCategory> itemCategoryOptional = itemCategoryRepository.findById(id);
		if(itemCategoryOptional.isEmpty()) {
			throw new InvalidRequestException("ItemCategory Not Found","해당 아이템카테고리를 찾을 수 없습니다");
		}
		ItemCategory itemCategory = itemCategoryOptional.get();
		itemCategory.setCategoryName(updateItemCategory.getCategoryName());
		ItemCategory savedItemCategory = itemCategoryRepository.save(itemCategory);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),savedItemCategory,ResultCode.SUCCESS.getMsg());
	}

	// ItemCategory 삭제
	@Override
	public ResponseDto<ItemCategory> deleteItemCategory(Long id) {
		Optional<ItemCategory> itemCategoryOptional = itemCategoryRepository.findById(id);
		if(itemCategoryOptional.isEmpty()) {
			throw new InvalidRequestException("ItemCategory Not Found","해당 아이템카테고리를 찾을 수 없습니다");
		}
		
		itemCategoryRepository.deleteById(id);
		
		return new ResponseDto<>(ResultCode.SUCCESS.name(),null,"ItemCategory 삭제가 완료되었습니다.");
	}

}
