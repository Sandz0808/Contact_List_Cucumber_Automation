package com.cheq.contact_list.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.nio.file.Paths;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    private WebDriver driver;
    private final Properties property;
    private final ConfigReaderUtil configReaderUtil;

    /** Constructor initializes ConfigReader and loads properties. */
    public DriverFactory() {
        configReaderUtil = new ConfigReaderUtil();
        property = configReaderUtil.initProperty();
    }

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /** Initializes the WebDriver based on the specified or system default browser. */
    public WebDriver initializeDriver(String browser) {
        String rootDirectory = Paths.get("").toAbsolutePath().toString();

        // Read headless from config.ini
        boolean headless = Boolean.parseBoolean(property.getProperty("headless", "false"));

        // Allow overriding with system property
        headless = Boolean.parseBoolean(System.getProperty("headless", String.valueOf(headless)));

        String systemBrowser = System.getProperty("browser", "chrome").toLowerCase();
        String selectedBrowser = (browser != null && !browser.isEmpty()) ? browser.toLowerCase() : systemBrowser;

        System.out.println("Browser selected: " + selectedBrowser);
        System.out.println("Headless mode: " + headless);

        switch (selectedBrowser) {
            case "chrome": {
                String chromedriverPath = property.getProperty("chrome_driver_path");
                System.setProperty("webdriver.chrome.driver", rootDirectory + chromedriverPath);
                ChromeOptions options = new ChromeOptions();
                if (headless) options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
                tlDriver.set(new ChromeDriver(options));
                break;
            }
            case "edge": {
                String edgedriverPath = property.getProperty("edge_driver_path");
                System.setProperty("webdriver.edge.driver", rootDirectory + edgedriverPath);
                EdgeOptions options = new EdgeOptions();
                if (headless) options.addArguments("--headless");
                tlDriver.set(new EdgeDriver(options));
                break;
            }
            case "firefox": {
                String geckodriverPath = property.getProperty("gecko_driver_path");
                System.setProperty("webdriver.gecko.driver", rootDirectory + geckodriverPath);
                FirefoxOptions options = new FirefoxOptions();
                if (headless) options.addArguments("-headless");
                tlDriver.set(new FirefoxDriver(options));
                break;
            }
            case "safari":
                WebDriverManager.safaridriver().setup();
                if (headless) {
                    throw new UnsupportedOperationException("Headless mode is not supported for Safari");
                }
                tlDriver.set(new SafariDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + selectedBrowser);
        }

        String baseUrl = System.getProperty("base.url");

        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = property.getProperty("url");
        }

        getDriver().get(baseUrl);
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
