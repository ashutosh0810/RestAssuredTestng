package org.bookingTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.booking.HttpsMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.ExtentTestManager;
import reporting.TestListeners;
import org.testng.annotations.Listeners;

@Listeners(TestListeners.class)
public class Test_editBooking extends BaseTest{

    Response response;

    @Test
    public void tc01_validateEdit() {
        ExtentTestManager.getTest().info("tc01_editbooking");
        response = HttpsMethods.put();
        ExtentTestManager.getTest().info(response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
    @Test
    public void tc02_validateEditBody()
    {
        ExtentTestManager.getTest().info(response.getBody().asPrettyString());
        Assert.assertNotNull(response.jsonPath().get("firstname"), "First name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("lastname"), "Last name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("totalprice"), "price name should not be null");
        Assert.assertTrue(response.body().jsonPath().get("depositpaid") instanceof Boolean);
    }
}
