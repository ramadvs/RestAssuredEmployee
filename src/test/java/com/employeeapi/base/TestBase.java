package com.employeeapi.base;



import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID ="2";//51838 - Hard coded  empid value to get the details of a single employee & update
	
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		logger = Logger.getLogger("EmployeesRestAPI");//Initiated logger and mentioned the name of the project to be shown in logs
		PropertyConfigurator.configure("log4j.properties"); // specifying the path of the log4j file
		logger.setLevel(Level.DEBUG);
	}
	
	
		
	

}
