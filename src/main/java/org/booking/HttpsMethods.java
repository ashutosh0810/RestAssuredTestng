package org.booking;

import io.restassured.response.Response;
import Pojo.Booking;
import Pojo.Bookingdates;
import reporting.ExtentTestManager;


import java.io.File;

import static io.restassured.RestAssured.*;

public class HttpsMethods {
    static String token;
    private static Bookingdates bookingDates = new Bookingdates(Commons.checkIn, Commons.checkOut);


    private static Booking booking =
            new Booking(Commons.firstName, Commons.lastName, Commons.totalprice, Commons.depositpaid, bookingDates, Commons.addNeeds);


    public static String authtoken() {

        System.out.println();
        token = given().baseUri(Util.readConfig("baseUri")).headers(Commons.getHeaders()).
                body(new File(System.getProperty("user.dir") + Commons.tokenJsonpath)).
                when().post(Util.readConfig("authpath")).jsonPath().get("token");

        return token;
    }

     //https://restful-booker.herokuapp.com/ping

    public static Response getPing()
    {
        return given().log().all().baseUri(Util.readConfig("baseUri")).when().
                get(Util.readConfig("pingpath"));
    }

    public static Response get(String id) {
        ExtentTestManager.getTest().info(" end point is " +
                Util.readConfig("baseUri") + Util.readConfig("path") + id);
        return
                given().log().all().baseUri(Util.readConfig("baseUri")).
                        when().get(Util.readConfig("path") + id);

    }

    public static Response post() {
        ExtentTestManager.getTest().info(" URI " +
                Util.readConfig("baseUri") + Util.readConfig("path"));
        return given().log().all().baseUri(Util.readConfig("baseUri")).
                headers(Commons.getHeaders()).
                body(booking).
                when().log().all().post(Util.readConfig("path"));

    }

    public static Response put() {
        ExtentTestManager.getTest().
                info(" URI " + Util.readConfig("baseUri") + "" + Util.readConfig("path") + Util.readConfig("bookingid"));
        Commons.getHeaders().put("Cookie", "token=" + authtoken());
        ExtentTestManager.getTest().info(" Token is " + authtoken());
        return given().headers(Commons.getHeaders()).baseUri(Util.readConfig("baseUri")).
                body(booking).
                when().
                put(Util.readConfig("path") + Util.readConfig("bookingid"));

    }


}
