package ru.yandex_praktikum.diplom.chrome;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex_praktikum.diplom.*;


public class PersonalCabinetFormTest {
    WebDriver driver;
    private HomePage homePage;
    private InputForm inputForm;
    private PersonalCabinetForm personalCabinetForm;
    @Before
    public void setUp() {
        ConfigDriver configDriver = new ConfigDriver();
        configDriver.driverSetup("Chrome");
        driver = configDriver.driver;
        // создали объект класса страницы стенда
        homePage = new HomePage(driver);
        inputForm = new InputForm(driver);
        RegisterForm registerForm = new RegisterForm(driver);
        personalCabinetForm = new PersonalCabinetForm(driver);
        // дождались загрузки страницы и прокликали до регистрации
        homePage.waitForLoadHomePage();
        homePage.clickSignInButton();
        inputForm.clickRegisterButton();
        registerForm.register(false);
        inputForm.waitForLoadInputForm();
        inputForm.setEmailField(registerForm.email);
        inputForm.setPasswordField(registerForm.password);
        inputForm.clickSignInButton();
        homePage.waitForLoadHomePage();

    }
    @After
    public void testDown(){
        driver.quit();
    }
    @Test
    @DisplayName("Check go from homepage to personal cabinet")
    public void checkGoToPersonalCabinetSuccessfully(){
        homePage.clickPersonalCabinetButton();
        personalCabinetForm.waitExitButtonVisibility();
    }
    @Test
    @DisplayName("Check go from personal cabinet to constructor")
    public void checkGoToConstructorSuccessfully(){
        homePage.clickPersonalCabinetButton();
        homePage.clickConstructorButton();
        homePage.waitForLoadHomePage();
        }
    @Test
    @DisplayName("Check go from personal cabinet to logo")
    public void checkGoToLogoSuccessfully(){
        homePage.clickPersonalCabinetButton();
        homePage.clickLogoButton();
        homePage.waitForLoadHomePage();
    }
    @Test
    @DisplayName("Check job exit button")
    public void checkExitButtonSuccessfully(){
        homePage.clickPersonalCabinetButton();
        personalCabinetForm.waitExitButtonVisibility();
        personalCabinetForm.clickExitButton();
        inputForm.waitForLoadInputForm();

    }
}