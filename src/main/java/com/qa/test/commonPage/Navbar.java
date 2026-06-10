package com.qa.test.commonPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.test.common.BasePage;
import com.qa.test.pages.EventsPage;

public class Navbar extends BasePage {
    // Locators for navigation bar elements
    private By homeTab = By.cssSelector("#nav-home");
    private By eventsTab = By.cssSelector("#nav-events");
    private By myBookingsTab = By.cssSelector("#nav-bookings");
    private By logoutButton = By.id("logout-btn");

    public Navbar(WebDriver driver) {
        super(driver);
        // Wait for the navigation bar to be visible to ensure it's loaded before interacting with it
        waitForVisibility(eventsTab);
    }

    // Click on the "Events" tab and return a new instance of EventsPage
    public EventsPage clickOnEventsTab() {
        click(eventsTab);
        return new EventsPage(driver);
    }

    // Méthode pour cliquer sur l'onglet "My Bookings"
    public void clickOnMyBookingsTab() {
        click(myBookingsTab);
    }

    // Méthode pour cliquer sur l'onglet "Home"
    public void clickHomeTab() {
        click(homeTab);
    }

    // Méthode pour cliquer sur le bouton "Logout"
    public void clickOnLogoutButton() {
        click(logoutButton);
    }

}
