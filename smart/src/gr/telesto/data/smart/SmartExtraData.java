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

public class SmartExtraData{
	private String startTime;
	private String locationName;
//	private ArrayList<Tweets> topTweets = new ArrayList<Tweets>();
//	private Sensing sensors;
	private Observations observations = new Observations();
	private Observations latestObservations = new Observations();
	
	private String rank;
	private String score;
	private String description;	
	public SmartExtraData(){
//		topTweets = null;
		this.startTime=null;
		this.locationName=null;
		this.description=null;

	}
	
	public SmartExtraData(String startTime,String locationName,String rank,String score,String description){
		this.setStartTime(startTime);
		this.setLocationName(locationName);
//		this.setTopTweets(topTweets);
		this.setRank(rank);
		this.setScore(score);
		this.setDescription(description);
		
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}



	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(String score) {
		this.score = score;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the topTweets
	 */
//	public ArrayList<Tweets> getTopTweets() {
//		return topTweets;
//	}

	/**
	 * @param topTweets the topTweets to set
	 */
//	public void setTopTweets(ArrayList<Tweets> topTweets) {
//		this.topTweets = topTweets;
//	}
	
//	public void addTopTweet(Tweets topTweet){
//		this.topTweets.add(topTweet);
//	}

//	public Sensing getSensors() {
//		return sensors;
//	}
//
//	public void setSensors(Sensing sensors) {
//		this.sensors = sensors;
//	}

	public Observations getLatestObservation() {
		return latestObservations;
	}

	public void setLatestObservation(Observations latestObservation) {
		this.latestObservations = latestObservation;
	}

	public Observations getObservations() {
		return observations;
	}

	public void setObservations(Observations observations) {
		this.observations = observations;
	}

}
