package com.qa.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.test.common.BasePage;

public class EventsPage extends BasePage {

    //Element locators Events page
    private By eventPageTitle = By.xpath("//h1[contains(text(), 'Upcoming Events')]");

    /**
     * Constructor for EventsPage that initializes the WebDriver and waits for the event page title to be visible. This ensures that the events page is fully loaded and ready for interaction before any methods are called on it. By calling the waitForVisibility method on a key element of the events page (e.g., eventPageTitle), we can confirm that the entire page is present and functional, preventing potential issues with interacting with elements that may not yet be available in the DOM.
     * @param driver The WebDriver instance to use for interacting with the web page.
     */
    public EventsPage(WebDriver driver) {
        super(driver);

        // wait for the event page title to be visible to ensure the page is loaded before interacting with it
        waitForVisibility(eventPageTitle);
    }

    /**
     * Method to book an event by its name.
     * @param eventName The name of the event to book.
     * @return An instance of the EventDetailPage class, initialized with the current WebDriver.
     */
    public EventDetailPage bookEventByName(String eventName) {
        
        By bookNowButton = By.xpath("//h3[contains(text(), '" + eventName + "')]/ancestor::article//a[@data-testid='book-now-btn']");
        click(bookNowButton);
        return new EventDetailPage(driver);
    }

}
