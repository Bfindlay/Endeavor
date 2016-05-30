package controllers;

import twitter.*;
import play.data.DynamicForm;
import play.data.Form;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import play.data.*;
import play.data.FormFactory;
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

	List<Tweet> tweetList = new ArrayList<Tweet>();
	ArrayList<Property> propertyList;
	@Inject
	private FormFactory formFactory;

	/**
	 * An action that renders an HTML page with a welcome message. The
	 * configuration in the <code>routes</code> file means that this method will
	 * be called when the application receives a <code>GET</code> request with a
	 * path of <code>/</code>.
	 */
	public Result index() {
		double longitude = 0;
		double latitude = 0;
		String tweet = null;
		propertyList = Property.getPropertyList();
		return ok(index.render(propertyList));
	}

	public Result addQuery() {
		DynamicForm requestData = formFactory.form().bindFromRequest();
		String query = requestData.get("query");
		TwitterSearch twitterSearch = new TwitterSearch();
		tweetList = twitterSearch.query(query);
		// String testTweet = tweetList.get(0).getText();
		// System.out.println(testTweet);
		return redirect(routes.HomeController.index());

	}

}
