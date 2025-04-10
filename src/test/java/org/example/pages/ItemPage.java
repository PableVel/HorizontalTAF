package org.example.pages;

import java.net.MalformedURLException;

import org.example.utils.Driver;
import org.example.utils.Extensions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ItemPage extends Extensions {
	@FindBy(xpath = "//input[contains(@id,'add-to-cart-button')]")
	WebElement addToCartButton;

	public ItemPage() throws MalformedURLException {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	public void addItemToCart() throws InterruptedException {
		click(addToCartButton);
		waitHelper.waitForPageLoaded();
	}

}
