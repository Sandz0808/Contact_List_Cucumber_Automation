@UnsuccessfulCreateUser @Regression
Feature: UnsuccessfulCreateUser

  Background:
  Given User has access to the application

  @TC6.1 @Api
  Scenario: Create User with blank Password field via API  
    When User use POST to create user with blank password field
    Then Verify the response code for blank field
    
  @TC6.2 @Api
  Scenario: Create User with blank fields via API
    When User use POST to create user with blank fields
    Then Verify the response code for blank field

