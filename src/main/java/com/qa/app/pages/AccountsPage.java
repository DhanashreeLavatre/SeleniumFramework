package com.qa.app.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.app.utils.ElementUtility;

import io.qameta.allure.Step;

import static com.qa.app.constants.AppConstant.*;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtility eleUtil;

	private final By headers = By.cssSelector("div#content > h2");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");
	
	private static final Logger log = LogManager.getLogger(AccountsPage.class);


	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);
	}

	@Step("getting acc page title")
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitle(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		log.info("home page title: " + title);
		return title;
	}

	@Step("getting acc page url")
	public String getAccPageURL() {
		String url = eleUtil.waitForUrlContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		log.info("home page url: " + url);
		return url;
	}

	@Step("getting acc page headers")
	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.getElements(headers);
		List<String> headerValList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headerValList.add(text);
		}
		log.info("Acc page headers: " + headerValList);
		return headerValList;
	}

	@Step("perform search: {0}")
	public SearchPage doSearch(String searchKey) {
		//System.out.println("search key: " + searchKey);
		log.info("search key: " + searchKey);

		eleUtil.getSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchPage(driver);
	}

}
