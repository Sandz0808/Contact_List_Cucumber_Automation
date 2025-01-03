package com.cheq.contact_list.step_definitions;

import java.io.IOException;
import java.util.Properties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;

import com.cheq.contact_list.hooks.Hooks;

import com.cheq.contact_list.reusable_steps.ReusableSteps;

import com.cheq.contact_list.utils.ConfigReaderUtil;

import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.test_data.TestDataGenerator;

public class CreateUserSteps {

    private Properties property;
    private String testDataAPI;
    private Response response;

    private ReusableSteps reusableSteps;

    public CreateUserSteps() {
        initializeProperties();
        reusableSteps = new ReusableSteps();
    }

    /** Initializes properties and sets the test data file path */
    private void initializeProperties() {
        ConfigReaderUtil configReader = new ConfigReaderUtil();
        property = configReader.initProperty();
        testDataAPI = property.getProperty("test_data_api");
    }
    
    String dataGroup = Hooks.getDataGroup();
    
    @Given("User has access to the application")
    public void user_has_access_to_the_application() throws IOException {

    }
    
    @When("User use POST to create new user")
    public void user_use_post_to_create_new_user() throws IOException {
    	
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");
        
        response = reusableSteps.createNewUser(firstName, lastName, email, password);
        reusableSteps.saveResponseBodyToFile(response, "CreateToken.json");
    }

    @Then("Verify the response code")
    public void verify_the_response_code() throws IOException {
    	 	
    	JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "CreateSuccessResponseAPI");     
        String expectedResponseCodeStr = uiDataNode.path("expectedResponse").asText();
    	
        reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);

    }
    
    @Given("User has an existing account")
    public void user_has_an_existing_account() throws IOException {
    	
    	String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");
        
        response = reusableSteps.createNewUser(firstName, lastName, email, password);
        reusableSteps.saveResponseBodyToFile(response, "CreateToken.json"); 
    }
}
