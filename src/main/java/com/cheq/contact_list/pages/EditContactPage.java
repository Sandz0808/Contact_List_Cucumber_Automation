package com.cheq.contact_list.pages;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.cheq.contact_list.utils.ElementAssertionUtil;
import com.cheq.contact_list.utils.ElementKeyboardActionUtil;
import com.cheq.contact_list.utils.ElementMouseActionUtil;
import com.cheq.contact_list.utils.ElementWaitUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;

public class EditContactPage {
		
    WebDriver driver;
    
    private ElementMouseActionUtil mouseActionsUtil;
    private ElementKeyboardActionUtil keyboardActionsUtil;
    private ElementAssertionUtil elementAssertUtil;
    private ScreenshotUtil screenshotUtil; 
    private ElementWaitUtil waitUtil;
    
    /** Constructor to initialize WebDriver and utility classes */
    public EditContactPage(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        this.waitUtil = new ElementWaitUtil(driver, screenshotUtil);
        this.keyboardActionsUtil = new ElementKeyboardActionUtil(driver, screenshotUtil);
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
        this.elementAssertUtil = new ElementAssertionUtil(driver, screenshotUtil);
    }

    /** Element locators for edit contact page */
       
    private By  FIRST_NAME_TXT = By.id("firstName");
    private By  LAST_NAME_TXT = By.id("lastName");
    private By  DATE_OF_BIRTH_TXT = By.id("birthdate");
    private By  EMAIL_TXT = By.id("email");
    private By  PHONE_NUM_TXT = By.id("phone");
    private By  ST_ADDRESS1_TXT = By.id("street1");
    private By  ST_ADDRESS2_TXT = By.id("street2");
    private By  CITY_TXT = By.id("city");
    private By  STATE_TXT = By.id("stateProvince");
    private By  POSTAL_CODE_TXT = By.id("postalCode");
    private By  COUNTRY_TXT = By.id("country");
    private By CONTACT_FULLNAME_TXT = By.xpath("//table[@id='myTable']/tr[1]/td[2]");
    private By  SUBMIT_BTN = By.id("submit");
    private By  UPDATE_ERR_LBL = By.id("error");
        
   
    /** Clears the value of the first name field */
    public void clearFirstName() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(FIRST_NAME_TXT);
    }
   
    /** Clears the value of the last name field */
    public void clearLastName() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(LAST_NAME_TXT);
    }
    
    /** Clears the value of the date of birth field */
    public void clearDateOfBirth() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(DATE_OF_BIRTH_TXT);
    }
    
    /** Clears the value of the email field */
    public void clearEmail() throws IOException, AWTException  {
    	
        keyboardActionsUtil.removeElementValue(EMAIL_TXT);
        waitUtil.implicitWait();
    }
    
    /** Clears the value of the phone number field */
    public void clearPhoneNum() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(PHONE_NUM_TXT);
    }
    
    /** Clears the value of the first street address field */
    public void clearStAddress1() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(ST_ADDRESS1_TXT);
    }
    
    /** Clears the value of the second street address field */
    public void clearStAddress2() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(ST_ADDRESS2_TXT);
    }
    
    /** Clears the value of the city field */
    public void clearCity() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(CITY_TXT);
    }
    
    /** Clears the value of the state field */
    public void clearState() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(STATE_TXT);
    }
    
    /** Clears the value of the postal code field */
    public void clearPostal() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(POSTAL_CODE_TXT);
    }
    

    /** Clears the value of the country field */
    public void clearCountry() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(COUNTRY_TXT);
    }

    
    /** Updates the phone number field */
    public void enterPhoneNumber(String newPhoneNum) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(PHONE_NUM_TXT, newPhoneNum);
    }

    /** Updates the city field */
    public void enterCity(String newCity) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(CITY_TXT, newCity);
    }

    /** Updates the postal code field */
    public void enterPostalCode(String newPostal) throws IOException, AWTException  {
    	
    	keyboardActionsUtil.inputElement(POSTAL_CODE_TXT, newPostal);
    	waitUtil.implicitWait();
    }

    /** Updates the email field */
    public void enterEmail(String dataName) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(EMAIL_TXT, dataName);
    	
    }	
    
    public void enterDateOfBirth(String dataName) throws IOException, AWTException  {
        waitUtil.implicitWait();
        keyboardActionsUtil.inputElement(DATE_OF_BIRTH_TXT, dataName);
    	
    	
    }

        /** Verifies that the expected error message is displayed */
    public void verifyErrorMessage(String attribute, String expectedValue) throws IOException, AWTException  {
        elementAssertUtil.assertElementAttribute(UPDATE_ERR_LBL, attribute, expectedValue);
    }

    /** Verifies that the city field has been updated correctly */
    public void verifyCityFieldUpdate(String attribute, String expectedValue) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	elementAssertUtil.assertElementAttribute(CITY_TXT, attribute, expectedValue);
    }

    /** Verifies that the postal code field has been updated correctly */
    public void verifyPostalFieldUpdate(String attribute, String expectedValue) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	elementAssertUtil.assertElementAttribute(POSTAL_CODE_TXT, attribute, expectedValue);
    }

    /** Verifies that the email field has been updated correctly */
    public void verifyEmailFieldUpdate(String attribute, String expectedValue) throws IOException, AWTException  {
    	waitUtil.implicitWait();
        elementAssertUtil.assertElementAttribute(EMAIL_TXT, attribute, expectedValue);
    }

    /** Verifies that a contact is not displayed on the page */
    public void verifyContactIsNotDisplayed() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        elementAssertUtil.assertElementIsNotVisible(CONTACT_FULLNAME_TXT);
    }
    
    /** Clicks the submit button  */
    public void clickSubmitButton() throws Exception {
    	mouseActionsUtil.clickElement(SUBMIT_BTN);
    	waitUtil.implicitWait();
    }
    
    
    
}
