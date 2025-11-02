package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class POJOclass {

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("totalprice")
	private int totalprice;

	@JsonProperty("depositpaid")
	private boolean depositpaid;

	@JsonProperty("additionalneeds")
	private String additionalneeds;

	@JsonProperty("bookingdates")
	private bookingDates_POJOclass bookingdates;


	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public int getTotalPrice() {
		return totalprice;
	}

	public void setTotalPrice(int totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositPaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalNeeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	public bookingDates_POJOclass getBookingDates() {
		return bookingdates;
	}

	public void setBookingDates(bookingDates_POJOclass bookingdates) {
		this.bookingdates = bookingdates;
	}

}
