package testcase;

import java.time.Duration;
import java.util.HashMap;
import java.util.function.Function;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.*;
import ultilities.ExcelConnect;
import commons.AbstractTest;
import factory.DriverFactory;

public class Payment_Test extends AbstractTest {

	private Login_Page login = new Login_Page();
	private Flight_Page flight = new Flight_Page();
	private Passenger_Page passenger = new Passenger_Page();
	private Payment_Page payment = new Payment_Page();
	ExcelConnect excel = new ExcelConnect("BookAFlight");
	
	@Test(dataProvider = "paymentCreationData")
	public void TC_BookAFlight_And_Payment(Object obj1) throws InterruptedException{

		@SuppressWarnings("unchecked")
		HashMap<String, String> testData = (HashMap<String, String>) obj1;
		
		login.doLogin("agileway", "testwise");
		flight.bookAFlight_OneTrip(testData.get("From_Port"), testData.get("To_Port"), testData.get("Depart_Day"), testData.get("Depart_Month"));
		passenger.enterInforPassenger(testData.get("First_Name"), testData.get("Last_Name"));
		payment.enterInforPayment(testData.get("Type_Card"), testData.get("Card_Number"), testData.get("Expiry_Month"), testData.get("Expiry_Year"));

		// Waiting 20 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getInstance().getDriver())
		  .withTimeout(Duration.ofSeconds(20))
		  .pollingEvery(Duration.ofSeconds(5))
		  .ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				// Click pay now button and waiting a element display.
				return driver.findElement(payment.CONFIRMATION);
			}
		});
		Thread.sleep(3000);
		Assert.assertTrue(element.getAttribute("innerHTML").contains("Booking number"));
	}

	// Data provider method --> return object array
	@DataProvider (name = "paymentCreationData", parallel=true)
	public Object[][] testDataSupplier() throws Exception {
		Object[][] obj = new Object[excel.getRowCount()][1];
		for (int i = 1; i <= excel.getRowCount(); i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i-1][0] = testData;
		}
		return obj;
	}
}