package com.kintsugi.apientregador.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.kintsugi.apientregador.model.Driver;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenUtil {
    private static final int SEGUNDOS = 1000;
    private static final int MINUTOS = 60 * SEGUNDOS;
    private static final int HORAS = 60 * MINUTOS;
    // private static final int DIAS = 24 * HORAS;

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "BEARER ";
    private static final long EXPIRATION = 2 * HORAS;
    private static final String SECRET_KEY = "ndGD62gs8sbXYfnBD3b.51bnBS*DvKaQ";
    private static final String EMISSOR = "kintsugiAPI";

    public static String createToken(Driver driver) {
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        String token = Jwts.builder()
                .setSubject(Integer.toString(driver.getId()))
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return PREFIX + token;
    }

    private static boolean isExpirationValid(Date expiration) {
        return expiration.after(new Date(System.currentTimeMillis()));
    }

    private static boolean isEmissorValid(String emissor) {
        return emissor.equals(EMISSOR);
    }

    private static boolean isSubjectValid(String email) {
        return email != null && email.length() > 0;
    }

    public static Authentication validate(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        token = token.replace(PREFIX, "").trim();

        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token);

        String email = jwsClaims.getBody().getSubject();
        String issuer = jwsClaims.getBody().getIssuer();
        Date expiration = jwsClaims.getBody().getExpiration();

        if (isSubjectValid(email) && isEmissorValid(issuer) && isExpirationValid(expiration)) {
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }

        return null;
    }
}
