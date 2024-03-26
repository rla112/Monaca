package com.dw.Monaca.model;

import com.dw.Monaca.jwtauthority.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// 일시적으로 사용되는 entity
@Entity
@Table(name = "item_Shop_cart")
public class ItemShopCart {

	@Id
	@Column(name = "item_shop_cart_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private ItemShop itemShop;

	public ItemShopCart() {
		super();
	}

	public ItemShopCart(Long id, User user, ItemShop itemShop) {
		super();
		this.id = id;
		this.user = user;
		this.itemShop = itemShop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ItemShop getItemShop() {
		return itemShop;
	}

	public void setItemShop(ItemShop itemShop) {
		this.itemShop = itemShop;
	}
	
	
}
