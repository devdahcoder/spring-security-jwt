package com.devdahcoder.configuration.security.filter;

import com.devdahcoder.authentication.service.AuthenticationService;
import com.devdahcoder.jwt.service.JwtService;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.service.UserService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, AuthenticationService authenticationService) {

        this.jwtService = jwtService;

        this.authenticationService = authenticationService;

    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (isValidAuthHeader(authHeader)) {

            try {

                String jwtToken = this.extractJwtToken(authHeader);

                String username = jwtService.extractUserDetails(jwtToken);

                logger.info("user {} not fount", username);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetailsContract userDetails = authenticationService.loadUserByUsername(username);

                    if (jwtService.isTokenValid(jwtToken, userDetails)) {

                        UsernamePasswordAuthenticationToken authentication = createAuthentication(userDetails, request);

                        SecurityContextHolder.getContext().setAuthentication(authentication);

                    }

                }

//                filterChain.doFilter(request, response);

            } catch (JwtException ex) {
                // Handle JWT-related exceptions (e.g., expired token, invalid signature, etc.)
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT token");
            } catch (AuthenticationException ex) {
                // Handle authentication-related exceptions (e.g., user not found, invalid credentials, etc.)
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authentication failed: " + ex.getMessage());
            }

        }

        filterChain.doFilter(request, response);

    }

    @Contract(pure = true)
    private @NotNull String extractJwtToken(@NotNull String authHeader) {

        return authHeader.substring(7);

    }

    private @NotNull UsernamePasswordAuthenticationToken createAuthentication(UserDetailsContract userDetails, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return authenticationToken;

    }

    private boolean isValidAuthHeader(String authHeader) {

        return authHeader != null && authHeader.startsWith("Bearer ");

    }

}
