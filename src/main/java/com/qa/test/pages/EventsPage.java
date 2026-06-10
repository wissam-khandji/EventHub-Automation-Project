package com.qa.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EventsPage extends BasePage {

    //Element locators Events page
    private By eventPageTitle = By.xpath("//h1[contains(text(), 'Upcoming Events')]");

    public EventsPage(WebDriver driver) {
        super(driver);

        // wait for the event page title to be visible to ensure the page is loaded before interacting with it
        waitForVisibility(eventPageTitle);
    }

    public EventDetailPage bookEventByName(String eventName) {
        
        By bookNowButton = By.xpath("//h3[contains(text(), '" + eventName + "')]/ancestor::article//a[@data-testid='book-now-btn']");
        click(bookNowButton);
        return new EventDetailPage(driver);
    }

}
