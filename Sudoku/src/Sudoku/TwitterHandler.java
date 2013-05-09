package Sudoku;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandler {
	public static final String OAUTH_KEY = "fwsTeJLODqETKsZodQ8SOw";
	public static final String OAUTH_SECRET = "sa8xOvHRm2ZqE2WAynpXwgzZbK0ksUjd94vSnfml1s";

	
	public String lastTweetStatusMsg;

	public void sendTweet(String tweetMsg) {

		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey(OAUTH_KEY)
					.setOAuthConsumerSecret(OAUTH_SECRET);
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			try {
				// get request token.
				// this will throw IllegalStateException if access token is
				// already available
				RequestToken requestToken = twitter.getOAuthRequestToken();
				System.out.println("Got request token.");
				System.out.println("Request token: " + requestToken.getToken());
				System.out.println("Request token secret: "
						+ requestToken.getTokenSecret());
				AccessToken accessToken = null;

				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				while (null == accessToken) {
					
					// TODO Print this in the UI (pop up?)
					System.out
							.println("Open the following URL and grant access to your account:");
					
					// Note: This is how you call the new method which will open the default browser to the auth page
					openWebpage(new URL(requestToken.getAuthenticationURL()));
					
					// TODO Change below to be gotten from an input in the UI? 
					System.out
							.print("Enter the PIN and hit enter after you granted access.[PIN]:");
					String pin = br.readLine();
					try {
						if (pin.length() > 0) {
							accessToken = twitter.getOAuthAccessToken(
									requestToken, pin);
						} else {
							accessToken = twitter
									.getOAuthAccessToken(requestToken);
						}
					} catch (TwitterException te) {
						if (401 == te.getStatusCode()) {
							System.out
									.println("Unable to get the access token.");
						} else {
							te.printStackTrace();
						}
					}

					cb.setOAuthAccessToken(accessToken.getToken())
							.setOAuthAccessTokenSecret(
									accessToken.getTokenSecret());
				}
				System.out.println("Got access token.");
				System.out.println("Access token: " + accessToken.getToken());
				System.out.println("Access token secret: "
						+ accessToken.getTokenSecret());
			} catch (IllegalStateException ie) {
				// access token is already available, or consumer key/secret is
				// not set.
				if (!twitter.getAuthorization().isEnabled()) {
					System.out.println("OAuth consumer key/secret is not set.");
					System.exit(-1);
				}
			}
			Status status = twitter.updateStatus(tweetMsg);
			System.out.println("Successfully updated the status to ["
					+ status.getText() + "].");
			this.lastTweetStatusMsg = "Successfully updated the status to ["
					+ status.getText() + "].";

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("Failed to read the system input.");
			System.exit(-1);
		}
	}
	
	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	}

	public void deleteTweet(Long id) {
		try {
			Twitter twitter = new TwitterFactory().getInstance();
			twitter.destroyStatus(id);
			System.out.println("Successfully deleted status [" + id + "].");

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to delete status: " + te.getMessage());

		}
	}
}
