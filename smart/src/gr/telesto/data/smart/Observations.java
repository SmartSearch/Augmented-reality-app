package gr.telesto.data.smart;

import java.util.ArrayList;

public class Observations {
	private ArrayList<Tweets> tweets = new ArrayList<Tweets>();
	private Sensing senses;
	public Observations(){
		
	}

	public Sensing getSenses() {
		return senses;
	}
	public void setSenses(Sensing senses) {
		this.senses = senses;
	}

	public ArrayList<Tweets> getTweets() {
		return tweets;
	}

	public void setTweets(ArrayList<Tweets> tweets) {
		this.tweets = tweets;
	}
	

}
