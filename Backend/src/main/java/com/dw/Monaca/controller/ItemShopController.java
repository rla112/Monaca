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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.model.ItemShop;
import com.dw.Monaca.service.impl.ItemShopServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ItemShopController {

	public final ItemShopServiceImpl itemShopServiceImpl;

	@Autowired
	public ItemShopController(ItemShopServiceImpl itemShopServiceImpl) {
		super();
		this.itemShopServiceImpl = itemShopServiceImpl;
	}

	// 전체 아이템 불러오기
	@GetMapping("api/itemShop/all")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<ItemShop>>> getAllItemShop() {
		return new ResponseEntity<>(itemShopServiceImpl.getAllItemShop(), HttpStatus.OK);
	}

	// 카테고리별 아이템 불러오기 (탭 사용)
	@GetMapping("api/itemShop/{itemShopCategory}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<List<ItemShop>>> getItemShopByItemCategory(
			@PathVariable(name = "itemShopCategory") String itemShopCategory) {
		return new ResponseEntity<>(itemShopServiceImpl.getItemShopByItemCategory(itemShopCategory), HttpStatus.OK);
	}

	// 아이템 업로드
	@PostMapping("api/itemShop")
	@PreAuthorize("hasAnyRole('ADMIN')")
	ResponseEntity<ResponseDto<ItemShop>> createItemShop(@RequestBody ItemShop itemShop) {
		return new ResponseEntity<>(itemShopServiceImpl.createItemShop(itemShop), HttpStatus.OK);
	}

	// 아이템 수정
	@PutMapping("api/itemShop/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	ResponseEntity<ResponseDto<ItemShop>> updateItemShop(@PathVariable(name = "id") Long id,
			@RequestBody ItemShop updateItemShop) {
		return new ResponseEntity<>(itemShopServiceImpl.updateItemShop(id, updateItemShop), HttpStatus.OK);
	}

	// 아이템 삭제
	@DeleteMapping("api/itemShop/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	ResponseEntity<ResponseDto<String>> deleteItemShop(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(itemShopServiceImpl.deleteItemShop(id), HttpStatus.OK);
	}
}
