package twitter;

import twitter4j.GeoLocation;
import twitter4j.Place;
import twitter4j.User;
import twitter4j.Status;

public class Tweet {

	public static final String UNKNOWN_COUNTRY = "NA";
	public static final String UNKNOWN_LOCATION = "NA";
	private Property tweetProperty;
	private String text = "";
	private String country = UNKNOWN_COUNTRY;
	private String location = UNKNOWN_LOCATION;
	private String username = "";
	private double latitude;
	private double longitude;

	public Tweet(Status status) {
		User user = status.getUser();

		username = user.getName();
		text = status.getText();

		Place pl = status.getPlace();
		GeoLocation geoLoc = status.getGeoLocation();
		if (pl != null && geoLoc != null) {
			country = pl.getCountry();
			location = pl.getFullName();
			String countryCode = pl.getCountryCode();

			/*
			 * PROPERTY INITIALISATION The following creates a new property
			 * containing latitude, longitude and tweet text and country Code
			 */
			latitude = geoLoc.getLatitude();
			longitude = geoLoc.getLongitude();
			Property tp = new Property(text, latitude, longitude, countryCode);
			setInstanceProperty(tp);
		}

		if (country == null)
			country = UNKNOWN_COUNTRY;
		if (location == null)
			location = UNKNOWN_LOCATION;

	}

	public void setInstanceProperty(Property tweetProperty) {
		this.tweetProperty = tweetProperty;
	}

	public Property getInstanceProperty() {

		return tweetProperty;
	}

	public String getUsername() {
		return username;
	}

	public String getText() {
		return text;
	}

	public String getCountry() {

		return country;
	}

	public String getLocation() {
		return location;
	}

}