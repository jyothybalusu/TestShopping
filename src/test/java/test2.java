import automation.Pages.*;
import library.Utility;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static automation.driver.base.*;


public class test2 {

    myAccount acc;
    categoryPage category;
    product prod;
    checkout cout;
    ordersHistory oHist;


    @BeforeClass
    public static void setup() throws Throwable {
        navigateToHome();
    }


    @Test
    public void testTwo() throws Throwable {
    acc = new myAccount(driver);
        category = new categoryPage(driver);
        prod = new product(driver);
        cout = new checkout(driver);
        oHist = new ordersHistory(driver);
        acc.clickSignInLink();
        acc.signIn("jyothy18@mailinator.com","BJSSTest");
        acc.navigateToOrderHistory();
        oHist.selectOrderFromOrderHistroy("02/01/2018");
        oHist.selectproductFromselectedOrder(1);
        oHist.addCommentsToProducts("test comments on product");
        oHist.checkMessageUnderMessages("test comments on product");
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

