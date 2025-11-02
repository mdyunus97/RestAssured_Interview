package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TestData;

@Listeners(base.Listeners.class)
public class BookingTests {
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

		String response = RestAssured.given().header("Content-Type", "application/json").when()
				.get("/booking/" + bookingID).then().statusCode(200).log().all().extract().response().asString();

		System.out.println(response);
	}

	@Test(dependsOnMethods = "getResources")
	public static void updateBooking() {

		Response response = RestAssured.given().header("Content-Type", "application/json").accept("application/json")
				.header("Authorization", basicAuth)
				.body("{\r\n" + "    \"firstname\" : \"New Yunus\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.when().put("/booking/" + bookingID).then().assertThat().statusCode(200).extract().response();
		System.out.println(response);

	}

	@Test(dependsOnMethods = "updateBooking")
	public static void verifyBookingUpdated() {

		Response response = RestAssured.given().header("Content-Type", "application/json").when()
				.get("/booking/" + bookingID).then().statusCode(200).log().all().extract().response();
		String updatedFisrtName = response.jsonPath().get("firstname");
		System.out.println(updatedFisrtName);
		Assert.assertEquals(updatedFisrtName, "New Yunus");

	}

	@Test(dependsOnMethods = "verifyBookingUpdated")
	public static void deleteBooking() {

		Response respone = RestAssured.given().header("Content-Type", "application/json").accept("application/json")
				.header("Authorization", basicAuth).when().delete("/booking/" + bookingID).then().statusCode(201).log()
				.all().extract().response();
		System.out.println(respone);
		System.out.println("Booking has been deleted successfully");
	}

}
