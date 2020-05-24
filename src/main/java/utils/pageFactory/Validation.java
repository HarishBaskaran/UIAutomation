package utils.pageFactory;

import utils.configurationProviders.Endpoints;

public class Validation {

	String testcaseName;

	public Validation(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void validate(String actualValue, String expectedValue) {

		if (Endpoints.getValue("debug").contains("true"))
			BaseSingleton.getInstance().printFieldNames(testcaseName,
					"Validate " + expectedValue + " with " + actualValue + " - ");
	}
}
