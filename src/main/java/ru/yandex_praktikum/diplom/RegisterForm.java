package ru.yandex_praktikum.diplom;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterForm {
    private final WebDriver driver;
    private final By nameField = By.xpath("(.//input[@class = 'text input__textfield text_type_main-default'])[1]");
    private final By emailField = By.xpath("(.//input[@class = 'text input__textfield text_type_main-default'])[2]");
    private final By passwordField = By.xpath("(.//input[@class = 'text input__textfield text_type_main-default'])[3]");
    private final By registerButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final By signInButton = By.className("Auth_link__1fOlj");
    private final By incorrectPasswordText = By.xpath(".//p[@class = 'input__error text_type_main-default' and text() = 'Некорректный пароль']");
    public String email;
    String name;
    public String password;

    public RegisterForm(WebDriver driver) {
        this.driver = driver;
        email  = RandomStringUtils.randomAlphanumeric(6) + "@test.com";
        name  = RandomStringUtils.randomAlphabetic(6);
        password = "Password";
    }

    public void setNameField(String name){
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }
    public void setEmailField(String email){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPasswordField(String password){
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
    }
    public void clickSignInButton(){
        driver.findElement(signInButton).click();
    }
    // регистрация в системе, флаг проверяет какой пароль использовать (валидный / не валидный)
    public void register(boolean passwordError){
        if (passwordError) {
            password = "Pass";
        }
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        clickRegisterButton();
    }
    public void waitIncorrectPasswordTextVisibility(){
        new WebDriverWait(driver,  Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordText));
    }

}

