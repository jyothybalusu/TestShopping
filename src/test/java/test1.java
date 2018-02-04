import automation.Pages.categoryPage;
import automation.Pages.checkout;
import automation.Pages.myAccount;
import automation.Pages.product;
import library.Utility;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static automation.driver.base.*;

public class test1  {

   myAccount acc;
   categoryPage category;
   product prod;
    checkout cout;


  @BeforeClass
       public static void setup() throws Throwable {
        navigateToHome();

    }


    @Test
    public void testOne() throws Throwable {
        acc = new myAccount(driver);
        category = new categoryPage(driver);
        prod = new product(driver);
        cout = new checkout(driver);
        acc.clickSignInLink();
        acc.signIn("jyothy18@mailinator.com","BJSSTest");
        acc.navigateTOHomePage();
        category.clickItem("women",1);
        prod.changesize(2);
        prod.addToCart();
        prod.continueShopping();
        acc.navigateTOHomePage();
        category.clickItem("dresses",2);
        prod.addToCart();
        prod.proceedToCheckout();
        cout.viewBasket();
        cout.checkTotalBasketPrice();
        cout.proceedTOCheckoutonSummaryPage();
        cout.proceedTOCheckoutonAddressPage();
        cout.agreeTermsonShippingPage();
        cout.proceedTOCheckoutonShippingPage();
        cout.payByBankWire();
        cout.confirmOrder();
        acc.logOut();
    }
    @AfterMethod
    public void tearDown(ITestResult result)
    {
        if(ITestResult.FAILURE==result.getStatus()){
            Utility.captureScreenshot(driver,result.getName());
        }
        driver.quit();
    }

    @AfterClass
     public static void closeSetup()
    {
        close();
    }
}
