package com.qa.test.tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Supplier;

import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;

public abstract class BaseTest {
    // On le met en 'protected' pour que les classes enfants (comme LoginTest) puissent y accéder
    protected WebDriver driver;

    //Screenshot method 
    public void takeScreenshot(String attachmentName) {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(attachmentName, new ByteArrayInputStream(screenshot));
        }
    }

    // Custom assertion method with integrated screenshot capture for better Allure reporting
   public void verifyEquals(String expected, Supplier<String> actualExpression, String message) {
    String actual = "";
        try {
            // execute the supplier to get the actual value, this allows us to capture Selenium exceptions if the element is not found
            actual = actualExpression.get(); 

            Assertions.assertEquals(expected, actual, message);
            
            // Screenshot in success case
            takeScreenshot(" PASS: " + message + " [Expected: " + expected + "]");

        } catch (AssertionError e) {
            // Screenshot if the element is found but the text is incorrect
            takeScreenshot(" FAIL (Assertion): " + message + " [Expected: " + expected + " | Actual: " + actual + "]");
            throw e;
        } catch (Exception e) {
                // Screenshot if Selenium throws an exception (like NoSuchElementException or Timeout)
            takeScreenshot(" CRASH (Selenium Error): " + message + " - Element not found or Timeout !");
            throw e; 
        }
    }

    // --- CUSTOM ASSERT TRUE ---
    public void verifyTrue(Supplier<Boolean> conditionExpression, String message) {
        boolean condition = false;
        try {
            // execute the supplier to evaluate the condition, this allows us to capture Selenium exceptions if the element is not found
            condition = conditionExpression.get();

            // Perform the standard JUnit assertion
            Assertions.assertTrue(condition, message);
            
            // Take a screenshot on success
            takeScreenshot("PASS: " + message);

        } catch (AssertionError e) {
            // Captured if the method returned 'false' instead of 'true'
            takeScreenshot("FAIL (Assertion): " + message + " [Expected: true | Actual: false]");
            throw e;
        } catch (Exception e) {
            // Captured if Selenium throws an exception (like NoSuchElementException or Timeout)
            takeScreenshot("CRASH (Selenium Error): " + message + " - Element not found or Timeout !");
            throw e; 
        }
    }



    private boolean isTestPassed = false;
    @BeforeEach
    public void setUp() {
        // Initialisation du driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Reset the flag to false at the beginning of every test
        isTestPassed = false;
    }

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
                // 🔒 This block GUARANTEES the browser closes, no matter what happens above
                driver.quit();
                System.out.println("🔒 Browser session closed safely.");
            }
        }
    }

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
