package com.devdahcoder.configuration.security.provider;

import com.devdahcoder.authentication.service.AuthenticationService;
import com.devdahcoder.configuration.security.authentication.UsernamePasswordAuthentication;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	private final AuthenticationService authenticationService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UsernamePasswordAuthenticationProvider(AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {

		this.authenticationService = authenticationService;

		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();

		String password = authentication.getCredentials().toString();

		UserDetailsContract userDetails = authenticationService.loadUserByUsername(username);

		return authenticationCheck(userDetails, username, password, passwordEncoder);

	}

	private @NotNull Authentication authenticationCheck(UserDetailsContract userDetails, String username, String password, PasswordEncoder passwordEncoder) {

		if (this.checkPasswordVerification(userDetails, password, passwordEncoder) && this.checkUsernameVerification(userDetails, username)) {

			return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

		} else {

			throw new BadCredentialsException("Bad credentials");

		}

	}

	private boolean checkUsernameVerification(@NotNull UserDetailsContract userDetails, String username) {

		return userDetails.getUsername().equals(username);

	}

	private boolean checkPasswordVerification(@NotNull UserDetailsContract userDetails, String password, @NotNull PasswordEncoder passwordEncoder) {

		return passwordEncoder.matches(password, userDetails.getPassword());

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);

	}

}
