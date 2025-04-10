package org.example.pages;

import java.net.MalformedURLException;
import java.util.List;

import org.example.utils.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2

public class SearchResultsPage extends Extensions{
	@FindBy(xpath = "//div[@role='listitem']")
	List<WebElement> itemsList;

	@FindBy(xpath = "//li[@class='a-dropdown-item']")
	List<WebElement> filterOptions;

	@FindBy(xpath = "//span[@class='a-dropdown-prompt']")
	WebElement filterDropdown;


	public SearchResultsPage() throws MalformedURLException {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	public void filterResults (int indexOption)  {
		click(filterDropdown);
		waitHelper.waitForAllElements(filterOptions);
		for(int i = 0; i< indexOption; i++){
			executeAction(Keys.ARROW_DOWN);
		}
		executeAction(Keys.ENTER);
		waitHelper.waitForAllElements(itemsList);
	}

	public String getFirstItemName() {
		waitHelper.waitForAllElements(itemsList);
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
