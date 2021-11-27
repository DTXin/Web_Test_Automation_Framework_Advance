package commons;

import java.lang.reflect.Method;
import java.time.Duration;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import factory.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest_WithBrowserStack extends AbstractPage {

	public WebDriver driver;
	public static final String USERNAME = "dinhtran_aLZ8Wb";
	public static final String AUTOMATE_KEY = "yhgj3ZJZLaAb9zz7C5CS";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@Parameters({"browser","browser_version","os","os_version"})
	@BeforeMethod
	public void setup(String browserName, String browserVersion, String os, String osVersion, Method name) {
		String methodName = name.getName();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser_version", browserVersion);
		caps.setCapability("os", os);
		caps.setCapability("os_version", osVersion);
		caps.setCapability("name", methodName);
		caps.setCapability("build", "browserstack-build-1");
		caps.setCapability("project", "AgileTravelProject");
		
		if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			caps.setCapability("browser", "edge");
		} else if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			caps.setCapability("browser", "chrome");
		} else if(browserName.equals("firefox")) {
			WebDriverManager.operadriver().setup();
			caps.setCapability("browser", "firefox");
		} else if(browserName.equals("safari")) {
			WebDriverManager.safaridriver().setup();
			caps.setCapability("browser", "safari");
		}

		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DriverFactory.getInstance().setDriver(driver);
		// To open web site from url address
		DriverFactory.getInstance().getDriver().get("http://travel.agileway.net");
		// To maximize browser  
		DriverFactory.getInstance().getDriver().manage().window().maximize();
		// Implicit wait 10 seconds
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterMethod
    public void markTestStatus(ITestResult result)
    {
		
        String status = "";

        if(result.getStatus() == ITestResult.SUCCESS) {
        	status = "PASSED";
        } else if(result.getStatus() == ITestResult.SKIP) {
        	status = "SKIP";
        }else if(result.getStatus() == ITestResult.FAILURE) {
        	status = "FAILED";
        }

        JavascriptExecutor jse = (JavascriptExecutor)DriverFactory.getInstance().getDriver();
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\"}}");
        
        // Close Browser and Quit Driver.
        DriverFactory.getInstance().getDriver().quit();
    }
	

}