package com.captainwat.tests;

import com.captainwat.utils.ConfigProvider;
import driver.DriverFactory; // <--- Імпортуємо твій клас!
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // 1. Качаємо драйвер (менеджер версій)
        WebDriverManager.chromedriver().setup();

        // 2. Створюємо браузер через твій DriverFactory!
        // Вся магія з відключенням паролів тепер всередині цього методу.
        driver = DriverFactory.createChrome();

        // 3. Загальні налаштування вікна та очікувань
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        // 4. Відкриваємо сайт
        driver.get(ConfigProvider.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
