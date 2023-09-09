package org.booking;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Random;

// This class will store the common values and constant values that can be used across the framework
public class Commons {


    // Using faker class for getiing the random values ;

    private static Faker faker = new Faker();
    private static final Random RANDOM = new Random();

    public static String configFilepath = "src\\main\\resources\\testConfig.properties";
    public static String tokenJsonpath="\\src\\main\\resources\\token.json";
    public static String author = "ashutosh ";
    public static String reportFilename = "Booking Api Automation";
    public static int bookingid;
    // storing Headers for the all the request whereever it is required
    public static HashMap<String, String> headersMap = new HashMap<>();


    // Generating fake datas .
    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static boolean depositpaid = faker.bool().bool();
    public static int totalprice = faker.number().numberBetween(0, 12);
    static String[] needs = {"Breakfast", "Extra bed", "Airport shuttle", "Late check-out"};
    public static String addNeeds = needs[RANDOM.nextInt(needs.length)];

    public static String checkIn = "2018-01-01";
    public static String checkOut = "2028-01-01";

    // Intializing the headers and value we are taking via Config file
    public static HashMap<String, String> getHeaders() {
        headersMap.put("Content-Type", Util.readConfig("Content-Type"));
        headersMap.put("Accept", Util.readConfig("Accept"));
        return headersMap;
    }

}
