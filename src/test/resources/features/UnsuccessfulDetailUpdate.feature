@UnsuccessfulDetailUpdate @Regression
Feature: UnsuccessfulDetailUpdate

  Background:
  Given User has an existing account

  @TC9.1
  Scenario: Verify Update Contact with blank Firstname and Lastname fields via UI
    Given User is already logged in
    When User adds a new contact
    And User clicks a contact
    And User clicks Edit Contact button
    And Clear values of firstname and lastname
    And Update street address
    And User clicks submit button in Contact Detail Page
    Then Verify error message for blank required fields
          
  @TC9.2
  Scenario: Verify Update contact with Blank Firstname field
    Given User is already logged in
    When User adds a new contact
    And User clicks a contact
    And User clicks Edit Contact button
    And Clear values of firstname
    And Update street address
    And User clicks submit button in Contact Detail Page
    Then Verify error message for blank firstname

  @TC9.3
  Scenario: Verify Update Contact with blank fields via UI
    Given User is already logged in
    When User adds a new contact
    And User clicks a contact
    And User clicks Edit Contact button
    And Clear values of all fields
    And User clicks submit button in Contact Detail Page
    Then Verify error message for blank required fields
    
  @TC9.4 @Api
  Scenario: Verify Update Contact with blank Firstname and Lastname fields via API
   	When User use POST to add new contact
    And User use PATCH to update contact with blank firstname and lastname
    Then Verify error response code
    
  @TC9.5 @Api
  Scenario: Verify Update Contact with blank fields via API
   	When User use POST to add new contact
    And User use PATCH to update contact with blank fields
    Then Verify error response code
    