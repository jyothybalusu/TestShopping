package automation.Pages;

import automation.driver.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class product extends base {

    ///**
    // * The productDetails class holds the
    // * details of the product information like
    // itemname, itemsize and itemprice
    // */
    public static class productDetails{
        public String itemname;
        public String itemsize;
        public String itemprice;
        public productDetails p;


        public productDetails(String itemname, String itemprice, String itemsize){
            this.itemname = itemname;
            this.itemprice = itemprice;
            this.itemsize = itemsize;

        }
        public productDetails(String itemsize){
            this.itemsize = itemsize;
        }
        public productDetails(productDetails p){
            this.p = p;
        }

        public String getItemname() {
            return itemname;
        }

        public String getSize() {
            return itemsize;
        }

        public String getPrice() {
            return itemprice;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public void setPrice(String itemprice) {
            this.itemprice = itemprice;
        }

        public void setSize(String size) {
            this.itemsize = size;
        }


    }
    /**
     * Declaration of all the Webelements used on this page
     */
    @FindBy(id = "group_1")
    private WebElement sizeDrropDown;
    @FindBy(css = "button.exclusive span")
    private WebElement addToCartBtn;
    @FindBy(css = "span.continue.btn.btn-default.button.exclusive-medium")
    private WebElement contShopBtn;
    @FindBy(css = "a.btn.btn-default.button.button-medium")
    private WebElement proToCheckoutBtn;
    @FindBy(id = "center_column")
    private WebElement shopCartSummary;


    public product(WebDriver driver){
        /**
         * Initialises all the Webelements defined on the top
         */
        PageFactory.initElements(driver,this);
    }

    public void  changesize(int value) throws Throwable {
     /**
     * Changes the Size of the item during the process of adding it to the cart
     */
        try{
        String isize=null;
        Select dropdown = new Select(sizeDrropDown);
        dropdown.selectByIndex(1);
        if(value==1) isize="S";
        else
        if(value==2) isize="M";
        else
        if(value==3) isize="L";
        System.out.println("ProductDetails size is :"+prodDetails.size());

        productDetails p1 = prodDetails.get(0);
        p1.setSize(isize);

        System.out.println("Replaced Item size is :"+ prodDetails.get(prodDetails.size()-1).getSize());
    }
        catch (Throwable throwable) {
            throw new Exception("Unable to change the size of the selected item");
        }
    }



    public void addToCart() throws Throwable {
        /**
         * Add the selected item to the cart
         */
        try {
            getElement(addToCartBtn,1).click();
        }

        catch (Throwable throwable) {
            throw new Exception("Unable to add the item to cart");
        }
    }

    public void continueShopping() throws Throwable {
        /**
         *Continues to shopping once an item is added to the cart
         */
        try {
            getElement(contShopBtn,3).click();
        }

        catch (Throwable throwable) {
            throw new Exception("Unable to click the button to continue shopping");
        }
    }

    public void proceedToCheckout() throws Throwable{
        /**
         * Proceeds to the checkout page from the cart and navigates to the summary page
         */
        getElement(proToCheckoutBtn,3).click();
        try {
            assertTrue(getElement(shopCartSummary,1).isDisplayed(),"Verfir that the shopping cart summary page is displayed");
        }

        catch (Throwable throwable) {
            throw new Exception("Unable to Proceed to checkout page from the cart");
        }
    }
}
