package com.cheq.contact_list.step_definitions;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

import com.cheq.contact_list.hooks.Hooks;

import com.cheq.contact_list.pages.LoginPage;
import com.cheq.contact_list.utils.DriverFactory;

import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;

public class UnsuccessfulUserLoginSteps {
	
	WebDriver driver;
	Properties property;
	
	private ScreenshotUtil screenshotUtil;
	private ConfigReaderUtil configReaderUtil;
	
	private LoginPage loginPage;

    private String testDataUI;

    public UnsuccessfulUserLoginSteps() {
        driver = DriverFactory.getDriver();
        screenshotUtil = Hooks.getScreenshotUtil();
        loginPage = new LoginPage(driver, Hooks.getScreenshotUtil());
        getProperty();

    }
  
    /** Initializes properties and the JSON file */
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        testDataUI = property.getProperty("test_data_ui");
    }
	
    @When("User populates login fields with invalid credentials")
    public void user_populates_login_fields_with_invalid_credentials() throws IOException, AWTException {
     
        JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "InvalidLoginCredentials");
        String emailData = loginData.path("email").asText();
        String passwordData = loginData.path("password").asText();
        
        loginPage.enterEmail(emailData);
        loginPage.enterPassword(passwordData);  
    }

    @Then("Verify error message for invalid credentials")
    public void verify_error_message_for_invalid_credentials() throws InterruptedException, IOException, AWTException {   	
		JsonNode contactData = DataDictionaryUtil.getDataNode(testDataUI, "ErrorMsgInvalidLogin");
		String errorMsg = contactData.path("invalidCredErrMsg").asText();
		
		loginPage.verifyInvalidCredErrorMessage("innerText", errorMsg); 	
    }
    
    @When("User populates login fields with blank credentials")
    public void user_populates_login_fields_with_blank_credentials() throws IOException, AWTException {  
    	
        JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "BlankCredentialsLogin");
        String emailData = loginData.path("email").asText();
        String passwordData = loginData.path("password").asText();

        loginPage.enterEmail(emailData);
        loginPage.enterPassword(passwordData);  	
    }
    
    @When("User click submit button | Invalid Login")
    public void user_click_submit_button_invalid_login() throws Exception {
    	loginPage.clickSubmitButton();
    	
    }

    @Then("Verify error message for blank fields")
    public void verify_error_message_for_blank_fields() throws InterruptedException, IOException, AWTException {

		JsonNode contactData = DataDictionaryUtil.getDataNode(testDataUI, "ErrorMsgInvalidLogin");
		String errorMsg = contactData.path("invalidCredErrMsg").asText();
		
		loginPage.verifyInvalidCredErrorMessage("innerText", errorMsg);   	   	
    }

  
}
