package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveArticleToMyList() {
        String name_of_folder = "Learning programming";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticles() {
        String name_of_folder = "Tamara";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        NavigationUI navigationUI = new NavigationUI(driver);
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.closeArticle();
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Appium");
        searchPageObject.clickByArticleWithSubstring("Appium");
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToMyList(name_of_folder);
        articlePageObject.addArticleToAlredyCreatedFolder(name_of_folder);
        articlePageObject.closeArticle();
        navigationUI.clickMyLists();
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);
        myListsPageObject.waitForArticleToAppearByTitle("Appium");
    }
}
