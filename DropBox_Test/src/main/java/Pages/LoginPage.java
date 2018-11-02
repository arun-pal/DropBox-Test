package Pages;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonFunctions.Log;
import CommonFunctions.PropertyManager;
import CommonFunctions.SharedDriver;
public class LoginPage {

	private SharedDriver shareddriver;
	WebDriverWait wait;
	
	// All Locators related to the page is kept here. 
	private static String EMAIL = "//*[contains(@id, 'login_email')]";
	private static String PASSWORD = "//*[contains(@id, 'login_password')]";
	private static String SIGNIN = "//button[@type='submit']";
	private static String SIGNOUT_DROPDOWN = "//div[2]/div/button";
	private static String SIGNOUT = "Sign out";
	private static String FILES = "Files";
	private static String UPLOAD_FILE = "//li[3]/div/button";
	private static String UPLOAD1 = "//div/div[2]/div/div/div/div/button";
	private static String UPLOAD1_FILES1 = "//nav/div/button";
	private static String CREATE_FOLDER = "//li[4]/button";
	private static String FOLDER_NAME = "(//input[@value=''])[2]"; 
	private static String HELPTEXT = "?";
	private static String SHOW_DELETED_FILES = "//li[5]/button"; 
	private static String RESTORE_DELETED_FILES = "//li[4]/button"; 
	private static String CLICK_HOME_LOGO = ".maestro-nav__logo"; 
	private static String NOTIFICATION = "button.notification-button.mc-popover-trigger"; 
	private static String NOTIFICATION_TEXT = "event-feed"; 
	private static String MENU_CLICK = "//footer/div/div/button"; 
	private static String SEARCH_TEXT = "input.search-bar__text-input"; 
	private static String SEARCH_TEXT1 = "top-menu-container";
	
	
	private static String Folder_Name_Text;
	
	public LoginPage(SharedDriver shareddriver) {
		this.shareddriver = shareddriver;
	}
	
	// Get the Dropbox Base URL
	public void getURL() throws IOException{
		shareddriver.maximizeWindow();
		shareddriver.goToURL(PropertyManager.getProp("Url"));
		Log.info("URL loaded in Browser:" + PropertyManager.getProp("Url"));
	}
	
	// Sign in to Dropbox
	public void signIn()
	{
		shareddriver.typeText(By.xpath(EMAIL), PropertyManager.getProp("Email"));
		shareddriver.typeText(By.xpath(PASSWORD), PropertyManager.getProp("Password"));
		shareddriver.click(By.xpath(SIGNIN));
	}
	
	// Signout from dropbox
	public void signOut()
	{
		// Wait for Element to be clickable
		shareddriver.waitForElementToBeClickable(By.linkText(FILES));
		shareddriver.click(By.linkText(FILES));			
		shareddriver.waitForElementToBeClickable(By.xpath(SIGNOUT_DROPDOWN));
		shareddriver.click(By.xpath(SIGNOUT_DROPDOWN));	
		shareddriver.click(By.linkText(SIGNOUT));	
		System.out.println("Logged out of DropBox Application");
		Log.info("Logged out of DropBox");		
	}
	
	// Create Folder and verify if Folder is present or not
	public void createFolder() {
		shareddriver.waitForElementToBeClickable(By.linkText(FILES));
		shareddriver.click(By.linkText(FILES));
		shareddriver.click(By.xpath(CREATE_FOLDER));	
		//shareddriver.typeText(By.xpath(FOLDER_NAME), PropertyManager.getProp("Folder_Name") + shareddriver.randomNoGeneration());		
		Folder_Name_Text = PropertyManager.getProp("Folder_Name") + shareddriver.randomNoGeneration();
        shareddriver.typeText(By.xpath(FOLDER_NAME), Folder_Name_Text);
        System.out.println("Creared Folder Is:" + Folder_Name_Text);		
		shareddriver.click(By.linkText(FILES));
		shareddriver.refresh();
		
		if (shareddriver.isTextPresent(Folder_Name_Text))
		{
			System.out.println("Folder is Present Under My Files Section:" + Folder_Name_Text);		 
		}
		else
		{
			System.out.println("Folder is not Present Under My Files Section:" + Folder_Name_Text);
		}	
	}
	
	// It willl go to Home page and Verify the Folder is present or not.
	public void goToHomePage()
	{
		shareddriver.waitForElementToBeClickable(By.cssSelector(CLICK_HOME_LOGO));
		shareddriver.click(By.cssSelector(CLICK_HOME_LOGO));		
		System.out.println(Folder_Name_Text);
		if (shareddriver.isTextPresent(Folder_Name_Text))
		{
			System.out.println("Folder is Present in Home Page:" + Folder_Name_Text);		 
		}
		else
		{
			System.out.println("Folder is not Present in Home Page:" + Folder_Name_Text);
		}	
	}
		
	// It will click on the HelpText
	public void click_HelpText() throws InterruptedException{
		shareddriver.waitForElementToBeClickable(By.linkText(HELPTEXT));
		shareddriver.click(By.linkText(HELPTEXT));		
		//shareddriver.scrollToBottomOfPage();
		//shareddriver.closeCurrentWindow();	
		shareddriver.windowHandler();				
	}
	
	// It will display Deleted Files.
	public void show_DeletedFiles(){
		shareddriver.waitForElementToBeClickable(By.linkText(FILES));
		shareddriver.click(By.linkText(FILES));
		shareddriver.click(By.xpath(SHOW_DELETED_FILES));
		signOut();		
	}
	
	// It will display all restored Files.
	public void restore_DeletedFiles(){
		shareddriver.addImplicitlyWait(10);
		shareddriver.click(By.xpath(RESTORE_DELETED_FILES));		
	}
	
	// This function will Click on notification and get text of Notification.
	public void notification(){
		
		shareddriver.waitForElementToBeClickable(By.linkText(FILES));
		shareddriver.click(By.linkText(FILES));
		shareddriver.waitForElementToBeClickable(By.cssSelector(NOTIFICATION));
		shareddriver.click(By.cssSelector(NOTIFICATION));
		System.out.println("Notification Text is :" + shareddriver.getText(By.id(NOTIFICATION_TEXT)));		
	}
	
	// Click Menu at bottom of the page.
	public void menuClick(){
		shareddriver.waitForElementToBeClickable(By.linkText(FILES));
		shareddriver.click(By.linkText(FILES));
		shareddriver.waitForElementToBeClickable(By.xpath(MENU_CLICK));
		shareddriver.click(By.xpath(MENU_CLICK));
	}
	
	public void searchText(){
		shareddriver.waitForElementToBeClickable(By.linkText(FILES));
		shareddriver.click(By.linkText(FILES));
		shareddriver.waitForElementToBeClickable(By.xpath(SEARCH_TEXT));
		if (shareddriver.isEnabled(By.className(SEARCH_TEXT)))
		{
			System.out.println("Search Box is Displayed");
		}
		else
		{
			System.out.println("Search Box is Not Displayed");
		}
		shareddriver.click(By.className(SEARCH_TEXT));
		shareddriver.typeText(By.className(SEARCH_TEXT1), PropertyManager.getProp("Search_Box"));		
	}
	
	public void closeWindow()
	{
		shareddriver.close();
	}
	
				
}