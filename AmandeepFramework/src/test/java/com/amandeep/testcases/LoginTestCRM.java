package com.amandeep.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.amandeep.pages.BaseClass;
import com.amandeep.pages.LoginPage;

public class LoginTestCRM extends BaseClass {

	@Test
	public void loginApp() {
		logger = report.createTest("Login to CRM");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting application");
		loginPage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		logger.pass("Login Success");

	}

}
