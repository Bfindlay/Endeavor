package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static ArrayList<Property> propertyList = new ArrayList<Property>();
	private Map<String, Integer> codeMap = new HashMap<String, Integer>();
	public Map<String, ArrayList<String>> tweetAnalysis = new HashMap<String, ArrayList<String>>();

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

		addTweetAnalysis(country, processString(tweet));

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
	 * wipes property list clear
	 */
	public void clearList() {
		if (!propertyList.isEmpty()) {
			propertyList.clear();
		}

	}

	/**
	 * Returns list of tweet properties
	 *
	 * @return List of Properties
	 */
	public static ArrayList<Property> getPropertyList() {

		return propertyList;
	}

	/**
	 * Adds a tweet property to the ArrayList
	 *
	 * @param tweetProperty
	 */
	public static void addPropertyToList(Property tweetProperty) {
		propertyList.add(tweetProperty);
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
	 * Returns a map containing all of the isoA2 codes available in the property
	 * list as a key and sets value to the number of tweets within that isoA2
	 * code
	 * 
	 * @return returns HashMap<CountryCode, numOccurrences>
	 */
	public Map<String, Integer> getCountryCodes() {

		for (int i = 0; i < propertyList.size(); i++) {
			String code = propertyList.get(i).getCountry();
			int count = codeMap.containsKey(code) ? codeMap.get(code) : 0;
			if (codeMap.containsKey(code)) {
				codeMap.put(code, count + 1);
			} else {
				codeMap.put(code, 1);
			}
		}
		return codeMap;
	}

	/**
	 * Clears the map of Country codes for a new search, mainly used to wipe
	 * geoJson layers from leaflet.js map
	 */
	public void clearCountryCodes() {
		if (!codeMap.isEmpty()) {
			codeMap.clear();
		}
	}

	/**
	 * Returns the number of tweets within a country using the isoA2 code as key
	 * 
	 * @param code
	 *            String containing isoA2 code
	 * @return number of tweets within a country
	 */
	public int getCountryCodesVal(String code) {

		return codeMap.get(code);
	}

	/**
	 * Returns a list containing tweets for a given country
	 * 
	 * @param code
	 *            isoA2 code
	 * @return Returns List<String>
	 */
	public ArrayList<String> getLocationTweetList(String code) {
		return tweetAnalysis.get(code);
	}

	/**
	 * Adds a Tweet to map based on the given country code and tweet String
	 * 
	 * @param code
	 *            String containing country code
	 * @param tweet
	 *            String containing a tweet
	 */
	public void addTweetAnalysis(String code, String tweet) {

		if (tweetAnalysis.containsKey(code)) {
			tweetAnalysis.get(code).add(tweet);
		} else {
			ArrayList<String> list = new ArrayList<String>();
			list.add(tweet);
			// tweetAnalysis.put(code, list);
		}
	}

	/**
	 * Clears the map of Country tweets for a new search, mainly used to wipe
	 * geoJson layers from leaflet.js map
	 */
	public void clearCountryTweets() {
		if (!tweetAnalysis.isEmpty()) {
			tweetAnalysis.clear();
		}
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
