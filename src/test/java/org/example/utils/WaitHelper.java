package org.example.utils;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


public class WaitHelper {
	private WebDriverWait wait;
	private ThreadLocal<WebDriver> driver = Driver.getDriverThreadLocal();

	public WaitHelper() {
		driver.set(Driver.getDriver());
		this.wait = new WebDriverWait(driver.get(), 10);
	}

	public WebElement waitUntilElementVisible(WebElement element) {
		return waitUntilCondition(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementClickable(WebElement element) {
		waitUntilCondition(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilFieldIsNotEmpty(WebElement field) {
		wait.until(driver -> !field.getAttribute("value").isEmpty());
	}

	public void waitForAllElements(List<WebElement> elementsList) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elementsList));
	}

	public void waitForPageLoaded() throws InterruptedException {
		int attempts = 0;
		while (!((JavascriptExecutor) driver.get()).executeScript("return document.readyState").equals("complete")) {
			Thread.sleep(200);
			attempts++;
			if (attempts > 10) {
				throw new RuntimeException("Unexpected: The page does not load");
			}
		}
	}

	private WebElement waitUntilCondition(Function<WebDriver, WebElement> condition) {
		final int MAX_ATTEMPTS = 5;
		for (int i = 0; i < MAX_ATTEMPTS; i++) {
			try {
				return wait.until(condition);
			} catch (StaleElementReferenceException exception) {
				if (i == MAX_ATTEMPTS - 1) {
					throw exception;
				}
			}
		}
		return null;
	}
}