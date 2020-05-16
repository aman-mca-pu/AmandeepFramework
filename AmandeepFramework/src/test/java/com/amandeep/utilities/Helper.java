package com.amandeep.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import io.restassured.path.json.JsonPath;

public class Helper {

	// screenshot, alerts, frames, windows, sync issues, java script executor

	public static String captureScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenShotPath = System.getProperty("user.dir") + "/Screenshots/FreeCRM_" + getCurrentDateTime()
				+ ".png";
		try {
			FileHandler.copy(src, new File(screenShotPath));
			System.out.println("Screenshot captured");
		} catch (Exception e) {
			System.out.println("Unable to capture screenshot " + e.getMessage());
		}
		return screenShotPath;
	}

	// Get current date time
	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}

	// Parse string to json
	public static JsonPath rawToJson(String response) {
		return new JsonPath(response);
	}

}
