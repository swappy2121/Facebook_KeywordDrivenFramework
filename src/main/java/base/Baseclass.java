package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Baseclass {
    public static Properties prop;
    public static WebDriver driver;

    public Properties initProperties(){
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("C:\\Users\\HP\\IdeaProjects\\Facebook_KeywordDrivenFramework\\src\\main\\java\\config\\config.properties"));
            try {
                prop.load(ip);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return prop;
    }
    public WebDriver setProperties(){
        System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
}
