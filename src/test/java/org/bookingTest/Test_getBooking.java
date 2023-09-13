package org.bookingTest;

// This test case if for  Get method generic for all types of get

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.booking.Commons;
import org.booking.HttpsMethods;
import org.booking.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import reporting.ExtentTestManager;
import reporting.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@Listeners(TestListeners.class)
public class Test_getBooking extends BaseTest {
    Response response;
    @BeforeMethod
    public void info()
    {
        ExtentTestManager.assignAuthor("Ashutosh");
        ExtentTestManager.testCategory("Regression");
    }

    @Test(description = " To validate the status code of get all booking ")
    public void tc01_getAllbookingId() throws IOException {
        ExtentTestManager.getTest().assignCategory("Regression ");
        ExtentTestManager.getTest().info(" Get all booking body is");
        response = HttpsMethods.get(Util.readConfig("path"), "");
        // Logging body in log report
        log.info(" response is " + response.getBody().asPrettyString());
        ExtentTestManager.getTest().info(response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
    }

    @Test(description = " To validate the status code of get specific booking ")
    public void tc02_getbookingId() throws IOException {
        log.info("path is ");
        ExtentTestManager.getTest().info("  tc02_getbookingId body is");
        response = HttpsMethods.get(Util.readConfig("path"), String.valueOf(Commons.bookingid));
        ExtentTestManager.getTest().info(" tc02_getbookingId" + response.getBody().asPrettyString());
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
    }

    @Test(description = " To validate the  404 NOT FOUND status code for wrong booking ")
    public void tc03_Neg_getbookingId() throws IOException {
        ExtentTestManager.getTest().info(" tc02_Neg_getbookingId");
        response = HttpsMethods.get(Util.readConfig("path"), "drew");
        ExtentTestManager.getTest().info(" Body is " + response.getBody().asPrettyString());

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
    }
}
