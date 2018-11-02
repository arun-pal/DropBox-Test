package CommonFunctions;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class SharedDriver extends EventFiringWebDriver {
	
	private static WebDriver driver = getDriver(); 
	private static String parentWindow;
	
	/**
	 * Create Driver instance as per browser selection. 
	 */
	
	public static WebDriver getDriver() {
   	 String browser = PropertyManager.getProp("Browser");	
   	 if(browser.equalsIgnoreCase("Firefox")){
   		 driver = new FirefoxDriver();
   	 }
   	 else if(browser.equalsIgnoreCase("Chrome")) {   		 
//   		Run below command in command Prompt.
//   		1: wget -N http://chromedriver.storage.googleapis.com/2.27/chromedriver_linux64.zip -P ~/
//   		2: unzip /chromedriver_linux64.zip -d /
//   		3: rm ~/chromedriver_linux64.zip
//   		4: sudo mv -f ~/chromedriver /usr/local/share/
//   		5: sudo chmod +x /usr/local/share/chromedriver
//   		6: sudo ln -s /usr/local/share/chromedriver /usr/local/bin/chromedriver
   		System.out.println("Started Chrome.......................................");
   		 driver = new ChromeDriver();
   	 }
   	 
   	 else if(browser.equalsIgnoreCase("HeadLessChrome")){

   		System.out.println("Started Headless Chrome.......................................");
   		System.setProperty("webdriver.chrome.driver", "/usr/local/chromedriver");
   		 ChromeOptions options = new ChromeOptions();
   	     options.addArguments("--headless");
   	    // options.addArguments("--proxy-server='direct://'");
   	    // options.addArguments("--proxy-bypass-list=*");
  	    // options.addArguments("--dns-prefetch-disable");
     	// options.addArguments("remote-debugging-port=9222");
     	// options.addArguments("--no-sandbox");
   	    // options.addArguments("disable-gpu");
   	    // options.addArguments("disable-infobars");
   	    // options.addArguments("--disable-extensions");
   	    // options.addArguments("window-size=1200x600");  	     
   	    // options.addArguments("disable-gpu");
   	     
   	     System.out.println("Getting Headless driver.......................................");
   	     driver = new ChromeDriver(options);
   	     System.out.println("Chrome Headless Started.......................................");
   	 }
   	 else{
   		 driver = new FirefoxDriver();
   	 }
   	 return driver;
	}
	

	public SharedDriver(){
		super(driver);	
	}
	
	public boolean isTextPresent(String text)
	{
	    try{
	        boolean b = driver.getPageSource().contains(text);
	        return b;
	       }
	    catch(Exception e)
	    {
	        return false;
	    }
	 }
	
	public String getText(By Element)
	{
		return driver.findElement(Element).getText();
	}
		
	public int randomNoGeneration()
	{    
		Random rand = new Random();      	       
		int randomno = rand.nextInt(1000);      
		System.out.println(randomno); 
		return randomno;
	}

	public void maximizeWindow(){
		//Log.info("started");
		driver.manage().window().maximize();
		parentWindow = driver.getWindowHandle();
	}
	
	// This function will close the current tab and go back to previous tab.
	public void closeCurrentWindow() throws InterruptedException{
		String winHandleBefore = driver.getWindowHandle();
		//Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle);
		    
		   // driver.findElement(By.linkText("Community")).click();
		    driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
			JavascriptExecutor js = ((JavascriptExecutor) driver); 
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
		// Perform the actions on new window
		
		
		//driver.close();
		//switch back to main window using this code
		driver.switchTo().window(winHandleBefore);
	}

	public void windowHandler(){		
		//handle windows change
		String base = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		 
		System.out.println(driver.getTitle());
		set.remove(base);
		assert set.size() == 1;
		driver.switchTo().window((String) set.toArray()[0]);
		System.out.println(driver.getTitle());
		
		JavascriptExecutor js = ((JavascriptExecutor) driver); 
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		//close the window
		//driver.close();
		//driver.switchTo().window(base);
	}
	
	public void alert(){
		driver.switchTo().alert();
		
	}
	
	public void take_Ele(String value){
		driver.switchTo().activeElement().sendKeys(value);
		
	}

	public WebElement findElement(By locator){
		scrollTo(locator);
		return driver.findElement(locator);
	}

	public void goToURL(String url){
		driver.get(url);
	}
	
	public void refresh(){
		driver.navigate().refresh();
	}

	public void click(By locator){
		waitForVisibilityOfElement(locator);
		waitForElementToBeClickable(locator);
		driver.findElement(locator).click();
	}

	public void waitForElementToBeClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 800);
	    wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void closeDriver(){
		driver.close();
	}

	public void quitDriver(){
		driver.quit();
	}

	public void waitForVisibilityOfElement(By locator){
		WebDriverWait wait = new WebDriverWait(driver, 2000);
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
	}

	public void typeText(By locator, String value){
		WebDriverWait wait = new WebDriverWait(driver, 800);
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
		driver.findElement(locator).clear();
	    driver.findElement(locator).sendKeys(value);
	}	

	public void addImplicitlyWait(int timeout){
		
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);	
	}

	public void scrollTo(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
   }
	
	public void scrollToBottomOfPage(){
		JavascriptExecutor js = ((JavascriptExecutor) driver); 
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollToTopOfPage(){
		JavascriptExecutor js = ((JavascriptExecutor) driver); 
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}
	
	public static void scrollTo1(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element);
    }	

	public void select(By locator , String text){
		Select dropdown = new Select(driver.findElement(locator));
		dropdown.selectByVisibleText(text);
	}

	public void selectByIndex(By locator , int num){
		Select dropdown = new Select(driver.findElement(locator));
		dropdown.selectByIndex(num);
	}

	public void selectByValue(By locator , String value){
		WebElement select_options = driver.findElement(locator);
		Select select_options_dropdown = new Select(select_options);
		select_options_dropdown.selectByValue(value);
	}

	public boolean isSelected(By locator){
		return driver.findElement(locator).isSelected();
	}	
	public boolean isDisplayed(By locator){
		return driver.findElement(locator).isDisplayed();
	}
	
	public boolean isEnabled(By locator){
		return driver.findElement(locator).isEnabled();
	}


	
}