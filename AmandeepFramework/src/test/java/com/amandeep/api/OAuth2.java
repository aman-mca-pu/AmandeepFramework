package com.amandeep.api;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class OAuth2 {

	public static void main(String[] args) throws InterruptedException {

		// Step 1 -> Go to following url
		// https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=amandeep

		// Step 2 -> Login
		// Step 3 -> Replace following url with actual from the browser
		String url = "https://rahulshettyacademy.com/getCourse.php?state=amandeep&code=4%2FzwHRSr-T0YlQZ_EaWgLeVD9DpKgXysQyvKxFdulT5NVl5GUxOSPDsHTX_rcBksrV1UAcCplH5R5Dx9G7X1DVlFw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		System.out.println("url2= " + url);
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope=")[0];
		System.out.println(code);
		String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code",
				code).queryParams("client_id",
						"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").queryParams(
						"redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().post(
						"https://www.googleapis.com/oauth2/v4/token").asString();

		System.out.println(accessTokenResponse);

		JsonPath js = new JsonPath(accessTokenResponse);

		String accessToken = js.get("access_token");
		String response = given().queryParam("access_token", accessToken).when().get(
				"https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
	}

}
