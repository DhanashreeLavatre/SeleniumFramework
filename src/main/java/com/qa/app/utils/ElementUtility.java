package com.qa.app.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtility {

	private WebDriver driver;

	public ElementUtility(WebDriver driver) {
		this.driver = driver;

	}

	public String getElementText(By locator) {

		return getElement(locator).getText();

	}

	public void doClick(By locator) {

		getElement(locator).click();

	}

	public void getSendKeys(By locator, String value) {

		getElement(locator).sendKeys(value);

	}

	public WebElement getElement(By locator) {

		return driver.findElement(locator);

	}
	
	
	public boolean isElementDisplayed(By locator) {
		
		return getElement(locator).isDisplayed();
		
		
	}

	// -------------------***Find Element with waits***-----------------------//

	public WebElement waitTillElementVisible(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return ele;

	}

	// ------------------***Find Elements***----------------------//

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);

	}

	public String getElementsText(By locator) {

		List<WebElement> footerLinks = getElements(locator);
		System.out.println(footerLinks.size());
		String getText = "";

		for (WebElement e : footerLinks) {
			getText = e.getText();
			System.out.println(getText);

		}

		return getText;
	}

	public void clickElements(By locator, String value) {
		List<WebElement> footerLinks = getElements(locator);
		System.out.println(footerLinks.size());

		for (WebElement e : footerLinks) {

			String getText = e.getText();
			System.out.println(getText);

			if (getText.contains(value)) {
				e.click();
				break;
			}

		}
	}
	
	// need to check? if it is list then why it is not getting itterate?
	public List<WebElement> waitForAllElementsVisible( By locator , int timeOut) {
		
//		List<WebElement> eleLists= getElements(locator);
//		System.out.println(eleLists.size());
//		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
		
	}

	// ------------*wait for title:

	public String waitForTitle(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();

	}

	// --------------*wait for url:

	public String waitForUrlContains(String fractionURL, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlContains(fractionURL));
		return driver.getCurrentUrl();

	}

	
	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * This can br use using wait
	 * 
	 * @param locator
	 * @param timeOut
	 */
	
	public void clickWhenReady(By locator , int timeOut) {
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
}
