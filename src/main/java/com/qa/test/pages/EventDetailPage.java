package com.qa.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    


    public EventDetailPage(WebDriver driver) {
        super(driver);
        
    }

    public String getTitleEventName(String eventName) {
        By eventNameLocator = By.xpath("//span[contains(text(), '" + eventName + "')]");
        waitForVisibility(eventNameLocator);
        return driver.findElement(eventNameLocator).getText();
    }

    public String getEventDetailText(String expectedDetailText) {
       String dynamicInfoXpath = String.format("//main//*[contains(text(), '%s')]", expectedDetailText);
        return driver.findElement(By.xpath(dynamicInfoXpath)).getText().trim();    
    }

    public String getBookingConfirmationMessage() {
        waitForVisibility(bookingConfirmationMessage);
        return driver.findElement(bookingConfirmationMessage).getText().trim();
    }


    public void completeBookingForm(String fullName, String email, String phone) {
        //click(plusButton);
        writeText(fullNameField, fullName);
        writeText(emailField, email);
        writeText(phoneField, phone);
        click(confirmBookingButton);
        waitForVisibility(bookingConfirmationMessage);
    }
}
