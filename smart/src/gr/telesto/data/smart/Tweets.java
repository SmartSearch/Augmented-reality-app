/*
 * Copyright (C) 2014 Telesto
 * 
 * This file is part of Smart AR app.
 * 
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. 
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>
 */
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
