package com.cheq.contact_list.test_runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features/UiUserLogin.feature", 
    glue = {"com/cheq/contact_list/step_definitions", "com/cheq/contact_list/hooks", "com/cheq/contact_list/reusable_steps"}, 
    plugin = {
        "pretty", 
        "html:reports/extent_html/extent_report.html", 
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
        "timeline:test-output-thread/"
    },
    monochrome = true
    
    //tags = "@TC3.1"

)
public class TestNgRunner extends AbstractTestNGCucumberTests{
	@Override
	@DataProvider(parallel = true)
	public Object [][] scenarios(){
		return super.scenarios();
		
	}

}