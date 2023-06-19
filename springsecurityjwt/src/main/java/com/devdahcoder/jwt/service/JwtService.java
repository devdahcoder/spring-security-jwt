package com.devdahcoder.jwt.service;

import com.devdahcoder.jwt.contract.JwtContract;
import com.devdahcoder.jwt.repository.JwtRepository;
import com.devdahcoder.user.contract.UserDetailsContract;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements JwtContract {

    private final JwtRepository jwtRepository;

    @Autowired
    public JwtService(JwtRepository jwtRepository) {

        this.jwtRepository = jwtRepository;

    }

    @Override
    public String extractUserDetails(String jwtToken) {

        return jwtRepository.extractUserDetails(jwtToken);

    }

    @Override
    public <T> T extractClaim(String jwtToken, @NotNull Function<Claims, T> claimsFunction) {

        return jwtRepository.extractClaim(jwtToken, claimsFunction);

    }

    @Override
    public String generateJwtToken(UserDetailsContract userDetails) {

        return jwtRepository.generateJwtToken(userDetails);

    }

    public String generateJwtToken(Map<String, Object> claims, @NotNull UserDetailsContract userDetails) {

        return jwtRepository.generateJwtToken(claims, userDetails);

    }

    public boolean isTokenValid(String jwtToken, @NotNull UserDetailsContract userDetailsContract) {

        return jwtRepository.isTokenValid(jwtToken, userDetailsContract);

    }

    public boolean isTokenExpired(String jwtToken) {

        return jwtRepository.isTokenExpired(jwtToken);

    }

    public Date extractExpiration(String jwtToken) {

        return jwtRepository.extractExpiration(jwtToken);

    }

    public Claims extractAllClaims(String jwtToken) {

        return jwtRepository.extractAllClaims(jwtToken);

    }

    public @NotNull Key getSignInKey() {

        return jwtRepository.getSignInKey();

    }

}
