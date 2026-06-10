package com.qa.test.tests.E2E;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.qa.test.pages.EventDetailPage;
import com.qa.test.pages.EventsPage;
import com.qa.test.pages.HomePage;
import com.qa.test.pages.LoginPage;
import com.qa.test.tests.common.BaseTest;
import com.qa.test.tests.utils.ConfigReader;
import io.qameta.allure.Allure;

@Tag("E2E")
public class T001_E2E_BookAnEventTest extends BaseTest{
    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testE2E_001_SuccessfulBookingAndCancellation() {

        // Target properties file name for this specific test case
        final String dataFile = "T001-E2E";

        // ---STEP 1: Login---
        Allure.step("Step 1: Access Login page ");
        String urlApp = ConfigReader.getProperty("config", "url.eventhubLogin");
        driver.get(urlApp);


        //---STEP 2: Perform Login---
        // Declaring the variable outside the lambda to assign it inside the Allure step closure
        final HomePage[] homePage = new HomePage[1]; 
        Allure.step("Step 2: Complete credential as per test data then click on login button", () -> {
            // Fetching credentials from the test data properties file
            String email = ConfigReader.getProperty(dataFile, "user.email");
            String password = ConfigReader.getProperty(dataFile, "user.password");
            
            homePage[0] = loginPage.loginSuccessfully(email, password);
        
            // ASSERTION: Check URL redirection after a successful login workflow
            verifyEquals(ConfigReader.getProperty("config", "url.eventhubHome"), () -> driver.getCurrentUrl(), "Redirection URL after login is incorrect");
        });

        // ---STEP 3: Click on Events---
        final EventsPage[] eventsPage = new EventsPage[1];
        Allure.step("Step 3: Click on Events tab", () -> {
            eventsPage[0] = homePage[0].getNavbar().clickOnEventsTab();

            verifyEquals(ConfigReader.getProperty("config", "url.EventsTab"), () ->driver.getCurrentUrl(), "Redirection URL after clicking on Events tab is incorrect");
        }); 

        //Step 4: Book an event
        final EventDetailPage[] eventDetailPage = new EventDetailPage[1];
        Allure.step("Step 4: Click on the button book now of the event as per test data", () -> {
            // Fetching the target event name dynamically
            String eventName = ConfigReader.getProperty(dataFile, "event.name");
            eventDetailPage[0] = eventsPage[0].bookEventByName(eventName);

            verifyEquals(eventName, () -> eventDetailPage[0].getTitleEventName(eventName), "Event name is not correct");
            
            // Fetching all expected UI values dynamically from the properties file
            String expectedDate = ConfigReader.getProperty(dataFile, "event.date"); 
            String expectedTime = ConfigReader.getProperty(dataFile, "event.time");
            String expectedVenue = ConfigReader.getProperty(dataFile, "event.venue");
            String expectedPrice = ConfigReader.getProperty(dataFile, "event.price");

        // Grouped assertions using JUnit 5 Assertions.assertAll
        Assertions.assertAll("Verify that all event details are displayed correctly", 
            // 💡 We pass the dynamic properties directly into both the locator method and the assertion
            () -> verifyEquals(expectedDate, () -> eventDetailPage[0].getEventDetailText(expectedDate), "Date verification"),
            () -> verifyEquals(expectedTime, () -> eventDetailPage[0].getEventDetailText(expectedTime), "Hour verification"),
            () -> verifyEquals(expectedVenue, () -> eventDetailPage[0].getEventDetailText(expectedVenue), "Venue verification"),
            () -> verifyEquals(expectedPrice, () -> eventDetailPage[0].getEventDetailText(expectedPrice), "Ticket price verification")
        );
        });

        //Step 5: Confirm Booking
        Allure.step("Step 5: Complete booking field as per test data then click confirm booking button", () -> {
            // Fetching user details required for the checkout/booking form
            String bookingName = ConfigReader.getProperty(dataFile, "booking.name");
            String bookingEmail = ConfigReader.getProperty(dataFile, "user.email");
            String bookingPhone = ConfigReader.getProperty(dataFile, "booking.phone");

            eventDetailPage[0].completeBookingForm(bookingName, bookingEmail, bookingPhone);

            verifyEquals("Booking Confirmed! 🎉", () -> eventDetailPage[0].getBookingConfirmationMessage(), "Booking confirmation message is not displayed !");
        });
}                                                                       

}
