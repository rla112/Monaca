package com.dw.Monaca.service.impl;

import java.util.List;
import java.util.ArrayList;
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
import com.dw.Monaca.model.UserItem;
import com.dw.Monaca.repository.ItemCategoryRepository;
import com.dw.Monaca.repository.ItemShopRepository;
import com.dw.Monaca.repository.UserItemRepository;
import com.dw.Monaca.service.UserItemService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserItemServiceImpl implements UserItemService{

	private UserItemRepository userItemRepository;
	private UserRepository userRepository;
	private ItemShopRepository itemShopRepository;
	private ItemCategoryRepository itemCategoryRepository;
	
	@Autowired
	public UserItemServiceImpl(UserItemRepository userItemRepository, UserRepository userRepository,
			ItemShopRepository itemShopRepository, ItemCategoryRepository itemCategoryRepository) {
		this.userItemRepository = userItemRepository;
		this.userRepository = userRepository;
		this.itemShopRepository = itemShopRepository;
		this.itemCategoryRepository = itemCategoryRepository;
	}

	
	private User getAuthenticatedUser() {
	    String currentLoginId = SecurityUtil.getCurrentLoginId()
	        .orElseThrow(() -> new InvalidRequestException("Authentication", "사용자 인증 정보를 찾을 수 없습니다."));
	    return userRepository.findByLoginId(currentLoginId)
	        .orElseThrow(() -> new InvalidRequestException("User Not Found", "회원이 아닙니다."));
	}

	// 유저가 가지고 있는 모든 아이템 불러오기
	@Override
	public ResponseDto<List<UserItem>> getAllUserItem() {
	
		User user = getAuthenticatedUser();
		List<UserItem> userItems = userItemRepository.findByUser(user);
		if(userItems.isEmpty()) {
			return new ResponseDto<>(ResultCode.SUCCESS.name(),userItems,"현재 가지고 있는 아이템이 없습니다.");
		}
		return new ResponseDto<>(ResultCode.SUCCESS.name(),userItems,ResultCode.SUCCESS.getMsg());
	}
	
	
	// 유저가 가지고 있는 아이템 카테고리별로 불러오기
	@Override
	public ResponseDto<List<UserItem>> getUserItemByItemCategory(String itemCategoryName) {
	    User user = getAuthenticatedUser();
	    Optional<ItemCategory> itemCategoryOptional = itemCategoryRepository.findByCategoryName(itemCategoryName);
	    if(itemCategoryOptional.isEmpty()) {
	        throw new InvalidRequestException("ItemCategory Not Found","해당 아이템 카테고리를 찾을 수 없습니다.");
	    }
	    ItemCategory itemCategory = itemCategoryOptional.get();
	    List<ItemShop> itemShops = itemShopRepository.findByItemCategory(itemCategory);
	    if(itemShops.isEmpty()) {
	        throw new InvalidRequestException("ItemShop Empty","아이템이 존재하지 않습니다.");
	    }
	    // 사용자 아이템을 저장할 UserItem 타입의 리스트를 생성
	    List<UserItem> userItems = new ArrayList<>();
	    for (ItemShop itemShop : itemShops) {
	    	//  현재 인증된 사용자와 각 ItemShop 객체에 해당하는 UserItem 객체를 찾음.
	    	//  찾은 UserItem 객체들을 userItems 리스트에 추가
	        userItems.addAll(userItemRepository.findByUserAndItemShop(user, itemShop));
	    }
	    if(userItems.isEmpty()) {
	        throw new InvalidRequestException("UserItem Empty","사용자 옷장에 아이템이 존재하지 않습니다.");
	    }
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), userItems, ResultCode.SUCCESS.getMsg());
	}


	// 내가 장착중인 아이템엔 "장착 중" 이라는 문구 표시 (클릭을 막음) 
	@Override
	public ResponseDto<UserItem> wearUserItem(Long id) {
	    User user = getAuthenticatedUser();
	    Optional<UserItem> userItemOptional = userItemRepository.findByIdAndUser(id, user);
	    if(userItemOptional.isEmpty()) {
	        throw new InvalidRequestException("UserItem Not Found", "해당 아이템을 찾을 수 없습니다.");
	    }
	    UserItem userItem = userItemOptional.get();
	    userItem.setWearing(true);
	    userItemRepository.save(userItem);
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), userItem, ResultCode.SUCCESS.getMsg());
	}


	// 유저가 현재 장착중인 아이템을 장착 해제
	@Override
	public ResponseDto<UserItem> takeOffUserItem(Long id) {
		User user = getAuthenticatedUser();
		Optional<UserItem> userItemOptional = userItemRepository.findByIdAndUser(id, user);
	    if(userItemOptional.isEmpty()) {
	        throw new InvalidRequestException("UserItem Not Found", "해당 아이템을 찾을 수 없습니다.");
	    }
	    UserItem userItem = userItemOptional.get();
	    userItem.setWearing(false);
	    userItemRepository.save(userItem);
	    return new ResponseDto<>(ResultCode.SUCCESS.name(), userItem, ResultCode.SUCCESS.getMsg());
	}




}
