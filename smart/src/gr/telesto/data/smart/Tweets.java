package gr.telesto.data.smart;

public class Tweets {
	private String lang;
	private String text;
	private String created_at;
	private Place place;
	private TweeterUser user;
	private boolean retweeted;
	public Tweets(){
		this.setLang(null);
		this.setText(null);
		this.setCreated_at(null);
		this.setPlace(null);
		this.setUser(null);
		this.setRetweeted(false);
	}
	public Tweets(String lang,String text,String created_at,Place place,TweeterUser user,boolean retweeted){
		this.setLang(lang);
		this.setText(text);
		this.setCreated_at(created_at);
		this.setPlace(place);
		this.setUser(user);
		this.setRetweeted(retweeted);
	}
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
	}
	/**
	 * @return the user
	 */
	public TweeterUser getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(TweeterUser user) {
		this.user = user;
	}
	/**
	 * @return the retweeted
	 */
	public boolean isRetweeted() {
		return retweeted;
	}
	/**
	 * @param retweeted the retweeted to set
	 */
	public void setRetweeted(boolean retweeted) {
		this.retweeted = retweeted;
	}

	
}
