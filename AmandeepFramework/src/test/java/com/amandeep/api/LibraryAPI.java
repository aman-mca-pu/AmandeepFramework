package com.amandeep.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amandeep.payloads.Payload;
import com.amandeep.utilities.Helper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPI {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "Application/json").body(Payload.addBook(isbn, aisle)).log()
				.all().when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		JsonPath js = Helper.rawToJson(response);
		js.get("ID");
	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		return new Object[][] { { "isbn2", "aisle2" }, { "isbn3", "aisle3" }, { "isbn4", "aisle4" } };
	}

}
