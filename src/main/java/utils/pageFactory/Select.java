package utils.pageFactory;

import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class Select {

	String testcaseName;

	public Select(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void selectByText(Object obj, By dropdownLocator, By optionsLocator, String value) {
		Constants.constant(testcaseName, obj, dropdownLocator, value);
		SelenideElement dropdownElement = $(dropdownLocator);

		new Click(testcaseName).click(obj, dropdownLocator, false);

		int time = 0;
		while (time <= Configuration.timeout) {

			ElementsCollection listElements = $$(optionsLocator);
			if (listElements.filter(Condition.text(value)).size() > 0) {
				listElements.findBy(matchesText(value)).click();
				return;
			}
			time = time + 2000;
			dropdownElement.click();
			Selenide.sleep(500);
			dropdownElement.click();
			Selenide.sleep(500);
		}
	}

	public void selectByText(Object obj, By dropdownLocator, int index, By optionsLocator, String value) {
		Constants.constant(testcaseName, obj, dropdownLocator, value);
		
		SelenideElement dropdownElement = $$(dropdownLocator).get(index);
		ElementsCollection listElements = $$(optionsLocator);
		
		new Click(testcaseName).click(obj, dropdownLocator, false);
		
		int time = 0;
		while (time <= 60000) {
			if (listElements.filter(Condition.text(value)).size() > 0) {
				listElements.findBy(matchesText(value)).click();
				return;
			}
			time = time + 2000;
			dropdownElement.click();
			Selenide.sleep(500);
			dropdownElement.click();
		}
	}
}
