package com.qa.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.test.common.BasePage;

public class LoginPage extends BasePage{

    //Element locators de la page de login
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-btn");
    private By errorMessage = By.xpath("//p[contains(text(), 'Invalid email or password')]");

    //  Le Constructeur qui passe le driver à la classe parente (BasePage)
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //  Les Actions (Méthodes métier)
    public void login(String email, String password) {
        writeText(emailField, email);
        writeText(passwordField, password);
        click(loginButton);
    }

    public HomePage loginSuccessfully(String email, String password) {
        login(email, password);
        // Après un login réussi, on s'attend à être redirigé vers la page d'accueil, donc on retourne une instance de HomePage
        return new HomePage(driver);
    }

    public String getErrorMessageText() {
        return waitForVisibility(errorMessage).getText();
    }

}
