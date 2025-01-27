package org.example.utils;

import org.testng.annotations.DataProvider;


public class TestDataProvider {

	@DataProvider(name = "searchItems")
	public static Object[][] getSearchItems() {
		return new Object[][] { { "Thinking in Java", "USD 74.35" }, { "Book Item", "USD 152" } };
	}
}
