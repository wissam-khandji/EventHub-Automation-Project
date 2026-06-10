package com.qa.test.commonPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.test.common.BasePage;
import com.qa.test.pages.EventsPage;

/**
 * The Navbar class represents the navigation bar component of the web application. It extends the BasePage class to inherit common functionalities and provides specific methods for interacting with the navigation bar elements, such as clicking on tabs and buttons. By encapsulating the navigation bar interactions within this class, we promote code reuse and maintainability, allowing any page that includes the Navbar to easily access its features without duplicating code. This design also keeps the page classes focused on their specific interactions while still being able to utilize common navigation features through the Navbar.
 */
public class Navbar extends BasePage {
    // Locators for navigation bar elements
    private By homeTab = By.cssSelector("#nav-home");
    private By eventsTab = By.cssSelector("#nav-events");
    private By myBookingsTab = By.cssSelector("#nav-bookings");
    private By logoutButton = By.id("logout-btn");

    /**
     * Constructor for Navbar that initializes the WebDriver and waits for the navigation bar to be visible. This ensures that the navigation bar is fully loaded and ready for interaction before any methods are called on it. By calling the waitForVisibility method on a key element of the navigation bar (e.g., eventsTab), we can confirm that the entire navigation bar is present and functional, preventing potential issues with interacting with elements that may not yet be available in the DOM.
     * @param driver The WebDriver instance to use for interacting with the web page.
     */
    public Navbar(WebDriver driver) {
        super(driver);
        // Wait for the navigation bar to be visible to ensure it's loaded before interacting with it
        waitForVisibility(eventsTab);
    }

    /**
     * Method to click on the "Events" tab in the navigation bar. This method uses the common click method from BasePage to perform the click action after waiting for the element to be clickable. After clicking on the "Events" tab, it returns a new instance of the EventsPage class, allowing for a seamless transition to the Events page and enabling further interactions specific to that page.
     * @return An instance of the EventsPage class, initialized with the current WebDriver, after clicking on the "Events" tab.
     */
    public EventsPage clickOnEventsTab() {
        click(eventsTab);
        return new EventsPage(driver);
    }

    //not yet configured
    public void clickOnMyBookingsTab() {
        click(myBookingsTab);
    }

    //not yet configured
    public void clickHomeTab() {
        click(homeTab);
    }

    //not yet configured
    public void clickOnLogoutButton() {
        click(logoutButton);
    }

}
