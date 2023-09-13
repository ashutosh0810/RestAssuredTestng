package org.bookingTest;

// This test case if for put request

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.booking.Commons;
import org.booking.HttpsMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporting.ExtentTestManager;
import reporting.TestListeners;
import org.testng.annotations.Listeners;

@Listeners(TestListeners.class)
public class Test_editBooking extends BaseTest {

    Response response;
    @BeforeMethod
    public void info()
    {
        ExtentTestManager.assignAuthor("Ashutosh");
        ExtentTestManager.testCategory("Regression");
    }

    @Test(description = " Verify the status code ")
    public void tc01_validateEdit() {
        ExtentTestManager.getTest().info("tc01_editbooking");
        response = HttpsMethods.put();
        log.info(" tc01_validateEdit " + response.getBody().asPrettyString());
        log.info(" PUT BODY IS " + response.getBody().asPrettyString());
        // logging the body in extent report
        ExtentTestManager.getTest().info("tc01_validateEdit" + response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(description = " Verify the body is not null ")
    public void tc02_validateEditBody() {
        log.info(" Validating the edit body and its object not be null ");
        ExtentTestManager.getTest().info(" tc02_validateEditBody " + response.getBody().asPrettyString());
        Assert.assertNotNull(response.jsonPath().get("firstname"), "First name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("lastname"), "Last name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("totalprice"), "price name should not be null");
        Assert.assertTrue(response.body().jsonPath().get("depositpaid") instanceof Boolean);
    }
}
