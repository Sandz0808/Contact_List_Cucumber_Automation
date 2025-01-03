@UnsuccessfulAddNewContact @Regression
Feature: UnsuccessfulAddNewContact
 
  Background:
  Given User has an existing account

  @TC8.1
  Scenario: Verify Adding Contact with blank Firstname and Lastname field via UI
    Given User is already logged in
    When User clicks Add New Contact button
    And User clicks submit button in Add Contact Page
    Then Verify error message displayed
       
  @TC8.2
  Scenario: Verify Adding Contact with blank Firstname field via UI
    Given User is already logged in
    When User clicks Add New Contact button
    And Populate fields with blank firstname
    And User clicks submit button in Add Contact Page
    Then Verify error message for blank first name field displayed
    
 	@TC8.3 @Api
  Scenario: Verify Adding Contact with blank Firstname and Lastname field via API  
	  When User use POST to add new contact with blank firstname and lastname
	  Then Verify the response code for blank required fields
	   
  @TC8.4 @Api
  Scenario: Verify Adding Contact with blank Firstname field via API
	  When User use POST to add new contact with blank firstname
	  Then Verify the response code for blank firstname field
	   
  