package ru.yandex_praktikum.diplom.yandex;

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
    String accessToken = "";
    @Before
    public void setUp() throws InterruptedException {
        ConfigDriver configDriver = new ConfigDriver();
        configDriver.driverSetup("Yandex");
        // создали объект класса страницы стенда
        driver = configDriver.driver;
        personalCabinetForm = new PersonalCabinetForm(driver);
        homePage = new HomePage(driver);
        inputForm = new InputForm(driver);
        RegisterForm registerForm = new RegisterForm(driver);
        homePage.waitForLoadHomePage();
        // регистрация пользователя
        User user = new User(registerForm.email, registerForm.password, registerForm.name);
        accessToken = UserMethodApi.createUser(user).then().extract().path("accessToken");
        homePage.clickPersonalCabinetButton();
        inputForm.waitForLoadInputForm();
        inputForm.signIn(registerForm.email, registerForm.password);
    }
    @After
    public void testDown(){
        if (accessToken!=null) {
            UserMethodApi.DeleteUser(accessToken);
        }
        driver.quit();
    }
    @Test
    @DisplayName("Check go from homepage to personal cabinet")
    public void checkGoToPersonalCabinetSuccessfully()  {
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