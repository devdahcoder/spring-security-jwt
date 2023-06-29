package com.devdahcoder.configuration.security.provider;

import org.jetbrains.annotations.NotNull;
import com.devdahcoder.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import com.devdahcoder.user.contract.UserDetailsContract;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Configuration
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UsernamePasswordAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;

		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public Authentication authenticate(@NotNull Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();

		String password = authentication.getCredentials().toString();

		UserDetailsContract userDetails = userRepository.loadUserByUsername(username);

		return authenticationCheck(userDetails, username, password, passwordEncoder);

	}

	private @NotNull Authentication authenticationCheck(UserDetailsContract userDetails, String username, String password, PasswordEncoder passwordEncoder) {

		if (this.checkPasswordVerification(userDetails, password, passwordEncoder) && this.checkUsernameVerification(userDetails, username)) {

			return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

		} else {

			throw new BadCredentialsException("Invalid username or password");

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
