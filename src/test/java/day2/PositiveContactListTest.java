package day2;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PositiveContactListTest {
  String id;
  @Test(enabled = true,description="Getting all Contact List")
  public void getAllContactListInfo() {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts\n")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200); 
  }
  
  @Test(enabled = true,description="Getting Specific Contact1")
  public void getSpecificContactInfo1() {
	  given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/615fa5ecf2967f0ec893ae91")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200); 
  }
  
  @Test(enabled = true,description="Getting Specific Contact2")
  public void getSpecificContactInfo2() {
  Response res = given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/615fa67cf2967f0ec893ae98");
	  
	  System.out.println(res.getTime());
	  
	  res.then()
	  	.log()
	  	.body()
	  	.statusCode(200); 
  }
   
  @Test(enabled = true,description="Adding Contact")
  public void addingContact() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "Prayagraj");
	  loc.put("country", "India");
	  emp.put("jobTitle", "SE");
	  emp.put("company", "LTI");
	  details.put("firstName", "Neha");
	  details.put("lastName", "Singh");
	  details.put("email", "neha@lnt.com");
	  details.put("location", loc);
	  details.put("employer", emp);

//	 String id = given()
//	  			.header("Content-Type","application/json")
//	  			.body(details.toJSONString())
//	  		.when()
//	  			.post("http://3.13.86.142:3000/contacts")
//	  		.then()
//	  			.log()
//	  			.body()
//	  			.statusCode(200)
//	  			.extract()
//	  			.path("_id");
//	  		
//	  System.out.println(id);
	  
	  //To get only particular key value
	  ExtractableResponse<Response> ex = given()
	  			.header("Content-Type","application/json")
	  			.body(details.toJSONString())
	  		.when()
	  			.post("http://3.13.86.142:3000/contacts")
	  		.then()
	  			.log()
	  			.body()
	  			.statusCode(200)
	  			.extract(); 
	  id = ex.path("_id");	
	  //System.out.println(ex.path("_id"));
	  System.out.println(ex.path("firstName"));
	  System.out.println(ex.path("location.city"));
	  System.out.println(ex.path("employer.company"));
 
  }
  
  @Test(enabled = true,dependsOnMethods="addingContact",description="Updating Specific Contact")
  public void updatingContact() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  emp.put("jobTitle", "SE");
	  emp.put("company", "LTI");
	  details.put("firstName", "Nehahaha");
	  details.put("lastName", "Singh");
	  details.put("email", "neha1@lnt.com");
	  details.put("location", loc);
	  details.put("employer", emp);

	        given()
	  			.header("Content-Type","application/json")
	  			.body(details.toJSONString())
	  		.when()
	  			.put("http://3.13.86.142:3000/contacts/"+id)
	  		.then()
	  			.log()
	  			.body()
	  			.statusCode(204);
	  			   
	 
  }
  @Test(enabled = true,dependsOnMethods="updatingContact",description="Getting Specific Contact3")
  public void getSpecificContactInfo3() {
  Response res=given()
	  .when()
	  	.get("http://3.13.86.142:3000/contacts/"+id) ;
	  	
   res.then()
	  	.log()
	  	.body()
	  	.statusCode(200); 
  }
  
  @Test(enabled = true,dependsOnMethods="getSpecificContactInfo3",description="Deleting Specific Contact")
  public void deletingContact() { 
	         given() 
	  		.when()
	  			.delete("http://3.13.86.142:3000/contacts/"+id)
	  		.then() 
	  			.statusCode(204); 
  }
  
  @Test(enabled = true, dependsOnMethods = "deletingContact", description = "Getting specific contact")
  public void getSpecificContact4() {
	Response res =   given()
	   .when()
	    .get("http://3.13.86.142:3000/contacts/"+id);
	   
	    System.out.println(res.getTime());
  
	 res.then()
	    .log()
	    .body()
	    .statusCode(404);
	  
  }
}