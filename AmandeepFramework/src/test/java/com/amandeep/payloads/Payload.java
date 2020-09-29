package com.amandeep.payloads;

public class Payload {

	public static String addBook(String isbn, String aisle) {
		return "{\r\n" + "    \"name\": \"Amandeep Book 3\",\r\n" + "    \"isbn\": \"" + isbn + "\",\r\n"
				+ "    \"aisle\": \"" + aisle + "\",\r\n" + "    \"author\": \"Amandeep_author_3\"\r\n" + "}";
	}

	public static String loginToJira(String userName, String password) {
		return "{\r\n" + "    \"username\": \"" + userName + "\",\r\n" + "    \"password\": \"" + password + "\"\r\n"
				+ "}";
	}

	public static String addComment(String comment) {
		return "{\r\n" + "    \"body\": \"" + comment + "\"\r\n" + "}";
	}

	public static String createBug() {
		return "{\r\n" + "    \"fields\": {\r\n" + "        \"project\": {\r\n" + "            \"key\": \"AMAN\"\r\n"
				+ "        },\r\n" + "        \"summary\": \"Bug created from rest assured\",\r\n"
				+ "        \"description\": \"This is the description of the bug created using automation\",\r\n"
				+ "        \"issuetype\": {\r\n" + "            \"name\": \"Bug\"\r\n" + "        }\r\n" + "    }\r\n"
				+ "}";
	}
}
