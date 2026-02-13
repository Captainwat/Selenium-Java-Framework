package com.captainwat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import com.captainwat.utils.ConfigProvider;

public class LoginPage {
    private WebDriver driver;

    @Step("Enter username: {0}")
    public void enterUsername(String user){
        driver.findElement(userName).sendKeys(user);
    }
    @Step("Enter password: {0}")
    public void enterUserpassword(String testPassword){
        driver.findElement(password).sendKeys(testPassword);
    }
    @Step("Click Login button")
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }
    @Step("Login in the system with creds")
    public void login(String user, String password){
        enterUsername(user);
        enterUserpassword(password);
        clickLoginButton();
    }
    @Step("Get the error message")
    public String getErrorMessage() {
        return driver.findElement(errorContainer).getText();
    }

    private By userName = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorContainer = By.cssSelector("[data-test='error']");

    

public LoginPage(WebDriver driver){
    this.driver = driver;
}
}