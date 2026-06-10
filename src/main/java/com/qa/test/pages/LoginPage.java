package com.qa.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.test.common.BasePage;

/**
 * The LoginPage class represents the login page of the web application. It extends the BasePage class to inherit common functionalities and provides specific methods for interacting with the login page elements, such as entering credentials and submitting the login form. By encapsulating the login page interactions within this class, we promote code reuse and maintainability, allowing any page that requires login functionality to easily access its features without duplicating code. This design also keeps the page classes focused on their specific interactions while still being able to utilize common login features through the LoginPage.
 */
public class LoginPage extends BasePage{

    //Element locators de la page de login
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-btn");
    private By errorMessage = By.xpath("//p[contains(text(), 'Invalid email or password')]");

    /**
    * Constructor for LoginPage that initializes the WebDriver and waits for the email field to be visible. This ensures that the login page is fully loaded and ready for interaction before any methods are called on it. By calling the waitForVisibility method on a key element of the login page (e.g., emailField), we can confirm that the entire login form is present and functional, preventing potential issues with interacting with elements that may not yet be available in the DOM.
    * @param driver The WebDriver instance to use for interacting with the web page.
    * @return An instance of the LoginPage class, initialized with the current WebDriver, after ensuring the email field is visible.
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Method to perform the login action by entering the provided email and password into the respective fields and clicking the login button. This method abstracts the login process, allowing test cases to simply call this method with the desired credentials without needing to worry about the underlying interactions with the web elements. By centralizing the login logic within this method, we promote code reuse and maintainability across all tests that require login functionality.
     * @param email The email address to enter into the email field.
     * @param password The password to enter into the password field.
     */
    public void login(String email, String password) {
        writeText(emailField, email);
        writeText(passwordField, password);
        click(loginButton);
    }

    /**
     * Method to perform a successful login by entering the provided email and password and clicking the login button. This method abstracts the login process, allowing test cases to simply call this method with the desired credentials without needing to worry about the underlying interactions with the web elements. By centralizing the login logic within this method, we promote code reuse and maintainability across all tests that require login functionality.
     * @param email The email address to enter into the email field.
     * @param password The password to enter into the password field.
     * @return An instance of the HomePage class, initialized with the current WebDriver, after a successful login.
     */
    public HomePage loginSuccessfully(String email, String password) {
        login(email, password);
        // Après un login réussi, on s'attend à être redirigé vers la page d'accueil, donc on retourne une instance de HomePage
        return new HomePage(driver);
    }

    /**
     * Method to get the text of the error message displayed on the login page.
     * @return The text of the error message.
     */
    public String getErrorMessageText() {
        return waitForVisibility(errorMessage).getText();
    }

}
