package product.pageObjects;

import product.pageFactory.PFLoginPage;
import utils.configurationProviders.Endpoints;

/*
 * 
 * Sample PageObjects class 
 * 
 * Have prefix as "PO" and then followed by Page Name and then finally add suffix "Page"
 * 
 * Have Variable testcase and constructor for runtime tracking purpose
 * 
 * This class should have only sequence of flows happening in that PAGE
 * 
 */
public class POLoginPage {

	String testcaseName;

	public POLoginPage(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void SignIn() {
		String user = Endpoints.getValue("username");
		String pass = Endpoints.getValue("password");

		new PFLoginPage(testcaseName).clickHeaderModal();
		new PFLoginPage(testcaseName).setInputUsername(user);
		new PFLoginPage(testcaseName).setInputPassword(pass);
		new PFLoginPage(testcaseName).clickButtonSignin();

	}

}
