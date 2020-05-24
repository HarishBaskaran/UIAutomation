package utils.pageFactory;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import utils.configurationProviders.Endpoints;

public class Write {

	String testcaseName;

	public Write(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void clickAndType(Object obj, By locator, SelenideElement element, String value, boolean sleep) {

		Constants.constant(testcaseName, obj, locator, value);

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator,
					"Clicked and Texted " + value + " - ");

		Constants.constant(locator, element, sleep);

		element.click();
		element.val(value);
	}

	public void type(Object obj, By locator, SelenideElement element, String value, boolean sleep) {

		Constants.constant(testcaseName, obj, locator, value);

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Texted " + value + " - ");

		Constants.constant(locator, element, sleep);

		element.val(value);
	}

	public void clearAndtype(Object obj, By locator, SelenideElement element, String value, boolean sleep) {

		Constants.constant(testcaseName, obj, locator, value);

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator,
					"Cleared and Texted " + value + " - ");

		Constants.constant(locator, element, sleep);

		element.clear();
		element.val(value);
	}

	public void clickAndClearAndtype(Object obj, By locator, SelenideElement element, String value, boolean sleep) {

		Constants.constant(testcaseName, obj, locator, value);

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator,
					"Clicked and Cleared and Texted " + value + " - ");

		Constants.constant(locator, element, sleep);

		element.click();
		element.clear();
		element.val(value);
	}

	public void clickAndType(Object obj, String xpath, String value, boolean sleep) {
		type(obj, By.xpath(xpath), $(By.xpath(xpath)), value, sleep);
	}

	public void clickAndType(Object obj, By locator, String value, boolean sleep) {
		type(obj, locator, $(locator), value, sleep);
	}

	public void type(Object obj, String xpath, String value, boolean sleep) {
		type(obj, By.xpath(xpath), $(By.xpath(xpath)), value, sleep);
	}

	public void type(Object obj, By locator, String value, boolean sleep) {
		type(obj, locator, $(locator), value, sleep);
	}

	public void clearAndtype(Object obj, String xpath, String value, boolean sleep) {
		type(obj, By.xpath(xpath), $(By.xpath(xpath)), value, sleep);
	}

	public void clearAndtype(Object obj, By locator, String value, boolean sleep) {
		type(obj, locator, $(locator), value, sleep);
	}

	public void clickAndClearAndtype(Object obj, String xpath, String value, boolean sleep) {
		type(obj, By.xpath(xpath), $(By.xpath(xpath)), value, sleep);
	}

	public void clickAndClearAndtype(Object obj, By locator, String value, boolean sleep) {
		type(obj, locator, $(locator), value, sleep);
	}

}
