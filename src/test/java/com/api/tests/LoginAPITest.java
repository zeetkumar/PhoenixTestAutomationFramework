package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentilas;
import static com.api.utils.SpecUtil.*;

public class LoginAPITest {
	
	private UserCredentilas userCredentilas;
	
	@BeforeMethod(description="Create the request Payload for the Login API")
	public void setup() {
		 userCredentilas = new UserCredentilas("iamfd", "password");
	}
	
	
	@Test(description="Verifying if login api is wroking for FD user",groups= {"api","regression","somoke"})
	public void loginAPITest() throws IOException {
		// Rest Assured Code !
		
		
		given()
		.spec(requestspec(userCredentilas))
	    .when()
        .post("login")
        .then()
        .body("message",equalTo("Success"))
        .and()
        .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
        
	}

}
