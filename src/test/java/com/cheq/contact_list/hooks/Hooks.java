package com.cheq.contact_list.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cheq.contact_list.utils.DriverFactory;

import com.cheq.contact_list.utils.LogMessage;
import com.cheq.contact_list.utils.ConfigReaderUtil;
import com.cheq.contact_list.utils.LoggerUtil;
import com.cheq.contact_list.utils.ScreenshotUtil;
import com.cheq.contact_list.utils.DataDictionaryUtil;

import com.cheq.contact_list.pages.BasePage;

import com.cheq.contact_list.test_data.TestDataGenerator;

public class Hooks {
	
	private static WebDriver driver;
	private Properties property;
	
    private static DriverFactory driverFactoryUtil;
    private static ScreenshotUtil screenshotUtil;
    private ConfigReaderUtil configReaderUtil;
    
    private BasePage basePage;
    
    private boolean skipHooks = false;
    private String logLevel;
    public static String dataGroup; 
    private static String timestampedFolder;
    
    
    /** Initializes a timestamped folder for screenshots at the start of the test run. */
    @BeforeAll
    public static void setupOnce() throws IOException {
    	
        String baseFolderPath = new ConfigReaderUtil().initProperty().getProperty("screenshots_folder");
    	
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String folderPath = baseFolderPath + File.separator + timestamp + File.separator;
        new File(folderPath).mkdirs();

        timestampedFolder = folderPath;
    }
    
    /** Loads configuration properties and sets up the logger before each scenario. */
    @Before(order = 0)
    public void getProperty() throws IOException {
        
    	configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
        LoggerUtil.setupLogger();
    }

    /** Loads the test data for the scenario before execution. */
    @Before(order = 1)
    public void loadTestData(Scenario scenario) throws IOException {
        
        skipHooks = scenario.getSourceTagNames().stream().anyMatch(tag -> tag.equals("@Api"));
        
        String[] fields = {"firstName", "lastName", "dateOfBirth", "email", "password", "phoneNumber", "stAddress1",
                "stAddress2", "city", "state", "postalCode", "country", "email", "password"};
        dataGroup = TestDataGenerator.generateTestData(fields);
        
        if (skipHooks) {

            String testDataAPI = property.getProperty("test_data_api");
            DataDictionaryUtil.loadJsonFile(testDataAPI);

        }else {

            String testDataUI = property.getProperty("test_data_ui");
            String testDataAPI = property.getProperty("test_data_api");
            DataDictionaryUtil.loadJsonFile(testDataUI);
            DataDictionaryUtil.loadJsonFile(testDataAPI); 	
        }
    }
    
    /** Returns the generated test data group. */
    public static String getDataGroup() {
        
        return dataGroup;
    }
    
    @Before(order = 2)
    public void launchBrowser(Scenario scenario) throws IOException {
        String featureName = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith("@"))
                .findFirst()
                .map(tag -> tag.substring(1))
                .orElse("DefaultFeatureName");
        String customScenarioName = scenario.getName();
        skipHooks = scenario.getSourceTagNames().stream().anyMatch(tag -> tag.equals("@Api"));

        if (skipHooks) {
            if (screenshotUtil == null) {
                screenshotUtil = new ScreenshotUtil(driver);
                return;
            }
            screenshotUtil.initializeScenarioFolder(featureName, customScenarioName, timestampedFolder);
            return;
        }

        String browserName = System.getProperty("browser", property.getProperty("browser"));

        driverFactoryUtil = new DriverFactory();
        driver = driverFactoryUtil.initializeDriver(browserName);

        screenshotUtil = new ScreenshotUtil(driver);
        screenshotUtil.initializeScenarioFolder(featureName, customScenarioName, timestampedFolder);
    }

    
    /** Provides the ScreenshotUtil instance to other classes. */
    public static ScreenshotUtil getScreenshotUtil() {
        
        if (screenshotUtil == null) {
            throw new IllegalStateException("ScreenshotUtil has not been initialized.");
        }
        return screenshotUtil;
    }

    /** Takes a screenshot after each step and attaches it to the scenario. */
    @AfterStep(order = 0)
    public void actionAfterEachStep(Scenario scenario) {
        
        DataDictionaryUtil.clearDataCache();
        if (skipHooks) {
            return;
        }

        if (driver instanceof TakesScreenshot) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Screenshot");
        }
    }
    
    /** Clears the test data dictionary after each scenario. */
    @After(order = 0)
    public void clearTestData() {
        
        TestDataGenerator.clearDataDictionary();
    }
    
    /** Performs cleanup actions after each scenario, such as logging out if the test failed. */
    @After(order = 1)
    public void afterScenario(Scenario scenario) {
    	
    	skipHooks = scenario.getSourceTagNames().stream().anyMatch(tag -> tag.equals("@Api"));
    	if (skipHooks) {
            return;
        }else {
        	logoutScenario(scenario);
        }
    
    }

    /** Quits the browser and performs final cleanup after each scenario. */
    @After(order = 2)
    public void quitBrowser() {
        
        DataDictionaryUtil.clearDataCache();
        if (skipHooks) {
            return;
        }

        if (screenshotUtil != null) {
            screenshotUtil.resetCounter();
        }

        if (driverFactoryUtil.getDriver() != null) {
        	driverFactoryUtil.getDriver().quit();
        }

    }
    
    @AfterAll
    public static void endOfTestLogMessage() {
        
        LoggerUtil.closeLogger();
    }
    
    
 public void logoutScenario(Scenario scenario) {
    	
	    if (scenario.isFailed()) {
	        try {            
	            basePage.clickLogoutButton();              
	            logLevel = "INFO";  
	            LoggerUtil.logMessage(logLevel, LogMessage.formatMessage(LogMessage.CLEANUP_MESSAGE), "Logout");
	        } catch (Exception e) {

	        }
	    }
    
    }
    
    
}
