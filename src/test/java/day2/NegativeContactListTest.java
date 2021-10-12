package day2;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
 

import static io.restassured.RestAssured.*;

public class NegativeContactListTest {
  @Test
  public void RecordNotFound() {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/5")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(404); 
  }
  
  @Test(enabled = true,description="Adding Contact with Missing details")
  public void addingContact() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "Bengaluru");
	  loc.put("country", "India");
	  emp.put("jobTitle", "SE");
	  emp.put("company", "LTI");
	  details.put("firstName", "");
	  details.put("lastName", "Singh");
	  details.put("email", "neha@lnt.com");
	  details.put("location", loc);
	  details.put("employer", emp);

	 String error = given()
	  			.header("Content-Type","application/json")
	  			.body(details.toJSONString())
	  		.when()
	  			.post("http://3.13.86.142:3000/contacts")
	  		.then()
	  			.log()
	  			.body()
	  			.statusCode(400)
	  			.extract()
	  			.path("err");
	  		
	  Assert.assertTrue(error.contains("firstName: First Name is required"));
	   
  }
  
  @Test(enabled = true,description="Adding Contact with too many Characters")
  public void addingContactTooManyChar() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "BengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluruBengaluru");
	  loc.put("country", "India");
	  emp.put("jobTitle", "SE");
	  emp.put("company", "LTI");
	  details.put("firstName", "Maani");
	  details.put("lastName", "Singh");
	  details.put("email", "Maani@lnt.com");
	  details.put("location", loc);
	  details.put("employer", emp);

	 String error = given()
	  			.header("Content-Type","application/json")
	  			.body(details.toJSONString())
	  		.when()
	  			.post("http://3.13.86.142:3000/contacts")
	  		.then()
	  			.log()
	  			.body()
	  			.statusCode(400)
	  			.extract()
	  			.path("err");
	  		
	  Assert.assertTrue(error.contains("is longer than the maximum allowed length (30)"));
	    
  }
  
  @Test(enabled = true,description="Adding Contact with Numbers in First Name")
  public void invalidFirstName() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "Bengaluru");
	  loc.put("country", "India");
	  emp.put("jobTitle", "SE");
	  emp.put("company", "LTI");
	  details.put("firstName", "Neha4");
	  details.put("lastName", "Singh");
	  details.put("email", "neha@lnt.com");
	  details.put("location", loc);
	  details.put("employer", emp);

	 String error = given()
	  			.header("Content-Type","application/json")
	  			.body(details.toJSONString())
	  		.when()
	  			.post("http://3.13.86.142:3000/contacts")
	  		.then()
	  			.log()
	  			.body()
	  			.statusCode(400)
	  			.extract()
	  			.path("err");
	  		
	  Assert.assertTrue(error.contains("Contacts validation failed: firstName: Validator failed for path `firstName` with value "));
  }
  

  @Test(enabled = true,description="Adding Contact with missing @ in email")
  public void invalidEmail() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "Bengaluru");
	  loc.put("country", "India");
	  emp.put("jobTitle", "SE");
	  emp.put("company", "LTI");
	  details.put("firstName", "Neha");
	  details.put("lastName", "Singh");
	  details.put("email", "nehalnt.com");
	  details.put("location", loc);
	  details.put("employer", emp);

	 String error = given()
	  			.header("Content-Type","application/json")
	  			.body(details.toJSONString())
	  		.when()
	  			.post("http://3.13.86.142:3000/contacts")
	  		.then()
	  			.log()
	  			.body()
	  			.statusCode(400)
	  			.extract()
	  			.path("err");
	  		
	  Assert.assertTrue(error.contains("Contacts validation failed: email: Validator failed for path `email` "));
  }
}
