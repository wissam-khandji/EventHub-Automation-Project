package com.qa.test.common;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.test.commonPage.Navbar;
/**
 * BasePage is an abstract class that serves as the parent for all page classes in the test framework. It provides common methods and utilities for interacting with web elements, waiting for conditions, and accessing shared components like the Navbar. By centralizing these functionalities, BasePage promotes code reuse and maintainability across all page objects.    
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor for BasePage that initializes the WebDriver and WebDriverWait instances. The WebDriver is passed as a parameter to allow for flexibility in test setup, while the WebDriverWait is initialized with a default timeout of 10 seconds for waiting on conditions throughout the page classes.   
     * @param driver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // On initialise un Explicit Wait par défaut de 10 secondes
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Provides access to the Navbar component, allowing page classes to interact with the navigation bar without needing to re-implement its functionality. This promotes code reuse and keeps page classes focused on their specific interactions while still being able to utilize common navigation features.   
     * @return An instance of the Navbar class, initialized with the current WebDriver.
     */
    public Navbar getNavbar() {
        return new Navbar(driver);
    }

    /**
     * Waits for an element to be visible and returns it.
     * @param locator The locator for the element to wait for.
     * @return The visible WebElement.
     */
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable and returns it.
     * @param locator The locator for the element to wait for.
     * @return The clickable WebElement.
     */
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Clicks an element after waiting for it to be clickable.
     * @param locator The locator for the element to click.
     */
    protected void click(By locator) {
        waitForClickability(locator).click();
    }

    /**
     * Writes text into an element after waiting for it to be visible.
     * @param locator The locator for the element to write text into.
     * @param text The text to write into the element.
     */
    protected void writeText(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Waits for the URL to contain a specific text (e.g., /dashboard or /home).
     * @param expectedFraction The fraction of the URL to wait for.
     * @return true if the URL contains the expected fraction, false otherwise.
     */
    protected boolean waitForUrlToContain(String expectedFraction) {
    return wait.until(ExpectedConditions.urlContains(expectedFraction));
}

}
