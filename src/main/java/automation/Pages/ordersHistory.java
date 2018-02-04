package automation.Pages;

import automation.driver.base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ordersHistory extends base {

    @FindBy(xpath = "//*[@name='id_product']")
    private WebElement prodDropdown;
    @FindBy(xpath = "//p[@class='form-group']/textarea")
    private WebElement prodCommentArea;
    @FindBy(css = "button.button.btn.btn-default.button-medium span")
    private WebElement sendBtn;
    @FindBy(xpath="//div[@class='table_block'][2]/table[@class='detail_step_by_step table table-bordered']/tbody/tr[@class='first_item item']/td[2]")
    private WebElement message;
    static String match;

    public ordersHistory(WebDriver driver) {
        /**
         * Initialises all the Webelements defined on the top
         */
        PageFactory.initElements(driver, this);
    }

    public void selectOrderFromOrderHistroy(int index) throws Throwable {
        /**
         * Selects the Order from order history using the index of the products
         */
        try{
        WebElement ONumber = driver.findElement(By.xpath("//tr["+index+"]//a[@class='color-myaccount']"));
        ONumber.click();
        }
        catch (Throwable throwable) {
            throw new Exception("Unable to select the order from the order histroy based on the index");
        }
    }


    public void selectOrderFromOrderHistroy(String Date) throws Throwable {
        /**
         * Selects the order from order history using date of Order
         */
        try{
        WebElement ONumber = driver.findElement(By.xpath("//tr//td[contains(text(),'"+ Date +"')]/preceding-sibling::td"));
        ONumber.click();
    }
        catch (Throwable throwable) {
            throw new Exception("Unable to select the order from the order histroy using the date of order");
        }
    }
    public void selectproductFromselectedOrder(int index) throws Throwable {
        /**
         * Selects the Product from the Selected Order using the index of the products
         */
        try {
        getElement(prodDropdown,3).click();
        Select dropdown = new Select(prodDropdown);
        dropdown.selectByIndex(index);
    }
        catch (Throwable throwable) {
        throw new Exception("Unable to select the Product from the Selected Order based on the index");
    }
}
        public void getDressColourfromProductOrders(int index) throws Throwable {
        /**
         * Gets the colour of the dress from the Selected Order using the index of the products
         */
        try{

            driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
            WebElement element = driver.findElement(By.xpath("//div[@class='table_block'][2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
         driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
        WebElement dressColour = driver.findElement(By.cssSelector("select[name='id_product'] option:nth-child("+index+")"));
        String s = getElement(dressColour,3).getText();
        System.out.println("The Text of the selected optoin is : " + s);
        Pattern pat = Pattern.compile("Color.+?[,]");
        Matcher mat = pat.matcher(s);
        while(mat.find())
            match = s.substring(mat.start(), (mat.end()));
        System.out.println("color found at index : " + match);
            match = match.substring(7,(match.length())-1);
        System.out.println("color found at index : " + match);
        assertTrue(match.equalsIgnoreCase("amber"), "Verfies that the dress colour is amber");
    }
    catch (Throwable throwable) {
            throw new Exception("Unable to get the colour from the selected Order using the index of the product");
        }
    }

    public void addCommentsToProducts(String text) throws Throwable{
        /**
         * Adds message to the selected product from the Order
         */
        try{
        getElement(prodCommentArea,3).sendKeys(text);
        getElement(sendBtn,3).click();
    }
        catch (Throwable throwable) {
            throw new Exception("Unable to add message to the product");
        }
    }

    public void checkMessageUnderMessages(String text) throws Throwable{
        /**
         * Checks the Message added under to the product from the selected order
         */
        try{
        WebElement msgLoc = driver.findElement(By.xpath("//td[contains(text(),'"+text+"')]/preceding-sibling::td/parent::tr/parent::tbody/preceding-sibling::thead//th[@class='last_item']"));
        assertTrue(msgLoc.isDisplayed(),"Verifies that the send comment appears under the Heading MESSAGES");
    }
        catch (Throwable throwable) {
            throw new Exception("Unable to check if message has been added to the product");
        }
    }


}
