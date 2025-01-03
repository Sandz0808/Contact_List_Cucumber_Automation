@DeleteContact
Feature: DeleteContact
 
  Background:
  Given User has an existing account
 
  @TC5.1
  Scenario: Verify a user can delete a new contact via UI 
    Given User is already logged in
    When User adds a new contact 
    And User clicks a contact
    And clicks delete contact button
    Then Verify contact is deleted
    
  @TC5.2
  Scenario: Verify a user can delete multiple contacts via UI
    Given User is already logged in
    When User adds a new contact
    And User clicks a contact
    And clicks delete contact button
    And Verify contact is deleted
    And User adds a new contact
    And User click second contact
    And clicks delete contact button
    Then Verify second contact is deleted
    
  @TC5.3 @Api
  Scenario: Verify deletion of a new contact via API   
    When User use POST to add new contact
    And Get the ID
    And Use DELETE to delete the contact
    Then Verify the response code for deletion
    
  @TC5.4 @Api
  Scenario: Verify deletion of multiple contact via API		
    When User use POST to add new contact
    And Get the ID
    And Use DELETE to delete the contact
    And Verify the response code for deletion
    And User use POST to add second contact
    And Get the second ID
    And Use DELETE to delete the second contact
    Then Verify the response code for deletion
    
  @TC5.5 
  Scenario: Verify deletion of a new contact via API with UI validation	
    When User use POST to add new contact
    And Get the ID
    And Use DELETE to delete the contact
    And User Login the Web Application
    Then Verify the contact is not displayed
