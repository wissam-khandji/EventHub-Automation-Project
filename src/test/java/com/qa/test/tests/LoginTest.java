package com.qa.test.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.test.pages.HomePage;
import com.qa.test.pages.LoginPage;
import com.qa.test.tests.utils.ConfigReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {
   
    private LoginPage loginPage;

    @BeforeEach
    public void initPage() {
        // Le driver a déjà été initialisé par BaseTest juste avant !
        // Récupération de l'URL depuis fichier config
        String urlApp = ConfigReader.getProperty("config", "url.eventhubLogin");
        driver.get(urlApp);
        
        // Initialisation de la page de login
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginInvalideAfficheErreur() {
       loginPage.login("test_invalide@email.com", "MauvaisMotDePasse");

         String expectedErrorMessage = "Invalid email or password";
         String actualErrorMessage = loginPage.getErrorMessageText();

         Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Le message d'erreur n'est pas celui attendu");
    }

    @Test
    public void testLoginWorksCorrectly() {
        // On utilise des données valides pour ce test
        HomePage homePage = loginPage.loginSuccessfully("khandji.w@gmail.com", "Test1234!");
        // Verification de la redirection vers la page d'accueil après login réussi
        String expectedUrl = ConfigReader.getProperty("config", "url.eventhubHome"); 
        String actualUrl = driver.getCurrentUrl();

        Assertions.assertEquals(expectedUrl, actualUrl, "L'URL de destination n'est pas celle attendue");

    }
}