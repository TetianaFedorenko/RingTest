package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.IntStream;

import static core.TestBase.webDriver;

public class CartPage extends GeneralPage {

    //Locators
    public static String removeButtonXpath = "//*[@id='cart']//td[2]/descendant::a[contains(@class,'cart__remove')]";
    public String productXpath = "//*[@id='cart']//tbody//td";
    public static String yourCartIsEmptyTextXpath = "//*[text()='Your cart is currently empty']";

    static List<WebElement> listOfElementsToRemove;

    public static void removeItemFromTheCart() {
        listOfElementsToRemove = webDriver.findElements(By.xpath(removeButtonXpath));
        IntStream.range(0, listOfElementsToRemove.size()).forEach(
                i -> listOfElementsToRemove.get(i).click()
        );
    }
}
