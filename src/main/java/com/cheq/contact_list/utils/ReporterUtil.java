package com.cheq.contact_list.utils;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReporterUtil {
	
	private WebDriver driver;
	Properties property;
	
    private ScreenshotUtil screenshotUtil;   
    private ConfigReaderUtil configReaderUtil;
    
    private String captureScreenshot;

    /** Initializes ReporterUtil with WebDriver and ScreenshotUtil */
    public ReporterUtil(WebDriver driver, ScreenshotUtil screenshotUtil) {
        this.driver = driver;
        this.screenshotUtil = screenshotUtil;
        getProperty();
    }

    /** Initializes properties and the JSON file. */
    public void getProperty() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        captureScreenshot = property.getProperty("screenshot_status");
    }

    /** Captures a screenshot and logs a message for the given test step 
     * @throws AWTException */
    public void resultsReporter(By elementName, String logLevel, String logMsg, String elementDetails) throws IOException, AWTException {
    	
        String stepName = getScreenshotDatetime();
        
        if ("info".equalsIgnoreCase(logLevel)) {
            LoggerUtil.logMessage(logLevel, logMsg, elementDetails);
            if ("On".equalsIgnoreCase(captureScreenshot)) {
          	  screenshotUtil.takeFullScreenScreenshotWithRobot(stepName);
            }
        } else {
            if ("On".equalsIgnoreCase(captureScreenshot)) {
            	  screenshotUtil.takeFullScreenScreenshotWithRobot(stepName);
            }
            LoggerUtil.logAndThrow(logLevel, logMsg, elementDetails);
        }

    }
    
    /** Logs messages based on the log level for API calls. */
    public void resultsReporterAPI(String logLevel, String logMsg, String elementDetails) throws IOException {
    	
        if ("info".equalsIgnoreCase(logLevel)) {
            LoggerUtil.logMessage(logLevel, logMsg, elementDetails);
        } else {
            LoggerUtil.logAndThrow(logLevel, logMsg, elementDetails);
        }
    }

    /** Generates a timestamped screenshot name based on current date and time. */
    private String getScreenshotDatetime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
        String formattedDate = currentDateTime.format(dateFormatter);
        String formattedTime = currentDateTime.format(timeFormatter);
        
        return "screenshot_" + formattedDate + "_" + formattedTime;
    }
}
