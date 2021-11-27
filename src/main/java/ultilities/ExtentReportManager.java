/*
 * Setup a extent report and generate a file htmlReport.
 */

package ultilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	public static ExtentReports extent;

	public ExtentReports setupExtentReport() throws IOException {

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");
		Date date = new Date();
		String actualDate = format.format(date);

		// Initialize the HtmlReporter
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + 
				"\\Reports\\extent_" + actualDate + ".html");

		// Load file configuration
		spark.loadXMLConfig(System.getProperty("user.dir") + "\\src\\test\\resources\\fileConfig\\ReportsConfig.xml");

		// Initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name: ", "Practice Selenium WebDriver Advanced");
		extent.setSystemInfo("Environment Required: ", "Java -\r\n"
														+ "Eclipse IDE -\r\n"
														+ "Selenium Webdriver -\r\n"
														+ "TestNG");
		extent.setSystemInfo("User Name", "DTXin");

		return extent;
	}
}