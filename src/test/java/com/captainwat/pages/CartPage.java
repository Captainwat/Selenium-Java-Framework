package com.captainwat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    private By checkOutButton = By.id("checkout");
    public void clickCheckout(){
        driver.findElement(checkOutButton).click();
    }
}
