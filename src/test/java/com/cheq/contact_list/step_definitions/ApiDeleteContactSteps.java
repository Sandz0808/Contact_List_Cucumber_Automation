package com.cheq.contact_list.step_definitions;

import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.cheq.contact_list.hooks.Hooks;
import java.util.Properties;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.reusable_steps.ReusableSteps;
import com.cheq.contact_list.utils.DataDictionaryUtil;

public class ApiDeleteContactSteps {
	
	Properties property;

	private ConfigReaderUtil configReaderUtil;
	private final ReusableSteps reusableSteps;
	
	private Response response; 
	private Response deleteResponse;
	private String responseId;
    private String testDataAPI;
	
    public ApiDeleteContactSteps() {
        reusableSteps = new ReusableSteps();
        getProperty();
    }

	public void getProperty() {
		configReaderUtil = new ConfigReaderUtil();
		property = configReaderUtil.initProperty();
        testDataAPI = property.getProperty("test_data_api");
	}

	    String dataGroup = Hooks.getDataGroup();
	
    @And("Get the ID")
    public void get_the_id() throws IOException {
    	
    	responseId = reusableSteps.extractIdFromResponseBody("AddingToken.json");
    }

    @When("Submit DELETE to delete the contact")
    public void use_delete_to_delete_the_contact() throws IOException {
    	
    	String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
    	deleteResponse = reusableSteps.deleteContact(responseId, authToken);
    	reusableSteps.saveResponseBodyToFile(deleteResponse, "DeleteToken.json" );

    }
 
    @Then("Verify the response code for deletion")
    public void verify_the_response_code_for_deletion() throws IOException {
   		
        JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "AddingSuccessResponseAPI");
        String expectedResponseCodeStr = apiDataNode.path("expectedResponse").asText();
        reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);	
    }
    
}
    
 

