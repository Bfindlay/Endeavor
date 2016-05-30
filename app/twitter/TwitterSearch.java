package twitter;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterSearch {

	private Twitter twitter;
	private int maxTweets = 50;
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

	public void setMaxTweets(int maxTweets) {
		this.maxTweets = maxTweets;
	}

	public TwitterSearch() {
		twitter = new TwitterFactory().getInstance();

	}

	public TwitterSearch(int maxTweets) {
		this();
		this.maxTweets = maxTweets;
	}

	public int getMaxTweets() {
		return maxTweets;
	}

	public void query(String... keywords) {

		String queryStr = String.join(" ", keywords);
		Query query = new Query(queryStr);
		query.count(maxTweets);
		QueryResult result;
		try {
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					Tweet t = new Tweet(tweet);
					Property tp = t.getInstanceProperty();
					if (!(tp == null)) {
						addPropertyToList(tp);
					}
				}
			} while (getPropertyList().size() < maxTweets && (query = result.nextQuery()) != null);
		} catch (TwitterException te) {

		}

	}

}