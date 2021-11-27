package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import commons.AbstractTest;
import factory.DriverFactory;
import pageObjects.Flight_Page;
import pageObjects.Login_Page;

public class Login_Test extends AbstractTest {

	private Login_Page login = new Login_Page();
	private Flight_Page flight = new Flight_Page();

	@Test
	public void TC01_Login_GoodPassword() {
		login.doLogin("agileway", "testwise");
		Assert.assertTrue(DriverFactory.getInstance().getDriver()
				.findElement(flight.LABEL_USER_NAV).getText().contains("agileway"));
	}
	
	@Test
	public void TC02_Login_WrongPassword() {
		login.doLogin("agileway", "badpass");
		Assert.assertFalse(DriverFactory.getInstance().getDriver()
				.findElement(flight.LABEL_USER_NAV).getText().contains("agileway"));
	}

	@Test
	public void TC03_Login_WrongPassword_VerifyAlertShowed() {
		login.doLogin("agileway", "badpass");
		assertEqualString(DriverFactory.getInstance().getDriver()
				.findElement(login.FLASH_ALERT).getText(), "Invalid email or password", "Login Error Message");
	}
}