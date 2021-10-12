package day2;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;
public class Github {
	
  @BeforeTest 
  public void BeforeTest() {
	  baseURI="https://api.github.com/user/repos";
	  authentication=oauth2("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");
  }
  @Test(enabled=true)
  public void gettingAllRepos() {
	   given() 
	  .when()
	  		.get(baseURI)
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
  
  @Test(enabled=true)
  public void creatingRepos() {
	  JSONObject data = new JSONObject();
	  data.put("name", "RestAssuredCreatedRepo2");
	  data.put("description", "This repo is Created By RestAssured Tool Again");
	  data.put("homepage", "https://github.com/NehaSingh0024");
	  
	   given() 
	  		.header("Content-Type","application/json")
	  		.body(data.toJSONString())
	  .when()
	  		.post(baseURI)
	  .then()
	  		.log()
	  		.body()//printing in console 
	  		.statusCode(201)
	  		.time(Matchers.lessThan(6000L),TimeUnit.MILLISECONDS);
  }
}
