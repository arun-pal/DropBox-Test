package CommonFunctions;
import org.openqa.selenium.WebDriver;
public class TestContext {
	
	public PageObjectManager pageobjectmanager;
	WebDriver driver;
	
	public TestContext(SharedDriver driver) {
		this.driver= driver;
		pageobjectmanager = new PageObjectManager(driver);
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageobjectmanager;
	}
	
	public WebDriver getDriver(){
		return this.driver;
	}
}