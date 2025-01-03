package com.cheq.contact_list.utils;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementKeyboardActionUtil {
	
	private WebDriver driver;
	
	private ElementWaitUtil waitUtil;
    private ScreenshotUtil screenshotUtil; 
    private ReporterUtil reportsUtil; 
    
    private String logLevel;
    
    /** Initializes WebDriver, ScreenshotUtil, and HelpersUtil */
    public ElementKeyboardActionUtil(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        this.waitUtil = new ElementWaitUtil(driver, screenshotUtil);
        this.reportsUtil = new ReporterUtil(driver, screenshotUtil);
    }   

    /** Enters a value into a field and takes a screenshot  */
    public void inputElement(By elementName, String dataName) throws IOException, AWTException {	
    	
    	waitUtil.waitForVisibility(elementName);	
        try {
        	
        	driver.findElement(elementName).sendKeys(dataName);
            logLevel = "INFO"; 
            reportsUtil.resultsReporter(elementName, logLevel, LogMessage.formatMessage(LogMessage.INPUT_TEXT_MESSAGE, dataName, elementName.toString()), elementName.toString());

        } catch (Exception e) {
        	
            logLevel = "ERROR"; 
            reportsUtil.resultsReporter(elementName, logLevel, LogMessage.formatMessage(LogMessage.INPUT_TEXT__FAILED_MESSAGE, dataName, elementName.toString()), elementName.toString());
            throw e;
        }      
        
    }
    
    /** Clears the value of a field and takes a screenshot  */
    public void removeElementValue(By elementName) throws IOException, AWTException {
    	
    	waitUtil.waitForVisibility(elementName);
  		
        try {
        	driver.findElement(elementName).clear();
            logLevel = "INFO"; 
            reportsUtil.resultsReporter(elementName, logLevel, LogMessage.formatMessage(LogMessage.REMOVE_TEXT_MESSAGE, elementName.toString()), elementName.toString());

        } catch (Exception e) {
        	
            logLevel = "ERROR"; 
            reportsUtil.resultsReporter(elementName, logLevel, LogMessage.formatMessage(LogMessage.REMOVE_TEXT_FAILED_MESSAGE, elementName.toString()), elementName.toString());
            throw e;
        }         	
    	
    }
    
}
