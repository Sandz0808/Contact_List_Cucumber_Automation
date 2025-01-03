@AddNewContact
Feature: AddNewContact

  Background:
  Given User has an existing account

	@TC3.1  
	Scenario: Verify Adding New Contact via UI
	  Given User is already logged in  
	  When User clicks Add New Contact button
	  And User populates all fields
	  And User clicks submit button in Add Contact Page
	  Then Verify new contact is added
      
  @TC3.2
  Scenario: Verify Adding New Contact Using Required Fields via UI 
    Given User is already logged in
    When User clicks Add New Contact button
    And User populates required fields
    And User clicks submit button in Add Contact Page
    Then Verify new contact is added
    
  @TC3.3  
	Scenario: Verify Adding Multiple Contacts via UI	
	  Given User is already logged in  
	  When User clicks Add New Contact button
	  And User populates all fields
	  And User clicks submit button in Add Contact Page
	  And Verify new contact is added
	  And Delete new record via UI
	  And User clicks Add New Contact button
	  And User populates all fields
	  And User clicks submit button in Add Contact Page
	  Then Verify new contact is added
	  
  @TC3.4 @Api
  Scenario: Verify Adding New Contact Using via API 
    When User use POST to add new contact
    Then Verify the response code for adding new contact
 
  @TC3.5 @Api
  Scenario: Verify Adding New Contact Using Firstname and Lastname via API
    When User use POST to add new contact using Firstname and Lastname
    Then Verify the response code for adding new contact using firstname and lastname
    
  @TC3.6 @Api
  Scenario: Verify Adding Muiltiple Contact via API	
    When User use POST to add first contact
    And Verify the response code for adding new contact
    And User use POST to add second contact
    Then Verify the response code for adding new contact

  @TC3.7
  Scenario: Verify Adding Contact via API with UI Validation
    When User use POST to add new contact
    And Verify the response code for adding new contact
    And User Login the Web Application
    Then Verify new contact is added