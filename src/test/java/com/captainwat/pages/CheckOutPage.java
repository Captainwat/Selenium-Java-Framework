package com.captainwat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutPage {
    private WebDriver driver;
    public CheckOutPage(WebDriver driver){
        this.driver = driver;
    }


    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessageNoField = By.cssSelector("h3[data-test='error']");


    @Step ("Entering the user name: {0}")
    public void enterName(String name){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        driver.findElement(firstName).sendKeys(name);
    }
    @Step ("Getting the error text from message")
    public String getErrorText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageNoField));
        String errorText = driver.findElement(errorMessageNoField).getText();
        return errorText;
    }
    @Step ("Fill checkout form with name: {0}, surname: {1}, zip: {2}")
    public void fillForm(String name, String surname, String postcode){
        enterName(name);
        enterLastName(surname);
        enterPostalCode(postcode);
        clickContinueButton();
    }

    public void enterLastName(String surname){
        driver.findElement(lastName).sendKeys(surname);
    }
    public void enterPostalCode(String postcode){
        driver.findElement(postalCode).sendKeys(postcode);
    }
    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
    
}
