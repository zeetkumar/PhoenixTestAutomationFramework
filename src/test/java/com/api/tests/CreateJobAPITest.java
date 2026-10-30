package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.SpecUtil.*;

public class CreateJobAPITest {
	// Creating the CreateJobPayload Object
	
	private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description= " Creating createjob api request payload")
	
	public void setup() {
		Customer customer= new Customer("Jiten", "Ram", "8768268447", "", "zitenram@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("D 404","Vasant Galaxy","Bangur Nagar","Inorbit", "Mumbai", "411039","India","Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10),"14057920736519","14057920736519","14057920736519",getTimeWithDaysAgo(10),
				Product.NEXUS_2.getCode(),Model.NEXUS_2_BLUE.getCode());
		Problems problems= new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(),"Battery issue");
		
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		
		 createJobPayload= new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(),Warranty_Status.OUT_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
	 
	}
	
	@Test  (description="Verify if the CreateJob API able to create Inwrranty job", groups={"api","smoke","regression"})
	public void createJobAPITest() {
		
		
		
	 
	   given()
	  .spec(requestSpecWithAuth(Role.FD, createJobPayload))
	  .when()
	  .post("/job/create")
	  .then()
	  .spec(responseSpec_OK())
	  .body(matchesJsonSchemaInClasspath("response-schema/CreateJonAPIResponseSchema.json"))
	  .body("message",equalTo("Job created successfully. "))
	  .body("data.mst_service_location_id",equalTo(1))
	  .body("data.job_number",startsWith("JOB_"));
		
	}

}
