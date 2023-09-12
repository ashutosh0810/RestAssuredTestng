package org.bookingTest;

// This test case if for Patch request

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.Commons;
import org.booking.HttpsMethods;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reporting.ExtentTestManager;
import reporting.TestListeners;

@Listeners(TestListeners.class)

public class Test_partialUpdate {
    Response response;
    Logger log = LogManager.getLogger(Test_partialUpdate.class);

    @Test(description = " Validate that user can update the booking partially ")
    public void tc01_updateNameonly() {
        ExtentTestManager.getTest().info("tc01_updateNameonly ");
        log.info(" Testing tc01_updateNameonly");
        response = HttpsMethods.patch(Commons.bookingid);
        ExtentTestManager.getTest().info(" response of the partial update is " + response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
}
