package com.cheq.contact_list.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.DriverFactory;

import com.cheq.contact_list.pages.LoginPage;

import com.cheq.contact_list.test_data.TestDataGenerator;



public class LoginUserSteps {

    private WebDriver driver;
    private LoginPage loginPage;
 
    public LoginUserSteps() { 
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver, Hooks.getScreenshotUtil());
    }
    
    String dataGroup = Hooks.getDataGroup();
    
    @When("User populates required login fields")
    public void user_populates_required_login_fields() throws IOException, AWTException {

    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");
        
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);  
    }

    @And("User clicks submit button in the Login Page")
    public void user_clicks_submit_button_in_the_login_page() throws Exception {
        loginPage.clickSubmitButton();  
    }
    
    @Then("Verify Contact List Page")
    public void verify_contact_list_page() throws IOException, AWTException {
        loginPage.verifyContactListPage("innerText", "Contact List");
    }
    
    @And("User Login the Web Application")
	public void user_login_the_web_application() throws Exception {
		
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");
    	
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);  
        loginPage.clickSubmitButton();    
		
	}
	
	@And("User is already logged in")
	public void user_is_already_logged_in() throws Exception {
		
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");
		
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);  
        loginPage.clickSubmitButton();
	}
	
    @When("User populates required login fields with email {string}, password {string}")
    public void user_populates_required_login_fields_with_email_and_password(String email, String password) throws IOException, AWTException {

	email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);  
    }
}
