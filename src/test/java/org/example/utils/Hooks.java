package org.example.utils;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class Hooks {
	protected WebDriver driver;
	protected WaitHelper waitHelper;

	@BeforeMethod
	public void beforeTest() throws InterruptedException, MalformedURLException {
		driver = Driver.getDriver();
		waitHelper = new WaitHelper();
		driver.manage().window().maximize();
		driver.get("https://www.bookdepository.com/");
		waitHelper.waitForPageLoaded();
	}

	@AfterMethod
	public void  afterTest(){
		Driver.quitDriver();
	}
}
