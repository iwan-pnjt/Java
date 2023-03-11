package com.dansmultipro.restapi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse {

    private Integer status;
    private Object data;
    private Object error;

    public APIResponse() {
        this.status = HttpStatus.OK.value();
        this.data = data;
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
    

//	@Component
//	public class JwtUtils {
//
//	    private static String secret = "This_is_secret";
//	    private static long expiryDuration = 60 * 60;
//	    public String generateJwt(User user){
//
//	        long milliTime = System.currentTimeMillis();
//	        long expiryTime = milliTime + expiryDuration * 1000;
//
//	        Date issuedAt = new Date(milliTime);
//	        Date expiryAt = new Date(expiryTime);
//
//	        // claims
//	        Claims claims = Jwts.claims()
//	                .setIssuer(user.getId().toString())
//	                .setIssuedAt(issuedAt)
//	                .setExpiration(expiryAt);
//
//	        // optional claims
//	        claims.put("type", user.getUserType());
//	        claims.put("name", user.getName());
//	        claims.put("emailId", user.getEmailId());
//
//	        // generate jwt using claims
//	        return Jwts.builder()
//	                .setClaims(claims)
//	                .signWith(SignatureAlgorithm.HS512, secret)
//	                .compact();
//	    }
//
//	    public Claims verify(String authorization) throws Exception {
//
//	        try {
//	            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
//	            return claims;
//	        } catch(Exception e) {
//	            throw new AccessDeniedException("Access Denied");
//	        }
//
//	    }
//	}
    
    
}

