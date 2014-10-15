/*
 * Copyright (C) 2012- Peer internet solutions & Finalist IT Group
 * 
 * This file is part of Smart AR app and is a modification of Mixare MixareDataProcessor file.
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
package gr.telesto.data.convert;

import gr.telesto.LocalMarker;
import gr.telesto.MixView;
import gr.telesto.POIMarker;
import gr.telesto.data.DataHandler;
import gr.telesto.data.DataSource;
import gr.telesto.data.smart.Observations;
import gr.telesto.data.smart.Place;
import gr.telesto.data.smart.Sensing;
import gr.telesto.data.smart.TweeterUser;
import gr.telesto.data.smart.Tweets;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mixare.lib.HtmlUnescape;

import android.util.Log;

/**
 * A data processor for custom urls or data, Responsible for converting raw data
 * (to json and then) to marker data.
 * 
 * @author A. Egal
 */
public class SmartDataProcessor extends DataHandler implements DataProcessor {

	public static final int MAX_JSON_OBJECTS = 1000;

	@Override
	public String[] getUrlMatch() {
		String[] str = { "search" };
		return str;
	}

	@Override
	public String[] getDataMatch() {
		String[] str = { "search" };
		return str;
	}

	@Override
	public boolean matchesRequiredType(String type) {
		if (type.equals(DataSource.TYPE.SEARCH.name())) {
			Log.v(MixView.TAG, "SmartDataconvertor choosen");
			return true;
		}
		return false;
	}

	@Override
	public List<LocalMarker> load(String rawData, int taskId, int colour) throws JSONException {
		List<LocalMarker> markers = new ArrayList<LocalMarker>();
		JSONObject root = convertToJSON(rawData);
		JSONArray dataArray = root.getJSONArray("results");
		int top = Math.min(MAX_JSON_OBJECTS, dataArray.length());
		// Log.v(MixView.TAG, "num of results=" + dataArray.length());

		for (int i = 0; i < top; i++) {
			JSONObject jo = dataArray.getJSONObject(i);
			LocalMarker ma = null;
			if (jo.has("title") && jo.has("lat") && jo.has("lon")) { // &&
																		// jo.has("elevation"))
																		// {
				Log.i("SmartDataProcessor", "load. POI selected ");
				String id = this.extractSimpleValue(jo, "id");
				String link = this.extractSimpleValue(jo,"URI");
				ma = new POIMarker(id, HtmlUnescape.unescapeHTML(this.extractSimpleValue(jo,"title"), 0), jo.getDouble("lat"),
						jo.getDouble("lon"), 0.0, link, taskId, colour);
				ma.extraData.setStartTime(this.extractSimpleValue(jo,"startTime"));
				ma.extraData.setLocationName(this.extractSimpleValue(jo,"locationName"));
				ma.extraData.setRank(this.extractSimpleValue(jo,"rank"));
				ma.extraData.setScore(this.extractSimpleValue(jo,"score"));
				ma.extraData.setDescription(this.extractSimpleValue(jo,"description"));
				ma.extraData.setObservations(this.extractObservations("observations", jo));
//				if (ma.extraData.getObservations().getTweets().size()!=0){
//					
//				}
				ma.extraData.setLatestObservation(this.extractObservations("latestObservation", jo));
				markers.add(ma);
			}
		}
		return markers;
	}

	private JSONObject convertToJSON(String rawData) {
		try {
			return new JSONObject(rawData);
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}

	private Observations extractObservations(String observationType, JSONObject object) {
		Observations rtrn =new Observations();
		try {
			if (!object.isNull(observationType)) {
//				JSONObject jo2 = object.getJSONObject(observationType);
				rtrn.setTweets(this.extractTweets(object.getJSONObject(observationType)));
				rtrn.setSenses(this.extractSensing(object.getJSONObject(observationType)));
			}
			else rtrn = null;
		} catch (JSONException e) {
			rtrn=null;
		}
		return rtrn;
	}

	private ArrayList<Tweets> extractTweets(JSONObject object) {
		ArrayList<Tweets> rtrn = new ArrayList<Tweets>();
		try {
			if (object.has("topTweets")) {
				// ma.setColour(Color.GREEN);
				JSONArray tweetArray = object.getJSONArray("topTweets");
				int count = tweetArray.length();
				for (int j = 0; j < count; j++) {
					JSONObject to = tweetArray.getJSONObject(j);
					Log.i("SmartDataProcessor", "load. topTweets selected " + count);
					Tweets tTo = new Tweets();

					tTo.setLang(this.extractSimpleValue(to,"lang"));
					tTo.setText(this.extractSimpleValue(to,"text"));
					tTo.setCreated_at(this.extractSimpleValue(to,"created_at"));
					tTo.setRetweeted(to.getBoolean("retweeted"));
					
					Place pl = new Place();
					{
						pl.setPlace_type(this.extractSimpleValue(to.getJSONObject("place"),"place_type"));
						pl.setName(this.extractSimpleValue(to.getJSONObject("place"),"name"));
						pl.setCountry_code(this.extractSimpleValue(to.getJSONObject("place"),"country_code"));
						pl.setUrl(this.extractSimpleValue(to.getJSONObject("place"),"url"));
						pl.setCountry(this.extractSimpleValue(to.getJSONObject("place"),"country"));
						pl.setFull_name(this.extractSimpleValue(to.getJSONObject("place"),"full_name"));
					}					
					tTo.setPlace(pl);
					
					TweeterUser tu = new TweeterUser();
					{
						tu.setName(this.extractSimpleValue(to.getJSONObject("user"),"name"));
						tu.setScreen_name(this.extractSimpleValue(to.getJSONObject("user"),"screen_name"));
						tu.setUrl(this.extractSimpleValue(to.getJSONObject("user"),"url"));
						tu.setFollowing(this.extractSimpleValue(to.getJSONObject("user"),"following"));
						tu.setFollowers_count(this.extractSimpleValue(to.getJSONObject("user"),"followers_count"));
						tu.setFriends_count(this.extractSimpleValue(to.getJSONObject("user"),"friends_count"));
					}					
					tTo.setUser(tu);					
					rtrn.add(tTo);
//					ma.extraData.addTopTweet(tTo);
				}
			}
			else rtrn=null;
		} catch (JSONException e) {
			rtrn = null;
		}
		return rtrn;
	}

	private Sensing extractSensing(JSONObject object) {
		Sensing sensorData = new Sensing();

		sensorData.setColors(this.extractSimpleArrayValue(object, "colors"));
		sensorData.setColorname(this.extractSimpleArrayValue(object, "colorname"));

		sensorData.setCrowd_density(this.extractSimpleValue(object, "crowd_density"));
		sensorData.setSound_level(this.extractSimpleValue(object, "sound_level"));
		sensorData.setCrowd_score(this.extractSimpleValue(object, "crowd_score"));
		sensorData.setApplause_score(this.extractSimpleValue(object, "applause_score"));
		sensorData.setTraffic_score(this.extractSimpleValue(object, "traffic_score"));
		sensorData.setMusic_score(this.extractSimpleValue(object, "music_score"));
		sensorData.setBattery(this.extractSimpleValue(object, "battery"));
		sensorData.setLight(this.extractSimpleValue(object, "light"));
		sensorData.setTemperature(this.extractSimpleValue(object, "temperature"));
		sensorData.setTotalCheckIns(this.extractSimpleValue(object, "totalCheckIns"));
		sensorData.setHereNow(this.extractSimpleValue(object, "hereNow"));
		return sensorData;
	}

	private String extractSimpleValue(JSONObject object, String name) {
		String rtrn;
		try {
			if (object.has(name)) {
				rtrn = object.getString(name);
			} else
				rtrn = null;
		} catch (JSONException e) {
			rtrn = null;
		}
		return rtrn;
	}

	private ArrayList<String> extractSimpleArrayValue(JSONObject object, String name) {
		ArrayList<String> rtrn = new ArrayList<String>();
		try {
			if (object.has(name)) {
				JSONArray colorNameArray = object.getJSONArray(name);
				int count = colorNameArray.length();
				for (int j = 0; j < count; j++) {
					rtrn.add(colorNameArray.getString(j));
				}
			} else
				rtrn = null;
		} catch (JSONException e) {
			rtrn = null;
		}
		return rtrn;
	}

}
