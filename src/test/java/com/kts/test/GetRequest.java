package com.kts.test;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class GetRequest {

	@Test
	public void getTest() {
		Response response = given().get("http://localhost:3000/employees");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		response.getTimeIn(TimeUnit.SECONDS);
		System.out.println(response.getContentType());
		System.out.println(response.header("Content-Type"));
		Headers headers = response.headers();
		for (Header header : headers) {
			System.out.println(header.getName() + ":" + header.getValue());
		}

	}
}
