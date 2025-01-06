package org.example;

import org.example.pages.*;
import org.example.utils.Driver;
import org.example.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class UiTest {


	WebDriver driver;
	WaitHelper waitHelper;


	@BeforeMethod
	public void beforeTest() {
		driver = Driver.getDriver();
		waitHelper = new WaitHelper(driver);
		driver.manage().window().maximize();
		driver.get("https://www.bookdepository.com/");
		waitHelper.waitForPageLoaded();
	}

	@AfterMethod
	public void  afterTest(){
		driver.quit();
	}

	@Test
	public void searchTest() {
		MainPage mainPage = new MainPage(driver);
		SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
		ItemPage itemPage = new ItemPage(driver);
		AddedToCartPage addedToCartPage = new AddedToCartPage(driver);
		SignInPage signInPage = new SignInPage(driver);

		String ItemText = "Thinking in Java";
		int minimumItems = 3;
		int indexOption = 2;
		String expectedSubtotal = "USD 74.35";
		String email = "testpablo364@gmail.com";
		String password = "Password123.";
		String publicationDateBeforeFilter;
		String publicationDateAfterFilter;

		mainPage.searchItem(ItemText);
		Assert.assertTrue(searchResultsPage.assertMinimumItems(minimumItems));

		publicationDateBeforeFilter = searchResultsPage.getItemNameByIndex(0);
		searchResultsPage.filterResults(indexOption);
		publicationDateAfterFilter= searchResultsPage.getItemNameByIndex(0);
		Assert.assertNotEquals(publicationDateAfterFilter,publicationDateBeforeFilter);

		searchResultsPage.selectItemNameByIndex(indexOption);
		itemPage.addItemToCart();
		Assert.assertEquals(addedToCartPage.getCartSubtotal(),expectedSubtotal);

		addedToCartPage.proceedToCheckout();
		signInPage.signIn(email, password);

	}
}
