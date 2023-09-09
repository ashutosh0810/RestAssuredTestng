package org.bookingTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.booking.Commons;
import org.booking.HttpsMethods;
import org.hamcrest.Matchers;
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

    Logger log = LogManager.getLogger(Test_createBooking.class);
    Response response;


    @Test
    public void tc01_createbooking() throws IOException {
        log.info(" **** tc01_createbooking() *****");
        ExtentTestManager.getTest().info("tc01_createbooking Testing ");
        ExtentTestManager.getTest().info(" validating the status of tc01_createbooking");
        response = HttpsMethods.post();
        log.info(" POST bODY is " + response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        // Storing booking id
        Commons.bookingid=response.jsonPath().get("bookingid");
    }

    @Test(description = "To validate the create Booking response schema")
    public void tc02_validateSchema() {

        response = HttpsMethods.post();
        response.then().assertThat().body("bookingid",Matchers.notNullValue());
        //C:\Users\ashutoshsingh03\Documents\AshutoshKumarSingh\BookingApp\src\main\resources\postBookingresponse.json
        response.then().assertThat().body(matchesJsonSchemaInClasspath("postBookingresponse.json"));
    }

    @Test(description = " To validate the header of response ")
    public void tc03_validateHeaders() {
        log.info(" tc03_validateHeaders To validate the header of response ");
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");

    }

    @Test(description = " To validate the body of json response  ")

    public void tc04_validateBody() {
        log.info(" tc04_validateBody  To validate the body of json response ");
        ExtentTestManager.getTest().info(response.getBody().asPrettyString());
        log.info(" validate that object types are not null ");
        log.info(" response.getBody().asPrettyString()");
        Assert.assertNotNull(response.jsonPath().get("booking.firstname"), "First name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("booking.lastname"), "Last name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("booking.totalprice"), "price name should not be null");
        Assert.assertTrue(response.body().jsonPath().get("booking.depositpaid") instanceof Boolean);
    }
}
