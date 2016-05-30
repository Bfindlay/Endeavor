package twitter;

/**
 *
 * @author Brett Findlay
 *
 */
public class Property {

	private String tweet;
	private double latitude;
	private double longitude;
	private String country;

	/**
	 * Main constructor to create a property object containing the Latitude,
	 * longitude and text form a specific tweet
	 *
	 * @param tweet
	 *            String containing a tweet
	 * @param latitude
	 *            double containing latitude value
	 * @param longitude
	 *            double containing a longitude value
	 */
	public Property(String tweet, double latitude, double longitude) {
		this.tweet = processString(tweet);
		this.latitude = latitude;
		this.longitude = longitude;

	}

	/**
	 * Constructor to create a property object contiaining the Latitude,
	 * longitude and text form a specific tweet as well as the tweets origin
	 * location
	 *
	 * @param tweet
	 *            String containing a tweet
	 * @param latitude
	 *            double containing latitude value of a tweet
	 * @param longitude
	 *            double containing a longitude value of a tweet
	 * @param country
	 *            value representing the country the tweet originated from
	 */
	public Property(String tweet, double latitude, double longitude, String country) {
		this.tweet = processString(tweet);
		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;

	}

	/**
	 * Constructor to create a Property only containing latitude and longitude
	 * values
	 *
	 * @param latitude
	 *            double containing latitude value
	 * @param longitude
	 *            double containing longitude value
	 */
	public Property(double latitude, double longitude) {

		this.latitude = latitude;
		this.longitude = longitude;

	}

	/**
	 * Creates a null tweet object
	 */
	public Property() {

		this.tweet = "NA";
		this.latitude = 0;
		this.longitude = 0;
		this.country = "NA";

	}

	/**
	 * returns the text containing a tweet from a property
	 *
	 * @return String containing tweet text
	 */
	public String getTweet() {
		return tweet;
	}

	/**
	 * Sets tweet property
	 *
	 * @param tweet
	 *            String containing tweet text
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	/**
	 * returns the latitude value of a tweet property
	 *
	 * @return double containing latitude of a tweet
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Sets latitude value for a tweet property
	 *
	 * @param latitude
	 *            double containing a latitude value
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * returns the longitude value of a tweet property
	 *
	 * @return double containing longitude of a tweet
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude value for a tweet property
	 *
	 * @param longitude
	 *            double containing longitude of a tweet
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Sets the country code for a tweet property
	 * 
	 * @param country
	 *            isoA2 code
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Returns the isoA2 code for a country
	 * 
	 * @return String containing ISOA2 code
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Used to process a tweet to be analyzed by removing special characters
	 * 
	 * @param tweet
	 *            String from a tweet, may or may not contain special characters
	 * @return tweet with all special characters removed
	 */
	public String processString(String tweet) {

		tweet = tweet.replaceAll("[^a-zA-Z0-9 ]+", " ");

		return tweet;
	}

}
