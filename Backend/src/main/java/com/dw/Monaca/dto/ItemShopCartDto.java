package com.dw.Monaca.dto;

public class ItemShopCartDto {

	private Long id;
	
	private Long itemShop;

	public ItemShopCartDto() {
		super();
	}

	public ItemShopCartDto(Long id, Long itemShop) {
		super();
		this.id = id;
		this.itemShop = itemShop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemShop() {
		return itemShop;
	}

	public void setItemShop(Long itemShop) {
		this.itemShop = itemShop;
	}
	
	
	
}
