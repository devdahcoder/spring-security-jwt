package com.devdahcoder.configuration.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class StaticKeyAuthenticationFilter implements Filter {

	@Value("${authorization.key}")
	private String authorizationKey;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

			var httpRequest = (HttpServletRequest) request;

			var httpResponse = (HttpServletResponse) response;

			String authentication = httpRequest.getHeader("Authorization");

			if (authorizationKey.equals(authentication)) {

				chain.doFilter(request, response);

			} else {

				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

			}

	}

}
