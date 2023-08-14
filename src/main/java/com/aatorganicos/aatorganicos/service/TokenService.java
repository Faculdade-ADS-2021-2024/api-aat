package com.aatorganicos.aatorganicos.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aatorganicos.aatorganicos.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String gerarToken(Usuario usuario) {
        return JWT.create()
            .withIssuer("AAT")
            .withSubject(usuario.getUsername())
            .withClaim("id", usuario.getId())
            .withExpiresAt(LocalDateTime.now()
                .plusMinutes(120)
                .toInstant(ZoneOffset.of("-03:00"))
            ).sign(Algorithm.HMAC256(jwtSecret));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(jwtSecret))
            .withIssuer("AAT")
            .build().verify(token).getSubject();
    }
    
}
