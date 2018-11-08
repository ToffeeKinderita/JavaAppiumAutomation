package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_RESULTS_BY_TITLE_AND_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']|//*[@text='{DESCRIPTION}']",
            SEARCH_RESULT_By_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULTS_LOCATOR = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            EMPTY_RESULT_LABEL = "//*[@text='No results found']",
            EMPTY_PAGE = "//*[@resource-id='org.wikipedia:id/search_empty_container']";

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
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and click search init element", 5);
    }

    public WebElement findSearchLine() {
        return this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find and click search init element", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelButton() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULTS_LOCATOR),
                "Cannot find search items",
                15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULTS_LOCATOR));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(EMPTY_RESULT_LABEL), "Cannot find empty result element", 15);
    }

    public void asserThereIsNoResultsOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULTS_LOCATOR), "We supposed to not find any results");
    }

    public WebElement showEmptySearchPage() {
        return this.waitForElementPresent(By.xpath(EMPTY_PAGE), "Search results weren't cleared", 5);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchElementByTwoOptions(title, description);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Title " + title + " or description " + description + " doesn't match " + search_result_xpath, 10);
    }
}
