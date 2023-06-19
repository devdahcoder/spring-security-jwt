package com.devdahcoder.configuration.security.filter;

import com.devdahcoder.jwt.service.JwtService;
import com.devdahcoder.user.contract.UserDetailsContract;
import com.devdahcoder.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserService userService) {

        this.jwtService = jwtService;

        this.userService = userService;

    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (isValidAuthHeader(authHeader)) {

            String jwtToken = this.extractJwtToken(authHeader);

            String username = jwtService.extractUserDetails(jwtToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetailsContract userDetailsContract = userService.loadUserByUsername(username);

                if (jwtService.isTokenValid(jwtToken, userDetailsContract)) {

                    UsernamePasswordAuthenticationToken authenticationToken = createAuthenticationToken(userDetailsContract, request);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

            }

            filterChain.doFilter(request, response);

        }

        filterChain.doFilter(request, response);

    }

    @Contract(pure = true)
    private @NotNull String extractJwtToken(@NotNull String authHeader) {

        return authHeader.substring(7);

    }

    private @NotNull UsernamePasswordAuthenticationToken createAuthenticationToken(UserDetailsContract userDetailsContract, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetailsContract, null, userDetailsContract.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return authenticationToken;

    }

    private boolean isValidAuthHeader(String authHeader) {

        return authHeader != null && authHeader.startsWith("Bearer ");

    }

}
