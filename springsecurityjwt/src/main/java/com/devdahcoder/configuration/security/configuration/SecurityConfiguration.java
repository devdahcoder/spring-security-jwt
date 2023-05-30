package com.devdahcoder.configuration.security.configuration;

import com.devdahcoder.configuration.security.provider.BasicAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final BasicAuthenticationProvider basicAuthenticationProvider;

	@Autowired
	public SecurityConfiguration(BasicAuthenticationProvider basicAuthenticationProvider) {

		this.basicAuthenticationProvider = basicAuthenticationProvider;

	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth ->
						auth.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.build();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) {

		authenticationManagerBuilder.authenticationProvider(basicAuthenticationProvider);

	}

}
