package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class bookingDates_POJOclass {

	@JsonProperty("checkin")
	private String checkin;

	@JsonProperty("checkout")
	private String checkout;

	public String getCheckIn() {
		return checkin;
	}

	public void setCheckIn(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckOut() {
		return checkout;
	}

	public void setCheckOut(String checkout) {
		this.checkout = checkout;
	}

}
