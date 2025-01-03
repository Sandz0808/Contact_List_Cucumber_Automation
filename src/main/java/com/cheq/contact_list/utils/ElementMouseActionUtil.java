package com.cheq.contact_list.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementMouseActionUtil {
	
	private WebDriver driver;
	
    private ElementWaitUtil waitUtil;
    private ScreenshotUtil screenshotUtil; 
    private ReporterUtil reportsUtil; 
    
    private String logLevel;

    /** Initializes WebDriver, ScreenshotUtil, and HelpersUtil */
    public ElementMouseActionUtil(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        this.waitUtil = new ElementWaitUtil(driver, screenshotUtil);
        this.reportsUtil = new ReporterUtil(driver, screenshotUtil);
    }
    
    /** Clicks on a specified element if visible.  */
    public void clickElement(By elementName) throws Exception {
    	
        waitUtil.waitForVisibility(elementName);
        
        try {
        	driver.findElement(elementName).click();
            logLevel = "INFO";  
            reportsUtil.resultsReporter(elementName, logLevel, LogMessage.formatMessage(LogMessage.CLICKING_MESSAGE, elementName.toString()), elementName.toString());
        }catch (Exception e) {
            logLevel = "ERROR";
            reportsUtil.resultsReporter(elementName, logLevel, LogMessage.formatMessage(LogMessage.NOT_CLICKABLE_ELEMENT_VERIFICATION_MESSAGE, elementName.toString()), elementName.toString());
            throw e;
            
        }
        
    }


}
