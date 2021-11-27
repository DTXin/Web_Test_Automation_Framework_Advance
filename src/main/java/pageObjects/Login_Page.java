package pageObjects;

import org.openqa.selenium.By;

import commons.AbstractPage;
import factory.DriverFactory;

public class Login_Page extends AbstractPage {

	By TXT_USERNAME = By.id("username");
	By TXT_PASSWORD = By.id("password");
	By BTN_SIGNIN = By.name("commit");
	public By FLASH_ALERT = By.id("flash_alert");

	// To login from login page.
	public void doLogin(String username, String password) {
		sendKeyToElement(DriverFactory.getInstance().getDriver().findElement(TXT_USERNAME), "txt_username", username);
		sendKeyToElement(DriverFactory.getInstance().getDriver().findElement(TXT_PASSWORD), "txt_password", password);
		clickToElement(DriverFactory.getInstance().getDriver().findElement(BTN_SIGNIN), "button_signin");
	}
}