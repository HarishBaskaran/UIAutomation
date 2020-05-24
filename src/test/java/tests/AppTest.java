package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import product.builders.BuilderLoginApplication;
import utils.wrappers.UIWrappers;

/**
 * Unit test for simple App.
 */
public class AppTest extends UIWrappers {

	@DataProvider // (parallel = true)
	public String[][] getData() {
		return utils.dataProviders.DataInputProvider.getSheet("DataProvider1", 0);
	}
	
	@Test
	public void testApp() {

		new BuilderLoginApplication("testApp").Login("http://www.google.com");

	}
}
