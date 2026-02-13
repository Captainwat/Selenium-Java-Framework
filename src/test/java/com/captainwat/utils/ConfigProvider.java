package com.captainwat.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static final Properties properties = new Properties();

    public static String getUrl() {
        return getBaseUrl();
    }

    static {
        boolean loaded = false;
        try (InputStream resource = ConfigProvider.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (resource != null) {
                properties.load(resource);
                loaded = true;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read config.properties from classpath", e);
        }

        if (!loaded) {
            try {
                File file = new File("src/test/resources/config.properties");
                if (file.exists()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        properties.load(fis);
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException("Cannot read src/test/resources/config.properties", e);
            }
        }
    }

    public static String getBaseUrl() {
        return read(
            new String[]{"base.url", "url"},
            new String[]{"base.url", "url"},
            new String[]{"BASE_URL", "URL"}
        );
    }

    public static String getUsername() {
        return read(
            new String[]{"username"},
            new String[]{"username"},
            new String[]{"USERNAME"}
        );
    }

    public static String getLockedUsername() {
        return read(
            new String[]{"locked_username"},
            new String[]{"locked_username"},
            new String[]{"LOCKED_USERNAME"}
        );
    }

    public static String getPassword() {
        return read(
            new String[]{"password"},
            new String[]{"password"},
            new String[]{"PASSWORD"}
        );
    }

    private static String read(String[] systemKeys, String[] propertyKeys, String[] envKeys) {
        for (String key : systemKeys) {
            String value = trimToNull(System.getProperty(key));
            if (value != null) {
                return value;
            }
        }
        for (String key : propertyKeys) {
            String value = trimToNull(properties.getProperty(key));
            if (value != null) {
                return value;
            }
        }
        for (String key : envKeys) {
            String value = trimToNull(System.getenv(key));
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    private static String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
