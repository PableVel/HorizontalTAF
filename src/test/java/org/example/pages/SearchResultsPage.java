package org.example.pages;

import java.util.List;

import org.example.utils.Extensions;
import org.example.utils.WaitHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class SearchResultsPage extends Extensions {
	@FindBy(xpath = "//div[@role='listitem']")
	List<WebElement> itemsList;

	@FindBy(name = "s")
	WebElement filterSelect;


	public SearchResultsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void filterResults (int indexOption) throws InterruptedException {
		Select select = new Select(filterSelect);
		select.selectByIndex(indexOption);
		waitHelper.waitForPageLoaded();
		waitHelper.waitForAllElements(itemsList);
	}

	public String getFirstItemName() {
		waitHelper.waitUntilElementVisible(itemsList.get(0));
		return itemsList.get(0).findElement(By.xpath(".//h2/span")).getText();
	}

	public void selectItemNameByIndex(int indexCard) {
		waitHelper.waitUntilElementClickable(itemsList.get(indexCard));
		click(itemsList.get(indexCard).findElement(By.xpath(".//h2/span")));
	}

	public Boolean assertMinimumItems(int minimumItems){
		waitHelper.waitForAllElements(itemsList);
		if(itemsList.size()<minimumItems){
			log.error("The total of results is "+itemsList.size());
			return false;
		}
		return true;
	}
}
