@LoginUser @Regression
Feature: LoginUser

  Background:
  Given User has an existing account
  
  @TC2.1
  Scenario: Verify User Login 
    When User populates required login fields
    And User clicks submit button in the Login Page
    Then Verify Contact List Page
    
  @TC2.2
  Scenario: Login User
    When User populates required login fields with email "<email>", password "<password>"
    And User clicks submit button in the Login Page
    Then Verify Contact List Page
