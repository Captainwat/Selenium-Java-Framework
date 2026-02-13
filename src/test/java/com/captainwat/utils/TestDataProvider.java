package com.captainwat.utils;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
    
    @DataProvider(name = "itemNames")
    public static Object [][] itemNames(){
        return new Object[][] { 
            {"4","Sauce Labs Backpack"},
            {"0","Sauce Labs Bike Light"},
            {"1","Sauce Labs Bolt T-Shirt"},
            {"5","Sauce Labs Fleece Jacket"},
            {"2","Sauce Labs Onesie"},
            {"3","Test.allTheThings() T-Shirt (Red)"}
        };
    }
}
