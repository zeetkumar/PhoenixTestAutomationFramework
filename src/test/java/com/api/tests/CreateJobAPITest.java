package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
import com.api.utils.DateTimeUtil;
import com.github.javafaker.Faker;

import static com.api.utils.SpecUtil.*;

public class CreateJobAPITest {
	private CreateJobPayload createJobPayload;
	private final static String COUNTRY= "India";
	
	@BeforeMethod(description = "Creating createjob api request payload")
	public void setup() {
Faker faker= new Faker(new Locale("en-IND")); // Help me create India specific fake data
		
		String fname=faker.name().firstName();
		String lname=faker.name().lastName();
		String mobileNumber=faker.numerify("70########");
		String alternateMobileNumber=faker.numerify("70########");
		String customerEmailAddress=faker.internet().emailAddress();
		String altCustomerEmailAddress=faker.internet().emailAddress();
		
		
		Customer customer= new Customer(fname, lname, mobileNumber,alternateMobileNumber,customerEmailAddress, altCustomerEmailAddress);
		
		System.out.println(customer);
		
		String flatNumber=faker.numerify("###");
		String apartmentName=faker.address().streetName();
		String streetName=faker.address().streetName();
		String landMark=faker.address().streetName();
		String area=faker.address().streetName();
		String pinCode=faker.numerify("#####");
		
		String state=faker.address().state();
		
		
		
		
		CustomerAddress customerAddress= new CustomerAddress(flatNumber, apartmentName, streetName,landMark, area, 
				pinCode,COUNTRY, state);
		
		System.out.println(customerAddress);
		
		
		//CustomerProduct Fake Object
		
		String dop= DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber=faker.numerify("###############");
		String popUrl = faker.internet().url();
		
		
		CustomerProduct customerProduct= new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,popUrl, 1, 1);
		System.out.println(customerProduct);
		
		System.out.println();
		String fakeRemark=faker.lorem().sentence(5);
		
		// I want to generate a random number between 1 to 27
		Random random = new Random();
		int problemId=random.nextInt(3)+1;
		
		Problems problem= new Problems(problemId, fakeRemark);
		System.out.println(problem);
		
		List<Problems> problemList= new ArrayList<Problems>();
		problemList.add(problem);
		
		createJobPayload = new CreateJobPayload(0,2,1,1, customer, customerAddress, customerProduct, problemList);
		
	}
	
	@Test(description = "Verifying if create job api is able to create Inwrranty job", groups ={"api", "regression","smoke"} )

	public void createJobAPITest() {

		given()
		.spec(requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message",equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",equalTo(1))
		.body("data.job_number",startsWith("JOB_"));

	}
	
}