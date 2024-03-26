package com.dw.Monaca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 어느 곳에서 포인트를 획득하고 사용했는지 출처를 볼 수 있도록 하기 위한 Entity임!!

@Entity
@Table(name = "candy_point_item")
public class CandyPointItem {

	@Id
	@Column(name = "item_name")
	private String itemName;

	public CandyPointItem() {
		super();
	}

	public CandyPointItem(String itemName) {
		super();
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

			
	
}
