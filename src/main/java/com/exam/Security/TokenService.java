package com.exam.Security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenService {
	
  

	
	 @Value("${jwt.expiry}")
	 private int jwtExp;
	 @Value("${refresh.time}")
	 private int refTime;
	 @Value("${jwt.secret}")
	 private String secretKey;
	public boolean validateTokenAndReturnBool(String token, String ip) {
        try {        	 
            Claims claims = validateToken(token);
           
            if (claims != null) {
                String userId = claims.getSubject();
                String cd = claims.get("unique_name", String.class);
//                
                
                List<Map<String, Object>> data =null;
//                		masterRepository.CheckLogoutForToken(cd, userId, token,ip);
                ////System.out.println("----"+data);
                if (data != null && !data.isEmpty()) {
                    boolean isLogout = (Boolean) data.get(0).get("check_flag");
                    ////System.out.println(isLogout);
                    return !isLogout; 
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	public String generateToken(String uuid, String role) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        Date expiryTime = new Date(System.currentTimeMillis() +jwtExp * 60 * 1000); 


        Date now = new Date(); 
        Claims claims = Jwts.claims().setSubject(uuid);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryTime)
                .setHeaderParam("typ", "JWT")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

	public Claims validateToken(String token) {
	        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	
	        try {
	        	 
	            return Jwts.parserBuilder()
	                    .setSigningKey(key)
	                    .build()
	                    .parseClaimsJws(token)
	                    .getBody();
	          
	        } catch (JwtException e) {
	            return null;
	        }
	    }
	
	 public String generateRefreshToken(String token, String secretKey, String ip) throws Exception {
	        try {
	            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
	            JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
	            Claims claims2 = validateToken(token);
	            if(claims2==null) {
	            	return null;
	            }
	            Claims claims = parser.parseClaimsJws(token).getBody();
	            String expiryClaim = claims.get("expiry", String.class);
	            if (expiryClaim == null) {
	                throw new Exception("Token is missing expiry claim");
	            }

	            Date expiryTime = Date.from(Instant.parse(expiryClaim));
	            Date nowPlusFiveMinutes = Date.from(Instant.now().plus(refTime, ChronoUnit.MINUTES));
	            if (expiryTime.before(nowPlusFiveMinutes) && ip.equals(claims.get("role", String.class))) {
	                String uid = claims.get("sub", String.class);
	                String role = claims.get("role", String.class);

	                String newToken= generateToken(uid, role);
	                return newToken;
	            }

	        } catch (JwtException e) {
	            e.printStackTrace();
	            return null;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
			return null;
	        
	        
	    }
	 
	 
	 public  String[] decodeJWT(String token) {
		 try {
//			 String secretKey2="your_fixed_secret_key_here_jkenfjenfjefbjefbkejfbed";
	            Claims claims = validateToken(token);
	            if (claims != null) {
	                String userID = claims.get("unique_name", String.class);
	                String role = claims.get("sub", String.class);
	                return new String[]{role, userID};
	                
	            } else {
	                return null;
	            }
		 }catch(Exception e) {
			 e.printStackTrace();
			 return null;
		 }
	
	 }
	
}