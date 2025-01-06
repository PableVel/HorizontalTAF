package org.example.pages;

import org.example.utils.Extensions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ItemPage extends Extensions {
	@FindBy(id = "add-to-cart-button")
	WebElement addToCartButton;

	public ItemPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void addItemToCart(){
		click(addToCartButton);
		waitHelper.waitForPageLoaded();
	}

}
