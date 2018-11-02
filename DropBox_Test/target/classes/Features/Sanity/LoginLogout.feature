Feature: DropBox Login

Background:
	Given I have started Browser
  When I sign in

@DropBoxLogin
Scenario: Login to DropBox
Then I redirect to appropriate Page