package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;

public class GetNewGrant extends TestBase
{
	
	@FindBy(xpath=".//a[@id='dashboard-menubox-app-apply-grant']/section")
	WebElement createNewGrantLink;

	@FindBy(xpath=".//span[contains(text(),'Set up an overseas market')]")
	WebElement functionalAreaSelector;
	
	@FindBy(xpath=".//div[contains(text(),'Bring my business overseas')]")
	WebElement developementAreaSelector;
	
	
	@FindBy(id="go-to-grant")
	WebElement bttnApply;
	
	@FindBy(id="keyPage-form-button")
	WebElement bttnProceed;
	
	@FindBy(id="IT")
	WebElement businessIT;
	
	@FindBy(id="react-eligibility-sg_registered_check-true")
	WebElement radiobttnregistryCheckTrue;
	
	@FindBy(xpath=".//input[@id='react-eligibility-sg_registered_check-false']//parent::label")
	WebElement radiobttnregistryCheckFalse;
	
	@FindBy(xpath=".//input[@id='react-eligibility-turnover_check-false']//parent::label")
	WebElement radiobttnTurnover_checkCheckFalse;
	
	@FindBy(id="react-eligibility-turnover_check-true")
	WebElement radiobttnTurnover_checkCheckTrue;
	
	
	@FindBy(xpath=".//input[@id='react-eligibility-global_hq_check-false']//parent::label")
	WebElement radiobttnlocalEquityCheckFalse;
	
	@FindBy(id="react-eligibility-global_hq_check-true")
	WebElement radiobttnlocalEquityCheckTrue;
	
	@FindBy(xpath=".//input[@id='react-eligibility-new_target_market_check-false']//parent::label")
	WebElement radiobttnmarketCheckFalse;
	
	@FindBy(id="react-eligibility-new_target_market_check-true")
	WebElement radiobttnmarketCheckTrue;
	
	@FindBy(xpath=".//input[@id='react-eligibility-started_project_check-false']//parent::label")
	WebElement radiobttnProjectCheckFalse;
	
	@FindBy(id="react-eligibility-started_project_check-true")
	WebElement rradiobttnProjectCheckTrue;
	
	@FindBy(xpath=".//div[@class='eligibility-advice']/span")
	WebElement eligibilityAlert;
	
	@FindBy(xpath=".//div[@class='eligibility-advice']/span/a")
	WebElement FAQlink;
	
	@FindBy(id="save-btn")
	WebElement saveBttn;
	
	public GetNewGrant()
	{
		PageFactory.initElements(driver, this);
	}
		
	
	
	public void createNewGrant()
	{
		createNewGrantLink.click();
		businessIT.click();
		developementAreaSelector.click();
		functionalAreaSelector.click();
		bttnApply.click();
		bttnProceed.click();
		
	}
	
	public void validateOptionsinGrantApplication(String[] validationtexts)
	{
		List<WebElement> labels = driver.findElements(By.className("control-label bgp-label"));
		int i = 0 ;
		for (WebElement label : labels) 
		{
			
			Assert.assertEquals(label.getText(), validationtexts[i]);
			i = i+1;
		}
		
	}
	
	
	public void validationofGrantWhenSelectedNo(String eligibilityWarning) 
	{
		
		radiobttnregistryCheckFalse.click();
		Assert.assertEquals(eligibilityAlert.getText(), eligibilityWarning);
		radiobttnregistryCheckTrue.click();
		
		radiobttnTurnover_checkCheckFalse.click();
		Assert.assertEquals(eligibilityAlert.getText(), eligibilityWarning);
		radiobttnTurnover_checkCheckTrue.click();
		
		radiobttnlocalEquityCheckFalse.click();
		Assert.assertEquals(eligibilityAlert.getText(), eligibilityWarning);
		radiobttnlocalEquityCheckTrue.click();
		
		radiobttnmarketCheckFalse.click();
		Assert.assertEquals(eligibilityAlert.getText(), eligibilityWarning);
		radiobttnmarketCheckTrue.click();
		
		radiobttnProjectCheckFalse.click();
		Assert.assertEquals(eligibilityAlert.getText(), eligibilityWarning);
		rradiobttnProjectCheckTrue.click();
	}
	
	
	public void validateFAQLink(String targetURL) 
	{
		String actualURL ;
		radiobttnProjectCheckFalse.click();
		FAQlink.click();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    actualURL = driver.getCurrentUrl();
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	    Assert.assertEquals(actualURL, targetURL);
	}
	
	
	public void registerandValidateGrantApplicationAfterSaving()
	{
		rradiobttnProjectCheckTrue.click();
		saveBttn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();
		
		
		
		Assert.assertTrue(radiobttnregistryCheckTrue.isSelected());
		Assert.assertTrue(radiobttnTurnover_checkCheckTrue.isSelected());
		Assert.assertTrue(radiobttnlocalEquityCheckTrue.isSelected());
		Assert.assertTrue(radiobttnmarketCheckTrue.isSelected());
		Assert.assertTrue(rradiobttnProjectCheckTrue.isSelected());
		
		
	}
	

}
