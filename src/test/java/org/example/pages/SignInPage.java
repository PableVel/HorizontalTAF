package org.example.pages;

import java.net.MalformedURLException;

import org.example.utils.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SignInPage extends Extensions {
	@FindBy(id = "ap_email")
	WebElement emailField;

	@FindBy(id = "ap_password")
	WebElement passwordField;

	@FindBy(id = "continue")
	WebElement continueButton;

	@FindBy(id = "signInSubmit")
	WebElement signInButton;

	public SignInPage() throws MalformedURLException {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	public void signIn(String email, String password) throws InterruptedException {
		sendKeys(emailField, email);
		enterByActions(continueButton);
		waitHelper.waitForPageLoaded();
		sendKeys(passwordField, password);
		executeAction(Keys.TAB);
		executeAction(Keys.ENTER);
	}

}
