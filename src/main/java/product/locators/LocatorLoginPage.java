package product.locators;

import org.openqa.selenium.By;

/*
 * 
 * Sample Locators Class - have constant returning BY objects
 * 
 * Have Prefix as "Locators" and then followed by Page NAME and finally have a suffix "Page"
 * 
 * Have the line 17 to 21 in all locators - this is used for runtime trackers
 */

public class LocatorLoginPage {

	private static final LocatorLoginPage inst = new LocatorLoginPage();

	public static LocatorLoginPage getInstance() {
		return inst;
	}

	public static final By CONTAINER_MODAL = By.xpath("//*[@title='Sign In']/..");
	public static final By HEADER_MODAL = By.xpath("//*[@title='Sign In']");
	public static final By INPUT_USERNAME = By.xpath("//input[@name='username']");
	public static final By INPUT_PASSWORD = By.xpath("//input[@name='password']");
	public static final By BUTTON_SIGN_IN = By.xpath("//button[@type='submit']");

}
