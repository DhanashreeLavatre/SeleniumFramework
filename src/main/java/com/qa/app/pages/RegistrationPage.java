package com.qa.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.app.utils.ElementUtility;
import com.qa.app.utils.StringUtility;

import static com.qa.app.constants.AppConstant.*;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtility eleUtil;
	

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtility(driver);
	}

	public boolean userRegisteration(String firstName, String lastName, 
			String telephone, String password, String subscribe) {

		eleUtil.waitTillElementVisible(this.firstName,DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.getSendKeys(this.lastName, lastName);
		eleUtil.getSendKeys(this.email, StringUtility.getRandomEmailId());
		eleUtil.getSendKeys(this.telephone, telephone);
		eleUtil.getSendKeys(this.password, password);
		eleUtil.getSendKeys(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);

		
		if (eleUtil.waitTillElementVisible(successMessg,MEDIUM_DEFAULT_TIMEOUT).getText().contains(REGISTER_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;

	}
}
