package com.devdahcoder.authentication.service;

import com.devdahcoder.authentication.contract.AuthenticationContract;
import com.devdahcoder.authentication.model.AuthenticateUserModel;
import com.devdahcoder.authentication.model.AuthenticationResponseModel;
import com.devdahcoder.authentication.repository.AuthenticationRepository;
import com.devdahcoder.jwt.service.JwtService;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService, AuthenticationContract {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService, AuthenticationRepository authenticationRepository) {

        this.jwtService = jwtService;

        this.authenticationManager = authenticationManager;

        this.userService = userService;

        this.authenticationRepository = authenticationRepository;

    }


    @Override
    public UserDetailsContract loadUserByUsername(String username) {

        return authenticationRepository.loadUserByUsername(username);

    }

    @Override
    public AuthenticationResponseModel authenticateUser(@NotNull AuthenticateUserModel authenticateUserModel) {

        try {

			authenticationManager.authenticate(

					new UsernamePasswordAuthenticationToken(
							authenticateUserModel.getUsername(),
							authenticateUserModel.getPassword()
					)

			);

            UserDetailsContract userExist = authenticationRepository.loadUserByUsername(authenticateUserModel.getUsername());

			var jwtToken = jwtService.generateJwtToken(userExist);

            return new AuthenticationResponseModel(jwtToken);

        } catch (BadCredentialsException ex) {

            throw new BadCredentialsException("Invalid Credentials");

        }

    }

}
