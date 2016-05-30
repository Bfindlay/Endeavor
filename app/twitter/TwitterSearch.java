package twitter;

import java.util.LinkedList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterSearch {

	private Twitter twitter;
	private int maxTweets = 200;

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

	public List<Tweet> query(String... keywords) {
		// TODO process keywords to force them into basic words
		String queryStr = String.join(" ", keywords);
		Query query = new Query(queryStr);
		query.count(maxTweets);
		QueryResult result;
		List<Tweet> fullTweets = new LinkedList<>();
		try {
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					Tweet t = new Tweet(tweet);
					fullTweets.add(t);

				}
			} while (fullTweets.size() < maxTweets && (query = result.nextQuery()) != null);
		} catch (TwitterException te) {

		}

		return fullTweets;
	}

	public static void main(String[] args) {

	}

}