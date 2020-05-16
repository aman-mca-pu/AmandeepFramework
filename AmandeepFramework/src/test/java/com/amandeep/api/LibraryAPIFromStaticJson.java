package com.amandeep.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amandeep.payloads.Payload;
import com.amandeep.utilities.Helper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPIFromStaticJson {

	@Test
	public void addBook() throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "Application/json")
				.body(generateStringFromResource(
						"C:\\Users\\amandeep.singh\\git\\AmandeepFramework\\AmandeepFramework\\PayloadJsons\\addBookPayload.json"))
				.log().all().when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js = Helper.rawToJson(response);
		js.get("ID");
	}

	public static String generateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
