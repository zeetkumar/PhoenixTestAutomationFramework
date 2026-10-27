package com.api.utils;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;

public class CreateJobAPITest {
	// Creating the CreateJobPayload Object
	
	
	
	@Test
	public void createJobAPITest() {
		Customer customer= new Customer("Jiten", "Ram", "8768268447", "", "zitenram@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("D 404","Vasant Galaxy","Bangur Nagar","Inorbit", "Mumbai", "411039","India","Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2025-09-01T18:30:00.000Z","11800155612729","11800155612729","11800155612729","2025-09-01T18:30:00.000Z", 3, 3);
		Problems problems= new Problems(1,"Battery issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]= problems;
		
		CreateJobPayload createJobPayload= new CreateJobPayload(0, 2, 1, 2, customer, customerAddress, customerProduct, problemsArray);
	 
		given()
	  .spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
	  .when()
	  .post("/job/create")
	  .then()
	  .spec(SpecUtil.responseSpec_OK());
		
	}

}
