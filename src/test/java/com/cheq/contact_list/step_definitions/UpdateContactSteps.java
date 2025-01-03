package com.cheq.contact_list.step_definitions;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.DriverFactory;

import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;
import com.cheq.contact_list.pages.AddContactPage;
import com.cheq.contact_list.pages.ContactDetailPage;
import com.cheq.contact_list.pages.ContactListPage;
import com.cheq.contact_list.pages.LoginPage;

import com.cheq.contact_list.reusable_steps.ReusableSteps;

import com.cheq.contact_list.test_data.TestDataGenerator;

public class UpdateContactSteps {
	
	WebDriver driver;
	Properties property;
	
	private ScreenshotUtil screenshotUtil;
	private ConfigReaderUtil configReaderUtil;
	
	private LoginPage loginPage;
	private ContactListPage contactListPage;
	private ContactDetailPage contactDetailPage;
	private ReusableSteps reusableSteps;
	
	private Response response;
	private String testDataUI;
	private String testDataAPI;
	private String idResponse;
	
    public UpdateContactSteps() throws IOException {
        driver = DriverFactory.getDriver();
        screenshotUtil = Hooks.getScreenshotUtil();
        loginPage = new LoginPage(driver, Hooks.getScreenshotUtil());
        contactListPage = new ContactListPage(driver, Hooks.getScreenshotUtil());
        contactDetailPage = new ContactDetailPage(driver, Hooks.getScreenshotUtil());
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
	
	
	@And("User clicks a contact")
	public void user_clicks_a_contact() throws Exception {
		
		contactListPage.clickTestContact();
	}

	@And("User clicks Edit Contact button")
	public void user_clicks_edit_contact_button() throws Exception {

		contactDetailPage.clickEditContactButton();
	}

	@And("User updates the email field")
	public void user_updates_the_email_field() throws IOException, AWTException {	

 		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataUI, "UpdateValues");
 		String emailDdt = uiDataNode.path("newEmail").asText();
 		
 		contactDetailPage.removEmailValue();
 		contactDetailPage.enterEmailDetail(emailDdt);
	}
	
    @And("User clicks submit button in Contact Detail Page")
    public void clicks_the_submit_button_in_contact_detail_page() throws Exception {
    	
    	contactDetailPage.clickSubmitButton();
    }

	@Then("Verify email is updated")
	public void verify_email_is_updated() throws IOException, AWTException {
		
		JsonNode contactData = DataDictionaryUtil.getDataNode(testDataUI, "UpdateValues");
		String expectedValue = contactData.path("newEmail").asText();
		
		contactDetailPage.verifyEmailFieldUpdate("innerText", expectedValue);
	}
	
	@And("User updates the multiple fields")
	public void user_updates_the_multiple_fields() throws IOException, AWTException {
	
		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataUI, "UpdateValues");
 		String phoneNumDdt = uiDataNode.path("newPhone").asText();
 		String cityDdt = uiDataNode.path("newCity").asText();
 		String postalCodeDdt = uiDataNode.path("newPostalCode").asText();
 		
 		contactDetailPage.removePhoneNumValue();
 		contactDetailPage.enterPhoneNumberDetail(phoneNumDdt);
 		contactDetailPage.removeCityValue();
 		contactDetailPage.enterCityDetail(cityDdt);
 		contactDetailPage.removePostalValue();
 		contactDetailPage.enterPostalDetail(postalCodeDdt);
		
	}

	@Then("Verify fields are updated")
	public void verify_fields_are_updated() throws IOException, AWTException {	
		
		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataUI, "UpdateValues");
		String expectedNewCity = uiDataNode.path("newCity").asText();
		String expectedNewPostal = uiDataNode.path("newPostalCode").asText();
		
		contactDetailPage.verifyCityFieldUpdate("innerText", expectedNewCity);
		contactDetailPage.verifyPostalFieldUpdate("innerText", expectedNewPostal);	
	}
	
	@When("User use Patch to update the contact details")
	public void user_use_patch_to_update_the_contact_details() throws IOException {
			
		    JsonNode updateData = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateValuesAPI");	    
		    String email = updateData.path("email").asText();
		    String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
		    idResponse = reusableSteps.extractIdFromResponseBody("AddingToken.json");
		    
			response = reusableSteps.updateEmailDetail(idResponse, email, authToken);
			reusableSteps.saveResponseBodyToFile(response, "UpdateToken.json");
	}

	@And("Verify the patch response code")
	public void verify_the_patch_response_code() throws IOException {

		JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateSuccessResponseAPI");
		String expectedValue = apiDataNode.path("expectedResponse").asText();	
		
		reusableSteps.verifyResponseCode(response, expectedValue);		 
	}
	
	@When("User use Patch to update multiple fields")
	public void user_use_patch_to_update_multiple_fields() throws IOException {
		
		    JsonNode updateData = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateValuesAPI");
		    String firstName = updateData.path("firstName").asText();
		    String lastName = updateData.path("lastName").asText();
		    String dateOfBirth = updateData.path("dateOfBirth").asText();
		    String email = updateData.path("email").asText();
		    String phoneNum = updateData.path("phone").asText();
		    String stAddress1 = updateData.path("stAddress1").asText();
		    String stAddress2 = updateData.path("stAddress2").asText();
		    String city = updateData.path("city").asText();
		    String state = updateData.path("state").asText();
		    String postalCode = updateData.path("postalCode").asText();
		    String country = updateData.path("country").asText();
		    String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
		    idResponse = reusableSteps.extractIdFromResponseBody("AddingToken.json");
		    
		    
			response = reusableSteps.updateContact(idResponse, firstName, lastName, dateOfBirth,
					email, phoneNum, stAddress1, stAddress2, city, state, postalCode, country, authToken);
			reusableSteps.saveResponseBodyToFile(response, "UpdateToken.json");
		
	}
	
	@When("User use Patch to update email field")
	public void user_use_patch_to_update_email_field() throws IOException {
		
	    JsonNode updateData = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateValuesAPI");
	    String email = updateData.path("email").asText();    
	    String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
	    idResponse = reusableSteps.extractIdFromResponseBody("AddingToken.json");
	    
		response = reusableSteps.updateEmailDetail(idResponse, email, authToken);
		reusableSteps.saveResponseBodyToFile(response, "UpdateToken.json");
		       
	}
	
	@And("Verify contact detail is updated in the application")
	public void verify_contact_detail_is_updated_in_the_application() throws Exception {
		
		JsonNode contactData = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateValuesAPI");
		String expectedValue = contactData.path("email").asText();
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");
    	
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);  
        loginPage.clickSubmitButton();
				
        contactListPage.clickTestContact();
		contactDetailPage.verifyEmailFieldUpdate("innerText", expectedValue);
	}

}
