package com.cheq.contact_list.step_definitions;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;

import com.cheq.contact_list.hooks.Hooks;
import com.cheq.contact_list.utils.DriverFactory;

import com.cheq.contact_list.utils.ScreenshotUtil;
import com.cheq.contact_list.utils.ConfigReaderUtil;

import com.cheq.contact_list.pages.ContactDetailPage;
import com.cheq.contact_list.pages.ContactListPage;

import com.cheq.contact_list.reusable_steps.ReusableSteps;

import com.cheq.contact_list.utils.DataDictionaryUtil;

public class DeleteContactSteps {
	
	WebDriver driver;
	Properties property;
	
	private ConfigReaderUtil configReaderUtil;
	private ScreenshotUtil screenshotUtil;
    
	private ContactListPage contactListPage;
	private ContactDetailPage contactDetailPage;
	private ReusableSteps reusableSteps;
	
	private Response response; 
	private Response deleteResponse;
	private String stresponse;
    private String testDataAPI;
	

    public DeleteContactSteps() {
        driver = DriverFactory.getDriver();
        screenshotUtil = Hooks.getScreenshotUtil();
        contactListPage = new ContactListPage(driver, Hooks.getScreenshotUtil());
        contactDetailPage = new ContactDetailPage(driver, Hooks.getScreenshotUtil());
        reusableSteps = new ReusableSteps();
        getProperty();
    }

	public void getProperty() {
		configReaderUtil = new ConfigReaderUtil();
		property = configReaderUtil.initProperty();
        testDataAPI = property.getProperty("test_data_api");
	}

	
	String dataGroup = Hooks.getDataGroup();

	@And("clicks delete contact button")
	public void clicks_delete_contact_button() throws Exception {
		
		contactDetailPage.clickDeleteContact();
		reusableSteps.acceptAllert();
	}


	@Then("Verify contact is deleted")
	public void verify_contact_is_deleted() throws InterruptedException, IOException, AWTException {

		contactDetailPage.verifyContactIsNotDisplayed();

	}

    @Then("User click second contact")
    public void user_click_second_contact() throws Exception {
    	
    	contactListPage.clickTestContact();
    }

    @Then("Verify second contact is deleted")
    public void verify_second_contact_is_deleted() throws InterruptedException, IOException, AWTException {

    	contactDetailPage.verifyContactIsNotDisplayed();
    }

    @And("Get the ID")
    public void get_the_id() throws IOException {
    	
    	stresponse = reusableSteps.extractIdFromResponseBody("AddingToken.json");
    }

    @When("Use DELETE to delete the contact")
    public void use_delete_to_delete_the_contact() throws IOException {
    	
    	String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
    	deleteResponse = reusableSteps.deleteContact(stresponse, authToken);
    	reusableSteps.saveResponseBodyToFile(deleteResponse, "DeleteToken.json" );

    }
 
    @Then("Verify the response code for deletion")
    public void verify_the_response_code_for_deletion() throws IOException {
   		
        JsonNode apiDataNode = DataDictionaryUtil.getDataNode(testDataAPI, "AddingSuccessResponseAPI");
        String expectedResponseCodeStr = apiDataNode.path("expectedResponse").asText();
    	
        reusableSteps.verifyResponseCode(response, expectedResponseCodeStr);
	
    }
    

    @When("Get the second ID")
    public void get_the_second_id() throws IOException {
    	
    	stresponse = reusableSteps.extractIdFromResponseBody("AddingToken.json");

    }

    @When("Use DELETE to delete the second contact")
    public void use_delete_to_delete_the_second_contact() throws IOException {
    	
    	String authToken = reusableSteps.extractTokenFromResponseBody("CreateToken.json");
	   	
	   	deleteResponse = reusableSteps.deleteContact(stresponse, authToken);
	   	reusableSteps.saveResponseBodyToFile(deleteResponse, "DeleteToken.json" );

    	
    }

    @Then("Verify the contact is not displayed")
    public void verify_the_contact_is_not_displayed() throws InterruptedException, IOException, AWTException {

    	contactDetailPage.verifyContactIsNotDisplayed();
		
    }
    
	@Then("Delete new record via UI")
	public void delete_new_record_via_ui() throws Exception {

		contactListPage.clickTestContact();
		contactDetailPage.clickDeleteContact();
		reusableSteps.acceptAllert();
	
	}
    
 
}
