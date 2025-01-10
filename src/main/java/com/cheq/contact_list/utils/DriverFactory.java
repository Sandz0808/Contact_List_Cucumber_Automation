package com.cheq.contact_list.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.nio.file.Paths;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    private WebDriver driver;
    private Properties property;
    private ConfigReaderUtil configReaderUtil;

    /** Constructor initializes ConfigReader and loads properties. */
    public DriverFactory() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
    }

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /** Initializes the WebDriver based on the specified or system default browser. */
    public WebDriver initializeDriver(String browser) {
        String rootDirectory = Paths.get("").toAbsolutePath().toString();
        
        String systemBrowser = System.getProperty("browser", "chrome").toLowerCase();
        String selectedBrowser = (browser != null && !browser.isEmpty()) ? browser.toLowerCase() : systemBrowser.toLowerCase();

        System.out.println("Browser selected: " + selectedBrowser);

        if (selectedBrowser.equals("chrome")) {
            String chromedriverPath = property.getProperty("chrome_driver_path");
            System.setProperty("webdriver.chrome.driver", rootDirectory + chromedriverPath);
            tlDriver.set(new ChromeDriver());
        } else if (selectedBrowser.equals("edge")) {
            String edgedriverPath = property.getProperty("edge_driver_path");
            System.setProperty("webdriver.edge.driver", rootDirectory + edgedriverPath);
            tlDriver.set(new EdgeDriver());
        } else if (selectedBrowser.equals("firefox")) {
            String geckodriverPath = property.getProperty("gecko_driver_path");
            System.setProperty("webdriver.gecko.driver", rootDirectory + geckodriverPath);
            tlDriver.set(new FirefoxDriver());
        } else if (selectedBrowser.equals("safari")) {
            WebDriverManager.safaridriver().setup();
            tlDriver.set(new SafariDriver());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + selectedBrowser);
        }

        String baseUrl = System.getProperty("base.url");

        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = property.getProperty("url");
        }

        System.out.println("Navigating to: " + baseUrl);
        getDriver().get(baseUrl); // Open the browser with the URL
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();

        return getDriver();
    }

    /** Returns the WebDriver instance associated with the current thread. */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    /** Closes the WebDriver instance for the current thread. */
    public static synchronized void closeDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
