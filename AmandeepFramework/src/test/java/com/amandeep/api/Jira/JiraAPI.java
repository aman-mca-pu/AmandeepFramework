package com.amandeep.api.Jira;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import com.amandeep.payloads.Payload;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraAPI {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter session = new SessionFilter();

		// Login
		given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body(Payload.loginToJira("amandeep.singh@centricconsulting.com", "amandeep")).filter(session).when()
				.post("/rest/auth/1/session").then().assertThat().statusCode(200);
		System.out.println("Login successful");

		// Create a bug
		String createdBugResponse = given().header("Content-Type", "application/json").body(Payload.createBug())
				.filter(session).when().post("/rest/api/2/issue").then().assertThat().statusCode(201).extract()
				.response().asString();

		JsonPath js = new JsonPath(createdBugResponse);
		String issueKey = js.getString("key");
		System.out.println("Bug created successfully = " + issueKey);

		// Add comment
		String comment = "Comment to test";

		String addCommentResponse = given().pathParam("issueIdOrKey", issueKey)
				.header("Content-Type", "application/json").body(Payload.addComment(comment)).filter(session).when()
				.post("/rest/api/2/issue/{issueIdOrKey}/comment").then().assertThat().statusCode(201).extract()
				.response().asString();

		JsonPath js1 = new JsonPath(addCommentResponse);
		String commentId = js1.get("id");

		System.out.println("Comment added successfully with ID = " + commentId);

		// Add Attachement
		given().header("X-Atlassian-Token", "no-check").header("Content-Type", "multipart/form-data").filter(session)
				.pathParam("issueIdOrKey", issueKey).multiPart("file", new File("pom.xml")).when()
				.post("/rest/api/2/issue/{issueIdOrKey}/attachments").then().assertThat().statusCode(200);
		System.out.println("Attachment added successfully");

		// Get issue
		String getIssueResponse = given().pathParam("issueIdOrKey", issueKey).filter(session)
				.queryParam("fields", "comment").when().get("/rest/api/2/issue/{issueIdOrKey}").then().extract()
				.response().asString();

		// iterate over fields to see if above fetched comment id is present
		JsonPath js2 = new JsonPath(getIssueResponse);
		int numberOfComments = js2.get("fields.comment.comments.size()");

		String actualComment = "";

		for (int i = 0; i < numberOfComments; i++) {
			if (js2.get("fields.comment.comments[" + i + "].id").toString().equalsIgnoreCase(commentId)) {
				actualComment = js2.get("fields.comment.comments[" + i + "].body").toString();
				break;
			}
		}

		Assert.assertEquals(actualComment, comment);
		System.out.println("comment added is correct");
	}

}
