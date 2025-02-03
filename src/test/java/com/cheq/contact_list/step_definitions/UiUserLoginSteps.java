package com.cheq.contact_list.step_definitions;

import org.openqa.selenium.WebDriver;
import java.awt.AWTException;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import java.util.Properties;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.utils.DriverFactory;
import com.cheq.contact_list.pages.LoginPage;
import com.cheq.contact_list.test_data.TestDataGenerator;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

public class UiUserLoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    Properties property;
    private ConfigReaderUtil configReaderUtil;
    private String testDataUI;
    
   
   
    public UiUserLoginSteps() { 
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver, Hooks.getScreenshotUtil());
        getProperty();
    }
    
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        testDataUI = property.getProperty("test_data_ui");
    }

    String dataGroup = Hooks.getDataGroup();
    
    
    @Before("@TC3.1")
    public void logTestDetails() {
        ExtentCucumberAdapter.addTestStepLog("💡 Purpose: Log In with valid credentials via UI");
        ExtentCucumberAdapter.addTestStepLog("★ Priority: 1️⃣");
        ExtentCucumberAdapter.addTestStepLog("👣 STEPS:");
        ExtentCucumberAdapter.addTestStepLog("1️⃣. Navigate the Contact List App");
        ExtentCucumberAdapter.addTestStepLog("2️⃣. Enter the Valid Credentials");
        ExtentCucumberAdapter.addTestStepLog("3️⃣. Click the Submit Button");
        ExtentCucumberAdapter.addTestStepLog("4️⃣. Verify Success Login");
        ExtentCucumberAdapter.addTestStepLog("═════════════════════════════════════════════════════════════");
    }

    
    @Given("User has an existing account")
    public void user_has_an_existing_account() {
    	
    	
    }
    
       @When("the user enters valid credentials")
    public void the_user_enters_valid_credentials() throws IOException, AWTException {
    	JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "ValidLoginCredentials");
    	
    	String email = loginData.path("email").asText();	
        String password = loginData.path("password").asText();
              
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
               
    }

    @And("the user clicks the Submit button")
    public void the_user_clicks_the_submit_button() throws Exception {
        loginPage.clickSubmitButton();
        
    }

    @Then("the user verifies that the Contact List Page is displayed")
    public void the_user_verifies_that_the_contact_list_page_ss_displayed() throws IOException, AWTException {
        loginPage.verifyContactListPage("innerText", "Contact List");
        
    }
    
    
    @Before("@TC3.2")
    public void logTestDetailsNegative() {
        ExtentCucumberAdapter.addTestStepLog("💡 Purpose: Log In with Invalid credentials via UI");
        ExtentCucumberAdapter.addTestStepLog("★ Priority: 1️⃣");
        ExtentCucumberAdapter.addTestStepLog("👣 STEPS:");
        ExtentCucumberAdapter.addTestStepLog("1️⃣. Navigate the Contact List App");
        ExtentCucumberAdapter.addTestStepLog("2️⃣. Enter the Invalid Credentials");
        ExtentCucumberAdapter.addTestStepLog("3️⃣. Click the Submit Button");
        ExtentCucumberAdapter.addTestStepLog("4️⃣. Verify Failed Login");
        ExtentCucumberAdapter.addTestStepLog("═════════════════════════════════════════════════════════════");
    }
    
    @Given("the user has no existing account")
    public void the_user_has_no_existing_account() {
    	
    	   	   	    	
    }
    
    @When("the user enters invalid credentials")
    public void the_user_enters_invalid_credentials() throws Exception {
        String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
        String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSubmitButton();
       
    }
    
    @Then("the user verifies the corresponding error message")
    public void the_user_verifies_the_corresponding_error_message() throws IOException, AWTException {
        loginPage.verifyInvalidCredErrorMessage("innerText", "Incorrect username or password");
        
    }
}
