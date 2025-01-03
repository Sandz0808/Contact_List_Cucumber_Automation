@UnsuccessfulUserLogin @Regression
Feature: UnsuccessfulUserLogin

  Background:
  Given User has an existing account

  @TC7.1
  Scenario: User login with invalid credentials
  	When User populates login fields with invalid credentials
    And User clicks submit button in the Login Page
    Then Verify error message for invalid credentials
    
  @TC7.2
  Scenario: User login with blank fields credentials  
  	When User populates login fields with blank credentials
    And User clicks submit button in the Login Page
    Then Verify error message for blank fields
