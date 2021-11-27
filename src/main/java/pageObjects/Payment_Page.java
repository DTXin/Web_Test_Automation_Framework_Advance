package pageObjects;

import org.openqa.selenium.By;

import commons.AbstractPage;
import factory.DriverFactory;

public class Payment_Page extends AbstractPage {

	By TXT_CARD_NUMBER = By.name("card_number");
	By DD_EXPIRY_MONTH = By.name("expiry_month");
	By DD_EXPIRY_YEAR = By.name("expiry_year");
	By RD_VISA_CARD = By.xpath("//input[@name='card_type' and @value='visa']");
	By RD_MASTER_CARD = By.xpath("//input[@name='card_type' and @value='master']");
	By BTN_PAYNOW = By.xpath("//input[@type='submit' and @value='Pay now']");
	public By CONFIRMATION = By.id("confirmation");

	// Method enter information to payment
	public void enterInforPayment(String type_card, String card_number, String expiry_month, String expiry_year) {
		chooseACardType(type_card);
		sendKeyToElement(DriverFactory.getInstance().getDriver().findElement(TXT_CARD_NUMBER), "TXT_CARD_NUMBER", card_number);
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_EXPIRY_MONTH), "DROPDOWN_EXPIRY_MONTH", expiry_month);
		selectDropDown_ByVisibleText(DriverFactory.getInstance().getDriver().findElement(DD_EXPIRY_YEAR), "DROPDOWN_EXPIRY_YEAR", expiry_year);
		clickUsingJavaScriptExecutor(DriverFactory.getInstance().getDriver(),
				DriverFactory.getInstance().getDriver().findElement(BTN_PAYNOW), "BTN_PAYNOW");
	}
	
	// Method choose a card type
	public void chooseACardType(String type_card) {
		clickToElement(DriverFactory.getInstance().getDriver().findElement(
				By.xpath("//input[@name='card_type' and @value='"+ type_card + "']")), type_card + " card");
	}
}