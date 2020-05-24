package utils.pageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.SelenideElement;

import utils.configurationProviders.Endpoints;

public class Scroll {

	public static By MENU_BAR = By.xpath("//nav[@role='menubar']");
	String testcaseName;

	public Scroll(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void scrollElementToVisibleArea(Object obj, By locator, SelenideElement selenideElement) {

		if (new Endpoints().getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator, "Scrolled to view ");

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
}
