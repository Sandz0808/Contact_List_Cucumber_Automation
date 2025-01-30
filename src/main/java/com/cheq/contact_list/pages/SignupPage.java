package com.cheq.contact_list.pages;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cheq.contact_list.utils.ElementAssertionUtil;
import com.cheq.contact_list.utils.ElementKeyboardActionUtil;
import com.cheq.contact_list.utils.ElementMouseActionUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;

public class SignupPage {
    
    private ElementMouseActionUtil mouseActionsUtil;
    private ElementKeyboardActionUtil keyboardActionsUtil;
    private ElementAssertionUtil elementAssertUtil;
    
    
    /** Constructor to initialize WebDriver and utility classes */
    public SignupPage(WebDriver driver, ScreenshotUtil screenshotUtil) {
        
        this.keyboardActionsUtil = new ElementKeyboardActionUtil(driver, screenshotUtil);
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
        this.elementAssertUtil = new ElementAssertionUtil(driver, screenshotUtil);
    }
  
    /** Element locators for the sign up page */
    private By SIGNUP_BTN = By.id("signup");
    private By CONTACT_LIST_LBL = By.cssSelector(".main-content header h1");
    private By FIRST_NAME_TXT = By.id("firstName");
    private By LAST_NAME_TXT = By.id("lastName");
    private By EMAIL_TXT = By.id("email");
    private By PASSWORD_TXT = By.id("password");
    private By SUBMIT_BTN = By.id("submit");
    private By SIGNUP_ERROR_LBL = By.id("error");

    /** Clicks the sign up button */
    public void clickSignupButton() throws Exception {
        mouseActionsUtil.clickElement(SIGNUP_BTN);
    }

    /** Enters the first name into the first name field */
    public void enterFirstName(String firstName) throws IOException, AWTException {
        keyboardActionsUtil.inputElement(FIRST_NAME_TXT, firstName);
    }

    /** Enters the last name into the last name field */
    public void enterLastName(String lastName) throws IOException, AWTException {
        keyboardActionsUtil.inputElement(LAST_NAME_TXT, lastName);
    }

    /** Enters an email into the email field */
    public void enterEmail(String email) throws IOException, AWTException {
        keyboardActionsUtil.inputElement(EMAIL_TXT, email);
    }

    /** Enters a password into the password field */
    public void enterPassword(String password) throws IOException, AWTException {
        keyboardActionsUtil.inputElement(PASSWORD_TXT, password);
    }

    /** Clicks the submit button */
    public void clickSubmitButton() throws Exception {
        mouseActionsUtil.clickElement(SUBMIT_BTN);
    }

    /** Verifies that the Contact List Page is displayed */
    public void verifyContactListPage(String attribute, String expectedValue) throws IOException, AWTException {
        elementAssertUtil.assertElementAttribute(CONTACT_LIST_LBL, attribute, expectedValue);
    }

    /** Verifies the sign up error message */
    public void verifyErrorMessage(String attribute, String expectedValue) throws IOException, AWTException {
        elementAssertUtil.assertElementAttribute(SIGNUP_ERROR_LBL, attribute, expectedValue);
    }
}