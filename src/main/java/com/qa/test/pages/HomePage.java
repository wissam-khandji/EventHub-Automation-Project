package com.qa.test.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.test.common.BasePage;

/**
 * The HomePage class represents the home page of the web application. It extends the BasePage class to inherit common functionalities and provides specific methods for interacting with the home page elements.
 */
public class HomePage extends BasePage {

    //Element locators for the home page
    private By FeaturedEvents = By.xpath("//h2[contains(text(), 'Featured Events')]");

    /**
     * Constructor for HomePage that initializes the WebDriver and waits for the Featured Events header to be visible. This ensures that the home page is fully loaded and ready for interaction before any methods are called on it. By calling the waitForVisibility method on a key element of the home page (e.g., FeaturedEvents), we can confirm that the entire page is present and functional, preventing potential issues with interacting with elements that may not yet be available in the DOM.
     * @param driver The WebDriver instance to use for interacting with the web page.
     */
    public HomePage(WebDriver driver) {
        super(driver);
        // wait for the Featured Events header to be visible to ensure the page is loaded before interacting with it
        waitForVisibility(FeaturedEvents);
    }

    /**
     * Method to verify that the Featured Events header is displayed.
     * @return true if the header is displayed, false otherwise.
     */
    public boolean isWelcomeHeaderDisplayed() {
        return waitForVisibility(FeaturedEvents).isDisplayed();
    }
}
