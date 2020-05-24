package utils.pageFactory;

import com.codeborne.selenide.Selenide;

import utils.configurationProviders.Endpoints;
import utils.listeners.RuntimeTracker_Singleton;

public class Open {

	String testcaseName;

	public Open(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void open(String value) {

		if (Endpoints.getValue("tracker").contains("true")) {

			String[] Line = new String[] { testcaseName, "Open URL", value };

			RuntimeTracker_Singleton.getInstance().writeResults(Line);
		}

		if (Endpoints.getValue("debug").contains("true")) {

			System.out.println(testcaseName + "Open URL " + value);
		}

		Selenide.open(value);
	}
}
