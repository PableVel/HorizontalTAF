package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Driver {
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

	public static WebDriver getDriver() {
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		WebDriver driver = driverThreadLocal.get();
		if (driver == null) {
			switch (browser.toLowerCase()) {
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					break;
				case "chrome":
				default:
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
			}
			driverThreadLocal.set(driver);
		}
		return driverThreadLocal.get();
	}

	public static ThreadLocal<WebDriver> getDriverThreadLocal() {
		return driverThreadLocal;
	}


	public static void quitDriver() {
		WebDriver driver = driverThreadLocal.get();
		if (driver != null) {
			driver.quit();
			driverThreadLocal.remove();
		}
	}
}