package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentilas;
import com.api.utils.SpecUtil;

public class LoginAPITest {
	
	@Test
	public void loginAPITest() throws IOException {
		// Rest Assured Code !
		
		  UserCredentilas userCredentilas= new UserCredentilas("iamfd", "password");
		given()
		.spec(SpecUtil.requestspec(userCredentilas))
		
		
        .when()
        .post("login")
        .then()
        .body("message",equalTo("Success"))
        .and()
        .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
        
	}

}
