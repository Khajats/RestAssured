package com.kts.test;

import java.util.Arrays;

import org.kts.RestAssure.Favfood;
import org.kts.RestAssure.Student;
import org.testng.annotations.Test;

public class test {

	@Test
	public void sample() {

		Favfood favfood = Favfood.builder().breakfast("idle").lunch(Arrays.asList("rice","chapapti")).dinner("rice").build();
		Student build = Student.builder().id(1).first_name("khaja").build();
		System.out.println(build);
		

	}

}
