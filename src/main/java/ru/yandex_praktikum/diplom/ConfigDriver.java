package ru.yandex_praktikum.diplom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.util.Objects;

public class ConfigDriver {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public WebDriver driver;
    public void driverSetup(String driverName){
        if (Objects.equals(driverName, "Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.get(BASE_URL);
        } else if (Objects.equals(driverName, "Yandex")) {
            System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-extensions");
            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("src\\main\\resources\\yandexdriver.exe")).build();
            driver = new ChromeDriver(service,options);
            driver.get(BASE_URL);
        }
    }
}
