package com.cheq.contact_list.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cheq.contact_list.utils.ElementMouseActionUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;

public class BasePage {

    private WebDriver driver;
    private ElementMouseActionUtil mouseActionsUtil;
    private ScreenshotUtil screenshotUtil;
    
    /** Constructor to initialize WebDriver and utility classes */
    public BasePage(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        this.mouseActionsUtil = new ElementMouseActionUtil(driver, screenshotUtil);
    }
  
    /** Element locators for the login page */
    private final By LOGOUT_BTN = By.cssSelector("#logout");

    /** Clicks the logout button to log in using the mouse action utility */
    public void clickLogoutButton() throws Exception {
    	mouseActionsUtil.clickElement(LOGOUT_BTN);
    }

}
