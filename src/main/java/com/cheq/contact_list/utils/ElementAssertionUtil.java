package com.cheq.contact_list.utils;

import java.awt.AWTException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementAssertionUtil {
	
	private WebDriver driver;
	
	private ElementWaitUtil waitUtil;
    private ScreenshotUtil screenshotUtil;
    private ReporterUtil reportsUtil; 
    
    private String logLevel;
    
    /** Initializes the WebDriver, ScreenshotUtil, and HelpersUtil. */
    public ElementAssertionUtil(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        this.waitUtil = new ElementWaitUtil(driver, screenshotUtil);
        this.reportsUtil = new ReporterUtil(driver, screenshotUtil);
    }
 
    /** Asserts that a specific attribute of an element matches the expected value. 
     * @throws AWTException */
    public void assertElementAttribute(By locator, String attribute, String expectedAttributeValue) throws IOException, AWTException {
    	
        waitUtil.waitForVisibility(locator);
        String actualAttributeValue = getElementAttribute(locator, attribute);

        try {
            
            Assertions.assertEquals(actualAttributeValue, expectedAttributeValue, "Attribute value mismatch");
            logLevel = "INFO";
            reportsUtil.resultsReporter(locator, logLevel, 
                LogMessage.formatMessage(LogMessage.ELEMENT_ASSERTION_MESSAGE, actualAttributeValue, expectedAttributeValue), 
                expectedAttributeValue); 
        } catch (AssertionError e) {
            logLevel = "ERROR";
            reportsUtil.resultsReporter(locator, logLevel, 
                LogMessage.formatMessage(LogMessage.ASSERTION_EXCEPTION_ERROR_MESSAGE, actualAttributeValue, expectedAttributeValue), 
                expectedAttributeValue); 
            throw e;
        }
    }

    /** Retrieves the value of a specified attribute for a given element. 
     * @throws AWTException */
    private String getElementAttribute(By locator, String attribute) throws IOException, AWTException {
        WebElement element = waitUtil.waitForVisibility(locator);
        String actualAttributeValue = element.getAttribute(attribute);

        try {
            logLevel = "INFO";
            reportsUtil.resultsReporter(locator, logLevel, LogMessage.ELEMENT_ATTRIBUTE_MESSAGE, actualAttributeValue);
            return actualAttributeValue;
        } catch (Exception e) {
            logLevel = "ERROR";
            reportsUtil.resultsReporter(locator, logLevel, LogMessage.ELEMENT_ATTRIBUTE_EXCEPTION_MESSAGE, e.getMessage());
            throw e;
        }
    }
    
    
    /** Verifies that an element is not visible on the page and handles exceptions gracefully. 
     * @throws AWTException */
    public void assertElementIsNotVisible(By locator) throws IOException, AWTException {
        try {

        	Boolean elementState = waitUtil.waitForInvisibilityOfElement(locator);
            if (elementState) {
                logLevel = "INFO";  
                reportsUtil.resultsReporter(locator, logLevel, 
                    LogMessage.formatMessage(LogMessage.ASSERT_ELEMENT_IS_NOT_VISIBLE_MESSAGE, locator.toString()), 
                    locator.toString());
          }
        }catch (Exception e) {
            logLevel = "ERROR";
            reportsUtil.resultsReporter(locator, logLevel, 
                LogMessage.formatMessage(LogMessage.ASSERT_ELEMENT_IS_NOT_VISIBLE_FAIL_MESSAGE, locator.toString()), 
                locator.toString());
            throw e;  
        }
    }

}
    