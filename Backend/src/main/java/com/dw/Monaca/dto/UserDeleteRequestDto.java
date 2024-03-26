package com.dw.Monaca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// 자발적 회원 탈퇴 시 비밀번호 입력을 위한 DTO( 보안을 강화하기 위해 사용자로부터 비밀번호를 한 번 더 입력받는 것)
public class UserDeleteRequestDto {

	@NotNull
	@NotBlank
	private String password;

	
	
	public UserDeleteRequestDto() {
		super();
	}

	public UserDeleteRequestDto(@NotNull @NotBlank String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 
}
