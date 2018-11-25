package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_RESULTS_BY_TITLE_AND_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']|//*[@text='{DESCRIPTION}']";
        SEARCH_RESULT_By_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULTS_LOCATOR = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        EMPTY_RESULT_LABEL = "xpath://*[@text='No results found']";
        EMPTY_PAGE = "xpath://*[@resource-id='org.wikipedia:id/search_empty_container']";
        SEARCH_RESULTS = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
