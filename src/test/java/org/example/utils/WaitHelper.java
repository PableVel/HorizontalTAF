package org.example.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;


public class WaitHelper {
	private static WebDriver driver;
	private static WebDriverWait wait;

	public WaitHelper(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
	}

	public static WebElement waitUntilElementVisible(WebElement element){
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilElementClickable(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitUntilFieldIsNotEmpty(WebElement field){
		wait.until(driver -> !field.getAttribute("value").isEmpty());	}

	public static void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = driver ->
				((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

		try {
			Thread.sleep(2000);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	}
}