package com.cheq.contact_list.step_definitions;

import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import java.util.Properties;
import com.cheq.contact_list.utils.DataDictionaryUtil;
import com.cheq.contact_list.reusable_steps.ReusableSteps;


public class ApiLoginSteps {

    private Properties property;
    private String testDataAPI;
    private Response apiResponse;
    private ReusableSteps reusableSteps;

    public ApiLoginSteps() {
        initializeProperties();
        reusableSteps = new ReusableSteps();
    }

    private void initializeProperties() {
        ConfigReaderUtil configReader = new ConfigReaderUtil();
        property = configReader.initProperty();
        testDataAPI = property.getProperty("test_data_api");
    }

    String dataGroup = Hooks.getDataGroup();

	@When("User Submit POST request to login endPoint")
	public void user_submit_post_request_to_login_end_point() throws IOException {
		JsonNode loginData = DataDictionaryUtil.getDataNode(testDataAPI, "ValidLoginCredentials");
    	String email = loginData.path("email").asText();
        String password = loginData.path("password").asText();
        apiResponse = reusableSteps.loginUser(email, password);
	}

	@Then("Verify user login success code")
	public void verify_user_login_success_code() throws IOException {
		JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "ValidLoginCredentials");
        String expectedStatusCode = apiDataNode.path("statusCode").asText();
        reusableSteps.verifyResponseCode(apiResponse, expectedStatusCode);
	}

	@Then("Verify user login error code")
	public void verify_user_login_error_code() throws IOException {
		JsonNode loginData = DataDictionaryUtil.getDataNode(testDataAPI, "InvalidLoginCredentials");
    	String email = loginData.path("email").asText();
        String password = loginData.path("password").asText();
        apiResponse = reusableSteps.loginUser(email, password);

		JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "InvalidLoginCredentials");
        String expectedStatusCode = apiDataNode.path("statusCode").asText();
        reusableSteps.verifyResponseCode(apiResponse, expectedStatusCode);

	}
}





