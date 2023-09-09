package org.booking;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Random;

public class Commons {

    private static Faker faker = new Faker();
    private static final Random RANDOM = new Random();

    public static String environment = "QA";
    public static String configFilepath = "src\\main\\resources\\testConfig.properties";
    //C:\Users\ashutoshsingh03\Documents\AshutoshKumarSingh\BookingApp\src\main\resources\token.json
    public static String tokenJsonpath="\\src\\main\\resources\\token.json";
    public static String author = "ashutosh ";
    public static String reportFilename = "Booking Api Automation";
    public static int bookingid;
    // Headers for the post
    public static HashMap<String, String> headersMap = new HashMap<>();


    // This will be the common data using fake
    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static boolean depositpaid = faker.bool().bool();
    public static int totalprice = faker.number().numberBetween(0, 12);
    static String[] needs = {"Breakfast", "Extra bed", "Airport shuttle", "Late check-out"};
    public static String addNeeds = needs[RANDOM.nextInt(needs.length)];

    public static String checkIn = "2018-01-01";
    public static String checkOut = "2028-01-01";

    public static HashMap<String, String> getHeaders() {
        headersMap.put("Content-Type", "application/json");
        headersMap.put("Accept", "*/*");
        return headersMap;
    }

}
