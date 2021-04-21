package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;

public class GrantContactDetails extends TestBase
{

	@FindBy(xpath=".//ul[@class='nav nav-sidebar sidebar-menu']/li[2]")
	WebElement contactDetailsLink;
	
	
	@FindBy(xpath=".//div[@class='bgp-questions-grp']//h3[contains(text(),'Main Contact Person')]")
	WebElement mainContactSubmission;
	
	@FindBy(id="react-contact_info-name-label")
	WebElement nameLabel;
	
	@FindBy(id="react-contact_info-designation-label")
	WebElement jobTitleLabel;
	
	@FindBy(id="react-contact_info-phone-label")
	WebElement contactLabel;
	
	@FindBy(id="react-contact_info-primary_email-label")
	WebElement emailLabel;
	
	@FindBy(id="react-contact_info-secondary_email-label")
	WebElement alternateEmailLabel;
	
	@FindBy(id="react-contact_info-correspondence_address-copied")
	WebElement sameAsRegAddressChkBox;
	
	@FindBy(id="react-contact_info-correspondence_address-postal")
	WebElement postalCodeTextBox;
	
	@FindBy(id="react-contact_info-correspondence_address-postal-postal")
	WebElement postalCodeSearchBttn;
	
	@FindBy(id="react-contact_info-correspondence_address-postal-alert")
	WebElement invalidPostalCodeErrorText;
	
	@FindBy(id="react-contact_info-correspondence_address-block")
	WebElement blockHouseNumberArea;
	
	@FindBy(id="react-contact_info-correspondence_address-street")
	WebElement StreetArea;
	

	@FindBy(id="react-contact_info-offeree_name-label")
	WebElement loaNameLbl;

	@FindBy(id="react-contact_info-offeree_designation-label")
	WebElement loaJobTitleLbl;

	@FindBy(id="react-contact_info-offeree_email-label")
	WebElement loaEmailLbl;

	@FindBy(id="react-contact_info-copied")
	WebElement sameAsMainPersonChkBox;
	
	@FindBy(id="react-contact_info-name")
	WebElement nameTextBox;
	
	@FindBy(id="react-contact_info-designation")
	WebElement jobTtileTextBox;
	
	@FindBy(id="react-contact_info-phone")
	WebElement phoneTextBox;
	
	@FindBy(id="react-contact_info-primary_email")
	WebElement primaryEmailTextBox;
	
	@FindBy(id="react-contact_info-offeree_name")
	WebElement LoanameTextBox;
	
	@FindBy(id="react-contact_info-offeree_designation")
	WebElement LoaJobTitleTextBox;
	
	@FindBy(id="react-contact_info-offeree_email")
	WebElement LoaEmailTextBox;
	
	@FindBy(id="save-btn")
	WebElement saveBttn;
	
	public GrantContactDetails()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void navigateToContactDetails() 
	{
		contactDetailsLink.click();
	}
	
	public void validateContactDetailsPage() 
	{
		Assert.assertNotNull(nameLabel, "Name label should be present");
		Assert.assertNotNull(jobTitleLabel, "Job Title label should be present");
		Assert.assertNotNull(contactLabel, "Contact label should be present");
		Assert.assertNotNull(emailLabel, "Email label should be present");
		Assert.assertNotNull(alternateEmailLabel, "Alternate Email label should be present");
	}
	
	public void validatePostCodeFunctionality(String validPostCode, String invalidPostCode) 
	
	{
	
		String invalidPostCodeError = "Oops, that’s not a 6-digit Singapore postal code";
		postalCodeTextBox.sendKeys(invalidPostCode);
		postalCodeSearchBttn.click();
		Assert.assertEquals(invalidPostalCodeErrorText.getText(),invalidPostCodeError);
		postalCodeTextBox.clear();
		postalCodeTextBox.sendKeys(validPostCode);
		postalCodeSearchBttn.click();
		Assert.assertNotNull(blockHouseNumberArea.getAttribute("value"), "Block House Number should not be null");
		Assert.assertNotNull(StreetArea.getAttribute("value"), "Street Number should not be null");
		
	}
	
	public void validateLOA()
	
	{
		Assert.assertNotNull(LoanameTextBox, "Validate that LOA Name text box is present");
		Assert.assertNotNull(loaNameLbl, "Validate that LOA Name Label is present");
		Assert.assertNotNull(loaJobTitleLbl, "Validate that LOA Job Title label is present");
		Assert.assertNotNull(LoaJobTitleTextBox, "Validate that LOA Job Title Text Box is present");
		Assert.assertNotNull(loaEmailLbl, "Validate that LOA Email label is present");
		Assert.assertNotNull(LoaEmailTextBox, "Validate that LOA Email Text Box is present");
		
	}
	
	public void enterContactDetailsAndSave(String name , String jobTitle, String contactNo,
			String email,String PostalCode)
	
	{
		nameTextBox.clear();
		nameTextBox.sendKeys(name);
		jobTtileTextBox.clear();
		jobTtileTextBox.sendKeys(jobTitle);
		phoneTextBox.clear();
		phoneTextBox.sendKeys(contactNo);
		primaryEmailTextBox.clear();
		primaryEmailTextBox.sendKeys(email);
		postalCodeTextBox.clear();
		postalCodeTextBox.sendKeys(PostalCode);
		postalCodeSearchBttn.click();
		sameAsMainPersonChkBox.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		saveBttn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.navigate().refresh();
		Assert.assertEquals(nameTextBox.getAttribute("value"), name);
		Assert.assertEquals(jobTtileTextBox.getAttribute("value"), jobTitle);
		Assert.assertEquals(phoneTextBox.getAttribute("value"), contactNo);
		Assert.assertEquals(primaryEmailTextBox.getAttribute("value"), email);
		Assert.assertEquals(postalCodeTextBox.getAttribute("value"), PostalCode);
		
	}
	
	
	
	
}
