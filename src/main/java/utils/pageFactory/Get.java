package utils.pageFactory;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import utils.configurationProviders.Endpoints;

public class Get {

	String testcaseName;

	public Get(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public String getText(Object obj, By locator) {

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Read Text - ");

		Constants.constant(locator, $(locator), false);

		return $(locator).getText();
	}

	public String getValue(Object obj, By locator) {

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Read Value - ");

		Constants.constant(locator, $(locator), false);

		new Click(testcaseName).click(obj, locator, false);

		return $(locator).getValue();
	}

	public String getInnerHTML(Object obj, By locator) {

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Read Value - ");

		Constants.constant(locator, $(locator), false);

		new Click(testcaseName).click(obj, locator, false);

		return $(locator).getAttribute("innerHTML");
	}
}
