package org.bookingTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.booking.Commons;
import org.booking.HttpsMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.ExtentTestManager;
import reporting.TestListeners;
import org.testng.annotations.Listeners;

@Listeners(TestListeners.class)
public class Test_deleteBooking extends BaseTest {
    Response response;
    // This is to validate the delete method
    @Test(description = " Verify status code post deletion is 201")
    public void tc01_deleteBooking() {
        response = HttpsMethods.delete(String.valueOf(Commons.bookingid));
        log.info(" Delete method body is " + response.getBody().asPrettyString());
        ExtentTestManager.getTest().info(" Delete response is " + response.getStatusCode());
        ExtentTestManager.getTest().info(" Delete response body is " + response.getBody());
        log.info(" asserting status as 201 ");
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    }

}
