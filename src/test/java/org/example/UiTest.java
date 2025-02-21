package org.example;

import java.net.MalformedURLException;

import org.example.pages.*;
import org.example.utils.*;
import org.testng.Assert;
import org.testng.annotations.*;



public class UiTest extends Hooks {

	@Test(dataProvider = "searchItems", dataProviderClass = TestDataProvider.class)
	public void searchTest(String searchItem, String expectedSubtotal) throws InterruptedException,
			MalformedURLException {
		MainPage mainPage = new MainPage();
		SearchResultsPage searchResultsPage = new SearchResultsPage();
		ItemPage itemPage = new ItemPage();
		AddedToCartPage addedToCartPage = new AddedToCartPage();
		SignInPage signInPage = new SignInPage();

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
		firstItemNameAfterFilter = searchResultsPage.getFirstItemName();
		Assert.assertNotEquals(firstItemNameBeforeFilter, firstItemNameAfterFilter);

		searchResultsPage.selectItemNameByIndex(indexOption);
		itemPage.addItemToCart();
		Assert.assertEquals(addedToCartPage.getCartSubtotal(), expectedSubtotal);

		addedToCartPage.proceedToCheckout();
		signInPage.signIn(email, password);

	}

	@Test()
	public void simpleTest() throws MalformedURLException {

		MainPage mainPage = new MainPage();
		String itemToSearch = "Harry Potter";
		mainPage.searchItem(itemToSearch);

		SearchResultsPage searchResultsPage = new SearchResultsPage();
		Assert.assertTrue(searchResultsPage.getFirstItemName().contains(itemToSearch));
	}


}
