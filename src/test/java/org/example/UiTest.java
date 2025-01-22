package org.example;

import org.example.pages.*;
import org.example.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class UiTest extends Hooks {

	@Test(dataProvider = "searchItems", dataProviderClass = TestDataProvider.class)
	public void searchTest(String searchItem, String expectedSubtotal) throws InterruptedException {
		MainPage mainPage = new MainPage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		ItemPage itemPage = new ItemPage(driver);
		AddedToCartPage addedToCartPage = new AddedToCartPage(driver);
		SignInPage signInPage = new SignInPage(driver);

		int minimumItems = 3;
		int indexOption = 2;
		String email = "testpablo364@gmail.com";
		String password = "Password123.";
		String firstItemNameBeforeFilter;
		String firstItemNameAfterFilter;

		mainPage.searchItem(searchItem);
		Assert.assertTrue(searchResultsPage.assertMinimumItems(minimumItems));
		firstItemNameBeforeFilter = searchResultsPage.getFirstItemName();
		searchResultsPage.filterResults(indexOption);
		firstItemNameAfterFilter= searchResultsPage.getFirstItemName();
		Assert.assertNotEquals(firstItemNameBeforeFilter,firstItemNameAfterFilter);

		searchResultsPage.selectItemNameByIndex(indexOption);
		itemPage.addItemToCart();
		Assert.assertEquals(addedToCartPage.getCartSubtotal(),expectedSubtotal);

		addedToCartPage.proceedToCheckout();
		signInPage.signIn(email, password);

	}
}
