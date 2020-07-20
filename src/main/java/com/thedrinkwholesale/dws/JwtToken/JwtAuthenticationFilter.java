package com.thedrinkwholesale.dws.JwtToken;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

//필터에 jwt 인증 작업 진행 필터 추가
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {


    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        해더에 있는 jwt 를 가져온다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
//        유효 토큰 확인
        if(token != null && jwtTokenProvider.validateToken(token)){
//            유효토큰 이면 토큰에서 유저정보 가져온다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
//            SecurityContext 에 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
