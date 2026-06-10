package com.qa.test.tests.sandbox;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.test.pages.HomePage;
import com.qa.test.pages.LoginPage;
import com.qa.test.tests.common.BaseTest;
import com.qa.test.tests.utils.ConfigReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
   
    private LoginPage loginPage;

    @BeforeEach
    public void initPage() {
        // driver initialization is handled in BaseTest, we just need to navigate to the login page and initialize the LoginPage object here
        // get the login page URL from the config properties file
        String urlApp = ConfigReader.getProperty("config", "url.eventhubLogin");
        driver.get(urlApp);
        
        // initialize the LoginPage object to be used in the test methods
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginInvalideAfficheErreur() {
       loginPage.login("test_invalide@email.com", "MauvaisMotDePasse");

         String expectedErrorMessage = "Invalid email or password";
         String actualErrorMessage = loginPage.getErrorMessageText();

         Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "error message is not correct");
    }

    @Test
    public void testLoginWorksCorrectly() {
        // we are using valid credentials stored in the config properties file for this test case
        HomePage homePage = loginPage.loginSuccessfully("testwissam@test.com", "Test1234!");
        // verify that after a successful login, we are redirected to the home page by checking the current URL
        String expectedUrl = ConfigReader.getProperty("config", "url.eventhubHome"); 
        String actualUrl = driver.getCurrentUrl();

        Assertions.assertEquals(expectedUrl, actualUrl, "The destination URL is not the expected one");

    }
}