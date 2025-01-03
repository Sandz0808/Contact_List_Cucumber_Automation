package com.cheq.contact_list.step_definitions;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import com.cheq.contact_list.utils.DriverFactory;
import com.cheq.contact_list.hooks.Hooks;

import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;

import com.cheq.contact_list.pages.AddContactPage;
import com.cheq.contact_list.pages.ContactListPage;

import com.cheq.contact_list.reusable_steps.ReusableSteps;

import com.cheq.contact_list.test_data.TestDataGenerator;

public class AddNewContactSteps {

    WebDriver driver;
    Properties property;
    
    private ConfigReaderUtil configReaderUtil;
    
    private ContactListPage contactListPage;
    private AddContactPage addContactPage;
    private ReusableSteps reusableSteps;
    
    private Response response; 
    private String testDataAPI;

    public AddNewContactSteps() {
        driver = DriverFactory.getDriver();
        contactListPage = new ContactListPage(driver, Hooks.getScreenshotUtil());
        addContactPage = new AddContactPage(driver, Hooks.getScreenshotUtil());
        reusableSteps = new ReusableSteps();
        getProperty();

    }
    
    /** Initializes properties and the JSON file */
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        testDataAPI = property.getProperty("test_data_api");
    }
	
    String dataGroup = Hooks.getDataGroup();
    
	@When("User clicks Add New Contact button")
	public void user_clicks_add_new_contact_button() throws Exception {

		contactListPage.clickAddNewContactButton();	
	}
	
	@And("User populates all fields")
	public void user_populates_all_fields() throws IOException, AWTException {
		
		
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
		String dateOfBirth = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "dateOfBirth");
		String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
		String phoneNumber = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "phoneNumber");
		String stAddress1 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress1");
		String stAddress2 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress2");
		String city = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "city");
		String state = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "state");
		String postalCode = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "postalCode");
		String country = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "country");

		addContactPage.enterFirstName(firstName);
		addContactPage.enterLastName(lastName);
		addContactPage.enterDateOfBirth(dateOfBirth);
		addContactPage.enterEmail(email);
		addContactPage.enterPhoneNumber(phoneNumber);
		addContactPage.enterStAddress1(stAddress1);
		addContactPage.enterStAddress2(stAddress2);
		addContactPage.entereCity(city);
		addContactPage.enterState(state);
		addContactPage.enterPostalCode(postalCode);
		addContactPage.enterCountry(country);

	}

	@And("User clicks submit button in Add Contact Page")
	public void user_click_submit_button_in_add_contact_page() throws Exception {

		addContactPage.clickSubmitButton();
	   
	}

	@And("Verify new contact is added")
	public void verify_new_contact_is_added() throws IOException, InterruptedException, AWTException {
			
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
    	String fullName = firstName + " " + lastName; 

	    contactListPage.verifyContactIsDisplayed("innerText", fullName);
	    
	}
	
	@And("User populates required fields")
	public void user_populates_required_fields() throws IOException, AWTException {
		
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");

    	addContactPage.enterFirstName(firstName);
    	addContactPage.enterLastName(lastName);
		
	}
	
	@When("User use POST to add new contact")
	public void user_use_post_to_add_new_contact() throws IOException {
		
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
    	String dateOfBirth = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "dateOfBirth");
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String phoneNumber = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "phoneNumber");
    	String stAddress1 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress1");
    	String stAddress2 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress2");
    	String city = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "city");
    	String state = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "state");
    	String postalCode = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "postalCode");
    	String country = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "country");
    	String authTokenDdt = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
    	
	    response = reusableSteps.addContactFullDetail(firstName, lastName, dateOfBirth, email, phoneNumber, 
	    		stAddress1, stAddress2, city, state, postalCode, 
	    		country, authTokenDdt);
	    reusableSteps.saveResponseBodyToFile(response, "AddingToken.json");
	}

    
	@And("Verify the response code for adding new contact")
    public void verify_the_response_code_for_adding_new_contact() throws IOException {
    	
        JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "AddingSuccessResponseAPI");
        String expectedResponseCodeStr = uiDataNode.path("expectedResponse").asText();
        
        reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);
 
    }

	@When("User use POST to add new contact using Firstname and Lastname")
	public void user_use_post_to_add_new_contact_using_firstname_and_lastname() throws IOException {
		
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
    	String authTokenDdt = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
	    
	    response = reusableSteps.addContactRequiredField(firstName, lastName, authTokenDdt);
	    reusableSteps.saveResponseBodyToFile(response, "AddingToken.json");
	}
		
	@Then("Verify the response code for adding new contact using firstname and lastname")
	public void verify_the_response_code_for_adding_new_contact_using_firstname_and_lastname() throws IOException {
		
        JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "AddingSuccessResponseAPI");     
        String expectedResponseCodeStr = uiDataNode.path("expectedResponse").asText();
        
        reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);
	}
	
	 @When("User adds a new contact")
		public void user_adds_a_new_contact() throws Exception {		
			
	    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
	    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
			String dateOfBirth = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "dateOfBirth");
			String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
			String phoneNumber = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "phoneNumber");
			String stAddress1 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress1");
			String stAddress2 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress2");
			String city = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "city");
			String state = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "state");
			String postalCode = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "postalCode");
			String country = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "country");

			contactListPage.clickAddNewContactButton();
					
			addContactPage.enterFirstName(firstName);
			addContactPage.enterLastName(lastName);
			addContactPage.enterDateOfBirth(dateOfBirth);
			addContactPage.enterEmail(email);
			addContactPage.enterPhoneNumber(phoneNumber);
			addContactPage.enterStAddress1(stAddress1);
			addContactPage.enterStAddress2(stAddress2);
			addContactPage.entereCity(city);
			addContactPage.enterState(state);
			addContactPage.enterPostalCode(postalCode);
			addContactPage.enterCountry(country);
			addContactPage.clickSubmitButton();
		} 

	@When("User use POST to add first contact")
	public void user_use_post_to_add_first_contact() throws IOException {
		
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
    	String dateOfBirth = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "dateOfBirth");
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String phoneNumber = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "phoneNumber");
    	String stAddress1 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress1");
    	String stAddress2 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress2");
    	String city = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "city");
    	String state = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "state");
    	String postalCode = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "postalCode");
    	String country = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "country");
    	String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
		
	    response = reusableSteps.addContactFullDetail(firstName, lastName, dateOfBirth, email, phoneNumber, 
	    		stAddress1, stAddress2, city, state, postalCode, 
	    		country, authToken);
	    reusableSteps.saveResponseBodyToFile(response, "AddingToken.json");
	}
	
	
	@And("User use POST to add second contact")
	public void user_use_post_to_add_second_contact() throws IOException {
		
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
    	String dateOfBirth = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "dateOfBirth");
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String phoneNumber = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "phoneNumber");
    	String stAddress1 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress1");
    	String stAddress2 = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "stAddress2");
    	String city = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "city");
    	String state = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "state");
    	String postalCode = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "postalCode");
    	String country = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "country");
    	String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");

    	response = reusableSteps.addContactFullDetail(firstName, lastName, dateOfBirth, email, phoneNumber, 
	    		stAddress1, stAddress2, city, state, postalCode, 
	    		country, authToken);
    	reusableSteps.saveResponseBodyToFile(response, "AddingToken.json");
	}
    

}
