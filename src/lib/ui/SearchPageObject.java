package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]",
            SEARCH_RESULTS_BY_TITLE_AND_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']|//*[@text='{DESCRIPTION}']",
            SEARCH_RESULT_By_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULTS_LOCATOR = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            EMPTY_RESULT_LABEL = "xpath://*[@text='No results found']",
            EMPTY_PAGE = "xpath://*[@resource-id='org.wikipedia:id/search_empty_container']",
            SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_title";
    ;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    // TEMPLATE METHODS
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_By_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTwoOptions(String title, String description) {
        return SEARCH_RESULTS_BY_TITLE_AND_SUBSTRING_TPL.replace("{DESCRIPTION}", description).replace("{TITLE}", title);
    }
    // TEMPLATE METHODS

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and click search init element", 5);
    }

    public WebElement findSearchLine() {
        return this.waitForElementPresent(SEARCH_INPUT, "Cannot find and click search init element", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULTS_LOCATOR,
                "Cannot find search items",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULTS_LOCATOR);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(EMPTY_RESULT_LABEL, "Cannot find empty result element", 15);
    }

    public void asserThereIsNoResultsOfSearch() {
        this.assertElementNotPresent(SEARCH_RESULTS_LOCATOR, "We supposed to not find any results");
    }

    public WebElement showEmptySearchPage() {
        return this.waitForElementPresent(EMPTY_PAGE, "Search results weren't cleared", 5);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTwoOptions(title, description);
        this.waitForElementPresent(search_result_xpath, "Title " + title + " or description " + description + " doesn't match " + search_result_xpath, 10);
    }


    public void assertSearchResultsContainSearchParameter(String search_param) {
        By by = this.getLocatorSring(search_param);
        List searchElements = driver.findElements(by);
        for (Object el : searchElements) {
            el = this.waitForElementAndGetAttribute(SEARCH_RESULTS, "text", "Element wasn't found " + SEARCH_RESULTS, 10);
            Assert.assertTrue("Search results contain invalid value: expected - " + search_param + ", actual: " + el, ((String) el).contains(search_param));
        }
    }
}
