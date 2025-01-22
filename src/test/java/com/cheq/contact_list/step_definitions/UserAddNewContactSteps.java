
package com.cheq.contact_list.step_definitions;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
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


public class UserAddNewContactSteps {

    WebDriver driver;
    Properties property;
        
    private ConfigReaderUtil configReaderUtil;
    private ContactListPage contactListPage;
    private AddContactPage addContactPage;       
    private String testDataUI;
    
 
    public UserAddNewContactSteps() {
        driver = DriverFactory.getDriver();
        contactListPage = new ContactListPage(driver, Hooks.getScreenshotUtil());
        addContactPage = new AddContactPage(driver, Hooks.getScreenshotUtil());
       
        getProperty();

    }
    
    
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        testDataUI = property.getProperty("test_data_ui");
    }
	
    String dataGroup = Hooks.getDataGroup();


	@When("User clicks Add New Contact button")
	public void user_clicks_add_new_contact_button() throws Exception {
		contactListPage.clickAddNewContactButton();
	}
	
	@And("User enters valid data details")
	public void user_populates_all_fields() throws IOException, AWTException {
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


	@And("User clicks submit button in Add Contact Page")
	public void user_clicks_Submit_button_in_add_contact_page()throws Exception {
		addContactPage.clickSubmitButton();
	}
	
	@Then("User verify new contact is added")
	public void verify_new_contact_is_added() throws IOException, AWTException {
		
		contactListPage.verifyContactIsDisplayed("innerText","Alex Sanders" );
		   
	  }
	
	
	
	@And("User enters invalid data details")
	public void user_enters_invalid_data_details() throws IOException, AWTException {
		
	
	String[] fieldKeys = { "firstName", "lastName", "dateOfBirth", "email", "phone", 
	        "stAddress1", "stAddress2", "city", "state", "postalCode", "country" };

	for (String key : fieldKeys) {
	    JsonNode value = DataDictionaryUtil.getDataNode(testDataUI, "AddContactFields").get(key);	
	    switch (key) {
	        case "firstName": addContactPage.enterFirstName(""); break;
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

@And("User clicks Submit button in Add Contact Page")
public void user_clicks_submit_button_in_add_contact_page() throws Exception {
	addContactPage.clickSubmitButton();
}
		
@Then("User verifies the error message")
public void user_verifies_the_error_message() throws Exception {
	
	addContactPage.verifyBlankFieldsErrorMessage("innerText", "Contact validation failed: firstName: Path `firstName` is required.");
	}

}        
	                       
	        
	           
	           
	        
            
	            
	        
	            
	            
	        
	            
	            
	        
	            
	            
	        
	             
	           
	        
	             
	            
	        
	             
	           
	       
	            
	



























