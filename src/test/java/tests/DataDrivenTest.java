package tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.DataProviderUtils;

@Listeners(base.Listeners.class)
public class DataDrivenTest {

	static List<Integer> bookingIDs = new ArrayList<Integer>();

	@Test(dataProvider = "bookingdata", dataProviderClass = DataProviderUtils.class)
	public void dataDrivenTest(org.json.simple.JSONObject bookingData) {

		Response response = RestAssured.given().header("Content-Type", "application/json").accept("application/json")
				.body(bookingData.toString()).when().post("/booking").then().assertThat().statusCode(200).log().all()
				.extract().response();
		int bookingID = response.jsonPath().getInt("bookingid");
		bookingIDs.add(bookingID);

	}

	@Test
	public static void getResources() throws InterruptedException {
		Thread.sleep(2000);

		for (int id : bookingIDs) {

			Response response = RestAssured.given().header("Content-Type", "application/json").when()
					.get("/booking/" + id).then().statusCode(200).log().all().extract().response();
			System.out.println(response);

			System.out.println(bookingIDs);
		}

	}
}
