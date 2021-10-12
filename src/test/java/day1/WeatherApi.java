package day1;
 
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherApi {
  @Test(description="Getting weather information of specific city")
  public void getWeather() {
	  RestAssured.given() //Some pre-condition like Authentication
	             .when()
	             .get("https://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=5c4e4a5a558d3c8005bc4fcf6c5a5b91")
                 .then() //some post-condition like Verification
                 .log()    //print data in console
                 .body()
                 .statusCode(200);
}
  
  @Test(description="Getting weather information of specific city")
  public void getWeather2() {
	Response res=  RestAssured.given() //Some pre-condition like Authentication
	             .when()
	             .get("https://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=5c4e4a5a558d3c8005bc4fcf6c5a5b91");
	System.out.println(res.prettyPrint());
	System.out.println(res.getTime());
	System.out.println(res.getStatusCode());
	System.out.println(res.getContentType());
	Assert.assertEquals(res.getStatusCode(), 200); //checking with help of testng assertion
	
               
}
  
  @Test(enabled=false,description="Getting weather information of specific city")
  public void getWeather3() {
	  RestAssured.given() //Some pre-condition like Authentication
	  .queryParam("q", "Mumbai")
	  .queryParam("appid","5c4e4a5a558d3c8005bc4fcf6c5a5b91" )
	             .when()
	             .get("https://api.openweathermap.org/data/2.5/weather")
                 .then() //some post-condition like Verification
                 .log()    //print data in console
                 .body()
                 .statusCode(200);
  }
  
  @Test(enabled=false,description="Getting weather information of specific city")
  public void getWeather4() {
	  Map<String,String>param=new HashMap<String,String>();
	  param.put("q", "Mumbai");
	  param.put("appid","5c4e4a5a558d3c8005bc4fcf6c5a5b91");
	  RestAssured.given() //Some pre-condition like Authentication
	  .queryParams(param)
	 
	             .when()
	             .get("https://api.openweathermap.org/data/2.5/weather")
                 .then() //some post-condition like Verification
                 .log()    //print data in console
                 .body()
                 .statusCode(200);
  }
}
















































