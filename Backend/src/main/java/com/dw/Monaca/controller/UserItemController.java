package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.UserItem;
import com.dw.Monaca.service.impl.UserItemServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT,
		RequestMethod.POST })
public class UserItemController {

	private final UserItemServiceImpl userItemServiceImpl;

	@Autowired
	public UserItemController(UserItemServiceImpl userItemServiceImpl) {
		this.userItemServiceImpl = userItemServiceImpl;
	}

	// 유저가 가지고 있는 모든 아이템 불러오기
	@GetMapping("api/userItem/all")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<UserItem>>> getAllUserItem() {
		return new ResponseEntity<>(userItemServiceImpl.getAllUserItem(), HttpStatus.OK);
	}

	// 유저가 가지고 있는 아이템 카테고리별로 불러오기
	@GetMapping("api/userItem/{itemCategoryName}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<UserItem>>> getUserItemByItemCategory(
			@PathVariable(name = "itemCategoryName") String itemShopCategoryName) {
		return new ResponseEntity<>(userItemServiceImpl.getUserItemByItemCategory(itemShopCategoryName), HttpStatus.OK);
	}

	// 유저가 장착중인 아이템엔 "장착 중" 이라는 문구 표시 (클릭을 막음)
	@PutMapping("api/userItem/wear/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<UserItem>> wearUserItem(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(userItemServiceImpl.wearUserItem(id), HttpStatus.OK);
	}

	// 유저가 현재 장착중인 아이템을 장착 해제
	@PutMapping("api/userItem/off/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<UserItem>> takeOffUserItem(Long id) {
		return new ResponseEntity<>(userItemServiceImpl.takeOffUserItem(id), HttpStatus.OK);
	}

}
