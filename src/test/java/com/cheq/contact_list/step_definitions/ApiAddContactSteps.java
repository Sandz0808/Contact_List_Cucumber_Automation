package com.cheq.contact_list.step_definitions;

import java.io.IOException;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Properties;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.reusable_steps.ReusableSteps;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.test_data.TestDataGenerator;


public class ApiAddContactSteps {

    private Properties property;
    private String testDataAPI;
    private Response response;
    private ReusableSteps reusableSteps;

    public ApiAddContactSteps() {
        initializeProperties();
        reusableSteps = new ReusableSteps();
    }

        private void initializeProperties() {
        ConfigReaderUtil configReader = new ConfigReaderUtil();
        property = configReader.initProperty();
        testDataAPI = property.getProperty("test_data_api");
    }

    String dataGroup = Hooks.getDataGroup();

	@When("User Submit POST request to \\/contacts\\/ endPoint")
	public void user_submit_post_request_to_contacts_end_point() throws IOException {

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
	    reusableSteps.saveResponseBodyToFile(response, "UpdateToken.json");
	   
	}

	@Then("Verify that the new Contact is created")
	public void verify_that_the_new_contact_is_created() throws IOException {

		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "AddingSuccessResponseAPI");
        String expectedResponse = uiDataNode.path("expectedResponse").asText();
        reusableSteps.verifyResponseCode(response, expectedResponse);

	}

	@Then("Verify error status code and that the contact is not created")
	public void verify_error_status_code_and_that_the_contact_is_not_created() throws IOException {

		JsonNode invalidData = DataDictionaryUtil.getDataNode(testDataAPI, "AddContactBlankReqFieldsAPI");

		String firstName = invalidData.path("firstName").asText();
		String lastName = invalidData.path("lastName").asText();
		String dateOfBirth = invalidData.path("dateOfBirth").asText();
		String email = invalidData.path("email").asText();
		String phoneNumber = invalidData.path("phone").asText();
		String stAddress1 = invalidData.path("stAddress1").asText();
		String stAddress2 = invalidData.path("stAddress2").asText();
		String city = invalidData.path("city").asText();
		String state = invalidData.path("state").asText();
		String postalCode = invalidData.path("postalCode").asText();
		String country = invalidData.path("country").asText();

		String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");

		response = reusableSteps.addContactFullDetail(firstName, lastName, dateOfBirth, email, phoneNumber,
	    		stAddress1, stAddress2, city, state, postalCode,
	    		country, authToken);

		reusableSteps.saveResponseBodyToFile(response, "AddingToken.json");

		JsonNode expectedError = DataDictionaryUtil.getDataNode(testDataAPI, "FailedAddingResponseAPI");
		String expectedResponse = expectedError.path("expectedResponse").asText();
		reusableSteps.verifyResponseCode(response, expectedResponse);
	}
}








