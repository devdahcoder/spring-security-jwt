package com.devdahcoder.configuration.security.provider;

import com.devdahcoder.configuration.security.authentication.UsernamePasswordAuthentication;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


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

		UserDetails userDetails = userRepository.loadUserByUsername(username);

		return authenticationCheck(userDetails, password, passwordEncoder);

	}

	@Contract("_, _, _ -> new")
	private @NotNull Authentication authenticationCheck(UserDetails userDetails, String password, PasswordEncoder passwordEncoder) {

		if (this.checkPasswordVerification(userDetails, password, passwordEncoder)) {

			return new UsernamePasswordAuthentication(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

		} else {

			throw new BadCredentialsException("Bad credentials");

		}

	}

	private boolean checkPasswordVerification(@NotNull UserDetails userDetails, String password, @NotNull PasswordEncoder passwordEncoder) {

		return passwordEncoder.matches(password, userDetails.getPassword());

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return AbstractAuthenticationToken.class.isAssignableFrom(authentication);

	}

}
