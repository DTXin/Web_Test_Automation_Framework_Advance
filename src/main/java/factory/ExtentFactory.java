/*
 * Create thread safe (using thread local) for extent report
 * 
 * Apply singleton and factory design pattern.
 */

package factory;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {

	// Private constructor so that no one else can create object of this class
	private ExtentFactory() {
		
	}

	private static ExtentFactory instance = new ExtentFactory();
	
	public static ExtentFactory getInstance() {
		return instance;
	}
	
	// Define separate factory methods for creating objects and create objects by calling that methods
	ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
	
	public ExtentTest getExtent() {
		return extent.get();
	}
	
	public void setExtent(ExtentTest extentParameter) {
		extent.set(extentParameter);
	}
	
	public void removeExtent() {
		extent.remove();
	}
}