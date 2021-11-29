/*
 * Setup browser and navigate to url address.
 */

package commons;

import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import factory.BrowserFactory;
import factory.DriverFactory;

public class AbstractTest extends AbstractPage {

	BrowserFactory bf = new BrowserFactory();

	@Parameters({"browser","url"})
	@BeforeMethod
	public void setup(String browser, String url) {
		// Init browser
		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		// To open web site from url address
		DriverFactory.getInstance().getDriver().get(url);
		// To maximize browser  
		DriverFactory.getInstance().getDriver().manage().window().maximize();
		// Implicit wait 10 seconds
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void closeDriver() {
		DriverFactory.getInstance().getDriver().quit();
	}
}