package pageObjects;

import org.openqa.selenium.By;

import commons.AbstractPage;
import factory.DriverFactory;

public class Passenger_Page extends AbstractPage {

	By TXT_FIRSTNAME = By.name("passengerFirstName");
	By TXT_LASTNAME = By.name("passengerLastName");
	By BTN_NEXT = By.xpath("//input[@type='submit' and @value='Next']");

	// Method enter first name and last name of passenger
	public void enterInforPassenger(String firstname, String lastname) {
		sendKeyToElement(DriverFactory.getInstance().getDriver().findElement(TXT_FIRSTNAME), "TXT_FIRSTNAME_PASSENGER", firstname);
		sendKeyToElement(DriverFactory.getInstance().getDriver().findElement(TXT_LASTNAME), "TXT_LASTNAME_PASSENGER", lastname);
		clickToElement(DriverFactory.getInstance().getDriver().findElement(BTN_NEXT), "BUTTON_NEXT");
	} 
}