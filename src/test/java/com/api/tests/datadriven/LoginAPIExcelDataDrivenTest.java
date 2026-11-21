package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestspec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.dataproviders.api.bean.UserBean;

public class LoginAPIExcelDataDrivenTest {
	
	
	
	
	@Test(description="Verifying if login api is wroking for FD user",
			groups= {"api","regression","datadriven"}, 
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="LoginAPIExcelDataProvider"
	
			)
	public void loginAPITest(UserBean userBean)  {
		// Rest Assured Code !
		
		
		given()
		.spec(requestspec(userBean))
	    .when()
        .post("login")
        .then()
        .body("message",equalTo("Success"))
        .and()
        .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
        
	}

}
