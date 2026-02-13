package com.captainwat.tests;


import java.util.ArrayList;
import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.captainwat.pages.CartPage;
import com.captainwat.pages.CheckOutPage;
import com.captainwat.pages.InventoryPage;
import com.captainwat.pages.LoginPage;
import com.captainwat.pages.OverviewPage;
import com.captainwat.utils.ConfigProvider;
import com.captainwat.utils.TestDataProvider;
import java.util.List;



public class BuyTest extends BaseTest {

LoginPage loginPage;
InventoryPage inventoryPage;
CheckOutPage checkOutPage;
CartPage cartPage;
OverviewPage overviewPage;


@BeforeMethod
public void setup(){
    
    loginPage = new LoginPage(driver);
    inventoryPage = new InventoryPage(driver);
    checkOutPage = new CheckOutPage(driver);
    cartPage = new CartPage(driver);
    overviewPage = new OverviewPage(driver);

}

@Test(dataProvider = "itemNames", dataProviderClass = TestDataProvider.class)
public void checkItemNames(String id, String name){
    loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
    inventoryPage.getURLitem(id);
    Assert.assertEquals(inventoryPage.getItemDetail(),name,"Name details are different");
}


@Test
public void orderBackpackTest(){
    loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
    inventoryPage.addBackpackToCart();
    inventoryPage.clickCartIcon();
    cartPage.clickCheckout();
    checkOutPage.fillForm("Test", "Testovich", "19000");
    overviewPage.clickFinishButton();
    overviewPage.checkFinalMessage();
    Assert.assertEquals(overviewPage.checkFinalMessage(), "Thank you for your order!");
}

@Test
public void orderBackPackDeniedNoPostCode(){
    loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
    inventoryPage.addBackpackToCart();
    inventoryPage.clickCartIcon();
    cartPage.clickCheckout();
    checkOutPage.fillForm("test", "testovich", "");
    Assert.assertEquals(checkOutPage.getTheErrorText(),"Error: Postal Code is required");
}
@Test
public void orderBackPackDeniesNoSurname(){
    loginPage.login(ConfigProvider.getUsername(),ConfigProvider.getPassword());
    inventoryPage.addBackpackToCart();
    inventoryPage.clickCartIcon();
    cartPage.clickCheckout();
    checkOutPage.fillForm("test", "", "1800");
    Assert.assertEquals(checkOutPage.getTheErrorText(), "Error: Last Name is required");
}

@Test
public void orderBackPackDeniesNoName(){
    loginPage.login(ConfigProvider.getUsername(),ConfigProvider.getPassword());
    inventoryPage.addBackpackToCart();
    inventoryPage.clickCartIcon();
    cartPage.clickCheckout();
    checkOutPage.fillForm("", "test", "1800");
    Assert.assertEquals(checkOutPage.getTheErrorText(), "Error: First Name is required");
}

@Test
public void clickProductDetail(){
    loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
    inventoryPage.checkBackPackDetails();
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(currentUrl.contains("id=4"));
}
@Test
public void checkPageFilteringByPricesLowToHigh(){
    loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
    inventoryPage.selectSortOption("Price (low to high)");
    List<Double> actualPrices = inventoryPage.getAllItemPrices();
    List<Double> expectedPrices = new ArrayList<>(actualPrices);
    Collections.sort(expectedPrices);
    Assert.assertEquals(actualPrices, expectedPrices,"Prices are not sorted Low to High");
}

@Test
public void checkPageFilteringByPricesHighToLow(){
    loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
    inventoryPage.selectSortOption("Price (high to low)");
    List<Double> actualPrices = inventoryPage.getAllItemPrices();
    List<Double> expectedPrices = new ArrayList<>(actualPrices);
    Collections.sort(expectedPrices);
    Collections.reverse(expectedPrices);
    Assert.assertEquals(actualPrices, expectedPrices,"Prices are not sorted High to Low");
}

@Test
public void checkPageFilteringByNamesZtoA(){
    loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
    inventoryPage.selectSortOption("Name (Z to A)");
    List<String> actualOrder = inventoryPage.getAllProductNames();
    Assert.assertFalse(actualOrder.isEmpty());
    List<String> expectedOrder = new ArrayList<>(actualOrder);
    Collections.sort(expectedOrder);
    Collections.reverse(expectedOrder);
    Assert.assertEquals(actualOrder, expectedOrder,"Order is not sorted");
}

@Test
public void checkPageFilteringByNameAtoZ(){
    loginPage.login(ConfigProvider.getUsername(), ConfigProvider.getPassword());
    inventoryPage.selectSortOption("Name (A to Z)");
    List<String> actualOrder = inventoryPage.getAllProductNames();
    Assert.assertFalse(actualOrder.isEmpty());
    List<String> expectedOrder = new ArrayList<>(actualOrder);
    Collections.sort(expectedOrder);
    Assert.assertEquals(actualOrder, expectedOrder,"Order is not sorted");
}

}

