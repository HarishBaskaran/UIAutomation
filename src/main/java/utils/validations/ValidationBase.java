package utils.validations;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import java.math.BigDecimal;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import utils.dataProviders.HelperMethods;

public class ValidationBase {

	public static void verifyElementPriceValue(String expectedValue, By locator) {
		if (expectedValue == null) {
			return;
		}

		try {
			String actualValue = HelperMethods
					.formatAmount(BigDecimal.valueOf(Float.parseFloat($(locator).getValue().substring(1))));
			Assert.assertEquals(actualValue, expectedValue);
		} catch (Exception e) {
			Assert.assertEquals($(locator).getValue(), expectedValue);
		}
	}

	public static void verifyElementValue(String expectedValue, By locator) {
		if (expectedValue == null) {
			return;
		}

		Assert.assertEquals($(locator).getValue(), expectedValue);

	}

	public static void verifyElementValue(String expectedValue, SelenideElement element) {
		if (expectedValue == null) {
			return;
		}
		try {
			String actualValue = HelperMethods
					.formatAmount(BigDecimal.valueOf(Float.parseFloat(element.getValue().substring(1))));
			Assert.assertEquals(actualValue, expectedValue);
		} catch (Exception e) {
			Assert.assertEquals(element.getValue(), expectedValue);
		}
	}

	public static void verifyElementText(String expectedText, By locator) {
		if (expectedText == null) {
			return;
		}
		scrollSTAYElementToVisibleArea($(locator));
		Assert.assertEquals($(locator).getText(), expectedText);
	}
	
	public static void verifyElementValueWithoutFormat(String expectedText, SelenideElement locator) {
		if (expectedText == null) {
			return;
		}
		scrollSTAYElementToVisibleArea(locator);
		Assert.assertEquals(locator.getValue(), expectedText);
	}
	
	
	public static void scrollSTAYElementToVisibleArea(SelenideElement selenideElement) {
		By MENU_BAR = By.xpath("//nav[@role='menubar']");
		int i = 1;
		int menuX = $(MENU_BAR).getCoordinates().inViewPort().x;
		int menuRightX = menuX + $(MENU_BAR).getSize().width;

		int menuY = $(MENU_BAR).getCoordinates().inViewPort().y;
		int menuBottomY = menuY + $(MENU_BAR).getSize().height;

		WebDriver driver = getWebDriver();
		int elementY = selenideElement.getCoordinates().inViewPort().y;
		int elementBottomY = elementY + selenideElement.getSize().height;
		Long viewPortHeight = (Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight");
		viewPortHeight = (long) (viewPortHeight * .80);

		int toScroll = 0;
		if (elementY <= menuBottomY) {
			toScroll = menuBottomY - elementY;
		} else if (elementBottomY > viewPortHeight) {
			toScroll = (int) (viewPortHeight - elementBottomY);
		}

		if (toScroll != 0) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + String.valueOf(0 - toScroll) + ")");
		}
	}

	public static void verifyElementText(String expectedText, SelenideElement element) {
		if (expectedText == null) {
			return;
		}
		Selenide.executeJavaScript("arguments[0].setAttribute('style', 'border: 2px solid red;')", element);
		Assert.assertTrue(element.getText().contains(expectedText));
	}

	public static void waitForElementValueToLoad(String value, By by) {
		int time = 0;
		while (time <= Configuration.timeout) {
			if ($$(by).filter(Condition.value(value)).size() > 0) {
				return;
			}
			Selenide.sleep(1000);
			time = time + 1000;
		}
	}

	public static void verifyElementNotPresent(By locator) {
		Assert.assertEquals($$(locator).size(), 0, "Element present");
	}

	public static int findElementIndexByValue(String value, By by) {
		waitForElementValueToLoad(value, by);
		ElementsCollection elements = $$(by);
		int i = 0;
		for (SelenideElement element : elements) {
			if (element.getValue().equalsIgnoreCase(value)) {
				return i;
			}
			i++;
		}
		Assert.fail("Element with value " + value + " not found");
		return -1;
	}

	public static void verifyElementAttributeContains(By elementLocator, String attributeName, String expected) {
		String actual = $(elementLocator).attr(attributeName);
		Assert.assertTrue(actual.contains(expected), "Actula is " + actual + ", " + expected);
	}

	public static void verifyCheckBox(boolean isChecked, By locator) {
		boolean actual = Arrays.asList($(locator).getAttribute("class").split(" ")).contains("active");
		Assert.assertEquals(actual, isChecked);
	}




}
