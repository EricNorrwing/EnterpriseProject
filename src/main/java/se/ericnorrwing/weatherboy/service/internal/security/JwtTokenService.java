package se.ericnorrwing.weatherboy.service.internal.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.ericnorrwing.weatherboy.configuration.configproperties.JwtKeyLoader;

import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;


@Service
@Slf4j
public class JwtTokenService {

    private static final long TOKEN_VALIDITY = 3600 * 1000;

    private final PrivateKey privateKey;
    private final RSAPublicKey publicKey;


    public JwtTokenService() throws Exception {
        this.privateKey = JwtKeyLoader.loadPrivateKey();
        this.publicKey = (RSAPublicKey) JwtKeyLoader.loadPublicKey();
    }


    public String generateJwtToken(String subject, String roles) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }


    public Claims parseJwtToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw e;
        }
    }


}