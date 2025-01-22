package com.cheq.contact_list.step_definitions;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.pages.ContactDetailsPage;
import com.cheq.contact_list.pages.ContactListPage;
import com.cheq.contact_list.pages.LoginPage;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.utils.DriverFactory;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteContactSteps {
	
	private WebDriver driver;
    
    private ContactDetailsPage contactDetailsPage;
    private ContactListPage contactListPage;	
    
    Properties property;
    
              
    public DeleteContactSteps() { 
        driver = DriverFactory.getDriver();
        contactDetailsPage = new ContactDetailsPage(driver, Hooks.getScreenshotUtil());
        contactListPage = new ContactListPage(driver, Hooks.getScreenshotUtil());
    }
           

	@When("User clicks the Delete Contact button")
	public void user_clicks_the_delete_contact_button() throws Exception {
		contactDetailsPage.clickDeleteContact();
		
	}
	
	@Then("User verifies if the contact is Deleted")
	public void user_verifies_if_the_contact_is_deleted() throws IOException, AWTException {
		contactListPage.verifyContactListLabel("innerText", "Contact List");
		    
	}
	
}