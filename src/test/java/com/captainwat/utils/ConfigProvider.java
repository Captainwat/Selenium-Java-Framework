package com.captainwat.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProvider {
    private static Properties properties = new Properties();

    static {
        try {
            // Вказуємо шлях до файлу
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            System.err.println("Помилка: Не можу знайти файл config.properties!");
            e.printStackTrace();
        }
    }

    // Метод, щоб дістати URL
    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    // Метод, щоб дістати логін
    public static String getUsername() {
        return properties.getProperty("username");
    }

    // Метод, щоб дістати логін для заблокованого користувача
    public static String getLockedUsername(){
        return properties.getProperty("locked_username");
    }

    // Метод, щоб дістати пароль
    public static String getPassword() {
        return properties.getProperty("password");
    }
}