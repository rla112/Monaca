package com.dw.Monaca.jwtauthority.controller;

import com.dw.Monaca.jwtauthority.model.Authority;
import com.dw.Monaca.jwtauthority.model.User;
import com.dw.Monaca.jwtauthority.service.UserService;

import java.util.List;
import java.util.ArrayList;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dw.Monaca.dto.ResponseDto;
import com.dw.Monaca.enumStatus.ResultCode;
import com.dw.Monaca.jwtauthority.dto.AuthorityDto;
import com.dw.Monaca.jwtauthority.dto.LoginDto;
import com.dw.Monaca.jwtauthority.dto.TokenDto;
import com.dw.Monaca.jwtauthority.jwt.JwtFilter;
import com.dw.Monaca.jwtauthority.jwt.TokenProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE })

public class AuthController {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	private final UserService userService;

	public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService) {
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.userService = userService;
	}

	// 로그인 하기 / OK
	@PostMapping("/authenticate") 
	public ResponseEntity<ResponseDto<TokenDto>> authorize(@RequestBody @Valid LoginDto loginDto) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginDto.getLoginId(), loginDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.createToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

		User user = userService.getAuthenticatedUser();
		TokenDto tokenDto = new TokenDto();
		tokenDto.setToken(jwt);
		tokenDto.setLoginId(user.getLoginId());
		tokenDto.setName(user.getName());
		List<String> userAuthorities = new ArrayList<String>();
		user.getAuthorities().forEach(data -> userAuthorities.add(data.getAuthorityName()));
		tokenDto.setAuthority(userAuthorities); 

		return new ResponseEntity<>(
				new ResponseDto<>(ResultCode.SUCCESS.name(), tokenDto, ResultCode.SUCCESS.getMsg()),
				httpHeaders, HttpStatus.OK);
	}

	
}
