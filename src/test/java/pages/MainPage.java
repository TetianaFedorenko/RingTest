package pages;

public class MainPage extends GeneralPage {

    //URLs
    public final static String RING_MAIN_URL = "https://ring.com/";

    //Locators
    public String productsButtonXpath = "//*[@href='//ring.com/products']";
    public String videoDoorbellsXpath = "//*[@class='products-menu-list']/li[1]/a[@href='//ring.com/videodoorbells']";
    public String cartXpath = "//*[@id='header']//*[@data-metric='Cart']";
    public String viewCartXpath = "//*[text()='View Cart']";

    public void openCategoryPage(String categoryXpath, String subcategoryXpath) {
        moveToWebElement(findElementByXpath(categoryXpath));
        findElementByXpath(subcategoryXpath).click();
    }
}
