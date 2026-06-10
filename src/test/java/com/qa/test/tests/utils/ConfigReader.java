package com.qa.test.tests.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    /**
     * Dynamically loads a target properties file and retrieves the value of the specified key.
     * * @param fileName The name of the properties file (excluding the ".properties" extension).
     * @param key      The configuration or test data key to search for.
     * @return The value associated with the key as a String.
     */
    public static String getProperty(String fileName, String key) {
        // Create a temporary configuration container for this specific read operation
        Properties customProps = new Properties();
        
        // Use try-with-resources to automatically open and safely close the file stream
        try (FileInputStream input = new FileInputStream("src/test/resources/" + fileName + ".properties")) {
            // Load the key-value pairs from the target file into memory
            customProps.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            // Crash the test execution immediately if the requested file is missing or corrupted
            throw new RuntimeException("Error: Unable to load the requested file: " + fileName + ".properties");
        }
        
        // Extract and return the value matching the requested key
        return customProps.getProperty(key);
    }
}

