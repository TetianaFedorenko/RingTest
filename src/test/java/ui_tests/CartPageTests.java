package ui_tests;

import core.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import testData.TestData;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import static pages.GeneralPage.findElementByXpath;

public class CartPageTests extends TestBase {

    MainPage mainPage;
    VideoDoorbellsPage videoDoorbellsPage;
    VideoDoorbellPage videoDoorbellPage;
    CartPage cartPage;

    List<WebElement> actualListOfDoorbellsParameters;
    List<WebElement> actualListOfChosenItems;
    WebElement buyNowButton;
    String actualDoorbellPrice;
    String actualDoorbellName;
    int actualItemsQuantity;
    BigDecimal actualTotalPrice;
    String expectedDoorbellName;
    String expectedDoorbellPrice;
    int expectedItemsQuantity;
    BigDecimal expectedTotalPrice;
    WebElement viewCartButton;

    @Test
    public void checkDoorbellParametersInTheCart() {

        mainPage = new MainPage();
        videoDoorbellsPage = new VideoDoorbellsPage();
        videoDoorbellPage = new VideoDoorbellPage();
        cartPage = new CartPage();

        //Expected Results
        expectedDoorbellName = TestData.expectedListOfDoorbellsNamesAndPrices[videoDoorbellsPage.locationOfRandomItem][TestData.NAME_IDENTIFIER];
        expectedDoorbellPrice = TestData.expectedListOfDoorbellsNamesAndPrices[videoDoorbellsPage.locationOfRandomItem][TestData.PRICE_IDENTIFIER];
        expectedItemsQuantity = videoDoorbellPage.itemsAmount;
        expectedTotalPrice = new BigDecimal(videoDoorbellPage.itemsAmount * Double.parseDouble(expectedDoorbellPrice.substring(1))).setScale(2, BigDecimal.ROUND_HALF_UP);

        //Step 1: Open page with doorbells
        mainPage.openCategoryPage(mainPage.productsButtonXpath, mainPage.videoDoorbellsXpath);
        videoDoorbellsPage.scrollIntoView(mainPage.findElementById(videoDoorbellsPage.doorbellsCollectionId));

        //Check price and doorbell name fields
        actualListOfDoorbellsParameters = videoDoorbellsPage.getListOfElements(videoDoorbellsPage.listOfDoorbellsXpath);
        IntStream.range(0, actualListOfDoorbellsParameters.size()-1).forEach(
                i -> {
                    Assert.assertTrue(actualListOfDoorbellsParameters.get(i).getText()
                            .contains(TestData.expectedListOfDoorbellsNamesAndPrices[i][TestData.NAME_IDENTIFIER]));
                    Assert.assertTrue(actualListOfDoorbellsParameters.get(i).getText()
                            .contains(TestData.expectedListOfDoorbellsNamesAndPrices[i][TestData.PRICE_IDENTIFIER]));
                }
        );

        //Step 2: Push «buy now» for doorbell
        buyNowButton = findElementByXpath(videoDoorbellsPage.buyNowButtonXpath);
        videoDoorbellsPage.scrollIntoView(buyNowButton);
        videoDoorbellsPage.clickElement(buyNowButton);

        //Step 3: Change quantity
        videoDoorbellPage.increaseNumberOfItems(videoDoorbellPage.upButtonXpath, videoDoorbellPage.randomQuantityOfItems);

        //Check correct price and name mapping for doorbell
        actualDoorbellPrice = findElementByXpath(videoDoorbellPage.productPriceXpath).getText();
        actualDoorbellName = findElementByXpath(videoDoorbellPage.productNameXpath).getText();
        Assert.assertEquals(actualDoorbellPrice, expectedDoorbellPrice);
        Assert.assertEquals(actualDoorbellName, expectedDoorbellName);

        //Step 4: Push «add to cart»
        videoDoorbellPage.findElementById(videoDoorbellPage.addToCartButtonId).click();

        //Step 5: Open Cart
        mainPage.clickElement(mainPage.findElementByXpath(mainPage.cartXpath));
        viewCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mainPage.viewCartXpath)));
        mainPage.clickElement(viewCartButton);

        //Check that price, name of doorbell, final amount and total price are correct
        actualListOfChosenItems = videoDoorbellsPage.getListOfElements(cartPage.productXpath);
        actualDoorbellName = actualListOfChosenItems.get(1).getText().substring(0, actualListOfChosenItems.get(1).getText().indexOf("\n"));
        actualDoorbellPrice = actualListOfChosenItems.get(2).getText();
        actualItemsQuantity = Integer.parseInt(actualListOfChosenItems.get(3).findElement(By.tagName(TestData.INPUT_TAG_NAME)).getAttribute(TestData.VALUE_ATTRIBUTE));
        actualTotalPrice = new BigDecimal(actualListOfChosenItems.get(4).findElement(By.tagName(TestData.DIV_TAG_NAME)).getText().substring(1).replace(",", ""));

        Assert.assertEquals(actualDoorbellName, expectedDoorbellName, TestData.nameOfProductIsDifferentThanExpected);
        Assert.assertEquals(actualDoorbellPrice, expectedDoorbellPrice, TestData.priceOfProductIsDifferentThanExpected);
        Assert.assertEquals(actualItemsQuantity, expectedItemsQuantity, TestData.itemQuantityIsDifferentThanExpected);
        Assert.assertEquals(actualTotalPrice, expectedTotalPrice, TestData.totalPriceIsDifferentThanExpected);
    }
}
