package org.bookingTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.HttpsMethods;
import org.testng.Assert;
import reporting.ExtentTestManager;
import reporting.TestListeners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Listeners(TestListeners.class)
public class Test_createBooking extends BaseTest {

    Logger log = LogManager.getLogger(Test_createBooking.class.getName());
    Response response;


    @Test
    public void tc01_createbooking() throws IOException {
        ExtentTestManager.getTest().info("tc01_createbooking Testing ");
        ExtentTestManager.getTest().info(" validating the status of tc01_createbooking");
        response = HttpsMethods.post();
        log.info(response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    // @Test(description = "To validate the create Booking response schema")
    public void tc02_validateSchema() {

        Assert.assertEquals(response.asString(), matchesJsonSchemaInClasspath("C:\\Users\\ashutoshsingh03\\Documents\\AshutoshKumarSingh\\BookingApp\\src\\main\\resources\\postBookingresponse.json"));
    }

    @Test(description = " To validate the header of response ")
    public void tc03_validateHeaders() {
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");

    }

    @Test(description = " To validate the body of json response  ")

    public void tc04_validateBody() {
        ExtentTestManager.getTest().info(response.getBody().asPrettyString());
        Assert.assertNotNull(response.jsonPath().get("booking.firstname"), "First name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("booking.lastname"), "Last name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("booking.totalprice"), "price name should not be null");
        Assert.assertTrue(response.body().jsonPath().get("booking.depositpaid") instanceof Boolean);
    }
}
