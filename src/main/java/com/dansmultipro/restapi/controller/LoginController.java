package com.dansmultipro.restapi.controller;

import com.dansmultipro.restapi.payload.APIResponse;
import com.dansmultipro.restapi.payload.LoginData;
import com.dansmultipro.restapi.service.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    //Login API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<APIResponse> login(@RequestBody LoginData loginObject){

        APIResponse apiResponse = loginService.login(loginObject);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    
    
    //Get Joblist API
    @GetMapping("/getjoblist")
    public ResponseEntity<APIResponse> getJoblist(HttpServletRequest request) throws IOException{
        
    	String access = "";
		try {
			for (Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("Access")) {
					access = cookie.getValue();
				}
			}
		} 
		catch (Exception e) {
			access = "";
		}
		
		APIResponse apiResponse = new APIResponse();
		if(access != "") {
			String URLJoblist = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
			String res = callUrl(URLJoblist, "");
			apiResponse.setData(res);
		}
		else {
			apiResponse.setData("you don't have authorization for this data");
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    
    //Get Detail Joblist API
    @GetMapping("/getjobdetail/{id}")
    public ResponseEntity<APIResponse> getJoblist(HttpServletRequest request, 
    							@PathVariable(value = "id") Long jobId) throws IOException{
        
    	String access = "";
		try {
			for (Cookie cookie : request.getCookies()) {
				if(cookie.getName().equals("Access")) {
					access = cookie.getValue();
				}
			}
		} 
		catch (Exception e) {
			access = "";
		}
		
		APIResponse apiResponse = new APIResponse();
		if(access != "") {
			String URLJoblist = "http://dev3.dansmultipro.co.id/api/recruitment/positions/{ID}";
			String res = callUrl(URLJoblist, "");
			apiResponse.setData(res);
		}
		else {
			apiResponse.setData("you don't have authorization for this data");
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    
    public String callUrl(String urlString, String data) throws IOException{
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
	   
		OutputStreamWriter writer2 = new OutputStreamWriter(conn.getOutputStream());
	    
	    writer2.write(data);
	    writer2.flush();

	    BufferedReader reader2 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    List<String> listString2 = new ArrayList<String>();
	    String line = null;
	    while ((line = reader2.readLine()) != null) {
	    	listString2.add(line);
	    }
	    reader2.close();
	    
	    return listString2.get(0);
	}
    

}