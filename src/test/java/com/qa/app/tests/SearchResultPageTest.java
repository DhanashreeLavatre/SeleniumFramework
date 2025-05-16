package com.qa.app.tests;

import org.testng.Assert;

import com.qa.app.base.BaseTest;

public class SearchResultPageTest extends BaseTest {

	public void searchSetup() {

		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	
	
	public void searchTest() {
		searchResultsPage=accPage.doSearch("macbook");
		int actResultCount= searchResultsPage.getResultsProductCount();
		Assert.assertEquals(actResultCount, 3);
		
		
	}
}