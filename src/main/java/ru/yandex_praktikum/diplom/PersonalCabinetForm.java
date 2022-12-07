package ru.yandex_praktikum.diplom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalCabinetForm {
    private final WebDriver driver;
    private final By exitButton = By.xpath(".//button[@class =  'Account_button__14Yp3 text text_type_main-medium text_color_inactive']");

    public PersonalCabinetForm(WebDriver driver) {
        this.driver = driver;
    }

    public void waitExitButtonVisibility(){
        new WebDriverWait(driver,  Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }
    public void clickExitButton(){
        driver.findElement(exitButton).click();
    }
}
