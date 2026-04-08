package com.ayush.util;

import java.util.Date;

import javax.crypto.SecretKey;

//import io.jsonwebtoken.*;
//import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey123"; // 36 chars = 288 bits ✅

    public static String generateToken(String username,String role) {
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)  // let JJWT pick the algorithm automatically
                .compact();
    }
}
	
