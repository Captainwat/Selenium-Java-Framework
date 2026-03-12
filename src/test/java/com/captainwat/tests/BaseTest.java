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
    
        WebDriverManager.chromedriver().setup();
        driver = DriverFactory.createChrome();

        
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        
        String baseUrl = ConfigProvider.getBaseUrl();
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException(
                "base.url is not configured. Set src/test/resources/config.properties or pass -Dbase.url / BASE_URL"
            );
        }
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
