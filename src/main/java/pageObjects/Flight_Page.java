package pageObjects;

import org.openqa.selenium.By;

import commons.AbstractPage;
import factory.DriverFactory;

public class Flight_Page extends AbstractPage {

	By RD_ONE_TRIP = By.xpath("//input[@name='tripType' and @value='oneway']");
	By RD_RETURN_TRIP = By.xpath("//input[@name='tripType' and @value='return']");
	By DD_FROM_PORT = By.name("fromPort");
	By DD_TO_PORT = By.name("toPort");
	By DD_DEPARTING_DAY = By.name("departDay");
	By DD_DEPARTING_MONTH = By.name("departMonth");
	By DD_RETURN_DAY = By.name("returnDay");
	By DD_RETURN_MONTH = By.name("returnMonth");
	By BTN_CONTINUE = By.xpath("//input[@type='submit']");
	public By LABEL_USER_NAV = By.id("user_nav");

	// Book a Flight One Trip
	public void bookAFlight_OneTrip(String from_port, String to_port, String depart_day, String depart_month) {
		// Click one trip radio button
		clickToElement(DriverFactory.getInstance().getDriver().findElement(RD_ONE_TRIP), "One Trip Radio Button");
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_FROM_PORT), "From_Port_DropDown", from_port);
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_TO_PORT), "To_Port_DropDown", to_port);
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_DEPARTING_DAY), "Departing_Day_DropDown", depart_day);
		selectDropDown_ByValue(DriverFactory.getInstance().getDriver().findElement(DD_DEPARTING_MONTH), "Departing_Month_DropDown", depart_month);
		clickToElement(DriverFactory.getInstance().getDriver().findElement(BTN_CONTINUE), "Continue_Button");
	}

	// Book a Flight Return Trip
	public void bookAFlight_ReturnTrip(String from_port, String to_port, String depart_day, 
			String depart_month, String return_day, String return_month) {
		// Click return trip radio button
		clickToElement(DriverFactory.getInstance().getDriver().findElement(RD_ONE_TRIP), "One Trip Radio Button");
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_FROM_PORT), "From_Port_DropDown", from_port);
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_TO_PORT), "To_Port_DropDown", to_port);
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_DEPARTING_DAY), "Departing_Day_DropDown", depart_day);
		selectDropDown_ByValue(DriverFactory.getInstance().getDriver().findElement(DD_DEPARTING_MONTH), "Departing_Month_DropDown", depart_month);
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_RETURN_DAY), "Return_Day_DropDown", depart_day);
		selectDropDown_ByValue(DriverFactory.getInstance().getDriver().findElement(DD_RETURN_MONTH), "Return_Month_DropDown", depart_month);
		clickToElement(DriverFactory.getInstance().getDriver().findElement(BTN_CONTINUE), "Continue_Button");
	} 
}