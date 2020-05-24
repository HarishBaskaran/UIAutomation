package utils.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestResultsAnalyzer extends TestListenerAdapter {
	
	String retryFileName = null;
	static boolean testflag = false;
	static int count = 0;
	static int retries = 1;

	TestNG testng = new TestNG();

	List<String> testFilesList = new ArrayList<String>();


    @Override
    public void onFinish(ITestContext context) {
        Iterator<ITestResult> failedTestCases = context.getFailedTests().getAllResults().iterator();
        
        while(failedTestCases.hasNext()) {
            ITestResult failedTest = failedTestCases.next();
            if (context.getPassedTests().getResults(failedTest.getMethod()).size() > 0) {                
                failedTestCases.remove();
                continue;
            }
            if (context.getFailedTests().getResults(failedTest.getMethod()).size() > 1)
                failedTestCases.remove();
        }
        
        Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
        while(skippedTestCases.hasNext()) {
            ITestResult skippedTest = skippedTestCases.next();
            if (context.getPassedTests().getResults(skippedTest.getMethod()).size() > 0) {                
                skippedTestCases.remove();
                continue;
            }
            
            if (context.getFailedTests().getResults(skippedTest.getMethod()).size() > 0) {                
                skippedTestCases.remove();
                continue;
            }
            if (context.getSkippedTests().getResults(skippedTest.getMethod()).size() > 1)
            	skippedTestCases.remove();
        }
    }
    
}

