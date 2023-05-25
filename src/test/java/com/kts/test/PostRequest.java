package com.kts.test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.kts.RestAssure.Favfood;
import org.kts.RestAssure.Student;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequest {

	// 1.passing json body as String --> not recommended

	// @Test
	public void postRequest() {
		//1.Passing json body as String --> Not recommended
		//2.easy to copy paste --> can be used to quickly check the behavior                                                        I
		//3.not recommended for larger json or dynamic json

		String reqBody = "{\r\n"
				+ "	\"id\": 1,\r\n"
				+ "	\"first_name\": \"Sebastian\",\r\n"
				+ "	\"last_name\": \"Eschweiler\",\r\n"
				+ "	\"email\": \"sebastian@codingthesmartway.com\",\r\n"
				+ "	\"job\":[\"tester\",\"yrainer\"],\r\n"
				+ "	\"favratefood\":{\r\n"
				+ "		\"breakfast\":\"idle\",\r\n"
				+ "		\"lunch\":[\"chapati\",\"milk\"],\r\n"
				+ "		\"dinner\":\"rice\"	\r\n"
				+ "	}\r\n"
				+ "}";
		Response response = given()
				.header("Content-Type", "application/json")
				// .header("Content-Tpe",ContentType.JSON)
				.body(reqBody)
				.log()
				.all()
				.post("http://localhost:3000/employees");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
	}
	
	//@Test
	public void test() {
		//pass it from an external file
		 // 1. You cannot get the request content from the file and print it on the console
		 // 2. Use this only for Static data
	
		Response response = given()
				.header("Content-Type", "application/json")
				.log()
				.all()
				.body(new File(System.getProperty("user.dir")+"/db.json"))
				.post("http://localhost:3000/employees");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
	}
	@Test
	public void test1() throws IOException {
		//read req body from an external file and convert to string
		 // 1. Logs the req body into the console
		 // 2. Change few parameters in the request
		   String reqbody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/db.json")));
		   String replace = reqbody.replace("18", "45");
			Response response = given()
					.header("Content-Type", "application/json")
					.log()
					.all()
					.body(replace)
					.post("http://localhost:3000/employees");
			response.prettyPrint();
			System.out.println(response.getStatusCode());


	}


	//@Test
	public void postRequest1() {
		//Using map and list from java
		//{} --> map interface
		//[] --> list
	    //serialization--> converts your Language objects --> byte stream --> json
		//verbose and not suitable for very big json files
		//generic type needs to be mentioned

		Map<String, Object> map = new HashMap<>();
		map.put("id", new Faker().number().numberBetween(20,100));
		map.put("first_name", "sameer");
		map.put("last_name", "rathod");
		map.put("email", "sameer@gmail.com");

		Response response = given()
				.header("Content-Type", "application/json")
				// .header("Content-Tpe",ContentType.JSON)
				.log()
				.all()
				.body(map)
				.post("http://localhost:3000/employees");
		response.prettyPrint();
		System.out.println(response.getStatusCode());

	}
	
	//@Test
	public void postRequest2() {
		Map<String, Object>object = new HashMap<>();
		object.put("id", new Faker().number().numberBetween(10, 100));
		object.put("first_name", "naresh");
		object.put("last_name","gonda");
		object.put("email", "naresh@gmail.com");
		object.put("job", Arrays.asList("tester","trainer"));
		Map<String, Object>favoritefood = new HashMap<>();
		favoritefood.put("breakfast", "idle");
		favoritefood.put("lunch",Arrays.asList("chapati","milk"));
		favoritefood.put("dinner", "rice");
		object.put("favoritefood", favoritefood);
		Response response = given()
				.header("Content-Type", "application/json")
				// .header("Content-Tpe",ContentType.JSON)
				.log()
				.all()
				.body(object)
				.post("http://localhost:3000/employees");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
	}
	
	//@Test
	public void postRequest3() {
		 //Using external json library
		 //having some collections that can solve the problems we had while using map and list
		 //{} --> JsonObject                                     I
		 //[] --> JsonArray

		JSONObject object = new JSONObject();
		object.putOnce("id", new Faker().number().numberBetween(10, 100));
		object.put("first_name", "naresh");
		object.put("last_name","gonda");
		object.put("email", "naresh@gmail.com");
		object.accumulate("email", "manasa@gmail.com");
		JSONArray job = new JSONArray();
		job.put("tester");
		job.put("trainer");
		object.put("job", job);
		JSONObject favoritefood = new JSONObject();
		favoritefood.put("breakfast", "idle");
		JSONArray lunch = new JSONArray();
		lunch.put("chapati");
		lunch.put("milk");
		favoritefood.put("lunch", lunch);
		favoritefood.put("dinner", "rice");
		object.put("favoritefood", favoritefood);
		Response response = given()
				.header("Content-Type", "application/json")
				// .header("Content-Tpe",ContentType.JSON)
				.log()
				.all()
				.body(object.toMap())// Jackson library to serialise java objects to byte streams
				.post("http://localhost:3000/employees");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
		
	}
	
	//@Test
	public void requestPost4() {
		Favfood favfood = Favfood.builder()
				.breakfast("idle")
				.lunch(Arrays.asList("chapathi","milk"))
				.dinner("rice").build();
		Student student = Student.builder()
				.id(16)
				.first_name("khajats")
				//.setLast_name("ts")
				.email("khajats9731@gmail.com")
				.job(Arrays.asList("tester","trainer"))
				.favfood(favfood).build();
		System.out.println(student);
		Response response = given()
				.contentType(ContentType.JSON)
				.log()
				.all()
				.body(student)
				.post("http://localhost:3000/employees");
		response.prettyPrint();
		System.out.println(response.getStatusCode());
	}

}
