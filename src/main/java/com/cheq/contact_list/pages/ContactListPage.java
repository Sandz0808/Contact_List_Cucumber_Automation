package com.cheq.contact_list.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.awt.AWTException;
import java.io.IOException;
import com.cheq.contact_list.utils.ElementAssertionUtil;
import com.cheq.contact_list.utils.ElementMouseActionUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;


public class ContactListPage {
	
    
    private ElementMouseActionUtil mouseActionsUtil;
    private ElementAssertionUtil elementAssertUtil;
    
 
    /** Constructor to initialize WebDriver and utility classes */
    public ContactListPage(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
        this.elementAssertUtil = new ElementAssertionUtil(driver, screenshotUtil);
    }

	/** Element locators for the contact list page */
	private By ADD_NEW_CONTACT_BTN = By.id("add-contact");
	private By CONTACT_FULLNAME_TXT = By.xpath("//tr[1]//td[2]");
	private By PAGE_LABEL = By.cssSelector("div[class='main-content'] header h1");
	

    /** Clicks the 'Add New Contact' button using the mouse action utility */
    public void clickAddNewContactButton() throws Exception {
    	mouseActionsUtil.clickElement(ADD_NEW_CONTACT_BTN);
    }

    /** Clicks on Test Contact using the mouse action utility  */
    public void clickSelectedContact() throws Exception {
     	mouseActionsUtil.clickElement(CONTACT_FULLNAME_TXT);
    }

    /** Verifies that a contact is displayed on the page using the element assertion utility */
    public void verifyContactIsDisplayed(String attribute, String expectedValue) throws IOException, AWTException  {
    	elementAssertUtil.assertElementAttribute(CONTACT_FULLNAME_TXT, attribute, expectedValue);
    }
    
    public void verifyContactListLabel(String attribute, String expectedValue) throws IOException, AWTException {
    	elementAssertUtil.assertElementAttribute(PAGE_LABEL, attribute, expectedValue);
    	
    }
    
      
    
}
