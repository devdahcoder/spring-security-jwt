package com.devdahcoder.configuration.security.configuration;

import com.devdahcoder.configuration.security.filter.JwtAuthenticationFilter;
import com.devdahcoder.configuration.security.provider.UsernamePasswordAuthenticationProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	public SecurityConfiguration(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {

		this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;

		this.jwtAuthenticationFilter = jwtAuthenticationFilter;

	}

	@Bean
	public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {

		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth ->
						auth.requestMatchers("/api/v1/user/signup", "/api/v1/user/login")
								.permitAll()
								.anyRequest()
								.authenticated()
				)
				.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authenticationProvider(usernamePasswordAuthenticationProvider)
				.build();

	}

	@Autowired
	public void configureGlobal(@NotNull AuthenticationManagerBuilder authenticationManagerBuilder) {

		authenticationManagerBuilder.authenticationProvider(usernamePasswordAuthenticationProvider);

	}

}
