package org.booking;

import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.Faker;
import reporting.ExtentTestManager;

import java.util.HashMap;
import java.util.Random;

// This class will store the common values and constant values that can be used across the framework
public class Commons {

    // Using faker class for geting the random values ;
    private static Faker faker = new Faker();
    private static final Random RANDOM = new Random();

    public static String configFilepath = "src\\main\\resources\\testConfig.properties";
    public static String tokenJsonfile = "\\src\\main\\resources\\jsonBody\\Authtoken.json";

    public static int bookingid;

    // storing Headers for the all the request whereever it is required
    public static HashMap<String, String> headersMap = new HashMap<>();
    static String[] needs = {"Breakfast", "Extra bed", "Airport shuttle", "Late check-out"};

    // Intializing the headers and value we are taking via Config file
    public static HashMap<String, String> getHeaders() {
        headersMap.put("Content-Type", Util.readConfig("Content-Type"));
        headersMap.put("Accept", Util.readConfig("Accept"));
        return headersMap;
    }

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().firstName();
    }

    public static int getTotalprice() {
        return faker.number().numberBetween(0, 12);
    }

    public static String getAddNeeds() {
        return needs[RANDOM.nextInt(needs.length)];
    }

    public static boolean getdepositPaid() {
        return faker.bool().bool();
    }

    public static String getCheckIn() {
        return Util.readConfig("checkin");
    }

    public static String getCheckOut() {
        return Util.readConfig("checkout");
    }



}
