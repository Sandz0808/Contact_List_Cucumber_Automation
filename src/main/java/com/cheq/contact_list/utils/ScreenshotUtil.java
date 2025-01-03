package com.cheq.contact_list.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    private WebDriver driver;
    
    private ConfigReaderUtil configReaderUtil;
    
    private String scenarioFolderPath;
    private String screenshotStatus;
    private int stepCounter = 1;
    

    /** Constructor to initialize the ScreenshotUtil with WebDriver */
    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
        getProperty();
    }

    /** Loads properties from the configuration file */
    public void getProperty() {
    	configReaderUtil = new ConfigReaderUtil();
        screenshotStatus = configReaderUtil.initProperty().getProperty("screenshot_status");
    }

    /** Initializes the scenario folder path under featureName and scenarioName */
    public void initializeScenarioFolder(String featureName, String scenarioName, String baseFolderPath) throws IOException {
        if (featureName == null || featureName.isEmpty() || scenarioName == null || scenarioName.isEmpty()) {
            throw new IllegalArgumentException("Feature name or scenario name cannot be null or empty");
        }

        if ("On".equalsIgnoreCase(screenshotStatus)) {
    
            String featureFolderPath = baseFolderPath + File.separator + featureName;
            this.scenarioFolderPath = featureFolderPath + File.separator + scenarioName;

            File featureFolder = new File(featureFolderPath);
            if (!featureFolder.exists()) {
                featureFolder.mkdirs();
            }

            File scenarioFolder = new File(this.scenarioFolderPath);
            if (!scenarioFolder.exists()) {
                scenarioFolder.mkdirs();
            }
        }
    }
    
    /** Takes a full-screen screenshot using Java Robot class */
    public void takeFullScreenScreenshotWithRobot(String stepName) throws AWTException, IOException {
        if ("Off".equalsIgnoreCase(screenshotStatus)) {
            return;
        }

        String formattedStep = String.format("%02d_%s", stepCounter++, stepName);
        String uniqueFileName = formattedStep + "_robot.png";

        Robot robot = new Robot();
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenCapture = robot.createScreenCapture(screenRect);

        File destinationFile = new File(scenarioFolderPath, uniqueFileName);
        File destinationFolder = destinationFile.getParentFile();
        if (destinationFolder != null && !destinationFolder.exists()) {
            boolean created = destinationFolder.mkdirs();
            if (!created) {
                throw new IOException("Failed to create directory: " + destinationFolder.getAbsolutePath());
            }
        }

        ImageIO.write(screenCapture, "PNG", destinationFile);
    }


    /** Resets the step counter to 1 */
    public void resetCounter() {
        stepCounter = 1;
    }

    /** Sets the status for screenshot capture ('On' or 'Off') */
    public void setScreenshotStatus(String status) {
        if ("On".equalsIgnoreCase(status) || "Off".equalsIgnoreCase(status)) {
            this.screenshotStatus = status;
        } else {
            throw new IllegalArgumentException("Invalid screenshot status. Use 'On' or 'Off'.");
        }
    }

    /** Returns the folder path where screenshots for the scenario are stored */
    public String getScenarioFolderPath() {
        return this.scenarioFolderPath;
    }
}
