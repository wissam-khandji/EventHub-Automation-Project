package com.qa.test.common;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.test.commonPage.Navbar;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    //Common constructor for all page classes that initializes the WebDriver and WebDriverWait
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // On initialise un Explicit Wait par défaut de 10 secondes
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //to permit access to the Navbar from any page that extends BasePage
    public Navbar getNavbar() {
        return new Navbar(driver);
    }

    // Common method to wait for an element to be visible and return it
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Common method to wait for an element to be clickable
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Common method to click an element with a prior wait
    protected void click(By locator) {
        waitForClickability(locator).click();
    }

    // Common method to write text into an element with a prior wait
    protected void writeText(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Common method to wait for the URL to contain a specific text (e.g., /dashboard or /home)
    protected boolean waitForUrlToContain(String expectedFraction) {
    return wait.until(ExpectedConditions.urlContains(expectedFraction));
}

}
