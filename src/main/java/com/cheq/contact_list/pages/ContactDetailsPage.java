package com.cheq.contact_list.pages;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.cheq.contact_list.utils.ElementAssertionUtil;
import com.cheq.contact_list.utils.ElementMouseActionUtil;
import com.cheq.contact_list.utils.ElementWaitUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;

public class ContactDetailsPage {
	
WebDriver driver;
    
    private ElementMouseActionUtil mouseActionsUtil;    
    private ElementAssertionUtil elementAssertUtil;    
    private ElementWaitUtil waitUtil;
    
    /** Constructor to initialize WebDriver and utility classes */
    public ContactDetailsPage(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        
        this.waitUtil = new ElementWaitUtil(driver, screenshotUtil);       
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
        this.elementAssertUtil = new ElementAssertionUtil(driver, screenshotUtil);
    }
    
    
    private By  EDIT_CONTACT_BTN = By.id("edit-contact");
    private By  DELETE_CONTACT_BTN = By.id("delete");
    private By  RETURN_TO_CONTACT_LIST_BTN = By.id("return");
    private By  FIRST_NAME_TXT = By.id("firstName");
    private By  LAST_NAME_TXT = By.id("lastName");
    private By  CONTACT_DETAILS_LBL = By.cssSelector("div[class='main-content'] header h1");
    
    
    /** Clicks the edit contact button */
    public void clickEditContactButton() throws Exception {
    	mouseActionsUtil.clickElement(EDIT_CONTACT_BTN);
    }
    
    /** Returns the locator for Delete button  */
    public void clickDeleteContact() throws Exception {
    	mouseActionsUtil.clickElement(DELETE_CONTACT_BTN);
    	waitUtil.implicitWait();
    }
    
    /** Returns the locator for Delete button  */
    public void clickReturnToListButton() throws Exception {
    	mouseActionsUtil.clickElement(RETURN_TO_CONTACT_LIST_BTN);
    	waitUtil.implicitWait();
    }
    
    public void verifyUpdatedFirstName(String attribute, String expectedValue) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	elementAssertUtil.assertElementAttribute(FIRST_NAME_TXT, attribute, expectedValue);
    }
    
    public void verifyUpdatedLastName(String attribute, String expectedValue) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	elementAssertUtil.assertElementAttribute(LAST_NAME_TXT, attribute, expectedValue);
    }
    
    public void verifyPageLabel(String attribute, String expectedValue) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	elementAssertUtil.assertElementAttribute(CONTACT_DETAILS_LBL, attribute, expectedValue);
    }
    
}    





