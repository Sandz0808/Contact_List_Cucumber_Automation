@UserLogin @Regression
Feature: UserLogin

 As a user,
  I want to log in to the system
  So that I can access the application features.

  Background:
  Given API server should be up and running

  @TC3.3 @Api
  Scenario: Log In with valid credentials via API.
    When User Submit POST request to login endPoint
    Then Verify user login success code


   @TC3.4 @Api
  Scenario: Log In with invalid credentials via API.
    When User Submit POST request to login endPoint
    Then Verify user login error code
