package com.api.utils;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentilas;

import io.restassured.http.ContentType;

public class AuthtokenProvider {
	private AuthtokenProvider() {
		 
	}

	public static String getToken(Role role) {
		// I want to make the request for the login api and we want to extract the token
		// print it on console!!
		UserCredentilas userCredentilas=null;
		if(role==FD) {
			userCredentilas= new UserCredentilas("iamfd","password");
		}
		else if(role==SUP) {
			userCredentilas= new UserCredentilas("iamsup","password");
		}
		else if(role==ENG) {
			userCredentilas= new UserCredentilas("iameng","password");
		}
		else if(role==QC) {
			userCredentilas= new UserCredentilas("iamqc","password");
		}
	String token = given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(userCredentilas)
		.when()
		.post("login")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.extract()
		.body()
		.jsonPath()
		.getString("data.token");
	System.out.println("--------------------");
	System.out.println(token);
	return token;

	}

}

