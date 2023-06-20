package com.devdahcoder.jwt.repository;

import com.devdahcoder.jwt.contract.JwtContract;
import com.devdahcoder.user.contract.UserDetailsContract;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Repository
public class JwtRepository implements JwtContract {

    private static final String SECURITY_KEY = "9C1u3g8B+V6kBrH9JJs42QaR9JEcry0pYUXQQz/2V/ht1xgkkuoI96MIBW2e/qxTubWNSRJf3uTkBLPzSqY80mtoIanUrvulP9A1qWS7ZBx/tDlFS/qR/ozBXYOpAkSBrl1DhM6q0OOX1zsKmV0rvcckDWDGys8nuvpb1IW4soeWhlRC8Yw8BL74CP+B7EOaqdGo0lE1aIDr2RhMQHoV+70CcYPTW2FuWGJ6qDaonTXXk9Ets7Q3hj4HyF0wCMiR9LwgGUh1e6D68w5KmbmYi2/I9Qq1R0IjdGy4Y5ywOjLgcxJTZYzBf6OvexYctpBc+ml7fA/NtHLQZvnxLgbGViuQVnT2MMkNQknw+v0MNe0=";

    @Override
    public String extractUserDetails(String jwtToken) {

        return extractClaim(jwtToken, Claims::getSubject);

    }

    @Override
    public <T> T extractClaim(String jwtToken, @NotNull Function<Claims, T> claimsFunction) {

        final Claims claims = extractAllClaims(jwtToken);

        return claimsFunction.apply(claims);

    }

    @Override
    public String generateJwtToken(UserDetailsContract userDetails) {

        return this.generateJwtToken(new HashMap<>(), userDetails);

    }

    @Override
    public String generateJwtToken(Map<String, Object> claims, @NotNull UserDetailsContract userDetails) {

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuer("devdahcoder")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    @Override
    public boolean isTokenValid(String jwtToken, @NotNull UserDetailsContract userDetails) {

        final String username = extractUserDetails(jwtToken);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);

    }

    @Override
    public boolean isTokenExpired(String jwtToken) {

        return extractExpiration(jwtToken).before(new Date());

    }

    @Override
    public Date extractExpiration(String jwtToken) {

        return extractClaim(jwtToken, Claims::getExpiration);

    }

    @Override
    public Claims extractAllClaims(String jwtToken) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

    }

    @Override
    public @NotNull Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECURITY_KEY);

        return Keys.hmacShaKeyFor(keyBytes);

    }

}
