package com.dw.Monaca.jwtauthority.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;

    // Authentication에서 로그인 아이디를 추출하는 메서드
    private String getLoginId(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            // Authentication 객체에서 UserDetails를 추출하고 그 중에서 로그인 아이디를 반환
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null; // 만약 추출에 실패하면 null 반환
    }

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // HttpServletRequest로 형변환
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 헤더에서 JWT 토큰을 추출
        String jwt = resolveToken(httpServletRequest);
        // 현재 요청의 URI
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            // JWT 토큰이 유효하면 해당 토큰으로부터 Authentication 객체를 추출
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            // SecurityContext에 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 디버그 로그 출력 (Security Context에 인증 정보 저장)
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", getLoginId(authentication), requestURI);
        } else {
            // 디버그 로그 출력 (유효한 JWT 토큰이 없음)
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        // 다음 필터로 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // HTTP 요청에서 헤더에서 JWT 토큰을 추출하는 메서드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            // "Bearer " 이후의 토큰 값만 추출
            return bearerToken.substring(7);
        }
        return null; // 헤더에 토큰이 없거나 형식이 올바르지 않을 경우 null 반환
    }
}

