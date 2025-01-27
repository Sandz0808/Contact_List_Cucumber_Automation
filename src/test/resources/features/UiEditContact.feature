@EditUserContactDetails
Feature: EditUserContactDetails

  
	@TC7.1  
	Scenario: Editing Contact with valid data via UI
		 Given User logs in to the Contact List App
		 When User clicks the selected contact
		 And User clicks the Edit Contact button
		 And User clears the fields to edit
		 And User enters valid details into the cleared fields
		 And User clicks the Submit button
		 Then User verifies if the contact is updated
		 
		 
		 
 @TC7.2  
	Scenario: Editing Contact with invalid data via UI
		 Given User logs in to the Contact List App
		 When User clicks the selected contact
		 And User clicks the Edit Contact button
		 And User clears the fields to edit
		 And User enters invalid details into the cleared fields
		 And User clicks the Submit button
		 Then User verifies the error messages
 		 

	  
	
