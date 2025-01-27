package com.cheq.contact_list.step_definitions;

import java.io.IOException;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.hooks.Hooks;
import java.util.Properties;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.test_data.TestDataGenerator;
import com.cheq.contact_list.reusable_steps.ReusableSteps;


public class ApiSignupSteps {

    private Properties property;
    private String testDataAPI;
    private Response ApiResponse;
    private ReusableSteps reusableSteps;

    public ApiSignupSteps() {
        initializeProperties();
        reusableSteps = new ReusableSteps();
    }
   
    private void initializeProperties() {
        ConfigReaderUtil configReader = new ConfigReaderUtil();
        property = configReader.initProperty();
        testDataAPI = property.getProperty("test_data_api");
    }

    String dataGroup = Hooks.getDataGroup();

	@Given("API server should be up and running")
	public void api_server_should_be_up_and_running() {
	}

	@When("User Submit POST request to users endPoint")
	public void user_submit_post_request_to_end_point() throws IOException {
		String firstName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "firstName");
    	String lastName = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "lastName");
    	String email = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "email");
    	String password = TestDataGenerator.getGeneratedDataFromDictionary(dataGroup, "password");

        ApiResponse = reusableSteps.createNewUser(firstName, lastName, email, password);
        reusableSteps.saveResponseBodyToFile(ApiResponse, "CreateToken.json");
    }

	@Then("Verify create user success code")
	public void verify_create_user_success_code() throws IOException {
		JsonNode uiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "CreateSuccessResponseAPI");
        String expectedResponseCodeStr = uiDataNode.path("expectedResponse").asText();
        reusableSteps.verifyResponseCode(ApiResponse, expectedResponseCodeStr);
	}

	@Then("Verify create user error code")
	public void verify_create_user_error_code() throws IOException {
		
		JsonNode usedEmailData = DataDictionaryUtil.getDataNode(testDataAPI, "InvalidCreateUserAPI");
		String firstName = usedEmailData.path("firstName").asText();
		String lastName = usedEmailData.path("lastName").asText();
		String email = usedEmailData.path("email").asText();
		String password = usedEmailData.path("password").asText();
		ApiResponse = reusableSteps.createNewUser(firstName, lastName, email, password);
		
		JsonNode errorResponseCode = DataDictionaryUtil.getDataNode(testDataAPI, "InvalidCreateUserAPI");
        String failedResponse = errorResponseCode.path("responseCode").asText();
        reusableSteps.verifyResponseCode(ApiResponse, failedResponse);
    }
}


