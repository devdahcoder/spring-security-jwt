package com.devdahcoder.configuration.security.provider;

import com.devdahcoder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class BasicAuthenticationProvider implements AuthenticationProvider {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public BasicAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {

		this.userService = userService;

		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();

		String password = authentication.getCredentials().toString();

		UserDetails userDetails = userService.loadUserByUsername(username);

		return authenticationCheck(userDetails, password, passwordEncoder);

	}

	private Authentication authenticationCheck(UserDetails userDetails, String password, PasswordEncoder passwordEncoder) {

		if (passwordEncoder.matches(password, userDetails.getPassword())) {

			return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

		} else {

			throw new BadCredentialsException("Bad credentials");

		}

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);

	}

}
