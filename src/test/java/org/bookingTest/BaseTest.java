package org.bookingTest;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.HttpsMethods;
import org.booking.Util;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import reporting.TestListeners;

import java.io.File;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

@Listeners(TestListeners.class)
// This class is base test and initialize the log folders
public class BaseTest {
    Logger log = LogManager.getLogger(BaseTest.class.getName());

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = Util.readConfig("baseUri");
        ExtentTestManager.startTest("start");
        ExtentTestManager.getTest().info("Starting Testing And Reporting  ");
        ExtentTestManager.getTest().info(RestAssured.baseURI);
        File logDir = new File("logs");
        if (!logDir.exists()) {
            log.info(" Creating logging directory ");
            logDir.mkdir();
        }

    }

    @BeforeMethod
    public void beforeInit() {

        ExtentTestManager.getTest().info(" ***** Checking the HEALTH ***** ");
        log.info(" ****** CHECKING HEALTH ********");
        Response response = HttpsMethods.get(Util.readConfig("pingpath"), "");
        HttpsMethods.get(Util.readConfig("authpath"), "");
        if (response.statusCode() == HttpStatus.SC_CREATED) {
            ExtentTestManager.getTest().log(Status.PASS, " *** PING IS HEALTHY ****");
            log.info(" ****** CHECKING HEALTH PASS  ******** ");
        } else {
            ExtentTestManager.getTest().log(Status.FAIL, " Not healthy");
            log.fatal(" PING NOT HEALTHY ");
        }
    }

    @AfterSuite
    public void tearDown()
    {
        ExtentTestManager.endTest();
    }

}
