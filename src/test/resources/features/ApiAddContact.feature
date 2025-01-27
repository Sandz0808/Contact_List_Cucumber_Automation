@AddNewContact @Regression
Feature: AddContact

  Background:
  Given API server should be up and running

   @TC4.4 @Api
  Scenario: Add Contact with missing Data via API.
    When User Submit POST request to /contacts/ endPoint
    Then Verify error status code and that the contact is not created
    
    @TC4.3 @Api
  Scenario: Add Contact with valid Data via API
    When User Submit POST request to /contacts/ endPoint
    Then Verify that the new Contact is created
    
    
  
