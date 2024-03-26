package com.dw.Monaca.jwtauthority.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dw.Monaca.jwtauthority.jwt.JwtAccessDeniedHandler;
import com.dw.Monaca.jwtauthority.jwt.JwtAuthenticationEntryPoint;
import com.dw.Monaca.jwtauthority.jwt.JwtFilter;
import com.dw.Monaca.jwtauthority.jwt.TokenProvider;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	private final TokenProvider tokenProvider; // 토큰을 생성해서 주는 클래스
	// private final CorsFilter corsFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; // 예외 설정 (인증 실패) ex) 패스워드 실패
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler; // 예외 설정 (인가 실패) ex) admin만 들어갈 수 있는 자리에 user가 들어옴

	public SecurityConfig(TokenProvider tokenProvider, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		super();
		this.tokenProvider = tokenProvider;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}

	@Bean
	public PasswordEncoder passwordEncoder() { // PasswordEncoder는 라이브러리가 사용하는 암호화 방식을 알려주는 것임. // Provider는 password를
												// 암호화 해서 비교해야 하기 때문에 암호화 하는 방식을 알려줌
		return new BCryptPasswordEncoder(); // 라이브러리에 BCrypt를 사용할거라고 알려줌 그렇기 때문에 retrun값만 지정
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // 명령 // 토큰을 쓰지 않고 세션을 쓴다면 csrf를 사용하는데 우리는 토큰을 사용할 것이기 때문에 csrf를 비활성화 한다.

				.exceptionHandling(exceptionHandling -> exceptionHandling // 예외처리 (예외핸들링 2가지를 해주세요.)
						.accessDeniedHandler(jwtAccessDeniedHandler) // 인증 실패 예외 처리와
						.authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인가 실패 예외처리를 익셉션 핸들링 처리 해주세요.
				)

				.authorizeHttpRequests(
						authorizeHttpRequests -> authorizeHttpRequests
						.requestMatchers("/api/authenticate").permitAll()
								.requestMatchers("/api/signup/**").permitAll() 
								.requestMatchers("/api/lecture/all").permitAll()
								.requestMatchers("/api/lecture/professor/{professorName}").permitAll()
								.requestMatchers("/api/lecture/professorInfo/{id}").permitAll()
								.requestMatchers("/api/lecture/lectureCategory/{categoryName}").permitAll()
								.requestMatchers("/api/lecture/paid").permitAll()
								.requestMatchers("/api/lecture/id/{id}").permitAll()
								.requestMatchers("/api/lecture/free").permitAll()
								.requestMatchers("/api/lecture/counts/lectureId/{lectureId}").permitAll()
								.requestMatchers("/api/apply/professor").permitAll()
								.requestMatchers("/api/lecture/professor/list/{lectureCategoryName}").permitAll()
								.requestMatchers("/api/teaching/professorName/{professorName}").permitAll()
								.requestMatchers("/api/part-of-user/{loginId}").permitAll()
								.requestMatchers("/api/lectureCategory/all").permitAll()
								.requestMatchers("/api/qanda/lectureName/{lectureName}").permitAll()
								.requestMatchers("api/saveWishLecture/{lectureId}").permitAll()
								.anyRequest().authenticated() // 이새기들은 필수!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				)

				.sessionManagement(
						sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.formLogin(formLoginCustomizer -> formLoginCustomizer.defaultSuccessUrl("/"))

				.addFilterBefore( // 토큰을 까서 보면 안에 있는 어떤 내용을 어떻게 처리할지에 대한 내용
						new JwtFilter(tokenProvider), // 토큰을 처리할 수 있는 방법에대한 커스터 마이징
						UsernamePasswordAuthenticationFilter.class // 이 앞에 써라. 유저와 패스워드로 인증해라.
				);

		return http.build(); // 사슬고리를 한데 묶어서 리턴한다.
	}
}
