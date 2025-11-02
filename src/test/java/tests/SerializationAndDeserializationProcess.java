package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.POJOclass;
import model.bookingDates_POJOclass;
import utils.TestData;

@Listeners(base.Listeners.class)
public class SerializationAndDeserializationProcess {

	@Test
	public static void convertPOJOtoJson() {

		POJOclass pojo = new POJOclass();
		bookingDates_POJOclass bookingdates = new bookingDates_POJOclass();

		bookingdates.setCheckIn("2018-01-01");
		bookingdates.setCheckOut("2019-01-01");
		pojo.setFirstName("Mahammed");
		pojo.setLastName("Yunus");
		pojo.setTotalPrice(100);
		pojo.setDepositPaid(true);
		pojo.setBookingDates(bookingdates);

		pojo.setAdditionalNeeds("Breakfast");

		Response response = RestAssured.given().header("Content-Type", "application/json").accept("application/json")
				.body(pojo).when().post("/booking").then().assertThat().statusCode(200).log().all().extract()
				.response();

		System.out.println(response);
		TestData.bookingID = response.jsonPath().getInt("bookingid");
		System.out.println(TestData.bookingID);
//		String checkDates = response.jsonPath().getString("booking.bookingdates");

//		System.out.println(checkDates);
	}

	@Test
	public static void getResources() throws InterruptedException {
		Thread.sleep(2000);

		Response response = RestAssured.given().header("Content-Type", "application/json").when()
				.get("/booking/" + TestData.bookingID).then().statusCode(200).log().all().extract().response();
		POJOclass bookingResponse = response.jsonPath().getObject("", POJOclass.class);
		System.out.println(bookingResponse.getFirstName());
		System.out.println(bookingResponse.getLastName());
		System.out.println(bookingResponse.getTotalPrice());
		System.out.println(bookingResponse.isDepositpaid());
		System.out.println(bookingResponse.getBookingDates().getCheckIn());
		System.out.println(bookingResponse.getBookingDates().getCheckOut());
		System.out.println(bookingResponse.getAdditionalneeds());

		System.out.println(response);

	}
}
