package org.booking;

import io.restassured.response.Response;
import Pojo.Postrequest.Booking;
import Pojo.Postrequest.Bookingdates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import reporting.ExtentTestManager;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import java.io.File;

import static io.restassured.RestAssured.*;

public class HttpsMethods {
    private static Logger log = LogManager.getLogger(HttpsMethods.class);
    static String token;
    public static Bookingdates bookingDates =
            new Bookingdates(Commons.getCheckIn(), Commons.getCheckOut());
    public static Booking booking =
            new Booking(Commons.getFirstName(), Commons.getLastName(), Commons.getTotalprice(),
                    Commons.getdepositPaid(), bookingDates, Commons.getAddNeeds());

    public static String authtoken() {
        //https://restful-booker.herokuapp.com/ping
        log.info(Util.readConfig("baseUri") + Util.readConfig("authpath"));
        ExtentTestManager.getTest().info("authtoken END POINT IS " + Util.readConfig("baseUri") + Util.readConfig("authpath"));
        ExtentTestManager.getTest().info(System.getProperty("user.dir") + Commons.tokenJsonfile);
        System.out.println(" PATH IS " + System.getProperty("user.dir") + Commons.tokenJsonfile);
        token = given().
                headers(Commons.getHeaders()).
                body(new File(System.getProperty("user.dir") + Commons.tokenJsonfile)).
                when().
                post(Util.readConfig("authpath")).jsonPath().get("token");

        return token;
    }

    public static Response get(String path, String id) {
        //https://restful-booker.herokuapp.com/auth
        //https://restful-booker.herokuapp.com/booking/939
        //https://restful-booker.herokuapp.com/booking
        log.info(" end point is " +
                Util.readConfig("baseUri") + path + id);
        ExtentTestManager.getTest().info(" GET end point is " +
                Util.readConfig("baseUri") + path + id);
        return given().log().all().
                when().
                get(path + id);


    }

    public static Response post() {
        ExtentTestManager.getTest().info(" URI >>>" +
                Util.readConfig("baseUri") + Util.readConfig("path"));
        return given().log().all().
                headers(Commons.getHeaders()).
                body(booking).
                when().log().all().
                post(Util.readConfig("path"));

    }

    public static Response put() {
        ExtentTestManager.getTest().
                info(" URI " +
                        Util.readConfig("baseUri") + "" +
                        Util.readConfig("path") + Commons.bookingid);
        Commons.getHeaders().put("Cookie", "token=" + authtoken());
        // need to change here the booking object
        ExtentTestManager.getTest().info(" Token is " + authtoken());
        ExtentTestManager.getTest().info(" Booking id is " + Commons.bookingid);
        return given().
                headers(Commons.getHeaders()).
                body(booking).
                when().
                put(Util.readConfig("path") + Commons.bookingid);

    }

    public static Response delete(String id) {
        ExtentTestManager.getTest().
                info(" URI IS >>> " +
                        Util.readConfig("baseUri") + "" + Util.readConfig("path") + id);
        System.out.println(Commons.getHeaders());
        return given().
                headers(Commons.getHeaders()).
                when().
                delete(Util.readConfig("path") + id);

    }

    public static Response patch(int id) {

        ExtentTestManager.getTest().info(" PATCH URL is " +
                Util.readConfig("baseUri") + Util.readConfig("path") + id);
        System.out.println(Commons.getHeaders());
        log.info(" PATCH URL is " +
                Util.readConfig("baseUri") + Util.readConfig("path") + id);
        String filePath;
        try {
            filePath = System.getProperty("user.dir") +
                    Util.readConfig("partial_editFile");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(" Patch body path is " + filePath);

        return given().
                headers(Commons.getHeaders()).
                body(new File(filePath)).
                when().
                patch(Util.readConfig("path") + id);

    }


}
