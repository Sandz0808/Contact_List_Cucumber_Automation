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

import com.cheq.contact_list.reusable_steps.ReusableSteps;


public class UnsuccessfulDetailUpdateSteps {
	
	WebDriver driver;
	Properties property;
	
	private ScreenshotUtil screenshotUtil;
	private ConfigReaderUtil configReaderUtil;
	
	private ContactDetailPage contactDetailPage;
	private AddContactPage addContactPage;
	private ReusableSteps reusableSteps;
	
	private String testDataUI;
	private String testDataAPI;
	private String idResponse;
	private Response response;
		
    public UnsuccessfulDetailUpdateSteps() {
        driver = DriverFactory.getDriver();
        screenshotUtil = Hooks.getScreenshotUtil(); 
        contactDetailPage = new ContactDetailPage(driver, Hooks.getScreenshotUtil());
        addContactPage = new AddContactPage(driver, Hooks.getScreenshotUtil());
        reusableSteps = new ReusableSteps();
        getProperty();
    }
    
	public void getProperty() {
		configReaderUtil = new ConfigReaderUtil();
		property = configReaderUtil.initProperty();
	    testDataUI = property.getProperty("test_data_ui");
	    testDataAPI = property.getProperty("test_data_api");
	}
	
	String dataGroup = Hooks.getDataGroup();

    @And("Clear values of firstname and lastname")
    public void clear_values_of_firstname_and_lastname() throws IOException, AWTException {
    	contactDetailPage.removeFirstNameValue();
    	contactDetailPage.removeLastNameValue();
    }

    @And("Update street address")
    public void update_street_adress() throws IOException, AWTException {

 		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataUI, "UpdateValues");
 		String stAddress1Ddt = uiDataNode.path("newStAddress").asText();
 		
 		contactDetailPage.enterStAddress(stAddress1Ddt);
    }
 

    @Then("Verify error message for blank required fields")
    public void validate_error_message_for_blank_required_fields() throws IOException, AWTException {
	
		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataUI, "ErrorMsgDetailUpdate");
		String errorMsg = uiDataNode.path("expectedBlankReqFieldsMsg").asText();
		
		addContactPage.verifyBlankFieldsErrorMessage("innerText", errorMsg); 
    } 
    
	@When("Clear values of firstname")
	public void clear_values_of_firstname() throws IOException, AWTException {

		contactDetailPage.removeFirstNameValue();		
	}
	
	@Then("Verify error message for blank firstname")
	public void Verify_error_message_for_blank_firstname()  throws IOException, AWTException{
	
		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataUI, "ErrorMsgDetailUpdate");
		String errorMsg = uiDataNode.path("expectedBlankFnamesMsg").asText();
		
		addContactPage.verifyBlankFieldsErrorMessage("innerText", errorMsg);
	}
   
	@When("Clear values of all fields")
	public void clear_values_of_all_fields() throws IOException, AWTException {

		contactDetailPage.removeFirstNameValue();
		contactDetailPage.removeLastNameValue();
		contactDetailPage.removEmailValue();
		contactDetailPage.removePhoneNumValue();
		contactDetailPage.removeDateOfBirthValue();
		contactDetailPage.removeStAddress1Value();
		contactDetailPage.removeStAddress2Value();
		contactDetailPage.removeCityValue();
		contactDetailPage.removeStateValue();
		contactDetailPage.removePostalValue();
		contactDetailPage.removeCountryValue();
	}
	
	@When("User use PATCH to update contact with blank firstname and lastname")
	public void user_use_patch_to_update_contact_with_blank_firstname_and_lastname() throws IOException {	

	    JsonNode blankUpdateData = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateBlankValuesAPI");
	    JsonNode updateData = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateValuesAPI");
	    
	    String firstName = blankUpdateData.path("firstName").asText();
	    String lastName = blankUpdateData.path("lastName").asText();
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

	@Then("Verify error response code")
	public void verify_error_response_code() throws IOException {
		
		JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "FaieldUpdateResponseAPI");
	    String expectedResponseCodeStr = apiDataNode.path("expectedResponse").asText();
		
	    reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);	 
	}
	
	@And("User use PATCH to update contact with blank fields")
	public void user_use_patch_to_update_contact_with_blank_fields() throws IOException {
		
			    JsonNode tokenData = DataDictionaryUtil.getDataNode(testDataAPI, "AuthCode");
			    JsonNode blankUpdateData = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateBlankValuesAPI");
    
			    String authTokenDdt = tokenData.path("token").asText();
			    String firstNameDdt = blankUpdateData.path("firstName").asText();
			    String lastNameDdt = blankUpdateData.path("lastName").asText();
			    String dateOfBirthDdt = blankUpdateData.path("dateOfBirth").asText();
			    String emailDdt = blankUpdateData.path("email").asText();
			    String phoneNumDdt = blankUpdateData.path("phone").asText();
			    String stAddress1Ddt = blankUpdateData.path("stAddress1").asText();
			    String stAddress2Ddt = blankUpdateData.path("stAddress2").asText();
			    String cityDdt = blankUpdateData.path("city").asText();
			    String stateDdt = blankUpdateData.path("state").asText();
			    String postalCodeDdt = blankUpdateData.path("postalCode").asText();
			    String countryDdt = blankUpdateData.path("country").asText();
			    idResponse = reusableSteps.extractIdFromResponseBody("AddingToken.json");
			    
				response = reusableSteps.updateContact(idResponse, firstNameDdt, lastNameDdt, dateOfBirthDdt,
						emailDdt, phoneNumDdt, stAddress1Ddt, stAddress2Ddt, cityDdt, stateDdt, postalCodeDdt, countryDdt, authTokenDdt);
				reusableSteps.saveResponseBodyToFile(response, "UpdateToken.json");
	}
	
}
