package utils.pageFactory;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import utils.configurationProviders.Endpoints;

public class Wait {

	String testcaseName;

	public Wait(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void waitExist(Object obj, By locator, int sleep) {
		wait(obj, locator, $(locator), Condition.exist, "exist", sleep);
	}

	public void waitEnabled(Object obj, By locator, int sleep) {
		wait(obj, locator, $(locator), Condition.enabled, "enabled", sleep);
	}

	public void waitAppear(Object obj, By locator, int sleep) {
		wait(obj, locator, $(locator), Condition.appear, "appear", sleep);
	}

	public void waitDisappear(Object obj, By locator, int sleep) {
		wait(obj, locator, $(locator), Condition.disappear, "disappear", sleep);
	}

	public void waitBasedOnText(Object obj, By locator, String text, int sleep) {
		wait(obj, locator, $(locator), Condition.text(text), "text appear", sleep);
	}

	public void wait(Object obj, By locator, SelenideElement element, Condition condition, String action, int sleep) {

		element.waitUntil(condition, sleep);

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Waited to " + action + " - ");

		element.hover();

	}
}
