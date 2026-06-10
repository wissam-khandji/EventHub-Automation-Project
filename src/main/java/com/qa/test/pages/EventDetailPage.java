package com.qa.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.test.common.BasePage;

public class EventDetailPage extends BasePage {

    //Element locators for the booking form
    By fullNameField = By.id("customerName");
    By emailField = By.id("customer-email");
    By phoneField = By.id("phone");
    By confirmBookingButton = By.id("confirm-booking");
    By plusButton = By.xpath("//button[contains(text(), '+')]");
    By minusButton = By.xpath("//button[contains(text(), '-')]");
    By ticketCount = By.id("ticket-count");

    By bookingConfirmationMessage = By.xpath("//h3[contains(text(), 'Booking Confirmed! ')]");
    

    /**
     * Constructor for EventDetailPage that initializes the WebDriver.
     * @param driver The WebDriver instance to use for interacting with the web page.
     */
    public EventDetailPage(WebDriver driver) {
        super(driver);
        
    }

    /**
     * Method to get the name of the event from its title.
     * @param eventName The name of the event.
     * @return The text of the event name.
     */
    public String getTitleEventName(String eventName) {
        By eventNameLocator = By.xpath("//span[contains(text(), '" + eventName + "')]");
        waitForVisibility(eventNameLocator);
        return driver.findElement(eventNameLocator).getText();
    }

    /**
     * Method to get the text of an event detail.
     * @param expectedDetailText The text of the detail to find.
     * @return The text of the event detail.
     */
    public String getEventDetailText(String expectedDetailText) {
       String dynamicInfoXpath = String.format("//main//*[contains(text(), '%s')]", expectedDetailText);
        return driver.findElement(By.xpath(dynamicInfoXpath)).getText().trim();    
    }

    /**
     * Method to get the booking confirmation message.
     * @return The text of the booking confirmation message.
     */
    public String getBookingConfirmationMessage() {
        waitForVisibility(bookingConfirmationMessage);
        return driver.findElement(bookingConfirmationMessage).getText().trim();
    }

    /**
     * Method to complete the booking form.
     * @param fullName The full name of the customer.
     * @param email The email address of the customer.
     * @param phone The phone number of the customer.
     */
    public void completeBookingForm(String fullName, String email, String phone) {
        //click(plusButton);
        writeText(fullNameField, fullName);
        writeText(emailField, email);
        writeText(phoneField, phone);
        click(confirmBookingButton);
        waitForVisibility(bookingConfirmationMessage);
    }
}
