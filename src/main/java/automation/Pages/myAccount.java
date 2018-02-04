package automation.Pages;

import automation.driver.base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class myAccount extends base {
    /**
     * Declaration of all the Webelements used on this page
     */
    @FindBy(xpath = "//a[@class='login'][@href='http://automationpractice.com/index.php?controller=my-account']")
    WebElement signInLink;
    @FindBy(id = "email")
    private WebElement username;
    @FindBy(id = "passwd")
    private WebElement password;
    @FindBy(id = "SubmitLogin")
    private WebElement signInBtn;
    @FindBy(xpath = "//a[@class='home']")
    private WebElement homeBtn;
    @FindBy(css = "a.logout")
    private WebElement logoutBtn;
    @FindBy(xpath = "//i[@class='icon-list-ol']")
    private WebElement orderHistory;
    @FindBy(xpath = "//table[@id='order-list']")
    private WebElement orderList;

    public myAccount(WebDriver driver) {
        /**
         * Initialises all the Webelements defined on the top
         */
        PageFactory.initElements(driver, this);
    }

    public void clickSignInLink() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signInLink);
        getElement(signInLink, 3).click();

    }

    public void signIn(String emailId, String pword) throws Throwable {
        /**
         * Signs in to the application by entering login name and password
         */
        try {
            getElement(username, 1).sendKeys(emailId);
            getElement(password, 1).sendKeys(pword);
            getElement(signInBtn, 1).click();
        } catch (Throwable throwable) {
            throw new Exception("Unable to Login to the application");
        }
    }

    public void navigateTOHomePage() throws Throwable {
        /**
         * Navigates to the Home Page from my account page
         */
        try {
            getElement(homeBtn, 1).click();
        } catch (Throwable throwable) {
            throw new Exception("Unable to navigate to the home page from Myaccount page");
        }
    }

    public void navigateToOrderHistory() throws Throwable {
        /**
         * Navigates to the Order history page from my account page
         */
        try {
            getElement(orderHistory, 3).click();
            assertTrue(orderList.isDisplayed(), "message");
        } catch (Throwable throwable) {
            throw new Exception("Unable to navigate to the order history page from Myaccount page");
        }
    }

    public void logOut() throws Throwable {
        /**
         * Logout user of the application
         */
        try {
            getElement(logoutBtn, 3).click();
        } catch (Throwable throwable) {
            throw new Exception("Unable to logout of the application");
        }

    }
}
