package org.example.pages;

import org.example.utils.Extensions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends Extensions {
	@FindBy(name = "field-keywords")
	WebElement searchField;

	@FindBy(id = "nav-search-submit-button")
	WebElement searchButton;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void searchItem(String textItem){
		sendKeys(searchField,textItem);
		click(searchButton);
	}

}
