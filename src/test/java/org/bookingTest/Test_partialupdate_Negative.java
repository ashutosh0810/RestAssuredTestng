package org.bookingTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.booking.Commons;
import org.booking.HttpsMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.ExtentTestManager;

public class Test_partialupdate_Negative {
    // This class will verify the status for the deleted booking id and it should be 404 Not found

    @Test( description  ="Validate deleted booking should not return other status than 405")
    public void tc01_Neg_PartialUpdate() {
        Response response = HttpsMethods.patch(Commons.bookingid);
        ExtentTestManager.getTest().info(" Validating deleted booking id >>> " + response.getStatusCode());
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }
}
