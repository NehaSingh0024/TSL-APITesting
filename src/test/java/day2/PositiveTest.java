package day2;


import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PositiveTest {
	String id;
  @Test(enabled = false, description = "Getting the entire contact list")
  public void getAllContactList() {
	  given()
	   .when()
	    .get("http://3.13.86.142:3000/contacts")
	   .then()
	    .log()
	    .body()
	    .statusCode(200);
	  
  }
  @Test(enabled = false, description = "Getting specific contact")
  public void getSpecificContact() {
	  given()
	   .when()
	    .get("http://3.13.86.142:3000/contacts/615fb453f2967f0ec893aea7")
	   .then()
	    .log()
	    .body()
	    .statusCode(200);
}
  @Test(enabled = false, description = "Getting specific contact")
  public void getSpecificContact2() {
	Response res =   given()
	   .when()
	    .get("http://3.13.86.142:3000/contacts/615fb453f2967f0ec893aea7");
	   
	    System.out.println(res.getTime());
	    
	 res.then()
	    .log()
	    .body()
	    .statusCode(200);
}
  
  @Test(enabled = true, description = "Add a contact")
  public void addContact() {
	  JSONObject details = new JSONObject();
	  JSONObject location = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  location.put("city", "Bengaluru");
	  location.put("country", "India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Michael");
	  details.put("lastName", "Jordan");
	  details.put("email", "jumpman23@gmail.com");
	  details.put("location", location);
	  details.put("employer", emp);
	  
ExtractableResponse<Response> res =	     given()
	        .header("Content-Type", "application/json")
	        .body(details.toJSONString())
	    .when()
	        .post("http://3.13.86.142:3000/contacts")
	    .then()
	        .log()
	        .body()
	        .statusCode(200)
	        .extract();
String name = res.path("firstName");
id = res.path("_id");
String city = res.path("location.city");
String com = res.path("employer.company");
	        //.path("_id");

System.out.println(name);
System.out.println(id);
System.out.println(city);
System.out.println(com);
  }
  @Test(enabled = true, dependsOnMethods = "addContact", description = "Updating specific contact")
  public void updateContact() {
	  JSONObject details = new JSONObject();
	  JSONObject location = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  location.put("city", "Bengaluru");
	  location.put("country", "India");
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  details.put("firstName", "Kobe");
	  details.put("lastName", "Jordan");
	  details.put("email", "jumpman23@gmail.com");
	  details.put("location", location);
	  details.put("employer", emp);
	  
         given()
	        .header("Content-Type", "application/json")
	        .body(details.toJSONString())
	    .when()
	        .put("http://3.13.86.142:3000/contacts/"+id)
	    .then()
	        .log()
	        .body()
	        .statusCode(204);
  }
  @Test(enabled = true, dependsOnMethods = "updateContact", description = "Getting specific contact")
  public void getSpecificContact3() {
	Response res =   given()
	   .when()
	    .get("http://3.13.86.142:3000/contacts/"+id);
	   
	    System.out.println(res.getTime());
  
	 res.then()
	    .log()
	    .body()
	    .statusCode(200);
	 //System.out.println(res.path("firstName"));
}
  
  @Test(enabled = true, dependsOnMethods = "getSpecificContact3", description = "deleting a contact")
  public void deleteContact() {
	   given()
	  .when()
	     .delete("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	     .statusCode(204);
	
  }
  @Test(enabled = true, dependsOnMethods = "deleteContact", description = "Getting specific contact")
  public void getSpecificContact4() {
	Response res =   given()
	   .when()
	    .get("http://3.13.86.142:3000/contacts/"+id);
	   
	    System.out.println(res.getTime());
  
	 res.then()
	    .log()
	    .body()
	    .statusCode(404);
	 //System.out.println(res.path("firstName"));
}
} 