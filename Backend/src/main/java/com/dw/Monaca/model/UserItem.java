package com.dw.Monaca.model;

import com.dw.Monaca.jwtauthority.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_item")
public class UserItem {

	@Id
	@Column(name = "user_item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;
	
	@ManyToOne
	private ItemShop itemShop;

	@Column(name = "is_wearing")
	private boolean isWearing;

	public UserItem() {
		super();
	}

	public UserItem(Long id, User user, ItemShop itemShop, boolean isWearing) {
		super();
		this.id = id;
		this.user = user;
		this.itemShop = itemShop;
		this.isWearing = isWearing;
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

	public boolean isWearing() {
		return isWearing;
	}

	public void setWearing(boolean isWearing) {
		this.isWearing = isWearing;
	}


}
