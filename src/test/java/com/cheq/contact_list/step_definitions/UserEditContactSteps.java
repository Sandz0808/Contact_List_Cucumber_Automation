package com.cheq.contact_list.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.cheq.contact_list.utils.DriverFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.pages.ContactDetailsPage;
import com.cheq.contact_list.pages.ContactListPage;
import com.cheq.contact_list.pages.EditContactPage;
import com.cheq.contact_list.pages.LoginPage;


public class UserEditContactSteps {
	
	 WebDriver driver;
	 Properties property;
	        
	    private ConfigReaderUtil configReaderUtil;
	    private ContactListPage contactListPage;	      
	    private LoginPage loginPage;
	    private ContactDetailsPage contactDetailsPage;
	    private EditContactPage editContactPage;
	    private String testDataUI;
	    
	    
	    public UserEditContactSteps() {
	        driver = DriverFactory.getDriver();
	        contactListPage = new ContactListPage(driver, Hooks.getScreenshotUtil());
	        loginPage = new LoginPage(driver, Hooks.getScreenshotUtil());
	        contactDetailsPage = new ContactDetailsPage(driver, Hooks.getScreenshotUtil());
	        editContactPage = new EditContactPage(driver, Hooks.getScreenshotUtil());
	        
	        getProperty();

	    }
	    
	    /** Initializes properties and the JSON file */
	    public void getProperty() {
	        configReaderUtil = new ConfigReaderUtil();
	        property = configReaderUtil.initProperty();
	        testDataUI = property.getProperty("test_data_ui");
	    }
		
	    String dataGroup = Hooks.getDataGroup();


		@Given("User logs in to the Contact List App")
		public void user_logs_in_to_the_contact_list_app() throws Exception {
			JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "ValidLoginCredentials");
			String email = loginData.path("email").asText();
		    String password = loginData.path("password").asText();		    
		    loginPage.enterEmail(email);
		    loginPage.enterPassword(password);
		    loginPage.clickSubmitButton();
		}
		
		@When("User clicks the selected contact")
		public void user_clicks_the_selected_contact() throws Exception {
			contactListPage.clickSelectedContact();
		}
		
		@When("User clicks the Edit Contact button")
		public void user_clicks_the_edit_contact_button() throws Exception {
			contactDetailsPage.clickEditContactButton();
		}
		
		@When("User clears the fields to edit")
		public void user_clears_the_fields_to_edit() throws IOException, AWTException {
			editContactPage.clearFirstName();
			editContactPage.clearLastName();
		    
		}
		
		@When("User enters valid details into the cleared fields")
		public void user_enters_valid_details_into_the_cleared_fields() throws IOException, AWTException {
			
			JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "EditContactDetails");		
			String updatedFirstName = loginData.path("email").asText();
		    String updatedLastName = loginData.path("password").asText();	
	        editContactPage.enterEmail(updatedFirstName);
			editContactPage.enterPhoneNumber(updatedLastName);
					    
		}
		
		@When("User clicks the Submit button")
		public void user_clicks_the_submit_button() throws Exception {
			editContactPage.clickSubmitButton();
		    
		}
		
		@Then("User verifies if the contact is updated")
		public void user_verifies_if_the_contact_is_updated() throws IOException, AWTException {
			
			contactDetailsPage.verifyPageLabel("innertext", "");
			contactDetailsPage.verifyUpdatedFirstName("innerText", "");
			contactDetailsPage.verifyUpdatedLastName("innerText", "");
		    
		}
		
		@When("User enters invalid details into the cleared fields")
		public void user_enters_invalid_details() throws Exception {
			editContactPage.clearDateOfBirth();
			
			JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "EditContactDetails");		
			String invalidDOB = loginData.path("invalidDOB").asText();
	        editContactPage.enterEmail(invalidDOB);
	        editContactPage.clickSubmitButton();
	        
		}
		
		@Then("User verifies the error messages")
		public void user_verifies_the_error_message() throws IOException, AWTException {
			editContactPage.verifyErrorMessage("innerText", "Validation failed: birthdate: Birthdate is invalid");
						
		}
		
		
}



