package ultilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import factory.DriverFactory;

public class TestUtilities {
	
	// Method to screenshot a picture and save it to a folder
	public static String captureScreenshot(String arg0) throws IOException {

		File scrFile = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");
		Date date = new Date();
		String actualDate = format.format(date);
		String path = System.getProperty("user.dir") + "/Reports/Screenshots/" + arg0 + "-" + actualDate + ".jpeg";
		// Create another File object variable which points (refer)
		File destination = new File(path);
		// Save screen shot at desired path
		FileUtils.copyFile(scrFile, destination);

		return path;
	}
}