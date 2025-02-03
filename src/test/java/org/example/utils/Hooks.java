package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class Hooks {
	protected ThreadLocal<WebDriver> driver = Driver.getDriverThreadLocal();
	protected WaitHelper waitHelper;

	@BeforeMethod
	public void beforeTest() throws InterruptedException {
		driver.set(Driver.getDriver());
		waitHelper = new WaitHelper();
		driver.get().manage().window().maximize();
		driver.get().get("https://www.bookdepository.com/");
		waitHelper.waitForPageLoaded();
	}

	@AfterMethod
	public void afterTest() {
		Driver.quitDriver();
	}
}
