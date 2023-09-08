package org.bookingTest;


import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.HttpsMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import reporting.TestListeners;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.sql.SQLOutput;

@Listeners(TestListeners.class)

public class BaseTest {
    Logger log = LogManager.getLogger(BaseTest.class.getName());
    HttpsMethods methods;

    @BeforeSuite
    public void setUp() {
        methods = new HttpsMethods();
        File logDir = new File("logFolder");
        if (!logDir.exists()) {
            log.info(" Creating logging directory ");
            logDir.mkdir();
        }

    }

    @BeforeMethod
    public void getToken() {
        System.out.println(" &*&************************");
        HttpsMethods.getPing();
        Assert.assertEquals(HttpsMethods.getPing().statusCode(), HttpStatus.SC_CREATED);

    }


}
