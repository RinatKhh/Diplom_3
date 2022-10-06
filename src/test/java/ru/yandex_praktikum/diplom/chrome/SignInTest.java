package ru.yandex_praktikum.diplom.chrome;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex_praktikum.diplom.*;


public class SignInTest {
    WebDriver driver;
    private HomePage homePage;
    private InputForm inputForm;
    private RegisterForm registerForm;
    private PasswordRecoveryForm passwordRecoveryForm;
    @Before
    public void setUp() {
        ConfigDriver configDriver = new ConfigDriver();
        configDriver.driverSetup("Chrome");
        driver = configDriver.driver;
        homePage = new HomePage(driver);
        inputForm = new InputForm(driver);
        registerForm = new RegisterForm(driver);
        passwordRecoveryForm = new PasswordRecoveryForm(driver);
        homePage.waitForLoadHomePage();
        homePage.clickSignInButton();
        inputForm.clickRegisterButton();
        registerForm.register(false);
    }
    @After
    public void testDown(){
        driver.quit();
    }
    @Test
    @DisplayName("Check SigIn button from homepage form")
    public void checkSigInFromHomePageSuccessfully() throws InterruptedException {
        homePage.clickLogoButton();
        homePage.clickSignInButton();
        inputForm.waitForLoadInputForm();
        inputForm.signIn(registerForm.email,registerForm.password);
        homePage.waitOrderPlaceButtonVisibility();
    }
    @Test
    @DisplayName("Check SigIn button from personal cabinet form")
    public void checkSigInFromPersonalCabinetFormSuccessfully() throws InterruptedException {
        homePage.clickLogoButton();
        homePage.clickPersonalCabinetButton();
        inputForm.waitForLoadInputForm();
        inputForm.waitForLoadSignInButton();
        inputForm.signIn(registerForm.email,registerForm.password);
        homePage.waitOrderPlaceButtonVisibility();
    }
    @Test
    @DisplayName("Check SigIn button from register form")
    public void checkSigInFromRegisterFormSuccessfully() throws InterruptedException {
        inputForm.waitForLoadInputForm();
        inputForm.clickRegisterButton();
        registerForm.clickSignInButton();
        inputForm.waitForLoadInputForm();
        inputForm.signIn(registerForm.email,registerForm.password);
        homePage.waitOrderPlaceButtonVisibility();
    }
    @Test
    @DisplayName("Check SigIn button from recovery password form")
    public void checkSigInFromRecoveryPasswordFormSuccessfully() throws InterruptedException {
        inputForm.waitForLoadInputForm();
        inputForm.clickRecoverPasswordButton();
        passwordRecoveryForm.clickSignInButton();
        inputForm.waitForLoadInputForm();
        inputForm.signIn(registerForm.email,registerForm.password);
        homePage.waitOrderPlaceButtonVisibility();
    }
}
