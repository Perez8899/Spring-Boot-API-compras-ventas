package com.perez.compras_ventas.auth;

import com.perez.compras_ventas.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpiration;

    @Value("${application.security.jwt.refresh.expiration}")
    private Long refreshExpiration;


    //extract userName
    public String extractUserName(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //generate Token
    public String generateToken(Usuario usuario){
        return buildToken(usuario, jwtExpiration);
    }
    //generate RefreshToken
    public String generateRefreshToken(Usuario usuario){
        return buildToken(usuario, jwtExpiration);
    }

    //build token
    private String buildToken(Usuario usuario, Long expiration){
        return Jwts.builder()
                .claims(Map.of("uid", "_" + usuario.getCorreo()))
                .subject(usuario.getUserName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())
                .compact();
    }
    //isTokenValid
    public boolean isTokenValid(String token, Usuario usuario){
        String username = extractUserName(token);
        return !isTokenExpired(token) && username.equals(usuario.getUserName());

    }
    //isTokenExpired
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }


    //key generation
    private SecretKey getSignKey() {
        final byte[] keyBites = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBites);
    }
}

