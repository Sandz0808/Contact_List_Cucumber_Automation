@UpdateContact @Regression
Feature: UpdateContact

  Background:
  Given User has an existing account

  @TC4.1
  Scenario: Verify Updating a contact detail via UI
    Given User is already logged in  
    When User adds a new contact
    And User clicks a contact
    And User clicks Edit Contact button
    And User updates the email field
    And User clicks submit button in Contact Detail Page
    Then Verify email is updated
    
  @TC4.2 
  Scenario: Verify Updating multiple contact details via UI
  	Given User is already logged in 
    When User adds a new contact  
    And User clicks a contact
    And User clicks Edit Contact button
    And User updates the multiple fields
    And User clicks submit button in Contact Detail Page
    Then Verify fields are updated
       
  @TC4.3 @Api
  Scenario: Verify Updating a contact detail via API
    When User use POST to add new contact
    And User use Patch to update the contact details
    Then Verify the patch response code
    
  @TC4.4 @Api 
  Scenario: Verify Updating multiple contact details via API
    When User use POST to add new contact
    And User use Patch to update multiple fields
    Then Verify the patch response code
    
  @TC4.5
  Scenario: Verify Updating a contact detail via API with UI validation
    When User use POST to add new contact
    And User use Patch to update email field
    And Verify the patch response code
    Then Verify contact detail is updated in the application
    
    