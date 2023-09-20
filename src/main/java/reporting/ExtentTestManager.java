package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    static ExtentReports extentReports = ExtentManager.getInstance();

    public static synchronized ExtentTest getTest() {
        //return extentTestMap.get((int) (Thread.currentThread().getId()));
        return  extentTestMap.get((int)Thread.currentThread().getId());
    }

    public static synchronized void endTest() {

        extentReports.flush();
    }

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest extentTest = extentReports.createTest(testName);
        extentTestMap.put((int) (Thread.currentThread().getId()), extentTest);
        return extentTest;
    }

    public static ExtentTest testCategory(String category) {

        return ExtentTestManager.getTest().assignCategory(category);
    }

    public static ExtentTest assignAuthor(String category) {
        return ExtentTestManager.getTest().assignAuthor(category);
    }
}
