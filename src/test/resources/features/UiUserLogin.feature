@LoginUser @Regression
Feature: UserLogin

  @TC3.1 @smoke
  Scenario: Login with valid credentials via UI
    Given User has an existing account
    When the user enters valid credentials
    And the user clicks the Submit button
    Then the user verifies that the Contact List Page is displayed

  @TC3.2 @smoke
  Scenario: Login with invalid credentials via UI
    Given the user has no existing account
    When the user enters invalid credentials
    Then the user verifies the corresponding error message
