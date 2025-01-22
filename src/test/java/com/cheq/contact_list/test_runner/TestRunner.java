package com.cheq.contact_list.test_runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", 
    glue = {"com/cheq/contact_list/step_definitions", "com/cheq/contact_list/hooks", "com/cheq/contact_list/reusable_steps"}, 
    plugin = {
        "pretty", 
        "html:reports/extent_html/extent_report.html", 
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
        "timeline:test-output-thread/"
      }
   
)
public class TestRunner {

}