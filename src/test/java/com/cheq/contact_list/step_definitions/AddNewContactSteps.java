
package com.cheq.contact_list.step_definitions;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.cheq.contact_list.utils.DriverFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.pages.AddContactPage;
import com.cheq.contact_list.pages.ContactListPage;
import com.cheq.contact_list.pages.LoginPage;

import com.cheq.contact_list.test_data.TestDataGenerator;

public class AddNewContactSteps {

    WebDriver driver;
    Properties property;
        
    private ConfigReaderUtil configReaderUtil;
    private ContactListPage contactListPage;
    private AddContactPage addContactPage;   
    private LoginPage loginPage;
    private String testDataUI;
    
 // Variables to store generated test data
    private String firstName;
    private String lastName;
    
    public AddNewContactSteps() {
        driver = DriverFactory.getDriver();
        contactListPage = new ContactListPage(driver, Hooks.getScreenshotUtil());
        addContactPage = new AddContactPage(driver, Hooks.getScreenshotUtil());
        loginPage = new LoginPage(driver, Hooks.getScreenshotUtil());
        getProperty();

    }
    
    /** Initializes properties and the JSON file */
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        testDataUI = property.getProperty("test_data_ui");
    }
	
    String dataGroup = Hooks.getDataGroup();



@Given("User login to Contact List App")
public void user_login_to_app() throws Exception {
	
	JsonNode loginData = DataDictionaryUtil.getDataNode(testDataUI, "ValidLoginCredentials");
	
	String email = loginData.path("email").asText();
    String password = loginData.path("password").asText();
    
    loginPage.enterEmail(email);
    loginPage.enterPassword(password);
    loginPage.clickSubmitButton();
}

@When("User clicks Add New Contact button")
public void user_clicks_add_new_contact_button() throws Exception {
	contactListPage.clickAddNewContactButton();
}

@When("User enters valid data details")
public void user_populates_all_fields() throws IOException, AWTException {
    String[] fieldKeys = { "firstName", "lastName", "dateOfBirth", "email", "phoneNumber", 
                           "stAddress1", "stAddress2", "city", "state", "postalCode", "country" };

    for (String key : fieldKeys) {
        String value = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, key);
        switch (key) {
            case "firstName": addContactPage.enterFirstName(value); break;
            case "lastName": addContactPage.enterLastName(value); break;
            case "dateOfBirth": addContactPage.enterDateOfBirth(value); break;
            case "email": addContactPage.enterEmail(value); break;
            case "phoneNumber": addContactPage.enterPhoneNumber(value); break;
            case "stAddress1": addContactPage.enterStAddress1(value); break;
            case "stAddress2": addContactPage.enterStAddress2(value); break;
            case "city": addContactPage.enterCity(value); break;
            case "state": addContactPage.enterState(value); break;
            case "postalCode": addContactPage.enterPostalCode(value); break;
            case "country": addContactPage.enterCountry(value); break;
        }
    }
}


@When("User clicks submit button in Add Contact Page")
public void user_clicks_Submit_button_in_add_contact_page()throws Exception {
	addContactPage.clickSubmitButton();
}

@Then("User verify new contact is added")
public void verify_new_contact_is_added() throws IOException, AWTException {
	
	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
	String fullName = firstName + " " + lastName; 

    contactListPage.verifyContactIsDisplayed("innerText", fullName);
	   
  }


@When("User enters invalid data details")
public void user_enters_invalid_data_details() throws IOException, AWTException {
	
	
	String[] fieldKeys = { "firstName", "lastName", "dateOfBirth", "email", "phone", 
	        "stAddress1", "stAddress2", "city", "state", "postalCode", "country" };

	for (String key : fieldKeys) {
	    JsonNode value = DataDictionaryUtil.getDataNode(testDataUI, "AddContactFields").get(key);	
	    switch (key) {
	        case "firstName": addContactPage.enterFirstName(value.asText()); break;
	        case "lastName": addContactPage.enterLastName(value.asText());  break;
	        case "dateOfBirth": addContactPage.enterDateOfBirth(value.asText());  break;
	        case "email": addContactPage.enterEmail(value.asText());  break;
	        case "phone": addContactPage.enterPhoneNumber(value.asText()); break;
	        case "stAddress1": addContactPage.enterStAddress1(value.asText()); break;
	        case "stAddress2": addContactPage.enterStAddress2(value.asText()); break;
	        case "city": addContactPage.enterCity(value.asText()); break;
	        case "state": addContactPage.enterState(value.asText());  break;
	        case "postalCode": addContactPage.enterPostalCode(value.asText()); break;
	        case "country": addContactPage.enterCountry(value.asText());  break;
	        default: throw new IllegalArgumentException("Unexpected key: " + key);
	        
	        
	        }

	    }

	}

@When("User clicks Submit button in Add Contact Page")
public void user_clicks_submit_button_in_add_contact_page() throws Exception {
	addContactPage.clickSubmitButton();
}
		
@Then("User verifies the error message")
public void user_verifies_the_error_message() throws Exception {
	
	addContactPage.verifyBlankFieldsErrorMessage("innerText", "Contact validation failed: firstName: Path `firstName` is required.");
	}

}        
	            
	           
	        
	           
	           
	        
            
	            
	        
	            
	            
	        
	            
	            
	        
	            
	            
	        
	             
	           
	        
	             
	            
	        
	             
	           
	       
	            
	



























