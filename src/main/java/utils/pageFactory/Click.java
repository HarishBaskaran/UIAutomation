package utils.pageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.Keys.ARROW_DOWN;
import static org.openqa.selenium.Keys.ENTER;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import utils.configurationProviders.Endpoints;

public class Click {

	String testcaseName;

	public Click(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void click(Object obj, String xpath, boolean sleep) {
		click(obj, By.xpath(xpath), $(By.xpath(xpath)), sleep);
	}

	public void click(Object obj, By locator, boolean sleep) {
		click(obj, locator, $(locator), sleep);
	}

	public void click(Object obj, By locator, int index, boolean sleep) {
		click(obj, locator, $$(locator).get(index), sleep);
	}

	public void click(Object obj, By locator, String text, boolean sleep) {
		click(obj, locator, $$(locator).findBy(Condition.text(text)), sleep);
	}

	public void click_ShouldNotHave(Object obj, By locator, String text, Condition condition, boolean sleep) {
		click(obj, locator, $$(locator).findBy(Condition.text(text)).shouldNotHave(condition), sleep);
	}

	public void doubleClick(Object obj, String xpath, boolean sleep) {
		doubleClick(obj, By.xpath(xpath), $(By.xpath(xpath)), sleep);
	}

	public void doubleClick(Object obj, By locator, boolean sleep) {
		doubleClick(obj, locator, $(locator), sleep);
	}

	public void doubleClick(Object obj, By locator, int index, boolean sleep) {
		doubleClick(obj, locator, $$(locator).get(index), sleep);
	}

	public void doubleClick(Object obj, By locator, String text, boolean sleep) {
		doubleClick(obj, locator, $$(locator).findBy(Condition.text(text)), sleep);
	}

	public void click(Object obj, By locator, SelenideElement element, boolean sleep) {

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Clicked - ");

		Constants.constant(locator, element, sleep);

		element.hover().click();

	}

	public void doubleClick(Object obj, By locator, SelenideElement element, boolean sleep) {

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Double Clicked - ");

		Constants.constant(locator, element, sleep);

		element.hover().doubleClick();

	}

	public void clickAndKeyEnter(Object obj, By locator, boolean sleep) {

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Clicked and Enter - ");

		SelenideElement element = $(locator);

		Constants.constant(locator, element, sleep);

		Selenide.actions().click(element).sendKeys(ENTER).build().perform();

	}

	public void clickAndKeyDownKeyEnter(Object obj, By locator, boolean sleep) {

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator,
					"Clicked and Arrow down and Enter - ");

		SelenideElement element = $(locator);

		Constants.constant(locator, element, sleep);

		Selenide.actions().click(element).sendKeys(ARROW_DOWN).sendKeys(ENTER).build().perform();

	}
}
