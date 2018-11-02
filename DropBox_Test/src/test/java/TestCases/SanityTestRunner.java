package TestCases;
import java.io.File;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions( plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"}, features = {"src/main/resources"}, tags = {"@DropBoxLogin"} )
public class SanityTestRunner {
	@AfterClass
    public static void teardown() throws IOException {                         
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        int n=0;
        for(int i=1;i<=n;i++){
        	File file = new File(System.getProperty("user.dir") + "/output/error"+i+".png");
        	if(file.exists())
        	Reporter.addScreenCaptureFromPath(System.getProperty("user.dir") + "/output/error"+i+".png", "Scenario :"+i);
        }
        Reporter.setTestRunnerOutput("Report completed Successfully");
    }
}

