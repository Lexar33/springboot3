package org.jalcantararivera.mitosales.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.Key;
import java.security.Signature;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//@Service - business logic
//@Repository - for data access
//@Controller - for frontend controller (views)
//@Component - utility class , not categorized class
@Component
public class JwtTokenUtil implements Serializable {

    //milisegundos
    public final long JWT_TOKEN_VALIDITY= 5*60*60*1000; // 5 hours
    @Value("${jwt.secret}") //Expression language
    public String secret;
    //Payload
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims= new HashMap<>();
        claims.put("role",userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
        claims.put("test","value-test");
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String,Object> claims,String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
                .signWith(getSignigKey())
                .compact();
    }

    private Key getSignigKey(){
        return new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS512.getJcaName());
    }

    ///////////////////////validaciones//////////////////////

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getSignigKey()).build().parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token , Function<Claims,T> claimsResolver){
        final Claims claims= getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    public String getUsernameFromToken(String token){
        //return getClaimFromToken(token,e->e.getSubject());
        return getClaimFromToken(token,Claims::getSubject);
    }
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        final Date expiration= getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token,UserDetails userDetails){
        final String username= getUsernameFromToken(token);
        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
