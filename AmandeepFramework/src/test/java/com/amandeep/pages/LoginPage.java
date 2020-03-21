package com.amandeep.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver localDriver) {
		this.driver = localDriver;
	}

	@FindBy(name = "username")
	WebElement uname;
	@FindBy(name = "passwordq")
	WebElement password;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;

	public void loginToCRM(String usernameApplication, String passwordApplication) {
		uname.sendKeys(usernameApplication);
		password.sendKeys(passwordApplication);
		loginButton.click();
	}
}
