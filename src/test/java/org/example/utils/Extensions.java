package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Extensions {
	private static WebDriver driver;
	protected static WaitHelper waitHelper;
	private JavascriptExecutor js;
	private Actions actions;

	public Extensions(WebDriver driver){
		this.driver = driver;
		waitHelper = new WaitHelper(driver);
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	public void click(WebElement webElement){
		waitHelper.waitUntilElementClickable(webElement);
		js.executeScript("arguments[0].click();", webElement);
	}

	public String getTextFromElement(WebElement webElement){
		waitHelper.waitUntilElementVisible(webElement);
		return webElement.getText();
	}

	public String getPropertyFromElement(WebElement webElement, String attribute){
		waitHelper.waitUntilElementVisible(webElement);
		return webElement.getAttribute(attribute);
	}

	public void sendKeys(WebElement webElement, String value){
		waitHelper.waitUntilElementClickable(webElement);
		js.executeScript("arguments[0].value='"+value+"';", webElement);
		waitHelper.waitUntilFieldIsNotEmpty(webElement);
	}

	public void enterByActions(WebElement elementToClick){
		actions.moveToElement(elementToClick).sendKeys(Keys.ENTER).build().perform();
	}

	public void executeAction(Keys actionToExecute){
		actions.sendKeys(actionToExecute).build().perform();
	}
}
