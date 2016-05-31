package twitter;

import java.util.ArrayList;
import java.util.List;

import twitter4j.conf.ConfigurationBuilder;

import twitter4j.StatusListener;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStreamFactory;
import twitter4j.*;

public class TwitterStream {

	private int maxTweets = 50;
	private int numTweets = 0;

	private List<Property> propertyList = new ArrayList<Property>();

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}

	public void addPropertyToList(Property property) {
		this.propertyList.add(property);
	}

	public int getNumTweets() {
		return numTweets;
	}

	public void setNumTweets(int numTweets) {
		this.numTweets = numTweets;
	}

	public void setMaxTweets(int maxTweets) {
		this.maxTweets = maxTweets;
	}

	public TwitterStream() {

	}

	public TwitterStream(int maxTweets) {
		this();
		this.maxTweets = maxTweets;
	}

	public int getMaxTweets() {
		return maxTweets;
	}

	public void search() {

		twitter4j.TwitterStream twitter = new TwitterStreamFactory().getInstance();

		StatusListener listener = new StatusListener() {

			public void onStatus(Status status) {
				GeoLocation loc = status.getGeoLocation();
				// System.out.println("@" + status.getUser().getScreenName() + "
				// - " + loc);
				if (loc != null && getNumTweets() < getMaxTweets()) {
					setNumTweets(getNumTweets() + 1);
					Tweet tweet = new Tweet(status);
					Property tp = tweet.getInstanceProperty();
					if (!(tp == null)) {
						addPropertyToList(tp);
					}

				} else if (getNumTweets() >= getMaxTweets()) {
					twitter.shutdown();
				}
			}

			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

			}

			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

			}

			public void onScrubGeo(long userId, long upToStatusId) {

			}

			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			public void onStallWarning(StallWarning e) {

			}
		};
		twitter.addListener(listener);
		FilterQuery filter = new FilterQuery();
		double[][] bb = { { -180, -90 }, { 180, 90 } };
		filter.locations(bb);
		twitter.filter(filter);
	}

}