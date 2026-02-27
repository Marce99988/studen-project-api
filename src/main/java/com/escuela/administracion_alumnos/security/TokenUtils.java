package com.escuela.administracion_alumnos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String SECRET_KEY = "";

    private final static Long TOKEN_ACCES_SECONDS = 2_592_000L;

    public static String createToken(String nombre, String email){
        Long expirationToken = TOKEN_ACCES_SECONDS *1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationToken);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();

    }

    public static UsernamePasswordAuthenticationToken getAuth(String token) {
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String email = claims.getSubject();

                return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
            }catch (JwtException e){
                return null;
            }
    }
}
