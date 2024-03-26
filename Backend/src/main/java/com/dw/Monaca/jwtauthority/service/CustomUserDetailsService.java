package com.dw.Monaca.jwtauthority.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dw.Monaca.exception.InvalidRequestException;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.repository.UserRepository;

@Component("userDetailService")
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String loginId) {
		return loadUserByUserLoginId(loginId);
	}

	// 실제 사용하고자 하는 메서드
	public UserDetails loadUserByUserLoginId(final String loginId) {
		return userRepository.findOneWithAuthoritiesByLoginId(loginId).map(user -> createUser(loginId, user))
				.orElseThrow(() -> new InvalidRequestException(loginId, loginId + " -> 데이터베이스에서 찾을 수 없습니다."));
	}

	private org.springframework.security.core.userdetails.User createUser(String loginId, User user) {
		if (!user.getActivated()) {
			throw new RuntimeException(loginId + " -> 활성화되어 있지 않습니다.");
		}

		List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getLoginId(), user.getPassword(),
				grantedAuthorities);
	}
}
