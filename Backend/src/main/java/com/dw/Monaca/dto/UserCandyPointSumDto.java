package com.dw.Monaca.dto;

// 각 사용자의 loginId와 그 사용자의 CandyPoint 합계
public class UserCandyPointSumDto {

	private String loginId;
	private Long totalPoints;
	
	public UserCandyPointSumDto() {
		super();
	}

	public UserCandyPointSumDto(String loginId, Long totalPoints) {
		super();
		this.loginId = loginId;
		this.totalPoints = totalPoints;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}
	
	

	  
	
}
