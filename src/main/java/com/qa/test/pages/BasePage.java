package com.qa.test.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.test.commonPage.Navbar;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    //Constructeur commun a toute les pages
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // On initialise un Explicit Wait par défaut de 10 secondes
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //to permit access to the Navbar from any page that extends BasePage
    public Navbar getNavbar() {
        return new Navbar(driver);
    }

    // Méthode globale pour attendre qu'un élément soit visible et le retourner
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Méthode globale pour attendre qu'un élément soit cliquable
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Encapsulation de l'action de cliquer avec attente préalable
    protected void click(By locator) {
        waitForClickability(locator).click();
    }

    // Encapsulation de l'action de saisir du texte avec attente préalable
    protected void writeText(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Attend que l'URL contienne un texte spécifique (ex: /dashboard ou /home)
    protected boolean waitForUrlToContain(String expectedFraction) {
    return wait.until(ExpectedConditions.urlContains(expectedFraction));
}

}
