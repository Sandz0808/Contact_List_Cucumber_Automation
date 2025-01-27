package com.cheq.contact_list.reusable_steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.cheq.contact_list.utils.DriverFactory;

import com.cheq.contact_list.utils.ElementWaitUtil;
import com.cheq.contact_list.utils.LogMessage;
import com.cheq.contact_list.utils.ReporterUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;
import com.cheq.contact_list.utils.ConfigReaderUtil;

public class ReusableSteps {
	
    WebDriver driver;
    Properties property;
    
    private ConfigReaderUtil configReaderUtil;
    private ScreenshotUtil screenshotUtil; 
	private ReporterUtil reportsUtil; 
    
    private Response response;
    private String logLevel;
    private String BASE_URI;
    private String testDataPath;
    private String apiTokenPath;  
    
    public ReusableSteps() {
    	screenshotUtil = screenshotUtil;
        driver = DriverFactory.getDriver();
        this.reportsUtil = new ReporterUtil(driver, screenshotUtil);
        getProperty();

    }
  
    /** Initializes properties and the JSON file */
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        BASE_URI = property.getProperty("url");
        testDataPath = property.getProperty("test_data_api");
        apiTokenPath = property.getProperty("api_token_path");
    } 
    
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);
	
    /** Waits for alert to be present and returns the Alert object. */
    public Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
	
	public void acceptAllert() {
		waitForAlert();
		
    	Alert alert = driver.switchTo().alert();
	    alert.accept();
	}
	
	/** Adds a new contact with detailed information. */
    public Response addContactFullDetail(String firstName, String lastName, String dateOfBirth, String email, String phoneNum,
                               String stAddress1, String stAddress2, String city, String state,
                               String postalCode, String country, String authToken) throws IOException {
       
        RestAssured.baseURI = BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
       
        
        try {
	        String requestBody = String.format("{"
	                + "\"firstName\": \"%s\","
	                + "\"lastName\": \"%s\","
	                + "\"birthdate\": \"%s\","
	                + "\"email\": \"%s\","
	                + "\"phone\": \"%s\","
	                + "\"street1\": \"%s\","
	                + "\"street2\": \"%s\","
	                + "\"city\": \"%s\","
	                + "\"stateProvince\": \"%s\","
	                + "\"postalCode\": \"%s\","
	                + "\"country\": \"%s\""
	                + "}", firstName, lastName, dateOfBirth, email, phoneNum, stAddress1, stAddress2, city, state, postalCode, country);
	        
	        httpRequest.body(requestBody);
	        httpRequest.header("Content-Type", "application/json");
	        httpRequest.header("Authorization", "Bearer " + authToken);
	        
	        Response response = httpRequest.post("/contacts");
	        
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String prettyJsonResponse = gson.toJson(gson.fromJson(response.getBody().asString(), Object.class));
	        logLevel = "INFO";  
	        reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.ADD_CONTACT_API_MESSAGE, prettyJsonResponse), " ");
	       
	        
	        return response;
	        
        
        } catch (Exception e) {
        	 logLevel = "ERROR";
        	 reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.FAILED_ADD_CONTACT_API_MESSAGE, firstName), " ");
             throw e;
         }
        
      
        
    }
    
	/** Adds a new contact with detailed information. */
    public Response addContactRequiredField(String firstName, String lastName, String authToken) throws IOException {
       
        RestAssured.baseURI = BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
   	
        try {
        	
            String requestBody = String.format("{"
                    + "\"firstName\": \"%s\","
                    + "\"lastName\": \"%s\""
                    + "}", firstName, lastName);
            
            httpRequest.body(requestBody);
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("Authorization", "Bearer " + authToken);
            
            Response response = httpRequest.post("/contacts");
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonResponse = gson.toJson(gson.fromJson(response.getBody().asString(), Object.class));
            
            logLevel = "INFO";  
            reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.ADD_CONTACT_API_MESSAGE, prettyJsonResponse), " ");
            return response;

            
        } catch (Exception e) {
        	
            logLevel = "ERROR";
            reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.FAILED_ADD_CONTACT_API_MESSAGE, firstName), " ");
            throw e;
        }
  
    }
    
    /** Sends a DELETE request to remove a contact by ID and logs the response. */
    public Response deleteContact(String contactId, String authToken) throws IOException {
    	
        
        try {
            RequestSpecification httpRequest = RestAssured.given()
                    .baseUri(BASE_URI) 
                    .header("Authorization", "Bearer " + authToken);
            
            Response response = httpRequest.delete("/contacts/" + contactId);
        	
	        logLevel = "INFO";  
	        reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.DELETE_CONTACT_API_MESSAGE, contactId.toString()), " ");
	        
	        return response;
        
        } catch (Exception e) {
        	 logLevel = "ERROR";
        	 reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.DELETE_CONTACT_API_FAILED_MESSAGE, contactId.toString()), " ");
             throw e;
         }
      
        
    }
    
    
    /** Updates email details for a contact. */
    public Response updateEmailDetail(String contactId, String email, String authToken) throws IOException {

        RestAssured.baseURI = BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
  
        try {
            String requestBody = String.format("{\"email\": \"%s\"}", email);

            httpRequest.body(requestBody);
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("Authorization", "Bearer " + authToken);
            
            Response response = httpRequest.patch("/contacts/" + contactId);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
 	        String prettyJsonResponse = gson.toJson(gson.fromJson(response.getBody().asString(), Object.class));
	        logLevel = "INFO";  
	        reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.UPDATE_CONTACT_API_MESSAGE, prettyJsonResponse), " ");
	        
	        return response;
        
        } catch (Exception e) {
        	 logLevel = "ERROR";
             reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.FAILED_UPDATE_CONTACT_API_MESSAGE, contactId.toString()), " ");
             throw e;
         }
        
    }
    
    /** Updates contact details with blank first and last name. */
    public Response updateContact(String contactId, String firstname, String lastName, String birthdate,
            String email, String phone, String st1, String st2, String city, String stateProvince, 
            String postalCode, String country, String authToken) throws IOException {
    	

        RestAssured.baseURI = BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
      
        try {
        	
        	String requestBody = String.format("{\"firstName\": \"%s\",\"lastName\": \"%s\",\"birthdate\": \"%s\",\"email\": \"%s\",\"phone\": \"%s\",\"street1\": \"%s\",\"street2\": \"%s\",\"city\": \"%s\",\"stateProvince\": \"%s\",\"postalCode\": \"%s\",\"country\": \"%s\"}",
             		firstname.toString(), lastName, birthdate, email, phone, st1, st2, city, stateProvince, postalCode, country);

            httpRequest.body(requestBody);
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("Authorization", "Bearer " + authToken.toString());

            Response response = httpRequest.patch("/contacts/" + contactId.toString());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
 	        String prettyJsonResponse = gson.toJson(gson.fromJson(response.getBody().asString(), Object.class));
	        logLevel = "INFO";  
	        reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.UPDATE_CONTACT_API_MESSAGE, prettyJsonResponse), " ");
	        
	        return response;
        
        } catch (Exception e) {
        	 logLevel = "ERROR";
             reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.FAILED_UPDATE_CONTACT_API_MESSAGE, contactId.toString()), " ");
             throw e;
         }
             
    }
    
    /** Sends a POST request to create a user. */
    public Response createNewUser(String firstName, String lastName, String email, String password) throws IOException {

        RestAssured.baseURI = BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
        
        try {
        	String requestBody = String.format("{"
                    + "\"firstName\": \"%s\","
                    + "\"lastName\": \"%s\","
                    + "\"email\": \"%s\","
                    + "\"password\": \"%s\""
                    + "}", firstName, lastName, email, password);
            
            httpRequest.body(requestBody);
            httpRequest.header("Content-Type", "application/json");
            
            Response response = httpRequest.post("/users");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
 	        String prettyJsonResponse = gson.toJson(gson.fromJson(response.getBody().asString(), Object.class));
	        logLevel = "INFO";  
	        reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.CREATE_USER_API_MESSAGE, prettyJsonResponse), " ");
	        
	        return response;
        
        } catch (Exception e) {
        	 logLevel = "ERROR";
             reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.FAILED_CREATE_USER_API_MESSAGE, firstName.toString()), " ");
             throw e;
         }

    }
    
    /** Verifies the response code against the expected value from the JSON file. */
    public void verifyResponseCode(Response response, String dataName) throws IOException {
        String expectedResponseCode = dataName.toString();

        if (response != null) {
            String actualStatusCode = Integer.toString(response.getStatusCode());
            expectedResponseCode = expectedResponseCode.trim();

            if (actualStatusCode.equals(expectedResponseCode)) {
                logLevel = "INFO";  
                reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.VERIFY_RESPONSE_CODE_MESSAGE, actualStatusCode, expectedResponseCode), null);
            } else {
                logLevel = "ERROR";  
                reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.VERIFY_FAILED_RESPONSE_CODE_MESSAGE, actualStatusCode, expectedResponseCode), null);
            }
        } 
    }
    
    /** Saves the response body to a specified file and updates the test data file with the token. */
    public void saveResponseBodyToFile(Response response, String fileName) throws IOException {
        String jsonFilePath = System.getProperty("user.dir") + apiTokenPath + fileName;

        if (response != null) {
            String responseBody = response.getBody().asString();

            File responseFile = new File(jsonFilePath);
            responseFile.getParentFile().mkdirs();

            JsonNode responseJsonNode = null;
            try {
                responseJsonNode = objectMapper.readTree(responseBody);
             
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(responseFile, responseJsonNode);

                if (responseJsonNode.has("token")) {
                    String token = responseJsonNode.get("token").asText();
                    File testDataFile = new File(System.getProperty("user.dir") + testDataPath);

                    if (testDataFile.exists()) {
                        ObjectNode testDataJsonNode = (ObjectNode) objectMapper.readTree(testDataFile);

                        ObjectNode authCodeNode = (ObjectNode) testDataJsonNode.get("AuthCode");
                        if (authCodeNode == null) {
                            authCodeNode = objectMapper.createObjectNode();
                            testDataJsonNode.set("AuthCode", authCodeNode);
                        }

                        authCodeNode.put("token", token);

                        objectMapper.writerWithDefaultPrettyPrinter().writeValue(testDataFile, testDataJsonNode);
                    }
                }

            } catch (IOException e) {

                writePlainText(responseBody, responseFile);
            }
        }
    }

    /** Writes plain text response to a file, enclosing it in double quotes. */
    private void writePlainText(String responseBody, File responseFile) throws IOException {
        
        String quotedResponseBody = "\"" + responseBody.replace("\"", "\\\"") + "\"";
        java.nio.file.Files.write(responseFile.toPath(), quotedResponseBody.getBytes());
    }

    /** Extracts the ID from a JSON file. */
    public String extractIdFromResponseBody(String fileName) throws IOException {
        String filePath = System.getProperty("user.dir") + apiTokenPath + fileName;
        File file = new File(filePath);

        if (file.exists()) {
            JsonNode rootNode = objectMapper.readTree(file);
            String id = rootNode.path("_id").asText(null);
            if (id != null) {
                id = id.trim();
            } else {
                System.err.println("ID not found in the JSON file.");
            }
            return id;
        } else {
            System.err.println("The file does not exist: " + filePath);
            return null;
        }
    }

    /** Extracts the token from a JSON file. */
    public String extractTokenFromResponseBody(String fileName) throws IOException {
        String filePath = System.getProperty("user.dir") + apiTokenPath + fileName;
        File file = new File(filePath);

        if (file.exists()) {
            JsonNode rootNode = objectMapper.readTree(file);
            String token = rootNode.path("token").asText(null);  // Adjust field name for token
            if (token != null) {
                token = token.trim();
            } else {
                System.err.println("Token not found in the JSON file.");
            }
            return token;
        } else {
            System.err.println("The file does not exist: " + filePath);
            return null;
        }
    }
    
    public Response loginUser(String email, String password) throws IOException {

        RestAssured.baseURI = BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
        
        try {
        	String requestBody = String.format("{"
                    + "\"email\": \"%s\","
                    + "\"password\": \"%s\""
                    + "}", email, password);
            
            httpRequest.body(requestBody);
            httpRequest.header("Content-Type", "application/json");  
	        //httpRequest.header("Authorization", "Bearer " + authToken);
            
            Response response = httpRequest.post("/users/login");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
 	        String prettyJsonResponse = gson.toJson(gson.fromJson(response.getBody().asString(), Object.class));
	        logLevel = "INFO";  
	        reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.LOGIN_API_MESSAGE, prettyJsonResponse), " ");
	        
	        return response;
        
        } catch (Exception e) {
        	 logLevel = "ERROR";
             reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.FAILED_LOGIN_API_MESSAGE, email.toString()), " ");
             throw e;
         }

    }
    
    public Response updateUserfirstName(String contactId, String key,  String newInfo, String authToken) throws IOException {

        RestAssured.baseURI = BASE_URI;
        RequestSpecification httpRequest = RestAssured.given();
  
        try {
            String requestBody = String.format("{\"firstName\": \"%s\"}", newInfo);
            //String requestBody = String.format("{\"email\": \"%s\"}", email);

            httpRequest.body(requestBody);
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("Authorization", "Bearer " + authToken);
            
            Response response = httpRequest.patch("/contacts/" + contactId);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
 	        String prettyJsonResponse = gson.toJson(gson.fromJson(response.getBody().asString(), Object.class));
	        logLevel = "INFO";  
	        reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.UPDATE_CONTACT_API_MESSAGE, prettyJsonResponse), " ");
	        
	        return response;
        
        } catch (Exception e) {
        	 logLevel = "ERROR";
             reportsUtil.resultsReporterAPI(logLevel, LogMessage.formatMessage(LogMessage.FAILED_UPDATE_CONTACT_API_MESSAGE, contactId.toString()), " ");
             throw e;
         }
        
    }
    
}
    

