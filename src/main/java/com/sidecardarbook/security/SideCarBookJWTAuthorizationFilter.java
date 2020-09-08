package com.sidecardarbook.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class SideCarBookJWTAuthorizationFilter extends BasicAuthenticationFilter {

	public SideCarBookJWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(SideCarBookSecurity.HEADER_STRING.getStringValue());

        if (header == null || !header.startsWith(SideCarBookSecurity.TOKEN_PREFIX.getStringValue())) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SideCarBookSecurity.HEADER_STRING.getStringValue());
        if (token != null) {
            // parse the token.
        	
            String user = JWT.require(Algorithm.HMAC512(SideCarBookSecurity.SECRET.getStringValue().getBytes()))
                    .build()
                    .verify(token.replace(SideCarBookSecurity.TOKEN_PREFIX.getStringValue(), ""))
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
	
	
	
}
