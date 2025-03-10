package com.cheq.contact_list.step_definitions;

import org.openqa.selenium.WebDriver;
import java.awt.AWTException;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.cheq.contact_list.hooks.Hooks;
import java.util.Properties;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DriverFactory;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.pages.ContactDetailsPage;
import com.cheq.contact_list.pages.ContactListPage;
import com.cheq.contact_list.pages.EditContactPage;
import com.cheq.contact_list.pages.LoginPage;


public class UiUserEditContactSteps {
	
		WebDriver driver;
		Properties property;
	    private ConfigReaderUtil configReaderUtil;
	    private ContactListPage contactListPage;	      
	    private LoginPage loginPage;
	    private ContactDetailsPage contactDetailsPage;
	    private EditContactPage editContactPage;
	    private String testDataUI;
	    
	    public UiUserEditContactSteps() {
	        driver = DriverFactory.getDriver();
	        contactListPage = new ContactListPage(driver, Hooks.getScreenshotUtil());
	        loginPage = new LoginPage(driver, Hooks.getScreenshotUtil());
	        contactDetailsPage = new ContactDetailsPage(driver, Hooks.getScreenshotUtil());
	        editContactPage = new EditContactPage(driver, Hooks.getScreenshotUtil());
	        
	        getProperty();
	    }
	    
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
		public void user_clears_the_fields_to_edit() throws IOException, AWTException, InterruptedException {
			editContactPage.clearEmail();
			editContactPage.clearPhoneNum();
			//Thread.sleep(3000);
		}
		
		@When("User enters valid details into the cleared fields")
		public void user_enters_valid_details_into_the_cleared_fields() throws IOException, AWTException {
			
			JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "EditContactDetails");		
			String email = loginData.path("email").asText();
		    String phone = loginData.path("phone").asText();	
	        editContactPage.enterEmail(email);
			editContactPage.enterPhoneNumber(phone);
		}
		
		@When("User clicks the Submit button")
		public void user_clicks_the_submit_button() throws Exception {
			editContactPage.clickSubmitButton();
			//Thread.sleep(3000);
		}
		
		@Then("User verifies if the contact is updated")
		public void user_verifies_if_the_contact_is_updated() throws IOException, AWTException {
			JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "EditContactDetails");
	    	
	    	String email = loginData.path("email").asText();
	        String phone = loginData.path("phone").asText();
			contactDetailsPage.verifyUpdatedEmail("innerText", email);
			contactDetailsPage.verifyUpdatedPhoneNum("innerText", phone);
		}
		
		@When("User enters invalid details into the cleared fields")
		public void user_enters_invalid_details() throws Exception {
			editContactPage.clearDateOfBirth();
			JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "EditContactDetails");		
			String invalidDOB = loginData.path("invalidDOB").asText();
	        editContactPage.enterDateOfBirth(invalidDOB);
	        editContactPage.clickSubmitButton();	        
		}
		
		@Then("User verifies the error messages")
		public void user_verifies_the_error_message() throws IOException, AWTException {
			editContactPage.verifyErrorMessage("innerText", "Validation failed: birthdate: Birthdate is invalid");
		}		
}



