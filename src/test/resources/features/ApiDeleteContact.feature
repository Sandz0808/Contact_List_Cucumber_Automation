@DeleteUserContact
Feature: DeleteUserContact
 
  Background:
  Given User has an existing account
 
   @TC5.3 @Api
  Scenario: Verify deletion of a new contact via API   
    When User Submit POST request to /contacts/ endPoint
    And Get the ID
    And Submit DELETE to delete the contact
    Then Verify the response code for deletion
    
 