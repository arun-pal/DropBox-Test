package CommonFunctions;
import Pages.LoginPage;
public class PageObjectManager {
	
	SharedDriver driver;
	LoginPage loginpageinstance;
	
	public PageObjectManager(SharedDriver driver) {
		this.driver = driver;
	}
	 
	public LoginPage getLoginPage(){
		return (loginpageinstance==null) ? loginpageinstance = new LoginPage(driver) : loginpageinstance ;		 
	}
}