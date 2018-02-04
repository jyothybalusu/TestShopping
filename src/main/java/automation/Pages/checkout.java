package automation.Pages;

/**
 * Created by Aram on 03/02/2018.
 */

import automation.driver.base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class checkout extends base {
    /**
     * declaration of all the Webelements
     */

    @FindBy(id = "total_price")
    private WebElement totalPrice;
    @FindBy(css = "a.button.btn.btn-default.standard-checkout.button-medium span")
    private WebElement proceedCOutSummary;
    @FindBy(css = "button.button.btn.btn-default.button-medium span")
    private WebElement proceedCOutAddress;
    @FindBy(css = "button.button.btn.btn-default.standard-checkout.button-medium span")
    private WebElement proceedCOutShipping;
    @FindBy(xpath = "//*[@id='uniform-cgv']")
    private WebElement agreeTerms;
    @FindBy(css = "a.bankwire")
    private WebElement payByWireOption;
    @FindBy(css = "button.button.btn.btn-default.button-medium span")
    private WebElement confirmOrderOption;
    @FindBy(id="center_column")
    private WebElement deliverAddress;
    @FindBy(id="form")
    private WebElement shippingOption;
    @FindBy(id="order-detail-content")
    private WebElement orderDetailsPaymenPage;
    @FindBy(css=".box.cheque-box")
    private WebElement orderSummary;
    @FindBy(id="center_column")
    private WebElement orderConfirmation;

    public checkout(WebDriver driver) {
        /**
         * Initialises all the Webelements defined on the top
         */
        PageFactory.initElements(driver, this);
    }

    public void viewBasket() {

        /**
         * View the basket and confirm that the items are of the product name,size you selected and the prices are correct
         */
        for (int i = 1; i <= prodDetails.size(); i++) {
            WebElement prodDesc = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='cart_description']/p[@class='product-name']/a"));
            WebElement prodsize = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='cart_description']/small/a"));
            WebElement prodprice = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='cart_unit']/span/span"));

            assertTrue(prodDesc.getText().equalsIgnoreCase(prodDetails.get(i - 1).getItemname()), "Verfies that the product details name is same as in the basket");
            assertTrue(prodsize.getText().substring((prodsize.getText().length()) - 1).equalsIgnoreCase(prodDetails.get(i - 1).getSize()), "Verfies that the product details size is same as in the basket");
            assertTrue(prodprice.getText().equalsIgnoreCase(prodDetails.get(i - 1).getPrice()), "Verfies that the product details price is same as in the basket");

        }
    }

    public void checkTotalBasketPrice() throws Throwable {
        /**
         * Verfiies that the Total Products is the sum of the two items added to the cart and that ‘Total’=Total Products + Shipping.
         */

        float totprice = 2;
        for (int i = 0; i < prodDetails.size(); i++)
            totprice += Float.parseFloat(prodDetails.get(i).getPrice().substring(1, (prodDetails.get(i).getPrice().length())));

        BigDecimal totalprice = new BigDecimal(totprice);
        totalprice = totalprice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal calculatedTotalPrice = new BigDecimal(getElement(totalPrice, 2).getText().substring(1, ((getElement(totalPrice, 2)).getText().length())));
        calculatedTotalPrice = calculatedTotalPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println("totalprice :" + totalprice);
        System.out.println("to be added value :" + Float.parseFloat(getElement(totalPrice, 2).getText().substring(1, ((getElement(totalPrice, 2)).getText().length()))));

        assertTrue(totalprice.equals(calculatedTotalPrice), "Verifies that the Total price of products and shipping equals Total price");

    }

    public void proceedTOCheckoutonSummaryPage() throws Throwable {
        /**
         * proceeds to checkout from the Summary Page and navigates to Address Page
         */
        try {
            getElement(proceedCOutSummary, 3).click();
            assertTrue(deliverAddress.isDisplayed(),"Verifies that the delivery addresses are displayed on the Address Page");
        } catch (Throwable throwable) {
            throw new Exception("Unable to proceed to checkout on the Summary Page");
        }
    }

    public void proceedTOCheckoutonAddressPage() throws Throwable {
        /**
         * proceeds to checkout from the Address Page and navigates to Shipping Page
         */
        try {
            getElement(proceedCOutAddress, 3).click();
            assertTrue(shippingOption.isDisplayed(),"Verifies that the shipping option is displayed on the shipping Page");
        } catch (Throwable throwable) {
            throw new Exception("Unable to proceed to checkout on the Address Page");
        }
    }

    public void agreeTermsonShippingPage() throws Throwable {
        /**
         * Agree the terms and conditions on the Shipping page
         */
        try {
            getElement(agreeTerms, 3).click();
            } catch (Throwable throwable) {
            throw new Exception("Unable to click the button agree terms on Shipping Page");
        }
    }

    public void proceedTOCheckoutonShippingPage() throws Throwable {
        /**
         * proceeds to checkout from the Shipping Page and navigates to Payment Page
         */
        try {
            getElement(proceedCOutShipping, 3).click();
            assertTrue(orderDetailsPaymenPage.isDisplayed(),"Verifies that the order details are displayed on the payment page");
        } catch (Throwable throwable) {
            throw new Exception("Unable to proceed to checkout on the Shipping Page");
        }
    }

    public void payByBankWire() throws Throwable {
        /**
         * Pays by the Bank Wire on the Payment Page
         */
        try {
            getElement(payByWireOption, 3).click();
            assertTrue(orderSummary.isDisplayed(),"Verifies that the Order Summary is displayed on the payment page");
        } catch (Throwable throwable) {
            throw new Exception("Unable to click the button pay by bank wire");
        }
    }

    public void confirmOrder() throws Throwable {
        /**
         * confirms the order on the Payment Page
         */
        try {
            getElement(confirmOrderOption, 3).click();
            assertTrue(orderConfirmation.isDisplayed(),"Verifies that the Order confirmation is displayed on order confirmation page");
        } catch (Throwable throwable) {
            throw new Exception("Unable to confirm the order");
        }
    }
}
