package com.captainwat.tests;

import com.captainwat.pages.LoginPage;
import com.captainwat.utils.ConfigProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.captainwat.utils.ConfigProvider;

import java.time.Duration;

public class LoginTest extends BaseTest{
   
    private LoginPage loginPage;

    
    @BeforeMethod
    public void pageSetup() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void succsesfullLoginTest(){
        loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"),"Login Unsucsesfull");
    }

    @Test
    public void loginDeniedTest(){
        loginPage.login("standard_user", "wrong_password_123");
        String actualError = loginPage.getErrorMessage();
        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualError, expectedError, "Access Denied");
    }
    @Test
    public void loginLockedUser(){
        loginPage.login(ConfigProvider.getLockedUsername(), ConfigProvider.getPassword());
        String actualError = loginPage.getErrorMessage();
        String expectedError = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(actualError,expectedError,"User is locked, access denied");
    }
}