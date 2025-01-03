package com.cheq.contact_list.step_definitions;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;

import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.DriverFactory;

import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;

import com.cheq.contact_list.pages.AddContactPage;

import com.cheq.contact_list.reusable_steps.ReusableSteps;

import com.cheq.contact_list.test_data.TestDataGenerator;


public class UnsuccessfulAddNewContactSteps {
	
	WebDriver driver;
	Properties property;
	
    private ScreenshotUtil screenshotUtil;
    private ReusableSteps reusableSteps;
    private ConfigReaderUtil configReaderUtil;
    
	private AddContactPage addContactPage;
	
	private Response response;
    private String testDataUI;
    private String testDataAPI;

	
    public UnsuccessfulAddNewContactSteps() {
        driver = DriverFactory.getDriver();
        addContactPage = new AddContactPage(driver, Hooks.getScreenshotUtil());
        reusableSteps = new ReusableSteps();
        getProperty();
       
    }
    
    /** Initializes properties and the JSON file */
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        testDataUI = property.getProperty("test_data_ui");
        testDataAPI = property.getProperty("test_data_api");
    }
    
    String dataGroup = Hooks.getDataGroup();

    @Then("Verify error message displayed")
    public void verify_error_message_displayed() throws IOException, AWTException {
    	
		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataUI, "ErrorMsgAddWithBlankField");
		String errorMsg = uiDataNode.path("blankFnameAndLName").asText();
		
		addContactPage.verifyBlankFieldsErrorMessage("innerText", errorMsg);

    }
    
	@And("Populate fields with blank firstname")
	public void populate_fields_with_blank_firstname() throws IOException, AWTException {

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
	
	@Then("Verify error message for blank first name field displayed")
	public void verify_error_message_for_blank_first_name_field_displayed() throws IOException, AWTException {

		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataUI, "ErrorMsgAddWithBlankField");

		String errorMsg = uiDataNode.path("blankFname").asText();
		addContactPage.verifyBlankFieldsErrorMessage("innerText", errorMsg);
				
	}
	
	@When("User use POST to add new contact with blank firstname and lastname")
	public void user_use_post_to_add_new_contact_with_blank_firstname_and_lastname() throws IOException {
    	
	    JsonNode blankData = DataDictionaryUtil.getDataNode(testDataAPI, "CreateWithBlankFields");   

	    String firstName = blankData.path("firstName").asText();
	    String lastName = blankData.path("lastName").asText();	    
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
	    		stAddress1, stAddress2, city, state, postalCode, country, authToken);
	    reusableSteps.saveResponseBodyToFile(response, "AddingToken.json");
    	
	}

	@Then("Verify the response code for blank required fields")
	public void verify_the_response_code_for_blank_required_fields() throws IOException {
	
        JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "FailedAddingResponseAPI");
        String expectedResponseCodeStr = uiDataNode.path("expectedResponse").asText();
    	
        reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);
  	
	}
	
	@When("User use POST to add new contact with blank firstname")
	public void user_use_post_to_add_new_contact_with_blank_firstname() throws IOException {

	    JsonNode blankData = DataDictionaryUtil.getDataNode(testDataAPI, "CreateWithBlankFields");

	    String firstName = blankData.path("firstName").asText();
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

	@Then("Verify the response code for blank firstname field")
	public void verify_the_response_code_for_blank_firstname_field() throws IOException {

		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "FailedAddingResponseAPI");
		String expectedResponseCodeStr = uiDataNode.path("expectedResponse").asText();

		reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);
	}
}
