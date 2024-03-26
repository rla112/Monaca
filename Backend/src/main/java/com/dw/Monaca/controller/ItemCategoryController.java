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
import com.dw.Monaca.model.ItemCategory;
import com.dw.Monaca.service.impl.ItemCategoryServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ItemCategoryController {

	private final ItemCategoryServiceImpl itemCategoryServiceImpl;

	@Autowired
	public ItemCategoryController(ItemCategoryServiceImpl itemCategoryServiceImpl) {
		this.itemCategoryServiceImpl = itemCategoryServiceImpl;
	}

	// 전체 ItemCategory 불러오기
	@GetMapping("api/itemCateogyr/all")
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR','USER')")
	public ResponseEntity<ResponseDto<List<ItemCategory>>> getAllCategory() {
		return new ResponseEntity<>(itemCategoryServiceImpl.getAllCategory(), HttpStatus.OK);
	}

	// ItemCategory 생성
	@PostMapping("api/itemCateogyr")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<ItemCategory>> createItemCategory(@RequestBody ItemCategory itemCategory) {
		return new ResponseEntity<>(itemCategoryServiceImpl.createItemCategory(itemCategory), HttpStatus.OK);
	}

	// ItemCategory 수정
	@PutMapping("api/itemCateogyr/update/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<ItemCategory>> updateItemCategory(@PathVariable(name = "id") Long id,
			@RequestBody ItemCategory updateItemCategory) {
		return new ResponseEntity<>(itemCategoryServiceImpl.updateItemCategory(id, updateItemCategory), HttpStatus.OK);
	}

	// ItemCategory 삭제
	@DeleteMapping("api/itemCateogyr/delete/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	ResponseEntity<ResponseDto<ItemCategory>> deleteItemCategory(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(itemCategoryServiceImpl.deleteItemCategory(id), HttpStatus.OK);
	}

}
