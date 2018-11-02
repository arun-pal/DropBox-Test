Feature: Display Deleted Folder

Background:
	Given I have started Browser
  When I sign in

@DisplayDeletedFolder
Scenario: Creating a folder in Dropbox
Then I display deleted folder