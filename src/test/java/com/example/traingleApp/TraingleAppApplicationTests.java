package com.example.traingleApp;

import io.restassured.response.ResponseBody;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TraingleAppApplicationTests {

	private static String url;
	private static final String RESOURCES_PATH = "src/test/resources";
	private static Properties properties = new Properties();

	@BeforeAll
	private static void setup() {
		readProperties();
	}

	private static void readProperties() {
		try {
			String path = RESOURCES_PATH + "/test.properties";

			InputStream inputStream = new FileInputStream(path);
			properties.load(inputStream);
			url = properties.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void emptyNumbersTest() {
		requestAndAssert("Please Use Only Numbers", "", "", "");
	}

	@Test
	public void clickSpaceOnOneFieldWithNumbersTest() {
		requestAndAssert("Please Use Only Numbers", " ", "2", "3");
	}

	@Test
	public void enterVeryBigNumber_OutOfIntegerRange() {
		requestAndAssert("Please Use Only Numbers", "2147483647 ", "2147483648", "2147483647");
	}

	@Test
	public void enterZeroNumbers() {
		requestAndAssert("Please Use Only Numbers", "0 ", "0", "0");
	}


	@Test
	public void equalNumbersTest() {
		requestAndAssert("EQUILATERAL", "2", "2", "2");
	}


	@Test
	public void twoSameNumbersTest() {
		requestAndAssert("ISOSCELES", "3", "3", "4");
	}

	@Test
	public void differentNumbersTest() {
		requestAndAssert("SCALENE", "2", "3", "4");
	}

	@Test
	public void nigativeNumbersTest() {
		requestAndAssert("NOT_TRAINGLE","-2", "-2", "-2");
	}

	@Test
	public void zerosNumbersTest() {
		requestAndAssert("NOT_TRAINGLE", "0", "0", "1");
	}

	private void requestAndAssert(String type, String a, String b, String c) {
		ResponseBody body = given().queryParam("a", a)
				.queryParam("b", b)
				.queryParam("c", c)
				.when()
				.get(url)
				.getBody();

		assertEquals(type, body.asString());
	}

}
