package com.qa.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.app.utils.ElementUtility;

import io.qameta.allure.Step;

import static com.qa.app.constants.AppConstant.*;

public class LoginPage {

	// private By locators

	// public page constructor

	// public page action/methods
	private WebDriver driver;
	private ElementUtility eleUtil;

	// 1. private By locators: OR
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");


	// 2. public page constr...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);
	}

	// 3. public page actions/methods
	@Step("getting login page title")
	public String getLoginPageTitle() {
		
		//String title = eleUtil.waitFotTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		String title = eleUtil.waitForTitle(getLoginPageTitle(), 0);
		System.out.println("login page title: " + title);
		return title;
	}

	@Step("getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForUrlContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
	
		System.out.println("login page url: " + url);
		return url;
	}

	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	@Step("login with valid username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user credentials: " + username + ":" + pwd);
		eleUtil.waitTillElementVisible(email, MEDIUM_DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.getSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		//after clicking on login button ---> landing on Accounts Page
		//responsible to return the AccountsPage class object
		return new AccountsPage(driver);
	}

	@Step("navigating to the registeration page")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegistrationPage(driver);
	}
	
}
