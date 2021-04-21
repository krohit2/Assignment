package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class HomePage extends TestBase 
{

	@FindBy(id="login-button")
	WebElement loginBttn;
	
	
	public HomePage() 
	{
		PageFactory.initElements(driver, this);
	}

	
	public void clickonLogin() 
	{
		loginBttn.click();
	}
}
