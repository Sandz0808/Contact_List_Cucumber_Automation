package com.cheq.contact_list.step_definitions;

import org.openqa.selenium.WebDriver;
import java.awt.AWTException;
import java.io.IOException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Properties;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.DriverFactory;
import com.cheq.contact_list.pages.ContactDetailsPage;
import com.cheq.contact_list.pages.ContactListPage;



public class UiDeleteContactSteps {
	
	private WebDriver driver;
    private ContactDetailsPage contactDetailsPage;
    private ContactListPage contactListPage;	
    
    Properties property;
    
    public UiDeleteContactSteps() { 
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