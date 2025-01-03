package com.cheq.contact_list.step_definitions;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

import com.cheq.contact_list.utils.DriverFactory;
import com.cheq.contact_list.hooks.Hooks;

import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;

import com.cheq.contact_list.reusable_steps.ReusableSteps;

public class UnsuccessfulDeleteContactSteps {

	WebDriver driver;
	Properties property;

	private ConfigReaderUtil configReaderUtil;
	private ReusableSteps reusableSteps;

	private String testDataAPI;
	private Response deleteResponse;
	private Response response;

	public UnsuccessfulDeleteContactSteps() {
		driver = DriverFactory.getDriver();
		reusableSteps = new ReusableSteps();
		getProperty();
	}

	public void getProperty() {
		configReaderUtil = new ConfigReaderUtil();
		property = configReaderUtil.initProperty();
		testDataAPI = property.getProperty("test_data_api");
	}

	String dataGroup = Hooks.getDataGroup();

	@And("User use DELETE to delete a contact with invalid ID")
	public void user_use_delete_to_delete_a_contact_with_invalid_id() throws IOException {

		JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "FailedDeleteAPI");
		String invalidId = apiDataNode.path("invalidID").asText();
		String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");

		deleteResponse = reusableSteps.deleteContact(invalidId, authToken);
		reusableSteps.saveResponseBodyToFile(deleteResponse, "DeleteToken.json");

	}

	@Then("Verify the delete response code")
	public void verify_the_delete_response_code() throws IOException {

		JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "FailedDeleteResponseAPI");
		String expectedResponseCodeStr = apiDataNode.path("expectedResponse").asText();

		reusableSteps.verifyResponseCode(deleteResponse, expectedResponseCodeStr);

	}

}
