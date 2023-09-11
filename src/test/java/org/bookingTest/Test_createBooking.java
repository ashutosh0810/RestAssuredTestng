package org.bookingTest;

import Pojo.Postrequest.Booking;
import Pojo.postResponse.CreateBookingResponse;
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
// This contains test cases for the post booking or Create booking
public class Test_createBooking extends BaseTest {

    private Logger log = LogManager.getLogger(Test_createBooking.class);
    private Response response;
    CreateBookingResponse createBookingResponse;
    Booking booking;


    @Test(description = "Post request for creating the booking ")
    public void tc01_createbooking() throws IOException {
        log.info(" **** tc01_createbooking() *****");
        ExtentTestManager.getTest().info(" validating the status of tc01_createbooking");
        response = HttpsMethods.post();
        log.info(" POST bODY is " + response.getBody().asPrettyString());
        ExtentTestManager.getTest().info("RESPONSE STATUS CODE " + response.getStatusCode());
        ExtentTestManager.getTest().info(" RESPONSE BODY is " + response.getBody().asPrettyString());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        // Storing booking id
        ExtentTestManager.getTest().info(" Booking id created >>>>" + response.jsonPath().get("bookingid"));
        Commons.bookingid = response.jsonPath().get("bookingid");
    }

    @Test(description = "To validate the create Booking response schema")
    public void tc02_validateSchema() {
        log.info("tc02_validateSchema ");
        ExtentTestManager.getTest().info(" Validating the entire schema ");
        response.then().assertThat().body("bookingid", Matchers.notNullValue());
        response.then().assertThat().body(matchesJsonSchemaInClasspath("jsonBody/postBookingresponse.json"));
    }

    @Test(description = " To validate the header of response ")
    public void tc03_validateHeaders() {
        log.info(" tc03_validateHeaders To validate the header of response ");
        log.info(" Headers are >>>> " + response.getHeaders());
        ExtentTestManager.getTest().info(" Header info for create booking" + response.getHeaders());
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");

    }

    @Test(description = " To validate the body of json response  ")

    public void tc04_BasicCheck_validateBody() {
        log.info(" tc04_validateBody  To validate the body of json response ");
        ExtentTestManager.getTest().info(response.getBody().asPrettyString());
        log.info(" validate that object types are not null ");
        log.info(" response.getBody().asPrettyString()");
        Assert.assertNotNull(response.jsonPath().get("booking.firstname"), "First name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("booking.lastname"), "Last name should not be null");
        Assert.assertNotNull(response.body().jsonPath().get("booking.totalprice"), "price name should not be null");
        Assert.assertTrue(response.body().jsonPath().get("booking.depositpaid") instanceof Boolean);
    }

    @Test(description = " This test case will validate every object as we " +
            "have passed while creating the post request ")
    public void tc05_validateBody() {
        createBookingResponse = response.as(CreateBookingResponse.class);
        String Response_bookingId = createBookingResponse.getBookingid();
        String res_FN = createBookingResponse.getBooking().getFirstname();
        String res_LN = createBookingResponse.getBooking().getLastname();
        String res_addNeed = createBookingResponse.getBooking().getAdditionalneeds();
        boolean res_depositpaid = createBookingResponse.getBooking().isDepositpaid();
        String res_checkinDate = createBookingResponse.getBooking().getBookingdates().getCheckin();
        String res_checkoutDates = createBookingResponse.getBooking().getBookingdates().getCheckout();
        log.info(" Response fetched using pojo  ");
        log.info(res_FN + "  " + res_LN + "  " + res_addNeed + "  " + res_depositpaid + "  ");
        ExtentTestManager.getTest().info(res_FN + " , " + res_LN + "  , " + res_addNeed + " , " + res_depositpaid + " ,  ");

        try{
            booking= HttpsMethods.booking;
            System.out.println(booking.getFirstname());
            System.out.println(booking.getLastname());
            System.out.println(booking.getTotalprice());


        }
         catch (Exception e)
         {
             System.out.println(e.getLocalizedMessage());
         }
       // validating all body of Request and response
        Assert.assertEquals(res_FN, booking.getFirstname());
        Assert.assertEquals(res_LN, booking.getLastname());
        Assert.assertEquals(res_addNeed, booking.getAdditionalneeds());
        Assert.assertEquals(res_depositpaid, booking.isDepositpaid());
        Assert.assertEquals(res_checkinDate, booking.getBookingdates().getCheckin());
        Assert.assertEquals(res_checkoutDates, booking.getBookingdates().getCheckout());

    }
}
