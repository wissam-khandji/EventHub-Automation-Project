package com.qa.test.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.test.common.BasePage;

public class HomePage extends BasePage {

    // Locators spécifiques à la page d'accueil
    private By FeaturedEvents = By.xpath("//h2[contains(text(), 'Featured Events')]");

    public HomePage(WebDriver driver) {
        super(driver);
        // wait for the Featured Events header to be visible to ensure the page is loaded before interacting with it
        waitForVisibility(FeaturedEvents);
    }

    //Method to verify that the welcome header is displayed
    public boolean isWelcomeHeaderDisplayed() {
        return waitForVisibility(FeaturedEvents).isDisplayed();
    }
}
