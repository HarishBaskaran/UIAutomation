package utils.pageFactory;

import java.lang.reflect.Field;

import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import utils.configurationProviders.Endpoints;
import utils.listeners.RuntimeTracker_Singleton;

class BaseSingleton {

	private static final BaseSingleton inst = new BaseSingleton();

	private BaseSingleton() {
		super();
	}

	protected static BaseSingleton getInstance() {
		return inst;
	}

	protected synchronized void printFieldNames(String testcaseName, Object obj, By locator, String action) {

		boolean flag = false;
		String name = null;
		String value = null;

		try {
			for (Field field : obj.getClass().getFields()) {
				if (locator.toString().contains(field.get(obj).toString())) {

					flag = true;
					name = field.getName();
					value = field.get(obj).toString();

				}

				if (flag) {

					System.out.println(testcaseName + " " + action + name + " from " + obj.getClass().getSimpleName()
							+ " : " + value);

					if (Endpoints.getValue("tracker").contains("true")) {

						String[] Line = new String[] { testcaseName, action, name, obj.getClass().getSimpleName(),
								value };

						RuntimeTracker_Singleton.getInstance().writeResults(Line);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Singleton class for debug operation failed");
		}
	}

	protected synchronized void printFieldNames(String testcaseName, String action) {

		System.out.println(testcaseName + " " + action);

		if (Endpoints.getValue("tracker").contains("true")) {

			String[] Line = new String[] { testcaseName, action };

			RuntimeTracker_Singleton.getInstance().writeResults(Line);
		}

	}
}

class Constants {

	protected static void constant(By locator, SelenideElement element, boolean sleep) {

		if (sleep)
			Selenide.sleep(3000);

		element.hover();

		if (Endpoints.getValue("highlight").contains("true"))
			Selenide.executeJavaScript("arguments[0].style.border='3px solid red'", element);

	}

	protected static void constant(String testcaseName, Object obj, By locator, String value) {
		if (value == null) {
			if (Endpoints.getValue("debug").contains("true"))
				BaseSingleton.getInstance().printFieldNames(testcaseName, obj, locator,
						"LEFT Type because of NULL VALUE - ");
			return;
		}
	}
}