package com.employeeapi.testCases;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_Employee_Record extends TestBase{
	
	@BeforeClass
	
	void getSingleEmployeeData() throws InterruptedException 
	{
       logger.info("*******started TC002_Get_Single_Employee_Record **********"); 
		
		RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/employee/"+empID);
		
		Thread.sleep(5000);	//mili seconds
		
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		logger.info("The response body is: "+ responseBody);
		//Assert.assertEquals(responseBody.contains("empid"), true);
	}
	
	@Test
	void checkStatusCode() 
	{
		int statusCode = response.getStatusCode();
		logger.info("The status code is: "+ statusCode);
		//Assert.assertEquals(statusCode, 200);
	}
	@Test
	void checkResponseTime() 
	{
		long responseTime =response.getTime();
		logger.info("The response time is: "+ responseTime);
		Assert.assertTrue(responseTime<4000);
	}
	
	@Test
	void checkStatusLine() 
	{
		 String statusLine = response.getStatusLine();
		 logger.info("The status is: "+ statusLine);
		 //Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");	
	}
	
	@Test
	void checkContentType() 
	{
		 String contentType = response.header("Content-Type");
		 logger.info("The conetnt type is: "+ contentType);
		 Assert.assertEquals(contentType, "application/json;charset=utf-8");	
	}
	
	@Test
	 void CheckServerType()
	 { 
		 String serverType = response.header("server");
		 logger.info("The server type is: "+ serverType);
		 Assert.assertEquals(serverType, "nginx/1.16.0");
	 }
	
	@Test
	 void checkContentLength()
	 { 
		 String contentLength = response.header("Content-Length");
		 logger.info("The content legth is "+ contentLength); 
		 int x= Integer.parseInt(contentLength); 
		 Assert.assertTrue(x<1500);	 	 
	 }
	 
	@AfterTest
	void tearDown()
	{
		logger.info("********Finished TC002_Get_Single_Employee_Record *********");
	}
	
	
	
	
	

}
