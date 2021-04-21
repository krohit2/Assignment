package com.org.sdet.selenium.com.org.sdet.selenium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import com.qa.base.TestBase;
import com.qa.pages.GetNewGrant;
import com.qa.pages.GrantContactDetails;
import com.qa.pages.HomePage;
import com.qa.pages.Login;
import com.qa.utill.*;

public class TestCases extends TestBase
{
	Login loginPage ;
	HomePage homePage;
	GetNewGrant getNewGrant;
	GrantContactDetails grantContactDetails;
	
	   
	public TestCases()
	{
			super();
			
	}
	
	
	@Parameters({"url","userName","password","user","fullName","UEN","role"})  
	@BeforeMethod
	public void setUp(String url, String userName , String password,
			String user, String fullName , String UEN, String role) throws InterruptedException 
	{
		String finalURL = "https://" + userName +":"+password+"@"+url;
		initialization(finalURL);
		homePage = new HomePage();
		homePage.clickonLogin();
		loginPage = new Login();
		
		loginPage.performLogin(user, fullName, UEN, role);
		getNewGrant = new GetNewGrant();
		getNewGrant.createNewGrant();
		
	}
	
	
	/*
	 *Assignment 1 - first part : Automate the below mentioned site
	 *1)    http://www.demoqa.com/selectable 
	 */
	
	
	@Parameters({"labelValues","eligibilityWarning","targetURL"})  
	@Test
	public void validateGrantOptions(String labelValues, String eligibilityWarning, String targetURL) 
	{
		
		String[] expValues = labelValues.split(";");
		getNewGrant.validateOptionsinGrantApplication(expValues);
		getNewGrant.validationofGrantWhenSelectedNo(eligibilityWarning);
		getNewGrant.validateFAQLink(targetURL);
		getNewGrant.registerandValidateGrantApplicationAfterSaving();
		
	}
	
	@Parameters({"name","jobTitle","email","phoneNumber","postalCode"})  
	@Test(priority=1)
	public void validateContactDetailsSection(String name , String jobTitle, String email,
			String phoneNumber,String postalCode)
	{
		grantContactDetails = new GrantContactDetails();
		grantContactDetails.navigateToContactDetails();
		grantContactDetails.validateContactDetailsPage();
		grantContactDetails.validatePostCodeFunctionality(postalCode, "12321");
		grantContactDetails.validateLOA();
		grantContactDetails.enterContactDetailsAndSave(name, jobTitle, phoneNumber, email, postalCode);
		
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	


}
