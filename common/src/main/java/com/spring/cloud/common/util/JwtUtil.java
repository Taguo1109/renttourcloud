package com.spring.cloud.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * ClassName: com.spring.cloud.common.util.JwtUtil
 * Package: com.spring.cloud.common.util
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/23 下午4:50
 * @Version 1.0
 */

/**
 * JWT 工具
 */
public class JwtUtil {
    private static final String SECRET = "c9sPT-7U6J-zucbljw_MAl7iv1hlInJUfCTPHnGwBGs=";
    private static final long   EXP_HOURS = 24;

    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(Date.from(Instant.now().plus(EXP_HOURS, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public static String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
