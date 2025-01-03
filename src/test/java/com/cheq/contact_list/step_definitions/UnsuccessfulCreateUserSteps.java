package com.cheq.contact_list.step_definitions;

import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.Properties;
import io.cucumber.java.en.Then;
import com.fasterxml.jackson.databind.JsonNode;

import com.cheq.contact_list.hooks.Hooks;

import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;

import com.cheq.contact_list.reusable_steps.ReusableSteps;

import com.cheq.contact_list.test_data.TestDataGenerator;

public class UnsuccessfulCreateUserSteps {
	
	Properties property;
	
    private ConfigReaderUtil configReaderUtil;
    
    private Response response;
    private String testDataAPI;
    private ReusableSteps reusableSteps;
 
    public UnsuccessfulCreateUserSteps() {
        getProperty();
        reusableSteps = new ReusableSteps();

    }
 
    /** Initializes properties and the JSON file */
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        testDataAPI = property.getProperty("test_data_api");
    }
    
    String dataGroup = Hooks.getDataGroup();
    
	@When("User use POST to create user with blank password field")
	public void user_use_post_to_create_user_with_blank_password_field() throws IOException {

		JsonNode blankData = DataDictionaryUtil.getDataNode(testDataAPI, "CreateUserBlankFields");   
	    	  
		String password = blankData.path("blankPassword").asText();
	    String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
	    String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
	    String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");

	    response = reusableSteps.createNewUser(firstName, lastName, email, password);
	    reusableSteps.saveResponseBodyToFile(response, "CreateToken.json");
		
	}

	@Then("Verify the response code for blank field")
	public void verify_the_response_code_for_blank_field() throws IOException {
	
    	JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "FailedCreateUserResponseAPI");     
        String expectedResponseCodeStr = apiDataNode.path("expectedResponse").asText();

        reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);

	}
	
	@When("User use POST to create user with blank fields")
	public void user_use_post_to_create_user_with_blank_fields() throws IOException {

		JsonNode blankData = DataDictionaryUtil.getDataNode(testDataAPI, "CreateUserBlankFields"); 
        String password = blankData.path("blankPassword").asText();
        String firstName = blankData.path("firstName").asText();
        String lastName = blankData.path("lastName").asText();
        String email = blankData.path("email").asText();
        
        response = reusableSteps.createNewUser(firstName, lastName, email, password);
        reusableSteps.saveResponseBodyToFile(response, "CreateToken.json");
		
	}

}
