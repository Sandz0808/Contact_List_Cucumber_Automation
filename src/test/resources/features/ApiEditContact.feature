@EditUserContact
Feature: EditUserContact

  Background:
    Given User has an existing account

  @TC7.3 @Api
  Scenario: Verify editing a contact via API
    When User Submit POST request to users endPoint
    When User Submit POST request to /contacts/ endPoint
    When User Submit PUT request to users endPoint
    Then Verify response code should indicate that the user is successfully updated
    
    
    
   @TC7.3 @Api
  Scenario: Verify editing a contact via API
    When User Submit POST request to users endPoint
    When User Submit POST request to /contacts/ endPoint
    When User Submit PUT request to users endPoint
    Then Verify error response code and  user is unsuccessfully updated  
