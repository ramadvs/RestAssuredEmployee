package com.employeeapi.testCases;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.*;

import io.restassured.RestAssured;
import io.restassured.http.Method;



public class TC001_Get_All_Employees extends TestBase{
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("*******started TC001_Get_All_Employees **********"); 
		
		RestAssured.baseURI= "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(3000);	//mili seconds
	}
	
	 @Test
	 void checkResponseBody()
	 {
		 logger.info("********* Checking response body ************");
		 
		 String responseBody = response.getBody().asString();
		 logger.info("Response Body is: "+ responseBody);
		 Assert.assertTrue(responseBody!=null);	 	 
	 }
	 
	 @Test
	 void checkStatusCode()
	 {
		 logger.info("******* Checking status code **********");
		 
		 int statusCode = response.getStatusCode();
		 logger.info(" Status code is: "+ statusCode);
		 Assert.assertEquals(statusCode, 200);	 	 
	 }
	 
	 @Test
	 void checkResponseTime()
	 {
		 logger.info("******* Checking response time ***********"); 
		 
		 long responseTime = response.getTime();
		 logger.info("The response time is: "+ responseTime);
		 
		 if(responseTime>2000)
	    logger.warn("Response Time is more than 2000");
		 
		// Assert.assertTrue(responseTime>2000);	 	 
	 }
	 
	 @Test
	 void checkStatusLine()
	 {
		 logger.info("******* Checking status line ************");  
		 
		 String statusLine = response.getStatusLine();
		 logger.info("Status line is: "+ statusLine);
		 Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	 }
	 
	 @Test
	 void checkContentType()
	 {
		 logger.info("*********Checking content type/header *********");  
		 
		 String contentType = response.header("Content-Type");
		 logger.info("The content type is: "+ contentType);
		 Assert.assertEquals(contentType, "application/json;charset=utf-8");
	 }
	 
	 @Test
	 void CheckServerType()
	 {
		 logger.info("*********Checking server type ***************");  
		 
		 String serverType = response.header("server");
		 logger.info("The server type is: "+ serverType);
		 Assert.assertEquals(serverType, "nginx/1.16.0");
	 }
	 
	 @Test
	 void checkContentEncoding()
	 {
		 logger.info("********checking content encoding***********");
		 
		 String contentEncoding = response.header("Content-Encoding");
		 logger.info("content-encoding is: "+ contentEncoding);
		 Assert.assertEquals(contentEncoding, "gzip");		 
	 }
	 
	 @Test
	 void checkContentLength()
	 {
		 logger.info("********checking content length***********");
		 
		 String contentLength = response.header("Content-Length");
		 logger.info("The content legth is "+ contentLength);
		 
		 int x= Integer.parseInt(contentLength);
		 
		 if(x < 100)
		logger.warn("The content length is less than 100");
		 
		 Assert.assertTrue(x>100);	 	 
	 }
	
	 @Test
	void checkCookies()
	{
	logger.info("***********  Checking Cookies **********");

	//String cookie = response.getCookie("PHPSESSID");
	//Assert.assertEquals(cookie,"1esuvsfslcmiee2bfrsgnijtg0");
	}
	 
	@AfterClass
	void tearDown()
	{
		logger.info("******Finished TC001_get_all_employees*********");
	}
	
}
