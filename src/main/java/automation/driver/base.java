package automation.driver;

import automation.Pages.product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by Aram on 31/01/2018
 */
public class base {

    /**
     * driver
     */
    public static WebDriver driver;
    public static ArrayList<product.productDetails> prodDetails = new ArrayList<product.productDetails>();

    public static void navigateToHome() throws Throwable {

//            System.setProperty("webdriver.chrome.driver", "//Users/Aram/Developer/testshopping/src/main/resources/chromedriver");
//            driver = new ChromeDriver();
//            driver.manage().deleteAllCookies();
        DesiredCapabilities capa = DesiredCapabilities.firefox();
        capa.setCapability("marionette", false);
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
            driver.navigate().to("http://automationpractice.com/index.php");
    }


    public static void close() {
        driver.quit();
    }

    protected ElementImpl getElement(WebElement element, int seconds) throws Throwable {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            return new ElementImpl(wait.until(ExpectedConditions.visibilityOf(element)));
        } catch (Exception e) {
            throw e;
        }
    }
}