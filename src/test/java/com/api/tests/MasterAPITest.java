package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import groovyjarjarpicocli.CommandLine.Spec;
import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constant.Role.*;
import static com.api.utils.AuthtokenProvider.*;
import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
		.post("master") // default content-type application/url-formencoded
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data",hasKey("mst_oem"))
		.body("data",hasKey("mst_model"))
		.body("$",hasKey("message"))
		.body("$",hasKey("data"))
		.body("data.mst_oem.size()",equalTo(2))// Check the size of the JSON Array with Matchers 
		.body("data.mst_model.size()",greaterThan(0))
		.body("data.mst_oem.id",everyItem(notNullValue()))
		.body("data.mst_oem.name",everyItem(notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
		
		
		
	}
	
	
	
	@Test
	public void invalidTokenforMasterAPITest() {
	given()
	.spec(SpecUtil.requestspec())
	.log().all()
	.when()
	.post("master") // default content-type application/url-formencoded
	.then()
	.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
