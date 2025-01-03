package com.cheq.contact_list.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;
import java.util.Properties;

public class LoggerUtil {

    private static Logger logger;
    private static FileHandler fileHandler;
    private static String BASE_LOG_FOLDER;

    /** Initializes the logger and sets up the log file handler */
    public static void setupLogger() {
        try {
            ConfigReaderUtil configReader = new ConfigReaderUtil();
            Properties property = configReader.initProperty();
            BASE_LOG_FOLDER = property.getProperty("logs_folder");
            createLogFolder();

            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String logFilePath = BASE_LOG_FOLDER + File.separator + date + "_logs.txt";

            logger = Logger.getLogger(LoggerUtil.class.getName());
            if (fileHandler != null) {
                fileHandler.close();
            }

            fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(new Date(record.getMillis()));
                    String logLevel = record.getLevel().getLocalizedName();
                    String message = formatMessage(record);

                    return String.format("%s - %s - %s%n", timestamp, logLevel, message);
                }
            });

            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

            String formattedDate = new SimpleDateFormat("MMMM dd, yyyy hh:mm a").format(new Date());

            String separatorLine1 = String.format(
                "````````````````` Start of Test ````````````````````"
            );
            logger.info(separatorLine1);

            String dateLine = String.format(
                "` Date : %s `",
                formattedDate
            );
            logger.info(dateLine);

            String separatorLine2 = String.format(
                "````````````````````````````````````````````````````"
            );
            logger.info(separatorLine2);

        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Creates the log folder if it does not exist */
    private static void createLogFolder() {
        File logFolder = new File(BASE_LOG_FOLDER);
        if (!logFolder.exists()) {
            if (!logFolder.mkdirs()) {
                throw new RuntimeException("Failed to create log directory: " + logFolder.getAbsolutePath());
            }
        }
    }

    /** Logs a message with the specified log level and the predefined log message. */
    public static void logMessage(String logLevel, String logMsg, Object... args) {
        if (args != null && args.length > 0) {
            logMsg = String.format(logMsg, args);
        }

        if (logger != null) {
            switch (logLevel.toUpperCase()) {
                case "INFO":
                    logger.info(logMsg);
                    break;
                case "SEVERE":
                    logger.severe(logMsg);
                    break;
                case "ERROR":
                    logger.severe(logMsg); 
                    break;
                default:
                    logger.info("Unknown log level");
                    break;
            }
        }
    }

    /** Logs and throws a RuntimeException with the given log message and log level */
    public static void logAndThrow(String logLevel, String logMsg, Object... args) {
        if (args != null && args.length > 0) {
            logMsg = String.format(logMsg, args);
        }
        
        logMessage(logLevel, logMsg, args);
        throw new RuntimeException(String.format("[%s] - %s", logLevel.toUpperCase(), logMsg));
    }

    /** Closes the logger and releases resources */
    public static void closeLogger() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }
}
