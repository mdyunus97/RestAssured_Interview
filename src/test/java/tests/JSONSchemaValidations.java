package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

@Listeners(base.Listeners.class)
public class JSONSchemaValidations {

	static String basicAuth = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
	static int bookingID;

	@Test
	public static void createBookingTest() {

		Response response = RestAssured.given().header("Content-Type", "application/json").accept("application/json")
				.body("{\r\n" + "    \"firstname\" : \"Yunus\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.when().post("/booking").then().assertThat().statusCode(200).log().all().extract().response();
		System.out.println(response);

		bookingID = response.jsonPath().getInt("bookingid");
		System.out.println(bookingID);

	}

	@Test(dependsOnMethods = "createBookingTest")
	public static void getResources() throws InterruptedException {
		Thread.sleep(2000);

		ValidatableResponse response = RestAssured.given().header("Content-Type", "application/json").when()
				.get("/booking/" + bookingID).then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storingJSONSchema.json"));

		System.out.println(response);
		System.out.println("Schema validation is completed");

	}

}
