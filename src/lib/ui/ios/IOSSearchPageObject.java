package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_RESULTS_BY_TITLE_AND_SUBSTRING_TPL = "xpath://XCUIElementTypeLink//*[@text='{TITLE}']|//*[@text='{DESCRIPTION}']";
        SEARCH_RESULT_By_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@text,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULTS_LOCATOR = "xpath://XCUIElementTypeLink";
        EMPTY_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        EMPTY_PAGE = "xpath://*[@resource-id='org.wikipedia:id/search_empty_container']";
        SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_title";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
