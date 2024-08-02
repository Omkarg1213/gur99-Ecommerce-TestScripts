package guru99.EcommerceProject.DriveFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DriverFactory {
    private static WebDriver driver;
    private static Properties config;

    public static WebDriver getDriver() {
        if (driver == null) {
            config = new Properties();
            try {
                FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
                config.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load configuration file");
            }

            String browser = config.getProperty("browser");
            if (browser == null) {
                throw new RuntimeException("Browser is not defined in the config.properties file");
            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    //System.setProperty("webdriver.chrome.driver", config.getProperty("chromeDriverPath"));
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", config.getProperty("geckoDriverPath"));
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", config.getProperty("edgeDriverPath"));
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}



