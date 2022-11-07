package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import java.time.Duration;


public class Driver {

    private Driver(){

    }

    private static WebDriver driver;
    public static WebDriver getDriver(){
        if (driver==null){
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--incognito");
                    options.addArguments("--start-maximized");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--allow-insecure--localhost");
                    options.addArguments("--acceptInsecureCerts");
                    options.addArguments("--disable-blink-features");
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--disable-extensions");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }

    public static void closeDriver(){
        if (driver!=null){
            driver.quit();
        }
        driver=null;
    }
}
