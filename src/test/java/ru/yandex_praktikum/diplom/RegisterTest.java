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
public class RegisterTest {
    WebDriver driver;
    String driverName;
    private InputForm inputForm;
    private RegisterForm registerForm;
    String accessToken = "";

    public RegisterTest(String driverName) {
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
        driver = configDriver.driver;
        // создали объект класса страницы стенда
        HomePage homePage = new HomePage(driver);
        inputForm = new InputForm(driver);
        registerForm = new RegisterForm(driver);
        homePage.waitForLoadHomePage();
        homePage.clickSignInButton();
        inputForm.clickRegisterButton();

    }
    @After
    public void testDown(){
        User user = new User(registerForm.email,registerForm.password);
        accessToken = UserMethodApi.SigInUser(user).then().extract().path("accessToken");
        if (accessToken!=null) {
            UserMethodApi.DeleteUser(accessToken);
        }
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