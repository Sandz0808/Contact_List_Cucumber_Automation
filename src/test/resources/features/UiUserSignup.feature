@UserSignup @Regression
Feature: UserSignup

  @TC1.1
  Scenario: Signup with valid details via UI
    Given the user clicks the Signup button
    When the user enters valid information details
    And the user clicks the Submit button
    Then the user verifies that the Contact List Page is displayed

  @TC1.2
  Scenario: Signup with invalid details via UI
    Given the user clicks the Signup button
    When the user enters invalid information details
    And the user clicks the Submit button
    Then the user verifies the error message
