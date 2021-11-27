/*
 * Initialization browser and set property to system.
 */

package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public WebDriver createBrowserInstance(String browser) {
		WebDriver driver = null;

		// Check if browser is 'chrome' and create driver instance
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		// Check if browser is 'firefox' and create driver instance
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		// Check if browser is 'edge' and create driver instance
		else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		return driver;
	}
}