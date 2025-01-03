package com.cheq.contact_list.utils;

import io.cucumber.java.Scenario;
import java.io.IOException;

public class ScenarioUtil {
	
	
		public boolean checkScenarioTags(Scenario scenario, String tagValue) throws IOException {
    	
	        scenario.getSourceTagNames().stream()
	                .filter(tag -> tag.startsWith("@"))
	                .findFirst()
	                .map(tag -> tag.substring(1))
	                .orElse("DefaultFeatureName");
	        return scenario.getSourceTagNames().stream().anyMatch(tag -> tag.equals(tagValue));
	    }
		
		public String getFeatureName(Scenario scenario) {
			
	       String featureName = scenario.getSourceTagNames().stream()
		          .filter(tag -> tag.startsWith("@"))
		          .findFirst()
		          .map(tag -> tag.substring(1))
		          .orElse("DefaultFeatureName");
	       
	       return featureName;
		
		}

}
