package com.devdahcoder.configuration.security.configuration;

import com.devdahcoder.configuration.security.filter.JwtAuthenticationFilter;
import com.devdahcoder.configuration.security.provider.UsernamePasswordAuthenticationProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

	@Autowired
	public void configureGlobal(@NotNull AuthenticationManagerBuilder authenticationManagerBuilder) {

		authenticationManagerBuilder.authenticationProvider(usernamePasswordAuthenticationProvider);

	}

	@Bean
	public AuthenticationManager authenticationManager(@NotNull AuthenticationConfiguration authConfiguration) throws Exception {

		return authConfiguration.getAuthenticationManager();

	}

	@Bean
	public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {

		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth ->
						auth.requestMatchers("/api/v1/user/signup", "/api/v1/user/hello", "/api/v1/auth/authenticate")
								.permitAll()
								.anyRequest()
								.authenticated()
				)
				.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
						.authenticationEntryPoint((request, response, authException) -> {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
				}))
				.formLogin(AbstractHttpConfigurer::disable)
				.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();

	}

}
