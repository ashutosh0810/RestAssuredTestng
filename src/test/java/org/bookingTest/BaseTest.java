package org.bookingTest;


import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.HttpsMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import reporting.ExtentTestManager;
import reporting.TestListeners;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;

@Listeners(TestListeners.class)
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

    }

    @BeforeMethod
    public void getHealthPing() {

        log.info(" ****** CHECKING HEALTH ********");
        HttpsMethods.getPing();
        Assert.assertEquals(HttpsMethods.getPing().statusCode(), HttpStatus.SC_CREATED);
        log.info(" ****** CHECKING HEALTH PASS  ******** ");

    }


}
