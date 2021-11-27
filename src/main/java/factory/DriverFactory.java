/*
 * Create thread safe driver (using thread local) for parallel testing.
 * 
 * Apply singleton and factory design pattern.
 */

package factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	// Private constructor so that no one else can create object of this class
	private DriverFactory() {
		
	}

	private static DriverFactory instance = new DriverFactory();
	
	public static DriverFactory getInstance() {
		return instance;
	}

	// Define separate factory methods for creating objects and create objects by calling that methods
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driverParameter) {
		driver.set(driverParameter);
	}

	public void closeDriver() {
		driver.get().close();
		driver.remove();
	}
}