#@CreateUser @Regression
#Feature: CreateUser
#
  #Background:
  #Given API server should be up and running
#
  #@TC1.5 @Api
  #Scenario: Sign up with valid test Data via API
    #When User Submit POST request to users endPoint
    #Then Verify create user success code
#
#
   #@TC1.6 @Api
  #Scenario: Sign up with a used email  via API
    #When User Submit POST request to users endPoint
    #Then Verify create user error code
#
#	
