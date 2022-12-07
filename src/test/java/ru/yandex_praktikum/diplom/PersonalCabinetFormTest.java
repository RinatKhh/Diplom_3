package ru.yandex_praktikum.diplom;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex_praktikum.diplom.*;

@RunWith(Parameterized.class)
public class PersonalCabinetFormTest {
    WebDriver driver;
    String driverName;
    private HomePage homePage;
    private InputForm inputForm;
    private PersonalCabinetForm personalCabinetForm;
    String accessToken = "";

    public PersonalCabinetFormTest(String driverName) {
        this.driverName = driverName;
    }
    @Parameterized.Parameters
    public static Object[][] getDriverName() {
        return new Object[][]{
                { "Chrome"},
                { "Yandex"},
        };
    }

    @Before
    public void setUp() {
        ConfigDriver configDriver = new ConfigDriver();
        configDriver.driverSetup(driverName);
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
            UserMethodApi.deleteUser(accessToken);
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