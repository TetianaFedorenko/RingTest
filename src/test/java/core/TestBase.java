package core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.GeneralPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static pages.CartPage.removeItemFromTheCart;
import static pages.CartPage.yourCartIsEmptyTextXpath;
import static pages.GeneralPage.*;
import static pages.MainPage.RING_MAIN_URL;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    public static WebDriver webDriver;
    public static WebDriverWait wait;

    @BeforeClass
    public void startDriver() throws IOException {
        webDriver = WebDriverFactory.getWebDriver(System.getProperty(DRIVER));
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, 10);
    }

    @BeforeMethod
    public void openPage() {
        open(RING_MAIN_URL);
        findElementByXpath(GeneralPage.initialPopupCloseButtonXpath).click();

    }

    @AfterMethod
    public void cleanCart() {
        if (findElementsByXpath(yourCartIsEmptyTextXpath).isEmpty()) {
            removeItemFromTheCart();
        }
    }

    @AfterClass
    public static void stopDriver() {
        tearDown();
    }
}
