package org.bookingTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.booking.Commons;
import org.booking.HttpsMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.TestListeners;
import org.testng.annotations.Listeners;

@Listeners(TestListeners.class)
public class Test_deleteBooking extends BaseTest {
    Response response;

    @Test
    public void tc01_deleteBooking() {
        response = HttpsMethods.delete(String.valueOf(Commons.bookingid));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
    }

}
