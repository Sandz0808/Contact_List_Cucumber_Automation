# Selenium Cucumber Framework
#### This Selenium Cucumber Automation Framework is designed for efficient and scalable end-to-end testing of web applications. By integrating the strengths of Selenium and Cucumber, this framework enables seamless cross-browser testing with a clear focus on behavior-driven development (BDD).

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Folder Structure](#folder-structure)
- [Configuration](#configuration)
- [Usage](#usage)
  - [Running Tests](#running-tests)
- [Reporting and Logs](#reporting-and-logs)

## Introduction

The Selenium Cucumber-Java Automation Framework is crafted for comprehensive end-to-end testing of web applications. It harnesses the powerful APIs of Selenium and Cucumber to provide robust, scalable, and cross-browser test execution capabilities.

## Features
- Cross-browser support (Chromium, Edge)
- Parallel test execution
- Page Object Model (POM) implementation
- Screenshot capture for failed tests
- Test data management using JSON files
- Integration with CI/CD pipelines (e.g., Jenkins)
- Comprehensive reports and logs for test execution

## Installation

To install and set up the framework, follow these steps:

**Installation Steps:**
 1. **Clone the Repository**

Clone the GitHub repository to your local machine:
```bash
git clone <link>
cd your-repo
```
 2. **Install the following softwares:**
- Java JDK
- Maven
  
### Steps to Install Java
### 1. Download Java JDK

- Go to the [Oracle JDK download page](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/)
- Select the appropriate version for Windows (e.g., `jdk-11_windows-x64_bin.exe`).

### 2. Install Java JDK

- Run the downloaded installer and follow the installation instructions.
- During installation, note the installation path (e.g., `C:\Program Files\Java\jdk-11.x.x`).

### 3. Set Environment Variables for Java
- Right-click on `This PC` or `My Computer` and select `Properties`.
- Click on `Advanced system settings`.
- Click on the `Environment Variables` button.
- Under `System variables`, click `New` and enter:
    **Variable name**: `JAVA_HOME`
    **Variable value**: Path to your JDK installation (e.g., `C:\Program Files\Java\jdk-11.x.x`).
- Find the `Path` variable, select it, and click `Edit`.
- Click `New` and add: `%JAVA_HOME%\bin`
- Click `OK` to close all dialog boxes.

### 4. Verify Java Installation

- Open a new Command Prompt window.
- Run the following command:
```bash
java -version
```
### Steps to Install Maven
### 1. Download Maven

- Go to the [Apache Maven download page](https://maven.apache.org/download.cgi).
- Download the latest binary zip file (e.g., `apache-maven-<version>-bin.zip`).

### 2. Extract the Archive

- Right-click the downloaded zip file and select "Extract All..."
- Choose a destination folder (e.g., `C:\Downloads`) and extract the contents.

### 3. Set Environment Variables for Maven

### a. Configure `MAVEN_HOME`

1. In the Environment Variables window, click `New` under `System variables` and enter:
   - **Variable name**: `MAVEN_HOME`
   - **Variable value**: Path to the Maven folder (e.g., `C:\Downloads\Apache\Maven`).

### b. Update the Path Variable

1. In the Environment Variables window, find the `Path` variable under `System variables` and select it.
2. Click `Edit`, then `New`, and add: `%MAVEN_HOME%\bin`
3. Click `OK` to close all dialog boxes.

### 4. Verify Maven Installation

- Open a new Command Prompt window.
- Run the following command:
```bash
mvn -v

```

## Folder Structure
#### The folder structure of the Selenium Cucumber Automation Framework is organized to promote clarity and ease of navigation. Below is a breakdown of the key directories:

```
SeleniumCucumber/
├── src/main/java/com/         # Pages and Utilities 
    └── pages                    # Contains reusable functions to iteract with the elements of the application
    └── utils                    # Contains reusable utilities that enhance the framework
├── src/main/resources/  
    └── config_file              # Contains configuration values used throughout the framework
    └── drivers  	         # Location of the drivers
├── src/test/java/com/        # Hooks, StepDefinition, TestRunner
    └── Hooks                    # Methods that allow code to execute at specific points in execution
    └── StepDefinition           # Define the actions and assertions performed during a test
    └── TestRunner               # Tool or class used to execute the tests
    └── test_data	         # Data used during testing to validate that an application data used during testing to validate that an application
├── src/test/resources/       # TestData, Drivers, Features 
    └── features                 # Describes the behavior of an application using Gherkin syntax
├── api_token/ 		      # Json file(s) containing the response body for API transactions 
├── logs/                     # Execution Logs 
├── reports/                  # extent_html, screenshots, extent_result
├── pom.xml                   # Selenium-Cucumber dependencies
├── testng.xml                # TestNG-Cucumber dependencies 
└── README.md                 # Project documentation

```
## Configuration

- Ensure that your `pom.xml` is configured with the necessary dependencies for Selenium, Cucumber, and JUnit.
- Edit the config.properties file to change the sreenshot status and update other configurations.

## Running Tests

### Using commandline:
- Open a terminal
- Go to the project directory (use "cd C:/project_path")
- Type "mvn test -PtestEnvironment_01" to run the test using the first Test Environment
  - Note: You can change the environment (staging, development, production) or add other parameters like "-Dcucumber.filter.tags=@Smoke" for using tags.
Default maven run using TestRunner class (Specify the TestRunner class : TestRunner for Junit; TestNgRunner for parellel)
```bash
mvn test -Dtest=TestRunner
```
To run all test using specific environment (staging, development, production)
```bash
mvn test -Pstaging -Dtest=TestRunner
mvn test -Pdevelopment -Dtest=TestRunner
mvn test -Pproduction -Dtest=TestRunner
```
To run with specific browser
```bash
mvn test -Pstaging -Dbrowser=edge -Dtest=TestRunner
mvn test -Pstaging -Dbrowser=chrome -Dtest=TestRunner
mvn test -Pstaging -Dbrowser=firefox -Dtest=TestRunner
```
To run specific scenario, using tags.
```bash
mvn test -Pstaging -Dbrowser="chrome" -Dtest=TestRunner -Dcucumber.filter.tags="@DeleteContact"
mvn test -Pstaging -Dbrowser="edge" -Dtest=TestRunner -Dcucumber.filter.tags="@TC3.3"
```
Please refer to the table below for the list of tags and their description.

| Category   			| Tags            					| Description                     								|
|-------------------------------|-------------------------------------------------------|---------------------------------	    							|
| Feature 		| @CreateUser   	| Use any of these tags to execute all the scenario(s) under the specified Feature.    		|
| 				| @AddNewContact    	|      												|
|  				| @DeleteUserContact    	|			          								|
| 				| @EditUserContact			|    				  								|
| 				| @LoginUser   				  								|
| API	 			| @Api		   					| Use this tag to run all api tests    								|
| UI	 			| @Ui		   					| Use this tag to run all UI tests    								|
| Scenario			| @TC1.1		   				| Use this tag to run specific test/scenario under CreateUser feature   			|
| Scenario			| @TC3.1			   			| Use this tag to run specific test/scenario under LoginUser feature				|
| Scenario	 		| @TC3.1-4		   				| Use any of these tags to run specific test/scenario under AddNewContact feature    		|
| Scenario		 	| @TC7.1-4			   			| Use any of these tags to run specific test/scenario under UpdateContact feature 		|
| Scenario		 	| @TC8.1			   			| Use any of these tags to run specific test/scenario under DeleteContact feature 		|
| Scenario		 	| @TC1.2, 4			   		    | Use any of these tags to run specific test/scenario under UnsuccessfulCreateUser feature 	|
| Scenario		 	| @TC3.2, 4			   			| Use any of these tags to run specific test/scenario under UnsuccessfulUserLogin feature 	|
| Scenario		 	| @TC4.2, 4			   			| Use any of these tags to run specific test/scenario under UnsuccessfulAddNewContact feature 	|
| Scenario		 	| @TC7.2, 4		   			    | Use any of these tags to run specific test/scenario under UnsuccessfulDetailUpdate feature 	|
	|

## Reporting and Logs
 * reports/extent_html: This folder contains the generated extent/html_report.
 * reports/screenshots: This folder contains the captured step screenshots.
 * reports/extent_result: This folder contain the screenshot bytes to be attached to the extent report.

#### Logs
 * Logs are stored in the logs/ folder.
 * They are generated every execution with name and date in the format test_logs_yyyy-mm-dd.
#### Screenshots
 * Screenshots are captured automatically and saved in the reports/screenshots/ folder.
 * A new folder is created in the format yyyy-mm-dd-ss/FeatureName/ScenarioName/dateandtime_.png
#### HtmlReport
 * All the results of test execution is saved in reports/extent_html/ folder.
 * Every execution the file is overwritten with new test results.
