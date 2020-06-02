package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.*;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_Put_Employee_Record extends TestBase{
	
	String empName=RestUtils.empName();
	String empSalary = RestUtils.empsal();
	String empAge = RestUtils.empAge();

	
@BeforeClass
      void createEmployee() throws InterruptedException {
		
        logger.info("******Started TC004_Put_Employee_Record******");
		
    RestAssured.baseURI=("http://dummy.restapiexample.com/api/v1");
		
     httpRequest = RestAssured.given();
		
    //JSon object/request payload to send along with post request
	JSONObject requestparams = new JSONObject();
			
			requestparams.put("name", empName);
			requestparams.put("salary", empSalary);
			requestparams.put("age", empAge);
		
			//Specifying the type of data we are sending.Adding a header
			httpRequest.header("Content-Type","application/json"); 
			

			//attaching Json pay load to request
			httpRequest.body(requestparams.toJSONString());
			
			//response object & sending request
			response = httpRequest.request(Method.PUT, "/update/"+empID);
		
			Thread.sleep(4000);
		
	}
@Test
void checkResponseBody()
{
	String Responsebody = response.getBody().asString();
	System.out.println("The response body is "+Responsebody);
	/*Assert.assertEquals(Responsebody.contains(empName), true);
	Assert.assertEquals(Responsebody.contains(empSalary), true);
	Assert.assertEquals(Responsebody.contains(empAge), true);
	*/
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
	logger.info("********Finished TC004_Put_Employee_Record *********");
}
	

}
