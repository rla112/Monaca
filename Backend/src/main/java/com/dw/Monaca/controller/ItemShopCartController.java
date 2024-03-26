package com.dw.Monaca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ItemShopCartDto;
import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ItemShopCart;
import com.dw.Monaca.service.impl.ItemShopCartServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
public class ItemShopCartController {

	private ItemShopCartServiceImpl itemShopCartServiceImpl;

	@Autowired
	public ItemShopCartController(ItemShopCartServiceImpl itemShopCartServiceImpl) {
		this.itemShopCartServiceImpl = itemShopCartServiceImpl;
	}

	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item 전부 조회
	@GetMapping("api/itemShopCart/all")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<List<ItemShopCart>>> getAllItemShopCart() {
		return new ResponseEntity<>(itemShopCartServiceImpl.getAllItemShopCart(), HttpStatus.OK);
	}

	// 현재 로그인한 User의 cart(장바구니)에 담아놓은 Item을 ID로 조회
	@GetMapping("api/itemShopCart/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<ItemShopCart>> getItemShopCartById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(itemShopCartServiceImpl.getItemShopCartById(id), HttpStatus.OK);
	}

	// 현재 로그인한 User의 cart에 Item 담기
	@PostMapping("api/itemShopCart")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<Long>> createItemShopCart(@RequestBody @Valid ItemShopCartDto itemShopCartDto) {
		return new ResponseEntity<>(itemShopCartServiceImpl.createItemShopCart(itemShopCartDto), HttpStatus.OK);
	}

	// 현재 로그인한 User가 장바구니에 담아놓은 Item을 결제( UserItem에는 복사되어 생성 )
	@PostMapping("api/itemShopCart/purchase/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<Long>> purchaseItemShopCart(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(itemShopCartServiceImpl.purchaseItemShopCart(id), HttpStatus.OK);

	}

	// 현재 로그인한 User의 cart에 담아놓은 Item 전부 삭제
	@DeleteMapping("api/itemShopCart/delete/all")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteAllItemShopCart() {
		return new ResponseEntity<>(itemShopCartServiceImpl.deleteAllItemShopCart(), HttpStatus.OK);
	}

	// 현재 로그인한 User의 cart에 담아놓은 Item ID로 선택하여 삭제
	@DeleteMapping("api/itemShopCart/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER', 'PROFESSOR')")
	ResponseEntity<ResponseDto<String>> deleteItemShopCartById(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(itemShopCartServiceImpl.deleteItemShopCartById(id), HttpStatus.OK);
	}

}
