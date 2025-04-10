package org.example.pages;

import java.net.MalformedURLException;

import org.example.utils.Driver;
import org.example.utils.Extensions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage extends Extensions {
	@FindBy(name = "field-keywords")
	WebElement searchField;

	@FindBy(id = "nav-search-submit-button")
	WebElement searchButton;

	public MainPage() throws MalformedURLException {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	public void searchItem(String textItem){
		sendKeys(searchField,textItem);
		click(searchButton);
	}

}
