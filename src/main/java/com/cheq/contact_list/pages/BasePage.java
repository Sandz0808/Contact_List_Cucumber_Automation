package com.cheq.contact_list.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.cheq.contact_list.utils.ScreenshotUtil;
import com.cheq.contact_list.utils.ElementMouseActionUtil;


public class BasePage {

    
    private ElementMouseActionUtil mouseActionsUtil;
   
    /** Constructor to initialize WebDriver and utility classes */
    public BasePage(WebDriver driver, ScreenshotUtil screenshotUtil) {
       
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
    }
  
    /** Element locators for the login page */
    private final By LOGOUT_BTN = By.id("logout");

    /** Clicks the logout button to log in using the mouse action utility */
    public void clickLogoutButton() throws Exception {
    	mouseActionsUtil.clickElement(LOGOUT_BTN);
    }

}
