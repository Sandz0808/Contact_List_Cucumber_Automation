@UnsuccessfulDeleteContact
Feature: UnsuccessfulDeleteContact

  Background:
  Given User has access to the application
  
  @TC10.1 @Api
  Scenario: Verify deletion of  contact using invalid ID via API 
  	Given User has an existing account
    When User use POST to add new contact
    And User use DELETE to delete a contact with invalid ID
    Then Verify the delete response code