@CreateUser @Regression
Feature: CreateUser

  Background:
  Given User has access to the application
  
  @TC1.1 @Api
  Scenario: Create a new user account
    When User use POST to create new user
    Then Verify the response code
    
