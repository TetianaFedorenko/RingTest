package testData;

public interface TestData {

    //Expected Results
    String[][] expectedListOfDoorbellsNamesAndPrices = {
            {"Door View Cam", "$199.00"},
            {"Video Doorbell", "$99.99"},
            {"Video Doorbell 2", "$199.00"},
            {"Video Doorbell Pro", "$169.00"},
            {"Video Doorbell Elite", "$499.00"}
    };

    //Constants
    int FIRST_PRODUCT = 1;
    int LAST_PRODUCT = 5;
    int NAME_IDENTIFIER = 0;
    int PRICE_IDENTIFIER = 1;
    int MIN_CLICKING_NUMBER = 0;
    int MAX_CLICKING_NUMBER = 20;
    String INPUT_TAG_NAME = "input";
    String DIV_TAG_NAME = "div";
    String VALUE_ATTRIBUTE = "value";

    //Error Strings
    String nameOfProductIsDifferentThanExpected= "NAME OF THE PRODUCT IS DIFFERENT THAN EXPECTED";
    String priceOfProductIsDifferentThanExpected= "PRICE OF THE PRODUCT IS DIFFERENT THAN EXPECTED";
    String itemQuantityIsDifferentThanExpected= "QUANTITY OF ITEMS IS DIFFERENT THAN EXPECTED";
    String totalPriceIsDifferentThanExpected= "TOTAL PRICE IS DIFFERENT THAN EXPECTED";
}
