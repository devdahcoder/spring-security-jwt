package com.devdahcoder.authentication.service;

import com.devdahcoder.exception.security.SecurityAuthenticationException;
import com.devdahcoder.exception.security.SecurityInternalAuthenticationServiceException;
import com.devdahcoder.exception.security.SecurityProviderNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jetbrains.annotations.NotNull;
import com.devdahcoder.jwt.service.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.devdahcoder.user.repository.UserRepository;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.exception.api.ApiAuthenticationException;
import com.devdahcoder.authentication.model.AuthenticateUserModel;
import com.devdahcoder.authentication.contract.AuthenticationContract;
import com.devdahcoder.authentication.model.AuthenticationResponseModel;

@Service
public class AuthenticationService implements AuthenticationContract {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserRepository userRepository) {

        this.jwtService = jwtService;

        this.authenticationManager = authenticationManager;

        this.userRepository = userRepository;

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

            UserDetailsContract userDetails = userRepository.loadUserByUsername(authenticateUserModel.getUsername());

			var jwtToken = jwtService.generateJwtToken(userDetails);

            return new AuthenticationResponseModel(jwtToken);

        } catch (UsernameNotFoundException ex) {

            throw new ApiAuthenticationException("User with username " + authenticateUserModel.getUsername() + " could not be found");

        } catch (BadCredentialsException ex) {

            throw new ApiAuthenticationException("Invalid credentials username or password no correct");

        } catch(ProviderNotFoundException ex) {

            String authenticationProviderNotFoundMessage = "ProviderNotFoundException: Authentication Manager could not find" +
                    "an authentication provider that matches this authentication type";

            logger.error(authenticationProviderNotFoundMessage, ex);

            throw new SecurityProviderNotFoundException(authenticationProviderNotFoundMessage);

        } catch (InternalAuthenticationServiceException ex) {

            String authenticationInternalExceptionMessage = "InternalAuthenticationServiceException: An internal " +
                    "system occurred preventing authentication of user " + authenticateUserModel.getUsername();

            logger.error(authenticationInternalExceptionMessage, ex);

            throw new SecurityInternalAuthenticationServiceException(authenticationInternalExceptionMessage, ex);

        } catch (AuthenticationException ex) {

            String authenticationExceptionMessage = "AuthenticationException: Something went wrong while trying to " +
                    "authenticate user " + authenticateUserModel.getUsername();

            logger.error(authenticationExceptionMessage, ex);

            throw new SecurityAuthenticationException(authenticationExceptionMessage, ex);

        }

    }

}
