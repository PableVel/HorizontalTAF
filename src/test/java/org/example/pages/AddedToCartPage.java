package org.example.pages;

import org.example.utils.Extensions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AddedToCartPage extends Extensions {
	@FindBy(xpath = "//div[@id = 'sw-subtotal']")
	WebElement cartSubtotal;

	@FindBy(name = "proceedToRetailCheckout")
	WebElement proceedToRetailCheckoutButton;

	public AddedToCartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getCartSubtotal(){
		return getPropertyFromElement(cartSubtotal, "data-price");
	}

	public void proceedToCheckout(){
		click(proceedToRetailCheckoutButton);
	}

}
