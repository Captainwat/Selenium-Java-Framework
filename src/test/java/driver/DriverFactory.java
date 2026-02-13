package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DriverFactory {

    public static WebDriver createChrome() {
        ChromeOptions options = new ChromeOptions();

        // Disable password manager & credential service
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // Kill Chrome UI noise
        options.addArguments(
            "--disable-notifications",
            "--disable-infobars",
            "--disable-features=PasswordManagerOnboarding,PasswordManagerLeakDetection,PasswordLeakDetection"
        );

        // Improve stability in Linux/WSL and CI-like environments.
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        if (os.contains("linux")) {
            options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        }

        boolean headless = Boolean.parseBoolean(
            System.getProperty("headless",
                System.getenv().getOrDefault("HEADLESS", os.contains("linux") ? "false" : "false"))
        );
        if (headless) {
            options.addArguments("--headless=new", "--remote-debugging-pipe");
        }

        return new ChromeDriver(options);
    }
}
