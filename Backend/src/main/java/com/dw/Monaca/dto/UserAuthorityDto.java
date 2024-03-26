package com.dw.Monaca.dto;

import java.util.Set;

import com.dw.Monaca.jwtauthority.model.Authority;

public class UserAuthorityDto {

	private String name;
	private String loginId;
    private Set<Authority> authorities;

    
    public UserAuthorityDto() {
		super();
	}

	public UserAuthorityDto(String name, String loginId, Set<Authority> authorities) {
		super();
		this.name = name;
		this.loginId = loginId;
		this.authorities = authorities;
	}
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
    
    
    
}
