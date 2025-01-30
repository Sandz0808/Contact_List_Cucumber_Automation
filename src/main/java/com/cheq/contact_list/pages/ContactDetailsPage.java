package com.cheq.contact_list.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.Alert;
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
    private By  EMAIL_TXT = By.id("email");
    private By  PHONE_TXT = By.id("phone");
    private By  CONTACT_DETAILS_LBL = By.xpath("//h1[normalize-space()='Contact Details']");
    
    
    /** Clicks the edit contact button */
    public void clickEditContactButton() throws Exception {
    	mouseActionsUtil.clickElement(EDIT_CONTACT_BTN);
    }
    
    /** Returns the locator for Delete button  */
    public void clickDeleteContact() throws Exception {
    	mouseActionsUtil.clickElement(DELETE_CONTACT_BTN);
    	waitUtil.implicitWait();
    	Alert alert = driver.switchTo().alert();
	    alert.accept();	
    }
    
    /** Returns the locator for Delete button  */
    public void clickReturnToListButton() throws Exception {
    	mouseActionsUtil.clickElement(RETURN_TO_CONTACT_LIST_BTN);
    	waitUtil.implicitWait();
    }
    
    public void verifyUpdatedEmail(String attribute, String expectedValue) throws IOException, AWTException  {
       	elementAssertUtil.assertElementAttribute(EMAIL_TXT, attribute, expectedValue);
    	waitUtil.implicitWait();
    }
    
    public void verifyUpdatedPhoneNum(String attribute, String expectedValue) throws IOException, AWTException  {
       	elementAssertUtil.assertElementAttribute(PHONE_TXT, attribute, expectedValue);
    	waitUtil.implicitWait();
    }
    
    public void verifyPageLabel(String attribute, String expectedValue) throws IOException, AWTException  {
    	waitUtil.implicitWait();
    	elementAssertUtil.assertElementAttribute(CONTACT_DETAILS_LBL, attribute, expectedValue);
    }
    
    
}    





