package automation.Pages;


import automation.driver.base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class categoryPage extends base {

    @FindBy(xpath = "//h1[@itemprop='name']")
    private WebElement itemName;
    @FindBy(id = "our_price_display")
    private WebElement itemPrice;
    @FindBy(xpath = "//*[@id='uniform-group_1']/span")
    private WebElement itemSize;
    @FindBy(xpath = "//div[@class='breadcrumb clearfix']/a")
    private WebElement homeBtn;
    @FindBy(xpath="//div[@id='block_top_menu']//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[1]")
    private WebElement womenCategory;
    @FindBy(xpath="//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li[2]/a")
    private WebElement dressesCategory;
    @FindBy(xpath="//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrowst']/li[3]/a")
    private WebElement tshirtsCategory;

    public categoryPage(WebDriver driver)
    {
        /**
         * Initialises all the Webelements defined on the top
         */
        PageFactory.initElements(driver,this);
    }


    public void clickItem(String category,int itemnumber) throws Throwable {
        /**
         * Clicks an item on the category page based on the indexnumber
         */
       try{
        if(category == "women")
            getElement(womenCategory,2).click();
        else
        if(category == "dresses")
            getElement(dressesCategory,2).click();
        else if(category == "tshirts")
            getElement(tshirtsCategory,2).click();

        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
         WebDriverWait wait = new WebDriverWait(driver, 10);
         WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul[@class='product_list grid row']/li["+ itemnumber +"]"))));
           //WebElement item = driver.findElement((By.xpath("//ul[@class='product_list grid row']/li["+ itemnumber +"]//a[@title='View']")));


           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
           getElement(item,5).click();
        prodDetails.add(new product.productDetails(getElement(itemName,1).getText(),getElement(itemPrice,1).getText(),getElement(itemSize,1).getText()));

//           WebDriverWait wait = new WebDriverWait(driver, 10);
//           WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul[@id='homefeatured']/li["+ itemnumber +"]"))));
//           getElement(item,5).click();
//           prodDetails.add(new product.productDetails(itemName.getText(),itemPrice.getText(),itemSize.getText()));

           System.out.println("Value of Productdetails when clicked are : ");
        for(int i=0;i<prodDetails.size();i++){

            System.out.println("Item Name:"+prodDetails.get(i).getItemname());
            System.out.println("ItemPrice:"+prodDetails.get(i).getPrice());
            System.out.println("Itemsize:"+prodDetails.get(i).getSize());
        }
    }
       catch (Throwable throwable) {
           throw new Exception("Unable to click on an item on the category page");
       }
    }


}
