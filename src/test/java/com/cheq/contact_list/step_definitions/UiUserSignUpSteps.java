package com.cheq.contact_list.step_definitions;

import org.openqa.selenium.WebDriver;
import java.awt.AWTException;
import java.io.IOException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.fasterxml.jackson.databind.JsonNode;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import java.util.Properties;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.utils.DriverFactory;
import com.cheq.contact_list.pages.SignupPage;
import com.cheq.contact_list.test_data.TestDataGenerator;


public class UiUserSignUpSteps {
	
    private WebDriver driver;
    private SignupPage SignupPage;
    Properties property;
    private ConfigReaderUtil configReaderUtil;
    private String testDataUI;
   

    public UiUserSignUpSteps() {
        driver = DriverFactory.getDriver();
        SignupPage = new SignupPage(driver, Hooks.getScreenshotUtil());
        getProperty();       
    }
    
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        testDataUI = property.getProperty("test_data_ui");
    }

    String dataGroup = Hooks.getDataGroup();
    
    @Given("the user clicks the Signup button")
    public void user_clicks_the_signup_button() throws Exception {
        SignupPage.clickSignupButton();
    }

    @When("the user enters valid information details")
    public void user_enters_valid_information_details() throws IOException, AWTException {
        String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
        String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
        String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
        String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");
        SignupPage.enterFirstName(firstName);
        SignupPage.enterLastName(lastName);
        SignupPage.enterEmail(email);
        SignupPage.enterPassword(password);
    }

    @And("the user enters invalid information details")
    public void user_enters_invalid_information_details() throws IOException, AWTException {
       
        JsonNode invalidSignupData = DataDictionaryUtil.getDataNode(testDataUI, "InvalidSignup");
        String firstName = invalidSignupData.path("firstName").asText();
        String lastName = invalidSignupData.path("lastName").asText();
        String email = invalidSignupData.path("email").asText();
        String password = invalidSignupData.path("password").asText();
        SignupPage.enterFirstName(firstName);
        SignupPage.enterLastName(lastName);
        SignupPage.enterEmail(email);
        SignupPage.enterPassword(password);
    }

    @Then("the user verifies the error message")
    public void user_verifies_the_error_message() throws IOException, AWTException {
        SignupPage.verifyErrorMessage("innerText", "Email address is already in use");
    }
}
