package pages;

import org.openqa.selenium.WebElement;
import testData.TestData;

import java.util.stream.IntStream;

public class VideoDoorbellPage extends GeneralPage{

    //Locators
    public String upButtonXpath = "//*[@class='qty_btn up']";
    public String productPriceXpath = "//*[@class='product-single__meta']/descendant::span[@id='ProductPrice-product-template']";
    public String productNameXpath = "//*[@class='product-single__meta']/*[@itemprop='name']";
    public String addToCartButtonId = "AddToCartText-product-template";

    public int randomQuantityOfItems = getRandomNumber(TestData.MIN_CLICKING_NUMBER, TestData.MAX_CLICKING_NUMBER);
    public int itemsAmount = randomQuantityOfItems + 1;

    public void increaseNumberOfItems(String xpath, int n) {
        WebElement upButton = findElementByXpath(xpath);
        IntStream.range(0, n).forEach(
                i -> upButton.click()
        );
    }
}
