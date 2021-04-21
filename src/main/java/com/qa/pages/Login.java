package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;

public class Login extends TestBase

{
	
	
	@FindBy(name="CPUID")
	WebElement userName;
	
	@FindBy(name="CPUID_FullName")
	WebElement fullName;
	
	@FindBy(name="CPEntID")
	WebElement uen;
	
	@FindBy(name="CPRole")
	WebElement roleSelector;
	
	@FindBy(xpath = ".//select[@name='CPRole']//following-sibling::button")
	WebElement loginBttn;
	
	
	public Login() 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void performLogin(String UserName , String FullName , String UEN, String Role) 
	{
		userName.clear();
		userName.sendKeys(UserName);
		fullName.clear();
		fullName.sendKeys(FullName);
		uen.clear();
		uen.sendKeys(UEN);
		Select roleSelect = new Select(roleSelector);
		roleSelect.selectByValue(Role);
		loginBttn.click();
		
	}

	
	
	
}
