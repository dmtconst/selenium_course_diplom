package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        var options = new ChromeOptions();
        options.addArguments("--start-maximized");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 7);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
