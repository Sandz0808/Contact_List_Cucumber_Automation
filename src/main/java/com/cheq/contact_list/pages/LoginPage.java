package com.cheq.contact_list.pages;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cheq.contact_list.utils.ElementAssertionUtil;
import com.cheq.contact_list.utils.ElementKeyboardActionUtil;
import com.cheq.contact_list.utils.ElementMouseActionUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;

public class LoginPage {

    private WebDriver driver;
    
    private ElementMouseActionUtil mouseActionsUtil;
    private ElementKeyboardActionUtil keyboardActionsUtil;
    private ElementAssertionUtil elementAssertUtil;
    private ScreenshotUtil screenshotUtil;
    
    /** Constructor to initialize WebDriver and utility classes */
    public LoginPage(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        this.keyboardActionsUtil = new ElementKeyboardActionUtil(driver, screenshotUtil);
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
        this.elementAssertUtil = new ElementAssertionUtil(driver, screenshotUtil);
    }
  
    /** Element locators for the login page */
    private By CONTACT_LIST_LBL = By.cssSelector(".main-content header h1");
    private By EMAIL_TXT = By.cssSelector("#email");
    private By PASSWORD_TXT = By.cssSelector("#password");
    private By SUBMIT_BTN = By.cssSelector("#submit");
    private By INVALID_CRED_LBL = By.cssSelector("#error");

    /** Enters an email into the email field using the keyboard action utility */
    public void enterEmail(String dataName) throws IOException, AWTException  {
        keyboardActionsUtil.inputElement(EMAIL_TXT, dataName);
    }

    /** Enters a password into the password field using the keyboard action utility */
    public void enterPassword(String dataName) throws IOException, AWTException  {
        keyboardActionsUtil.inputElement(PASSWORD_TXT, dataName);
    }

    /** Clicks the submit button to log in using the mouse action utility 
     * @throws Exception */
    public void clickSubmitButton() throws Exception {
    	mouseActionsUtil.clickElement(SUBMIT_BTN);
    }

    /** Verifies the invalid credentials error message using the element assertion utility */
    public void verifyInvalidCredErrorMessage(String attribute, String expectedValue) throws IOException, AWTException  {
        elementAssertUtil.assertElementAttribute(INVALID_CRED_LBL, attribute, expectedValue);
    }

    /** Verifies that the user is on the contact list page after login using the element assertion utility */
    public void verifyContactListPage(String attribute,String expectedValue) throws IOException, AWTException  {
        elementAssertUtil.assertElementAttribute(CONTACT_LIST_LBL, attribute, expectedValue);
    }
    
 }
