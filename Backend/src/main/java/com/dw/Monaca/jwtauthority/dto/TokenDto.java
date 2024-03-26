package com.dw.Monaca.jwtauthority.dto;

import java.util.List;

public class TokenDto {

	private String token;

	private String loginId;

	private List<String> authority;

	private String name;

	public TokenDto() {
		super();
	}

	public TokenDto(String token, String loginId, List<String> authority, String name) {
		this.token = token;
		this.loginId = loginId;
		this.authority = authority;
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public List<String> getAuthority() {
		return authority;
	}

	public void setAuthority(List<String> authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
