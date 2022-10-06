package ru.yandex_praktikum.diplom.chrome;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex_praktikum.diplom.ConfigDriver;
import ru.yandex_praktikum.diplom.HomePage;

public class ConstructorSectionTest {
    WebDriver driver;
    HomePage homePage;
    @Before
    public void setUp() {
        ConfigDriver configDriver = new ConfigDriver();
        configDriver.driverSetup("Chrome");
        driver = configDriver.driver;
        // создали объект класса страницы стенда
        homePage = new HomePage(driver);
        homePage.waitForLoadHomePage();


    }
    @After
    public void testDown(){
        driver.quit();
    }
    @Test
    @DisplayName("Check constructor go to bun section")
    public void checkGoToBunSectionSuccessfully(){
        homePage.clickConstructorButton();
        homePage.clickSauceSectionButton();
        homePage.clickBunSectionBButton();
        homePage.waitBunSectionTextVisibility();
    }
    @Test
    @DisplayName("Check constructor go to sauce section")
    public void checkGoToSauceSectionSuccessfully(){
        homePage.clickConstructorButton();
        homePage.clickSauceSectionButton();
        homePage.waitSauceSectionTextVisibility();
    }
    @Test
    @DisplayName("Check constructor go to topping section")
    public void checkGoToToppingSectionSuccessfully(){
        homePage.clickConstructorButton();
        homePage.clickToppingSectionButton();
        homePage.waitToppingSectionTextVisibility();
    }
}

