package org.example.utils;

import java.net.MalformedURLException;
import java.net.URL;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;


public class Driver {
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


	public static WebDriver getDriver() throws MalformedURLException {
		String browser = null;
		if (browser == null) {
			browser = "chrome";
		}
		WebDriver driver = driverThreadLocal.get();
		if (driver == null) {
			switch (browser.toLowerCase()) {
				case "firefox":
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					driver = new RemoteWebDriver(new URL("http://192.168.100.7:4444/wd/hub"), firefoxOptions);
					break;
				case "chrome":
				default:
					ChromeOptions chromeOptions = new ChromeOptions();
					driver = new RemoteWebDriver(new URL("http://192.168.100.7:4444/wd/hub"), chromeOptions);
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