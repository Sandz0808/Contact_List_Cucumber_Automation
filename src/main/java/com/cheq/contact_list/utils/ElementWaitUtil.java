package com.cheq.contact_list.utils;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementWaitUtil {
    
    private WebDriver driver;
    Properties property;
    
    private ConfigReaderUtil configReaderUtil;
    private ReporterUtil reportsUtil;
    private ScreenshotUtil screenshotUtil;
  
    private Integer implicitWait;
    private Integer explicitWait;
    
    public ElementWaitUtil(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        this.reportsUtil = new ReporterUtil(driver, screenshotUtil);
        getProperty();
    }

    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        
        String waitExplicit = property.getProperty("explicit_wait");
        String waitImplicit = property.getProperty("implicit_wait");
        
        explicitWait = Integer.parseInt(waitExplicit);
        implicitWait = Integer.parseInt(waitImplicit);
    }

    /** Waits for an element to be visible. 
     * @throws AWTException */
    public WebElement waitForVisibility(By locator) throws IOException, AWTException {
    	
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            String logLevel = "INFO";
            reportsUtil.resultsReporter(locator, logLevel, LogMessage.VISIBLE_ELEMENT_MESSAGE, locator.toString());
            
            return element;
        } catch (TimeoutException e) {
 
            String logLevel = "ERROR";
            reportsUtil.resultsReporter(locator, logLevel, LogMessage.TIMEOUT_EXCEPTION_ERROR_MESSAGE, locator.toString());
            throw e; 
        }
    }
    
    /** Waits for an element to be clickable. */
    public WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    } 
    
    /** Waits for an element invisibility. */
    public Boolean waitForInvisibilityOfElement(By locator) {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    } 
    
    
    
         

    /** Implicit wait for a predefined duration. */
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

    }
    
}
