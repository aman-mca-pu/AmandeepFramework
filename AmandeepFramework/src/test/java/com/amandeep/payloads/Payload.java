package com.amandeep.payloads;

public class Payload {

	public static String addBook(String isbn, String aisle) {
		return "{\r\n" + 
				"    \"name\": \"Amandeep Book 3\",\r\n" + 
				"    \"isbn\": \""+isbn+"\",\r\n" + 
				"    \"aisle\": \""+aisle+"\",\r\n" + 
				"    \"author\": \"Amandeep_author_3\"\r\n" + 
				"}";
	}
}
