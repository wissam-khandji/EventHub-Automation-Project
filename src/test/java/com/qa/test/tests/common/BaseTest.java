package com.qa.test.tests.common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    protected WebDriver driver;

    /**
     * Utility method to capture a screenshot and attach it to Allure with a custom name.
     * @param attachmentName
     */
    public void takeScreenshot(String attachmentName) {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(attachmentName, new ByteArrayInputStream(screenshot));
        }
    }

   /**
    * Verifies that two strings are equal and takes a screenshot based on the result.
    * @param expected The expected string value.
    * @param actual The actual string value obtained from the application.
    * @param message A custom message describing the assertion being made, used for both success and failure cases. 
    */
    public void verifyEquals(String expected, String actual, String message) {
        try {
            // Standard JUnit assertion
            Assertions.assertEquals(expected, actual, message);

            // SUCCESS CASE: Take a screenshot to document the pass
            takeScreenshot(" PASS: " + message + " [Expected: " + expected + "]");

        } catch (AssertionError e) {
            //FAIL (Wrong text): Take a specific assertion failure screenshot
            takeScreenshot(" FAIL (Assertion): " + message + " [Expected: " + expected + " | Actual: " + actual + "]");
            throw e; 
        }
    }

    /**
     * Verifies that a condition is true and takes a screenshot based on the result.
     * @param condition The boolean condition to verify.
     * @param message A custom message describing the assertion being made, used for both success and failure cases.
     */
    public void verifyTrue(boolean condition, String message) {
        try {
            // Standard JUnit assertion
            Assertions.assertTrue(condition, message);
            
            // SUCCESS CASE: Take a screenshot to document the pass
            takeScreenshot("PASS: " + message);

        } catch (AssertionError e) {
            // FAIL (False condition): Take a specific assertion failure screenshot
            takeScreenshot("FAIL (Assertion): " + message + " [Expected: true | Actual: false]");
            throw e; 
        }
    }



    

    /**
     * This method runs before each test to set up the WebDriver and maximize the browser window. It also resets the test pass flag to false at the start of each test.
     */
    private boolean isTestPassed = false;
    @BeforeEach
    public void setUp() {
        // driver initialize 
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Reset the flag to false at the beginning of every test
        isTestPassed = false;
    }

    /**
     * This method runs after each test to clean up resources and take a screenshot in case of failure.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            try {
                // If the test finished without setting the flag to true, it means it crashed or failed!
                if (!isTestPassed) {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("❌ CRASH SCREENSHOT (Automatic)", new ByteArrayInputStream(screenshot));
                    System.out.println("📸 Global failure screenshot successfully attached to Allure!");
                }
            } catch (Exception e) {
                System.out.println("❌ Failed to capture automatic screenshot: " + e.getMessage());
            } finally {
                //  This block GUARANTEES the browser closes, no matter what happens above
                driver.quit();
                System.out.println(" Browser session closed safely.");
            }
        }
    }

    /**
     * Generates an Allure report automatically after all tests have run.
     */
    @AfterAll
    public static void generateAllureReportAutomatically() {
        System.out.println("====== AUTOMATIC ALLURE REPORT GENERATION ======");
        try {
            // This line executes the generation command directly in your Ubuntu system background
            Process process = Runtime.getRuntime().exec("allure generate target/allure-results --clean -o target/allure-report");
            
            // We wait for the generation process to fully complete before finishing
            process.waitFor();
            System.out.println("======  REPORT SUCCESSFULLY REGENERATED IN target/allure-report ======");
        } catch (IOException | InterruptedException e) {
            System.err.println(" Error during automatic Allure report generation: " + e.getMessage());
        }
    }
}
