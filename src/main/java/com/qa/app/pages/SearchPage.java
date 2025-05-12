package com.qa.app.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import com.qa.app.utils.ElementUtility;

import io.qameta.allure.Step;
import static com.qa.app.constants.AppConstant.*;

public class SearchPage {

	
	private WebDriver driver;
	private ElementUtility eleUtil;
	
	private final By resultsProduct = By.cssSelector("div.product-thumb");
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);
	}
	
	@Step("getting the product count on results page")
	public int getResultsProductCount() {
		int searchCount = eleUtil.waitForAllElementsVisible(resultsProduct, MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("total number of search products: "+ searchCount);
		return searchCount;
	}
	
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("product name: "+ productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}
