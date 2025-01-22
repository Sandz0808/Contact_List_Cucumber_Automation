package com.cheq.contact_list.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.utils.DriverFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.cheq.contact_list.pages.LoginPage;
import com.cheq.contact_list.test_data.TestDataGenerator;

public class UserLoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    
    Properties property;
    private ConfigReaderUtil configReaderUtil;
    private String testDataUI;
   

    public UserLoginSteps() { 
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
