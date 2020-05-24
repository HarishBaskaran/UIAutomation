package product.builders;

import product.pageObjects.POLoginPage;
import utils.pageFactory.Open;

/*
 * 
 * Sample Builder class 
 * 
 * Have prefix as "Builder" and then followed by Main Flow name 
 * Have methods names as actual flows in that page using different POs.
 * 
 * Have Variable testcase and constructor for runtime tracking purpose
 * 
 * This class should have only sequence of flows happening in product.
 */

public class BuilderLoginApplication {

	String testcaseName;

	public BuilderLoginApplication(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void Login(String url) {

		new Open(testcaseName).open(url);

		new POLoginPage(testcaseName).SignIn();

	}

}
