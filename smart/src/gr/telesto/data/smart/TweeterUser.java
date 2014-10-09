package gr.telesto.data.smart;

public class TweeterUser {
	private String name;
	private String following;
	private String followers_count;
	private String url;
	private String friends_count;
	private String screen_name;	
	public TweeterUser(){
		this.setName(null);

		this.setUrl(null);

		this.setScreen_name(null);
	}
	
	public TweeterUser(String name,String following,String followers_count,String url,String friends_count, String screen_name){
		this.setName(name);
		this.setFollowing(following);
		this.setFollowers_count(followers_count);
		this.setUrl(url);
		this.setFriends_count(friends_count);
		this.setScreen_name(screen_name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the following
	 */
	public String getFollowing() {
		return following;
	}

	/**
	 * @param following the following to set
	 */
	public void setFollowing(String following) {
		this.following = following;
	}

	/**
	 * @return the followers_count
	 */
	public String getFollowers_count() {
		return followers_count;
	}

	/**
	 * @param followers_count the followers_count to set
	 */
	public void setFollowers_count(String followers_count) {
		this.followers_count = followers_count;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the friends_count
	 */
	public String getFriends_count() {
		return friends_count;
	}

	/**
	 * @param friends_count the friends_count to set
	 */
	public void setFriends_count(String friends_count) {
		this.friends_count = friends_count;
	}

	/**
	 * @return the screen_name
	 */
	public String getScreen_name() {
		return screen_name;
	}

	/**
	 * @param screen_name the screen_name to set
	 */
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}


}
