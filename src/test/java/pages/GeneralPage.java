package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

import static core.TestBase.webDriver;

public class GeneralPage {

    Actions action = new Actions(webDriver);
    Random random = new Random();

    public static String initialPopupCloseButtonXpath = "//*[@id='intlUsersPopup']/div/a[@class='close']";
    public static final String DRIVER = "driver";

    public static void tearDown() {
        webDriver.quit();
    }

    public static void open(String url) {
        webDriver.get(url);
    }

    public static WebElement findElementByXpath(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    public WebElement findElementById(String id) {
        return webDriver.findElement(By.id(id));
    }

    public static List<WebElement> findElementsByXpath(String xpath) {
        return webDriver.findElements(By.xpath(xpath));
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void moveToWebElement(WebElement element) {
        action.moveToElement(element).perform();
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public int getRandomNumber(int min, int max) {
        int diff = max - min;
        return random.nextInt(diff + 1) + min;
    }
}
