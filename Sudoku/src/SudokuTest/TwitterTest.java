package SudokuTest;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import org.junit.Test;
import Sudoku.TwitterHandler;

public class TwitterTest {

	public TwitterHandler th;
	public Random gen = new Random();

	@Test
	public void testTweetsGivenMsg() {
		Locale loc = new Locale("en", "US");
		ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", loc);
		this.th = new TwitterHandler(bundle);
		String tweetMsg = "Testing Java and twitter integration"
				+ gen.nextInt();
		th.sendTweet(tweetMsg);
		System.out.println("Comparing to: ");
		System.out.println("Successfully updated the status to [" + tweetMsg
				+ "].");
		assertEquals("Successfully updated the status to [" + tweetMsg + "].",
				th.lastTweetStatusMsg);

	}

}
