package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

public class UserDetailsAPITest {
	@Test (description="Verify if the Userdetails API response is shown correctly", groups={"api","smoke","regression"})
	public void UserDetailsAPITest() throws IOException {
		
	
		
		
	given()
	.spec(SpecUtil.requestSpecWithAuth(FD))
    .when()
    .get("userdetails")
    .then()
    .spec(SpecUtil.responseSpec_OK())
    .and()
    .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
