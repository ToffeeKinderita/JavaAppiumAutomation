package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class SearchTests extends CoreTestCase {

    @Test
    public void testAmountOfSearchResults() {
        String search_line = "Linkin Park Discography";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();
        assertTrue("We found some results", amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        String search_line = "rggdsvdr";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.asserThereIsNoResultsOfSearch();
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelButton();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchPlaceholer() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        WebElement placeholder = searchPageObject.findSearchLine();
        assertTrue(placeholder.isDisplayed());
    }

    @Test
    public void testClearSearchResults() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        searchPageObject.clickCancelButton();
        WebElement emptyScreen = searchPageObject.showEmptySearchPage();
        assertTrue(emptyScreen.isDisplayed());
    }

    @Test
    public void testSearchResultsByTitleAndDescription() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("London");
        searchPageObject.waitForElementByTitleAndDescription("London", "Capital and largest city of the United Kingdom");
    }

    @Test
    public void testExFour() {
        String search_param = "London";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);;
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_param);
        searchPageObject.assertSearchResultsContainSearchParameter(search_param);
    }
}
