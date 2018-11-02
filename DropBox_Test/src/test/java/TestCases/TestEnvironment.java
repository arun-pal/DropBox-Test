package TestCases;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import CommonFunctions.TestContext;
import Pages.LoginPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
public class TestEnvironment {
	
	public WebDriver driver;
	static int i = 0;
	public LoginPage loginpageinstance;
	
	public TestEnvironment(TestContext testContext) {
		driver = testContext.getDriver();
		loginpageinstance=testContext.getPageObjectManager().getLoginPage();
	}
		
	@After(order=10001)
	public void tearDown(Scenario scenario) throws IOException {
		System.out.println("after 1 st priority......");
		i++;
		if (scenario.isFailed()) {
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String dest = System.getProperty("user.dir") + "/output/error" +i+".png"; 
			FileUtils.copyFile(src, new File(dest));
		}
		else{
			 File file = new File(System.getProperty("user.dir") + "/output/error" +i+".png");
			 if(file.exists())
				 file.delete();
		}
	}
}