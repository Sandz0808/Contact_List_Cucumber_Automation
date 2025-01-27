@AddNewContact
Feature: UserAddNewContact

  
	@TC4.1  
	Scenario: Verify Adding valid New Contact via UI
	  Given User logs in to the Contact List App 
	  When User clicks Add New Contact button
	  And User enters valid data details
	  And User clicks Submit button in Add Contact Page
	  Then User verify new contact is added
	  
	  
	@TC4.2  
	Scenario: Verify Adding invalid New Contact via UI
	 Given User logs in to the Contact List App
	  When User clicks Add New Contact button
	  And User enters invalid data details
	  And User clicks Submit button in Add Contact Page
	  Then User verifies the error message  