package com.cheq.contact_list.utils;

public class LogMessage {


	// Visibility-related messages
	public static final String VISIBLE_ELEMENT_MESSAGE = "Element located by ( '%s' ) is visible within the timeout period.";
	public static final String TIMEOUT_EXCEPTION_ERROR_MESSAGE = "Timeout occurred: Element located by ( '%s' ) is not visible within the specified timeout.";
	public static final String ASSERT_ELEMENT_IS_NOT_VISIBLE_MESSAGE = "Element:( '%s' ) is NOT visible as expected";
	public static final String ASSERT_ELEMENT_IS_NOT_VISIBLE_FAIL_MESSAGE = "Assertion failed. Element:( '%s' ) is visible which is NOT expected";

	// Element Interaction - Click and Input
	public static final String CLICKING_MESSAGE = "Clicking the element identified by ( '%s' ) successful.";
	public static final String INPUT_TEXT_MESSAGE = "Entering the text value '%s' into the element located by ( '%s' ).";
	public static final String INPUT_TEXT__FAILED_MESSAGE = "Failed to input the text value ( '%s' ) into the element located by ( '%s' ).";
	public static final String REMOVE_TEXT_MESSAGE = "Clearing text from the element located by ( '%s' ).";
	public static final String REMOVE_TEXT_FAILED_MESSAGE = "Clearing text from the element located by ( '%s' ) failed";
	public static final String NOT_CLICKABLE_ELEMENT_VERIFICATION_MESSAGE = "The element described as ( '%s' ) is not clickable.";
	public static final String CLICKABLE_ELEMENT_VERIFICATION_MESSAGE = "The element described as ( '%s' ) with locator '%s' is clickable.";
	public static final String DISABLED_ELEMENT_VERIFICATION_MESSAGE = "The element described as ( '%s' ) with locator '%s' is disabled.";
	public static final String CLICKABLE_ELEMENT_MESSAGE = "The element located by ( '%s' ) is clickable within the timeout period.";
	 
	//Verification
	public static final String VERIFY_RESPONSE_CODE_MESSAGE = "Actual response code to: ( '%s' ), Expected response code: ( '%s' ).";
	public static final String VERIFY_FAILED_RESPONSE_CODE_MESSAGE = "Verification error occured. Actual response code to: ( '%s' ), Expected response code: ( '%s' ).";
	
	// API Messages
	public static final String ADD_CONTACT_API_MESSAGE = "Adding contact via API response body: ( '%s' ).";
	public static final String FAILED_ADD_CONTACT_API_MESSAGE = "Adding contact via API failed. First name: ( '%s' ).";
	public static final String DELETE_CONTACT_API_MESSAGE = "Deleting contact via API response body: ( '%s' ).";
	public static final String DELETE_CONTACT_API_FAILED_MESSAGE = "Delete contact via API failed. Contact ID: ( '%s' ).";
	public static final String UPDATE_CONTACT_API_MESSAGE = "Update contact via API response body: ( '%s' ).";
	public static final String FAILED_UPDATE_CONTACT_API_MESSAGE = "Update contact via API failed. Contact ID: ( '%s' ).";
	public static final String CREATE_USER_API_MESSAGE = "Create user via API response body: ( '%s' ).";
	public static final String FAILED_CREATE_USER_API_MESSAGE = "Create user via API failed. Firstname: ( '%s' ).";
	public static final String LOGIN_API_MESSAGE = "Login via API response body: ( '%s' ).";
	public static final String FAILED_LOGIN_API_MESSAGE = "Login via API failed. Email ( '%s' ).";	
 
	// Text Assertion
	public static final String ELEMENT_ASSERTION_MESSAGE = "Assertion passed. Actual value: ( '%s' ), Expected value: ( '%s' ).";
	public static final String ASSERTION_EXCEPTION_ERROR_MESSAGE = "Assertion error occurred. Actual value: ( '%s' ), Expected value: ( '%s' ).";

	// Element Attribute
	public static final String ELEMENT_ATTRIBUTE_MESSAGE = "The actual value of the attribute is: ( '%s' ).";
	public static final String ELEMENT_ATTRIBUTE_EXCEPTION_MESSAGE = "Error occurred while retrieving the attribute value. Error: ( '%s' ).";
	
	public static final String CLEANUP_MESSAGE = "Test failed! Logging out...";
	
	
	/** Formats a message with values. */
    public static String formatMessage(String message, Object... values) {
        return String.format(message, values);
    }

}

