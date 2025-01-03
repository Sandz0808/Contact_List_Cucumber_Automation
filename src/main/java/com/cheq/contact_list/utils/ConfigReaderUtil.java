package com.cheq.contact_list.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReaderUtil {
    
    private Properties property;

    
    /** Initializes and loads properties from the configuration file. */
    public Properties initProperty() {
        property = new Properties();
        String configFilePath = System.getProperty("user.dir") + "/src/main/resources/Config/Config.properties";

        try (FileInputStream ip = new FileInputStream(configFilePath)) {
            property.load(ip);
        } catch (FileNotFoundException e) {
            System.err.println("Config file not found: " + configFilePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error loading config file: " + e.getMessage());
            e.printStackTrace();
        }

        return property;
    }

    /** Retrieves the root directory of the project. */
    private String getRootDirectory() {
        try {
            return Paths.get("").toAbsolutePath().toString();
        } catch (Exception e) {
            System.err.println("Failed to get root directory: " + e.getMessage());
            return "";
        }
    }
}
