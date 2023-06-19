package com.devdahcoder.jwt.contract;

import com.devdahcoder.user.contract.UserDetailsContract;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtContract {

    public String extractUserDetails(String jwtToken);

    public <T> T extractClaim(String jwtToken, @NotNull Function<Claims, T> claimsFunction);

    public String generateJwtToken(UserDetailsContract userDetails);

    public String generateJwtToken(Map<String, Object> claims, @NotNull UserDetailsContract userDetails);

    public boolean isTokenValid(String jwtToken, @NotNull UserDetailsContract userDetailsContract);

    public boolean isTokenExpired(String jwtToken);

    public Date extractExpiration(String jwtToken);

    public Claims extractAllClaims(String jwtToken);

    public @NotNull Key getSignInKey();

}
