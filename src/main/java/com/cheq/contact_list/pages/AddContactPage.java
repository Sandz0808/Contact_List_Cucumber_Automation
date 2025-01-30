package com.cheq.contact_list.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.awt.AWTException;
import java.io.IOException;
import com.cheq.contact_list.utils.ElementAssertionUtil;
import com.cheq.contact_list.utils.ElementKeyboardActionUtil;
import com.cheq.contact_list.utils.ElementMouseActionUtil;
import com.cheq.contact_list.utils.ElementWaitUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;

public class AddContactPage {

    WebDriver driver;
    
    private ElementWaitUtil waitUtil;
    private ElementMouseActionUtil mouseActionsUtil;
    private ElementKeyboardActionUtil keyboardActionsUtil;
    private ElementAssertionUtil elementAssertUtil;
    
    
    /** Constructor to initialize WebDriver and utility classes */
    public AddContactPage(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.waitUtil = new ElementWaitUtil(driver, screenshotUtil);
        this.keyboardActionsUtil = new ElementKeyboardActionUtil(driver, screenshotUtil);
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
        this.elementAssertUtil = new ElementAssertionUtil(driver, screenshotUtil);
    }

    /** Element locators for the Add Contact page */
    private By FIRST_NAME_TXT = By.id("firstName");
    private By LAST_NAME_TXT = By.id("lastName");
    private By DATE_OF_BIRTH_TXT = By.id("birthdate");
    private By EMAIL_TXT = By.id("email");
    private By PHONE_NUM_TXT = By.id("phone");
    private By STREET_ADDRESS1_TXT = By.id("street1");
    private By STREET_ADDRESS2_TXT = By.id("street2");
    private By CITY_TXT = By.id("city");
    private By STATE_TXT = By.id("stateProvince");
    private By POSTAL_CODE_TXT = By.id("postalCode");
    private By COUNTRY_TXT = By.id("country");
    private By SUBMIT_BTN = By.id("submit");
    private By BLANK_FIELD_ERR_LBL = By.id("error");

    /** Populates the first name field with the provided value 
     * @throws AWTException */
    public void enterFirstName(String dataName) throws IOException, AWTException {
    	keyboardActionsUtil.inputElement(FIRST_NAME_TXT, dataName);
    }

    /** Populates the last name field with the provided value 
     * @throws AWTException */
    public void enterLastName(String dataName) throws IOException, AWTException {
    	keyboardActionsUtil.inputElement(LAST_NAME_TXT, dataName);
    }

    /** Populates the city field with the provided value */
    public void enterCity(String city) throws IOException, AWTException  {
    	keyboardActionsUtil.inputElement(CITY_TXT, city);
    }

    /** Populates the state field with the provided value */
    public void enterState(String state) throws IOException, AWTException  {
    	keyboardActionsUtil.inputElement(STATE_TXT, state);
    }

    /** Populates the postal code field with the provided value */
    public void enterPostalCode(String postalCode) throws IOException, AWTException  {
    	keyboardActionsUtil.inputElement(POSTAL_CODE_TXT, postalCode);
    }

    /** Populates the country field with the provided value */
    public void enterCountry(String country) throws IOException, AWTException  {
    	keyboardActionsUtil.inputElement(COUNTRY_TXT, country);
    }

    /** Populates the date of birth field with the provided value */
    public void enterDateOfBirth(String dateOfBirth) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(DATE_OF_BIRTH_TXT, dateOfBirth);
    }

    /** Populates the email field with the provided value */
    public void enterEmail(String email) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	keyboardActionsUtil.inputElement(EMAIL_TXT, email);
    }

    /** Populates the phone number field with the provided value */
    public void enterPhoneNumber(String dataName) throws IOException, AWTException  {
    	keyboardActionsUtil.inputElement(PHONE_NUM_TXT, dataName);
    }

    /** Populates the first street address field with the provided value */
    public void enterStAddress1(String stAddress1) throws IOException, AWTException  {
    	keyboardActionsUtil.inputElement(STREET_ADDRESS1_TXT, stAddress1);
    }

    /** Populates the second street address field with the provided value */
    public void enterStAddress2(String stAddress2) throws IOException, AWTException  {
    	keyboardActionsUtil.inputElement(STREET_ADDRESS2_TXT, stAddress2);
    }

    /** Clicks the submit button after waiting for it to be click able */
    public void clickSubmitButton() throws Exception {
    	mouseActionsUtil.clickElement(SUBMIT_BTN);
    	waitUtil.implicitWait();
    }

    /** Verifies that the blank fields error message is displayed */
    public void verifyBlankFieldsErrorMessage(String attribute, String expectedValue) throws IOException, AWTException  {
    	elementAssertUtil.assertElementAttribute(BLANK_FIELD_ERR_LBL, attribute, expectedValue);
    }
}
