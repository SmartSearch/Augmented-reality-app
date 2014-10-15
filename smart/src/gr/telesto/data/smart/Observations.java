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
