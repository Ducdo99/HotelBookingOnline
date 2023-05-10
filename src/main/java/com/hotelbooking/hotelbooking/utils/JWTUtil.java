package com.hotelbooking.hotelbooking.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    public String createJWTString(String email, Long roleID) {
        Instant currentUTCTime = Instant.now();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(email.trim())
                .setIssuedAt(new Date(currentUTCTime.toEpochMilli()))
                .setExpiration(new Date(Instant.now().toEpochMilli() + MyConstantVariables.EXPIRATION_TIME * 1000))
                .claim("roleID", roleID) // using addClaims() to store any extra claims
                .signWith(SignatureAlgorithm.HS384, secretKey.trim());
        return jwtBuilder.compact();
    }

    private Claims getClaimsFromJWTString(String jwtString) {
        return Jwts.parser().setSigningKey(secretKey.trim()).parseClaimsJws(jwtString.trim()).getBody();
    }

    public String getEmailFromJWTString(String jwtString) {
        Claims claims = this.getClaimsFromJWTString(jwtString);
        return claims.getSubject().trim();
    }

    public String getRoleID(String jwtString) {
        Claims claims = this.getClaimsFromJWTString(jwtString.trim());
        return claims.get("roleID").toString();
    }

    public Date getJWTExpirationTime(String jwtString) {
        Claims claims = this.getClaimsFromJWTString(jwtString.trim());
        return claims.getExpiration();
    }

    public boolean checkJWTExpirationTime(String jwtString) {
        Date expirationTime = this.getJWTExpirationTime(jwtString.trim());
        Instant currentUTCTime = Instant.now();
        Date currentTime = new Date(currentUTCTime.toEpochMilli());
        return currentTime.before(expirationTime);
    }

    public String getJWTStringFromHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization".trim());
        return authHeader.substring("Bearer ".length());
    }

}
