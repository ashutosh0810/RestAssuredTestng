package org.bookingTest;

import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.HttpsMethods;
import org.booking.Util;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import reporting.TestListeners;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

@Listeners(TestListeners.class)
// This class is base test and initialize the log folders
public class BaseTest {
    Logger log = LogManager.getLogger(BaseTest.class.getName());
    HttpsMethods methods;

    @BeforeSuite
    public void setUp() {
        methods = new HttpsMethods();
        File logDir = new File("logs");
        if (!logDir.exists()) {
            log.info(" Creating logging directory ");
            logDir.mkdir();
        }
        ExtentTestManager.startTest("start");
        ExtentTestManager.getTest().info("Starting Testing And Reporting  ");
    }

    @BeforeMethod
    public void beforeInit() {

        ExtentTestManager.getTest().info(" ***** Checking the HEALTH ***** ");
        log.info(" ****** CHECKING HEALTH ********");
        Response response = HttpsMethods.get(Util.readConfig("pingpath"), "");
        HttpsMethods.get(Util.readConfig("authpath"), "");
        int status = response.getStatusCode();
        int stat = HttpStatus.SC_OK;
        if (response.statusCode() == HttpStatus.SC_CREATED) {
            ExtentTestManager.getTest().log(Status.PASS, " *** PING IS HEALTHY ****");
            log.info(" ****** CHECKING HEALTH PASS  ******** ");
        } else {
            ExtentTestManager.getTest().log(Status.FAIL, " Not healthy");
            log.fatal(" PING NOT HEALTHY ");
        }
        // Assert.assertEquals(HttpsMethods.get(Util.readConfig("pingpath"), "").statusCode(), HttpStatus.SC_CREATED);


    }


}
