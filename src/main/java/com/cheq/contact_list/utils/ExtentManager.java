package com.cheq.contact_list.utils;

import com.aventstack.extentreports.ExtentReports;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getExtent() {
        if (extent == null) {
            extent = new ExtentReports();
            // Initialize and configure the ExtentReports
        }
        return extent;
    }
}
