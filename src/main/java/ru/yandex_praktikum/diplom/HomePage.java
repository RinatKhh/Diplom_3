package ru.yandex_praktikum.diplom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final By signInButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text() = 'Войти в аккаунт']");
    private final By personalCabinetButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By constructorButton =By.xpath(".//p[text() = 'Конструктор']");
    private final By logoButton = By.className("AppHeader_header__logo__2D0X2");
    private final By orderPlaceButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text() = 'Оформить заказ']");
    private final By bunSectionButton  = By.xpath(".//span[text() = 'Булки']");
    private final By sauceSectionButton  = By.xpath(".//span[text() = 'Соусы']");
    private final By toppingSectionButton  = By.xpath(".//span[text() = 'Начинки']");
    private final By bunSection = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Булки']");
    private final By sauceSection = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Соусы']");
    private final By toppingSection = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Начинки']");
    private final By textLoadedPage = By.xpath(".//*[text() = 'Соберите бургер']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickSignInButton(){
       driver.findElement(signInButton).click();
    }

    public void clickPersonalCabinetButton(){
        driver.findElement(personalCabinetButton).click();
    }
    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }
    public void clickLogoButton(){
        driver.findElement(logoButton).click();
    }
    public void clickBunSectionBButton(){
        driver.findElement(bunSectionButton).click();
    }
    public void clickSauceSectionButton(){
        driver.findElement(sauceSectionButton).click();
    }
    public void clickToppingSectionButton(){
        driver.findElement(toppingSectionButton).click();
    }

    public void waitForLoadHomePage(){
        new WebDriverWait(driver,  Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textLoadedPage));
    }
    public void waitOrderPlaceButtonVisibility(){
        new WebDriverWait(driver,  Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPlaceButton));
    }
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public boolean isBunSectionPresent(){
        return isElementPresent(bunSection);
    }
    public boolean isSauceSectionPresent(){
        return isElementPresent(sauceSection);
    }
    public boolean isToppingSectionPresent(){
        return isElementPresent(toppingSection);
    }
}
