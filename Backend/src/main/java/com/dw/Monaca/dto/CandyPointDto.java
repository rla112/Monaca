package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotNull;

public class CandyPointDto {

	private String userLoginId;
	
	private String candyName;
	
	private String candyPointItem;
	
	@NotNull
	private int point;
	
	private String description;

	public CandyPointDto() {
		super();
	}

	public CandyPointDto(String userLoginId, String candyName, String candyPointItem, @NotNull int point,
			String description) {
		super();
		this.userLoginId = userLoginId;
		this.candyName = candyName;
		this.candyPointItem = candyPointItem;
		this.point = point;
		this.description = description;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getCandyName() {
		return candyName;
	}

	public void setCandyName(String candyName) {
		this.candyName = candyName;
	}

	public String getCandyPointItem() {
		return candyPointItem;
	}

	public void setCandyPointItem(String candyPointItem) {
		this.candyPointItem = candyPointItem;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
