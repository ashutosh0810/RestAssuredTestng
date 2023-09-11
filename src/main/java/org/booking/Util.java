package org.booking;

import com.github.javafaker.Faker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Util {

    // This will contains generics functions and reusable functions
    private static Properties properties;


    private static FileInputStream fis;

    public static String readConfig(String key)  {

        try {
            // streaming the file bytes
            fis = new FileInputStream(Commons.configFilepath);
            properties = new Properties();
            //loading the file
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty(key);
    }


}
