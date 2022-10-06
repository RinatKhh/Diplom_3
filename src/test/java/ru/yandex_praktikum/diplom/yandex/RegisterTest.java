package ru.yandex_praktikum.diplom.yandex;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex_praktikum.diplom.InputForm;
import ru.yandex_praktikum.diplom.RegisterForm;
import ru.yandex_praktikum.diplom.ConfigDriver;
import ru.yandex_praktikum.diplom.HomePage;

public class RegisterTest {
    WebDriver driver;
    private InputForm inputForm;
    private RegisterForm registerForm;
    @Before
    public void setUp() {
        ConfigDriver configDriver = new ConfigDriver();
        configDriver.driverSetup("Yandex");
        driver = configDriver.driver;
        // создали объект класса страницы стенда
        HomePage homePage = new HomePage(driver);
        inputForm = new InputForm(driver);
        registerForm = new RegisterForm(driver);
        // дождались загрузки страницы и прокликали до регистрации
        homePage.waitForLoadHomePage();
        homePage.clickSignInButton();
        inputForm.clickRegisterButton();

    }
    @After
    public void testDown(){
        driver.quit();
    }
    @Test
    @DisplayName("Check register Successfully new user")
    public void checkRegisterSuccessfully(){
        registerForm.register(false);
        inputForm.waitForLoadInputForm();
    }
    @Test
    @DisplayName("Check register new user incorrect password")
    public void checkRegisterIncorrectPassword(){
        registerForm.register(true);
        registerForm.waitIncorrectPasswordTextVisibility();
    }
}