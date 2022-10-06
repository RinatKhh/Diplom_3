package ru.yandex_praktikum.diplom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryForm {
    private final WebDriver driver;
    private final By signInButton = By.className("Auth_link__1fOlj");

    public PasswordRecoveryForm(WebDriver driver) {
        this.driver = driver;
    }
    public void clickSignInButton(){
        driver.findElement(signInButton).click();
    }
}
