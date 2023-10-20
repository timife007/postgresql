package com.timife.prodatabase.config.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service //Make this class an injectable component
public class JwtService {
    private static final String SECRET_KEY = "uByCyIE+C2zyFk0s3rLz2A1XBD/BnXNTCc4TAr7PKSj8DTM6QwT2qtiqcilsqsRzyf+9LpHHdDNb6uForIwqHqu12df41H9otyCm5yiZM1KpKABxIcdxPxTBIbM61Uc2FCoUboT28zltIexK3hTdyCiIoETm3Xik+ukf2zZHEBE2UfnhiaKTS0XoTZrEqcT3OX6lGsHt2lYF/IEskAf78Vw0uK43EBHT8hSB8Iztoijn1b0OAc03V6rj8Hqr5cSOp9CBGRknQKCBWKIQ/mwGRcYo+t3iRvsiQTJ3Q7pLRPaLWHkLjR2w9VYJzNn2vjkhSXj3OOgH4v8DiXfOoyqQY02euqiw3acAZ7lXIKF+/tk=";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }


    //Generate token by using the Jwts builder, setting the subject, issuedAtTime,
    //Expiration, and sign in key.
    private String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //helper function to generate token by providing the user details.
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }


    //Check the validity of the token by extracting the username from the token
    //and comparing it with the one in the db; and also checking if it is expired.
    public boolean isTokenValid(
            String token, UserDetails userDetails
    ){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    //Check if the provided token is expired, by confirming if
    // the expiration data is before the current date.
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //extract the expiration date from the claims.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Extract individual user claim from the user claims, using a functional interface
    //Similar to a lambda function in kotlin, takes in a parameter Claims and returns
    //a response T.
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Fetch all the claims encoded into the token.
    //Claims contain different information present in the token.
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
