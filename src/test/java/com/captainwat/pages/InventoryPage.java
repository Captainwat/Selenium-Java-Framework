package com.captainwat.pages;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class InventoryPage {
    private WebDriver driver;
    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }

    private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartButton = By.className("shopping_cart_link");
    private By itemBackpackButoon = By.id("item_4_title_link");
    private By sortDropdown = By.className("product_sort_container");
    private By itemPrices = By.className("inventory_item_price");
    private By itemNames = By.className("inventory_item_name");
    
    private By itemNameDetail = By.cssSelector("[data-test='inventory-item-name']");


    @Step("Add Backpack to cart")
    public void addBackpackToCart(){
        driver.findElement(addToCartButton).click();
    }
    @Step("Get URL for product")
    public void getURLitem(String id){
        driver.get("https://www.saucedemo.com/inventory-item.html?id=" + id);
    }
    @Step("Click on Cart icon")
    public void clickCartIcon(){
        driver.findElement(cartButton).click();
    }
    @Step("Click on the product name")
    public void checkBackPackDetails(){
        driver.findElement(itemBackpackButoon).click();
    }
    @Step("Check the item name")
    public String getItemDetail(){
       return driver.findElement(itemNameDetail).getText();
    }
    @Step("Selecting the option in the sorting drop-down")
    public void selectSortOption(String value){
        WebElement dropdoWebElement = driver.findElement(sortDropdown);
        Select select = new Select(dropdoWebElement);
        select.selectByVisibleText(value);
    }
    @Step("Collecting all prices on pages")
    public List<Double> getAllItemPrices(){
        List<WebElement> priceElements = driver.findElements(itemPrices);
        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            // Отримуємо текст "$29.99"
            String text = element.getText();
            // Відрізаємо знак долара
            String cleanText = text.replace("$", "");
            // Перетворюємо текст на число і додаємо в список
            prices.add(Double.parseDouble(cleanText));
        }
        return prices;
    }
    @Step
    public List<String> getAllProductNames(){
        List<WebElement> nameItems = driver.findElements(itemNames);
        List<String> names = new ArrayList<>();
        for (WebElement element : nameItems){
            names.add(element.getText());
        }
        return names;
    }
}
