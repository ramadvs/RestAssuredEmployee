package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_Employee_Record extends TestBase{
	
@BeforeClass
void deleteEmployee() throws InterruptedException
{
	logger.info("****Started TC005Delete_Employee_record********");
	
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	
  httpRequest = RestAssured.given();
  
  response = httpRequest.request(Method.GET,"/employees");
  
  // JsonPath object 
  JsonPath jsonPathEvaluator = response.jsonPath();
  
  //Capture id
  String empID = jsonPathEvaluator.get("[0].id");
  response = httpRequest.request(Method.DELETE, "/delete/"+empID); // pass the captured ID to delete record
  
  Thread.sleep(4000);	
	
}

@Test
void checkResponseBody()
{
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
	//Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
}
@Test
void checkStatusCode()
{
	int Statuscode = response.getStatusCode();
	System.out.println("the status code is: "+ Statuscode);
	Assert.assertEquals(Statuscode, 200);
}
@Test
void checkStatusline()
{
	String Statusline = response.getStatusLine();
	System.out.println("the status line is: "+ Statusline);
	Assert.assertEquals(Statusline, "HTTP/1.1 200 OK");
}

@Test
void checkContentType()
{
	String Contenttype = response.header("Content-Type");
	Assert.assertEquals(Contenttype,"application/json;charset=utf-8");
}
@Test
void CheckServerType()
{	 
	 String Servertype = response.header("server");
	 Assert.assertEquals(Servertype, "nginx/1.16.0");
}

@Test
void checkContentEncoding()
{	 
	 String Contentencoding = response.header("Content-Encoding");
	 System.out.println(Contentencoding);		 
}

@AfterTest
void tearDown()
{
	logger.info("********Finished TC005_Delete_Employee_Record *********");
}
	


}
