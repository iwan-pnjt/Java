package com.dansmultipro.restapi.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dansmultipro.restapi.entity.User;
import com.dansmultipro.restapi.payload.APIResponse;
import com.dansmultipro.restapi.payload.LoginData;
import com.dansmultipro.restapi.repository.UserRepository;


@Service
public class LoginService {
    
	@Autowired
	private UserRepository userRepository;
	
	public APIResponse login(LoginData loginRequest) {

		Map<String,Object> jwtResponse = new HashMap<String,Object>();
		APIResponse apiResponse = new APIResponse();

        //verify user exist with given username and password
        User user = userRepository.findByUsernameandPassword(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

        if(user == null){
        	apiResponse.setData("User login failed");
            return apiResponse;
        }

        //generate jwt
//      String token = jwtUtils.generateJwt(user);
        String token = encryptThisString(loginRequest.getUsernameOrEmail());
        jwtResponse.put("accessToken", token);

        apiResponse.setData(jwtResponse);
        return apiResponse;
    }
	
	
	//failed to generate json web token, karena masalah pada java version yang saya gunakan. 
	//saya coba generate token dengan melakukan enkripsi manual
	public static String encryptThisString(String input)
    {
        try {
        	String secret = "This_is_secret";
    	    long expiryDuration = 60 * 60;
        	long milliTime = System.currentTimeMillis();
	        long expiryTime = milliTime + expiryDuration * 1000;

	        Date issuedAt = new Date(milliTime);
	        Date expiryAt = new Date(expiryTime);
	        
	        secret = secret + input + expiryAt.toString() + issuedAt.toString();
        	
            MessageDigest md = MessageDigest.getInstance("SHA-256");
  
            byte[] messageDigest = md.digest(secret.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
  
            String hashtext = no.toString(16);
  
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
  
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
}
