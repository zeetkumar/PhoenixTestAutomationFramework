package com.api.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;

public class CreateJobAPITest {
	// Creating the CreateJobPayload Object
	
	
	
	@Test
	public void createJobAPITest() {
		Customer customer= new Customer("Jiten", "Ram", "8768268447", "", "zitenram@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("D 404","Vasant Galaxy","Bangur Nagar","Inorbit", "Mumbai", "411039","India","Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-09-01T18:30:00.000Z","14057920836419","14057920836419","14057920836419","2025-09-01T18:30:00.000Z",3,3);
		Problems problems= new Problems(1,"Battery issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 2, customer, customerAddress, customerProduct, problemsList);
	 
		given()
	  .spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
	  .when()
	  .post("/job/create")
	  .then()
	  .spec(SpecUtil.responseSpec_OK())
	  .body(matchesJsonSchemaInClasspath("response-schema/CreateJonAPIResponseSchema.json"))
	  .body("message",equalTo("Job created successfully. "))
	  .body("data.mst_service_location_id",equalTo(1))
	  .body("data.job_number",startsWith("JOB_"));
		
	}

}
