package ru.yandex_praktikum.diplom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class InputForm {
    private final WebDriver driver;

    private final By signInButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By registerButton = By.xpath(".//a[@class = 'Auth_link__1fOlj' and text() = 'Зарегистрироваться']");
    private final By recoverPasswordButton = By.xpath(".//a[@class = 'Auth_link__1fOlj' and text() = 'Восстановить пароль']");
    private final By emailField = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @type ='text']");
    private final By passwordField = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @type ='password']");
    private final By inputText = By.xpath(".//h2[text() = 'Вход']");

    public InputForm(WebDriver driver) {
        this.driver = driver;
    }
    public void setEmailField(String email){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPasswordField(String password){
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    // вход в систему
    public void signIn(String email,String password) throws InterruptedException {
        waitForLoadSignInButton();
        setEmailField(email);
        setPasswordField(password);
        TimeUnit.SECONDS.sleep(1);
        clickSignInButton();
    }

    public void waitForLoadInputForm() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(inputText));
    }
    public void waitForLoadSignInButton() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(signInButton));
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(signInButton));
    }
}
