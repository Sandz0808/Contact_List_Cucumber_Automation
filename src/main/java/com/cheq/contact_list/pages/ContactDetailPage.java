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

public class ContactDetailPage {
		
    WebDriver driver;
    
    private ElementMouseActionUtil mouseActionsUtil;
    private ElementKeyboardActionUtil keyboardActionsUtil;
    private ElementAssertionUtil elementAssertUtil;
    private ScreenshotUtil screenshotUtil; 
    private ElementWaitUtil waitUtil;
    
    /** Constructor to initialize WebDriver and utility classes */
    public ContactDetailPage(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        this.waitUtil = new ElementWaitUtil(driver, screenshotUtil);
        this.keyboardActionsUtil = new ElementKeyboardActionUtil(driver, screenshotUtil);
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
        this.elementAssertUtil = new ElementAssertionUtil(driver, screenshotUtil);
    }

    /** Element locators for the contact detail page */
    private By  EDIT_CONTACT_BTN = By.cssSelector("#edit-contact");
    private By  DELETE_CONTACT_BTN = By.cssSelector("#delete");
    private By  RETURN_TO_CONTACT_LIST_BTN = By.cssSelector("#return");
    private By  EMAIL_TXT = By.cssSelector("#email");
    private By  PHONE_NUM_TXT = By.cssSelector("#phone");
    private By  CITY_TXT = By.cssSelector("#city");
    private By  POSTAL_CODE_TXT = By.cssSelector("#postalCode");
    private By  SUBMIT_BTN = By.cssSelector("#submit");
    private By  LAST_NAME_TXT = By.cssSelector("#lastName");
    private By  FIRST_NAME_TXT = By.cssSelector("#firstName");
    private By  ST_ADDRESS1_TXT = By.cssSelector("#street1");
    private By  ST_ADDRESS2_TXT = By.cssSelector("#street2");
    private By  UPDATE_ERR_LBL = By.cssSelector("#error");
    private By  STATE_TXT = By.cssSelector("#stateProvince");
    private By  COUNTRY_TXT = By.cssSelector("#country");
    private By  DATE_OF_BIRTH_TXT = By.cssSelector("#birthdate");
    private By CONTACT_FULLNAME_TXT = By.xpath("//table[@id='myTable']/tr[1]/td[2]");

    /** Updates the street address field */
    public void enterStAddress(String dataName) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(ST_ADDRESS1_TXT, dataName);
    }

    /** Clears the value of the last name field */
    public void removeLastNameValue() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(LAST_NAME_TXT);
    }

    /** Clears the value of the first name field */
    public void removeFirstNameValue() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(FIRST_NAME_TXT);
    }

    /** Clears the value of the country field */
    public void removeCountryValue() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(COUNTRY_TXT);
    }

    /** Clears the value of the postal code field */
    public void removePostalValue() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(POSTAL_CODE_TXT);
    }

    /** Clears the value of the state field */
    public void removeStateValue() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(STATE_TXT);
    }

    /** Clears the value of the city field */
    public void removeCityValue() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(CITY_TXT);
    }

    /** Clears the value of the date of birth field */
    public void removeDateOfBirthValue() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(DATE_OF_BIRTH_TXT);
    }

    /** Clears the value of the phone number field */
    public void removePhoneNumValue() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(PHONE_NUM_TXT);
    }

    /** Clears the value of the second street address field */
    public void removeStAddress2Value() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(ST_ADDRESS2_TXT);
    }

    /** Clears the value of the first street address field */
    public void removeStAddress1Value() throws IOException, AWTException  {
    	waitUtil.implicitWait();
        keyboardActionsUtil.removeElementValue(ST_ADDRESS1_TXT);
    }

    /** Clears the value of the email field */
    public void removEmailValue() throws IOException, AWTException  {
    	
        keyboardActionsUtil.removeElementValue(EMAIL_TXT);
        waitUtil.implicitWait();
    }

    /** Clicks the edit contact button */
    public void clickEditContactButton() throws Exception {
    	mouseActionsUtil.clickElement(EDIT_CONTACT_BTN);
    }

    /** Updates the phone number field */
    public void enterPhoneNumberDetail(String newPhoneNum) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(PHONE_NUM_TXT, newPhoneNum);
    }

    /** Updates the city field */
    public void enterCityDetail(String newCity) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(CITY_TXT, newCity);
    }

    /** Updates the postal code field */
    public void enterPostalDetail(String newPostal) throws IOException, AWTException  {
    	
    	keyboardActionsUtil.inputElement(POSTAL_CODE_TXT, newPostal);
    	waitUtil.implicitWait();
    }

    /** Updates the email field */
    public void enterEmailDetail(String dataName) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(EMAIL_TXT, dataName);
    }

    /** Clicks the submit button  */
    public void clickSubmitButton() throws Exception {
    	mouseActionsUtil.clickElement(SUBMIT_BTN);
    	waitUtil.implicitWait();
    }

    /** Verifies that the expected error message is displayed */
    public void verifyUpdateErrorMessage(String attribute, String expectedValue) throws IOException, AWTException  {
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
    
    /** Returns the locator for Delete button  */
    public void clickDeleteContact() throws Exception {
    	mouseActionsUtil.clickElement(DELETE_CONTACT_BTN);
    	waitUtil.implicitWait();
    }
}
