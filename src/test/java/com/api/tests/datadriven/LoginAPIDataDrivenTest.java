package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestspec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.dataproviders.api.bean.UserBean;

public class LoginAPIDataDrivenTest {
	
	
	
	
	@Test(description="Verifying if login api is wroking for FD user",
			groups= {"api","regression","datadriven"}, 
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="LoginAPIDataProvider"
	
			)
	public void loginAPITest(UserBean userbean)  {
		// Rest Assured Code !
		
		
		given()
		.spec(requestspec( userbean))
	    .when()
        .post("login")
        .then()
        .body("message",equalTo("Success"))
        .and()
        .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
        
	}

}
