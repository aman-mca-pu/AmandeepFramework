package com.amandeep.api;

import com.amandeep.utilities.Helper;

import io.restassured.path.json.JsonPath;

public class RestAssuredPractice2 {

	public static void main(String[] args) {
		String response = "{\r\n" + "\r\n" + "\"dashboard\": {\r\n" + "\r\n" + "\"purchaseAmount\": 910,\r\n" + "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n" + "\r\n" + "},\r\n" + "\r\n" + "\"courses\": [\r\n"
				+ "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"Selenium Python\",\r\n" + "\r\n" + "\"price\": 50,\r\n"
				+ "\r\n" + "\"copies\": 6\r\n" + "\r\n" + "},\r\n" + "\r\n" + "{\r\n" + "\r\n"
				+ "\"title\": \"Cypress\",\r\n" + "\r\n" + "\"price\": 40,\r\n" + "\r\n" + "\"copies\": 4\r\n" + "\r\n"
				+ "},\r\n" + "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"RPA\",\r\n" + "\r\n" + "\"price\": 45,\r\n"
				+ "\r\n" + "\"copies\": 10\r\n" + "\r\n" + "}\r\n" + "\r\n" + "]\r\n" + "\r\n" + "}";

		// Print number of courses
		JsonPath js = Helper.stringToJson(response);
		int numberOfCourses = js.getInt("courses.size()");
		System.out.println("Number of courses = " + numberOfCourses);

		// Print purchase amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount = " + purchaseAmount);

		// Print title of the first course
		String titleOfFirstCourse = js.get("courses[0].title");
		System.out.println("Title of the first course = " + titleOfFirstCourse);

		// Print all courses titles and their price
		for (int i = 0; i < numberOfCourses; i++) {
			String courseTitle = js.getString("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			System.out.println("Course " + i + " ===> \t Title = " + courseTitle + " & Price= " + price);
		}

		// Print number of copies purchased for course RPA
		for (int i = 0; i < numberOfCourses; i++) {
			if (js.getString("courses[" + i + "].title").equals("RPA")) {
				System.out.println("Copies of RPA = " + js.getInt("courses[" + i + "].copies"));
				break;
			}
		}
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		

	}

}
