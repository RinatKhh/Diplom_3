package ru.yandex_praktikum.diplom;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class ConstructorSectionTest {
    String driverName;
    WebDriver driver;
    HomePage homePage;

    public ConstructorSectionTest(String driverName) {
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
        Assert.assertTrue(homePage.isBunSectionPresent());
    }
    @Test
    @DisplayName("Check constructor go to sauce section")
    public void checkGoToSauceSectionSuccessfully(){
        homePage.clickConstructorButton();
        homePage.clickSauceSectionButton();
        Assert.assertTrue(homePage.isSauceSectionPresent());
    }
    @Test
    @DisplayName("Check constructor go to topping section")
    public void checkGoToToppingSectionSuccessfully(){
        homePage.clickConstructorButton();
        homePage.clickToppingSectionButton();
        Assert.assertTrue(homePage.isToppingSectionPresent());
    }
}

