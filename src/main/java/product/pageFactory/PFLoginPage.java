package product.pageFactory;

import static product.locators.LocatorLoginPage.BUTTON_SIGN_IN;
import static product.locators.LocatorLoginPage.HEADER_MODAL;
import static product.locators.LocatorLoginPage.INPUT_PASSWORD;
import static product.locators.LocatorLoginPage.INPUT_USERNAME;

import product.locators.LocatorLoginPage;
import utils.pageFactory.Click;
import utils.pageFactory.Wait;
import utils.pageFactory.Write;

/*
 * 
 * Sample Page factory class 
 * 
 * Have prefix as "PF" and then followed by Page Name and then finally add suffix "Page"
 * 
 * Have Variable testcase and constructor for runtime tracking purpose
 * 
 * This class should have only the possible selenide actions on the elements available on that page or modal
 * 
 */
public class PFLoginPage {

	String testcaseName;

	public PFLoginPage(String testcaseName) {
		super();
		this.testcaseName = testcaseName;
	}

	public void clickHeaderModal() {
		new Wait(testcaseName).waitAppear(LocatorLoginPage.getInstance(), HEADER_MODAL, 120000);
		new Click(testcaseName).click(LocatorLoginPage.getInstance(), HEADER_MODAL, false);

	}

	public void setInputUsername(String username) {
		new Write(testcaseName).clickAndType(LocatorLoginPage.getInstance(), INPUT_USERNAME, username, false);
	}

	public void setInputPassword(String password) {
		new Write(testcaseName).clickAndType(LocatorLoginPage.getInstance(), INPUT_PASSWORD, password, false);
	}

	public void clickButtonSignin() {
		new Click(testcaseName).click(LocatorLoginPage.getInstance(), BUTTON_SIGN_IN, false);
	}

}