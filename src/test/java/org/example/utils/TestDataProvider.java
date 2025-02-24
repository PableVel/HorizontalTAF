package org.example.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name = "searchItems", parallel = true)
	public static Object[][] getSearchItems() {
		return new Object[][] { { "Thinking in Java", "USD 74.35" }, { "Balon", "USD 586.57" } };
	}
}
