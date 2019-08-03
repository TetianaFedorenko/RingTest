package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testData.TestData;

import java.util.List;

import static core.TestBase.webDriver;

public class VideoDoorbellsPage extends GeneralPage {

    int randomItem = getRandomNumber(TestData.FIRST_PRODUCT, TestData.LAST_PRODUCT);
    public int locationOfRandomItem = randomItem - 1;

    //Locators
    public String buyNowButtonXpath = String.format("//*[@id='Collection']//*[@class='products doorcams doorbells-pmp']//div[%s]//*[text()='Buy Now']"
            , randomItem);
    public String listOfDoorbellsXpath = "//*[@class='product cflex']";
    public String doorbellsCollectionId = "Collection";

    public List<WebElement> getListOfElements(String xpath) {
        return webDriver.findElements(By.xpath(xpath));
    }
}
