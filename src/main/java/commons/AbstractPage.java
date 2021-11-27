/*
 * Collection all action of pages and 
 * add status of that action to extent report.
 */

package commons;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import factory.ExtentFactory;

public class AbstractPage {

	// Click a element method
	public void clickToElement(WebElement element, String fieldname) {
		try {
			element.click();
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldname + "==> Clicked Successfully");
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field " + fieldname + " due to exception: " + e);
		}
	}
	
	// Click a element with java script method
	public void clickUsingJavaScriptExecutor(WebDriver driver, WebElement element, String fieldname) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldname + "==> Clicked Successfully");
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field " + fieldname + " due to exception: " + e);
		}
	}

	// Type text to a element method
	public void sendKeyToElement(WebElement element, String fieldname, String value) {
		try {
			element.sendKeys(value);
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldname + "==> Entered value as: " + value);
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field " + fieldname + " is failed due to exception: " + e);
		}
	}

	// Clear data from a field
	public void clearData(WebElement element, String fieldname) {
		try {
			element.clear();
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldname + "==> Data cleared successfully !");
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to clear data on field " + fieldname + " due to exception: " + e);
		}
	}

	// Select item from drop down By visible text
	public void selectDropDown_ByVisibleText(WebElement element, String fieldname, String item) {
		try {
			Select itemInDropDown = new Select(element);
			itemInDropDown.selectByVisibleText(item);
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldname + "==> Dropdown value selected by visible text: " + item);
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field " + fieldname + " due to exception: " + e);
		}
	}

	// Select item from drop down By value
	public void selectDropDown_ByValue(WebElement element, String fieldname, String item) {
		try {
			Select itemInDropDown = new Select(element);
			itemInDropDown.selectByValue(item);
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldname + "==> Dropdown value selected by value: " + item);
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field " + fieldname + " due to exception: " + e);
		}
	}

	// Get text from a element
	public String getTextElement(WebElement element, String fieldname) {
		String text = "";
		try {
			element.getText();
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldname + "==> Text retried is:  " + text);
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Text not retried due to exception: " + e);
		}
		return text;
	}

	// Check if element is present
	public boolean isElementPresent(WebElement element, String fieldname) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldname + "==> Presence of field is " + flag);
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Checking for presence of field: " + fieldname + " not tested due to exception:: " + e);
		}
		return flag;
	}
	
	// String assert
	public void assertEqualString(String expValue, String actualValue, String fieldname) {
		try {
			Assert.assertEquals(expValue, actualValue);
			// Log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "String assertion is successfully on field: " + fieldname);
		} catch (Exception e) {
			// Log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String assertion Failed on field: " + fieldname + " Exception" + e);
		}
	}
}