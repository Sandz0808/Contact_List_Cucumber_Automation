package com.cheq.contact_list.step_definitions;

import java.io.IOException;
import java.util.Properties;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.reusable_steps.ReusableSteps;


public class ApiEditUserSteps {
	
	Properties property;

	private ConfigReaderUtil configReaderUtil;
	private final ReusableSteps reusableSteps;
	private Response response; 
    private String testDataAPI;
	
    public ApiEditUserSteps() {
        
        reusableSteps = new ReusableSteps();
        getProperty();
    }

	public void getProperty() {
		configReaderUtil = new ConfigReaderUtil();
		property = configReaderUtil.initProperty();
        testDataAPI = property.getProperty("test_data_api");
	}

	    String dataGroup = Hooks.getDataGroup();
	    
	    @When("User Submit PUT request to users endPoint")
	    public void user_submit_put_request_to_users_end_point() throws IOException {
	    	
	    	JsonNode invalidData = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateValuesAPI");

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
			
			String contactID = reusableSteps.extractIdFromResponseBody("AddingToken.json");
			String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
			
			response = reusableSteps.updateContact(contactID, firstName, lastName, dateOfBirth, email, phoneNumber,
		    		stAddress1, stAddress2, city, state, postalCode,
		    		country, authToken);	        
	    }

	    @Then("Verify response code should indicate that the user is successfully updated")
	    public void verify_response_code_should_indicate_that_the_user_is_successfully_updated() throws IOException {
	    	JsonNode responseCode = DataDictionaryUtil.getDataNode(testDataAPI, "UpdateSuccessResponseAPI");
	        String expectedResponse = responseCode.path("expectedResponse").asText();
	        reusableSteps.verifyResponseCode(response, expectedResponse);
	    }
	    
	    @And("Verify error response code and  user is unsuccessfully updated")
	    public void verify_error_response_code() throws IOException {
	    	
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
			
			String contactID = reusableSteps.extractIdFromResponseBody("AddingToken.json");
			String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");

			response = reusableSteps.updateContact(contactID, firstName, lastName, dateOfBirth, email, phoneNumber,
		    		stAddress1, stAddress2, city, state, postalCode,
		    		country, authToken);
	    	
	    	JsonNode responseCode = DataDictionaryUtil.getDataNode(testDataAPI, "FaieldUpdateResponseAPI");
	        String expectedResponse = responseCode.path("expectedResponse").asText();
	        reusableSteps.verifyResponseCode(response, expectedResponse);
	    }	
}
