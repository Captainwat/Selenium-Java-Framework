package com.captainwat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewPage {
    private WebDriver driver;

    public OverviewPage(WebDriver driver){
        this.driver = driver;
    }
    private By finishButton = By.id("finish");
    private By pageHeader = By.className("complete-header");

    public void clickFinishButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
        driver.findElement(finishButton).click();
    }

    public String checkFinalMessage(){
        String final_text = driver.findElement(pageHeader).getText();
        return final_text;
    }
}
