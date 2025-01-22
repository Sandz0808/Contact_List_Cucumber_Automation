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
    private By EMAIL_TXT = By.id("email");
    private By PASSWORD_TXT = By.id("password");
    private By SUBMIT_BTN = By.id("submit");
    private By INVALID_CRED_LBL = By.id("error");

    public void enterEmail(String dataName) throws IOException, AWTException  {
        keyboardActionsUtil.inputElement(EMAIL_TXT, dataName);
    }
   
    public void enterPassword(String dataName) throws IOException, AWTException  {
        keyboardActionsUtil.inputElement(PASSWORD_TXT, dataName);
    }

    public void clickSubmitButton() throws Exception {
    	mouseActionsUtil.clickElement(SUBMIT_BTN);
    }

    public void verifyInvalidCredErrorMessage(String attribute, String expectedValue) throws IOException, AWTException  {
        elementAssertUtil.assertElementAttribute(INVALID_CRED_LBL, attribute, expectedValue);
    }

    public void verifyContactListPage(String attribute,String expectedValue) throws IOException, AWTException  {
        elementAssertUtil.assertElementAttribute(CONTACT_LIST_LBL, attribute, expectedValue);
    }
    
 }
