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
package gr.telesto.details;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gr.telesto.LocalMarker;
import gr.telesto.MainApp;
import gr.telesto.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class DetailActivity extends Activity {
	private LocalMarker ins;

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);
		ins = MainApp.getInstance().selectedMarker;

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();
		
		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Event Description");
		listDataHeader.add("Event Location");
		listDataHeader.add("Event Occurrence");
		listDataHeader.add("Event Ranking");
		listDataHeader.add("Related Sensor Data");
		listDataHeader.add("Latest Observations Data");

		// Adding child data
		List<String> description = new ArrayList<String>();
		description.add("Title : " + ins.getTitle());
		description.add("Description :" +ins.getExtraData().getDescription());
		description.add(ins.getURL());

		List<String> location = new ArrayList<String>();
		location.add(ins.getExtraData().getLocationName());
		location.add("Latitude\t:" + ins.getLatitude());
		location.add("Longitude\t:" + ins.getLongitude());

		List<String> occurance = new ArrayList<String>();
		occurance.add(ins.getExtraData().getStartTime());

		List<String> ranking = new ArrayList<String>();
		ranking.add("Score :" + ins.getExtraData().getScore());
		ranking.add("Rank :" + ins.getExtraData().getRank());

		List<String> sensors = new ArrayList<String>();
		if (ins.extraData.getObservations() != null) {
			if (ins.extraData.getObservations().getSenses() != null) {
				if (ins.extraData.getObservations().getSenses().getColorname() != null)
				{
					String colours="";
					for (String clr: ins.extraData.getObservations().getSenses().getColorname())
					{
						colours += clr + "," ;
					}
					sensors.add("Colours	:" + colours);
				}
				if (ins.extraData.getObservations().getSenses().getApplause_score() != null)
					sensors.add("Applause Score	:" + ins.extraData.getObservations().getSenses().getApplause_score());
				if (ins.extraData.getObservations().getSenses().getCrowd_density() != null)
					sensors.add("Crowd Density	:" + ins.extraData.getObservations().getSenses().getCrowd_density());
				if (ins.extraData.getObservations().getSenses().getCrowd_score() != null)
					sensors.add("Crowd Score	:" + ins.extraData.getObservations().getSenses().getCrowd_score());
				if (ins.extraData.getObservations().getSenses().getLight() != null)
					sensors.add("Light			:" + ins.extraData.getObservations().getSenses().getLight());
				if (ins.extraData.getObservations().getSenses().getMusic_score() != null)
					sensors.add("Music Score	:" + ins.extraData.getObservations().getSenses().getMusic_score());
				if (ins.extraData.getObservations().getSenses().getSound_level() != null)
					sensors.add("Sound Level	:" + ins.extraData.getObservations().getSenses().getSound_level());
				if (ins.extraData.getObservations().getSenses().getTemperature() != null)
					sensors.add("Temperature	:" + ins.extraData.getObservations().getSenses().getTemperature());
				if (ins.extraData.getObservations().getSenses().getTotalCheckIns() != null)
					sensors.add("Total checkins	:" + ins.extraData.getObservations().getSenses().getTotalCheckIns());
				if (ins.extraData.getObservations().getSenses().getTraffic_score() != null)
					sensors.add("Traffic		:" + ins.extraData.getObservations().getSenses().getTraffic_score());
				if (ins.extraData.getObservations().getSenses().getBattery() != null)
					sensors.add("Battery		:" + ins.extraData.getObservations().getSenses().getBattery());
			}
		}
		List<String> latest = new ArrayList<String>();
		if (ins.extraData.getLatestObservation() != null) {
			if (ins.extraData.getLatestObservation().getSenses() != null) {
				if (ins.extraData.getLatestObservation().getSenses().getColorname() != null)
				{
					String colours="";
					for (String clr: ins.extraData.getLatestObservation().getSenses().getColorname())
					{
						colours += clr + "," ;
					}
					sensors.add("Colours	:" + colours);
				}
				if (ins.extraData.getLatestObservation().getSenses().getApplause_score() != null)
					sensors.add("Applause Score	:" + ins.extraData.getLatestObservation().getSenses().getApplause_score());
				if (ins.extraData.getLatestObservation().getSenses().getCrowd_density() != null)
					sensors.add("Crowd Density	:" + ins.extraData.getLatestObservation().getSenses().getCrowd_density());
				if (ins.extraData.getLatestObservation().getSenses().getCrowd_score() != null)
					sensors.add("Crowd Score	:" + ins.extraData.getLatestObservation().getSenses().getCrowd_score());
				if (ins.extraData.getLatestObservation().getSenses().getLight() != null)
					sensors.add("Light			:" + ins.extraData.getLatestObservation().getSenses().getLight());
				if (ins.extraData.getLatestObservation().getSenses().getMusic_score() != null)
					sensors.add("Music Score	:" + ins.extraData.getLatestObservation().getSenses().getMusic_score());
				if (ins.extraData.getLatestObservation().getSenses().getSound_level() != null)
					sensors.add("Sound Level	:" + ins.extraData.getLatestObservation().getSenses().getSound_level());
				if (ins.extraData.getLatestObservation().getSenses().getTemperature() != null)
					sensors.add("Temperature	:" + ins.extraData.getLatestObservation().getSenses().getTemperature());
				if (ins.extraData.getLatestObservation().getSenses().getTotalCheckIns() != null)
					sensors.add("Total checkins	:" + ins.extraData.getLatestObservation().getSenses().getTotalCheckIns());
				if (ins.extraData.getLatestObservation().getSenses().getTraffic_score() != null)
					sensors.add("Traffic		:" + ins.extraData.getLatestObservation().getSenses().getTraffic_score());
				if (ins.extraData.getLatestObservation().getSenses().getBattery() != null)
					sensors.add("Battery		:" + ins.extraData.getLatestObservation().getSenses().getBattery());
			}
		}

		listDataChild.put(listDataHeader.get(0), description); // Header, Child
																// data
		listDataChild.put(listDataHeader.get(1), location);
		listDataChild.put(listDataHeader.get(2), occurance);
		listDataChild.put(listDataHeader.get(3), ranking);
		listDataChild.put(listDataHeader.get(4), sensors);
		listDataChild.put(listDataHeader.get(5), latest);
	}

	public class ExpandableListAdapter extends BaseExpandableListAdapter {
		private Context _context;
		private List<String> _listDataHeader; // header titles
		// child data in format of header title, child title
		private HashMap<String, List<String>> _listDataChild;

		public ExpandableListAdapter(Context context, List<String> listDataHeader,
				HashMap<String, List<String>> listChildData) {
			this._context = context;
			this._listDataHeader = listDataHeader;
			this._listDataChild = listChildData;
		}

		@Override
		public Object getChild(int groupPosition, int childPosititon) {
			return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {

			final String childText = (String) getChild(groupPosition, childPosition);

			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_item, null);
			}

			TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

			txtListChild.setText(childText);
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return this._listDataHeader.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			return this._listDataHeader.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			String headerTitle = (String) getGroup(groupPosition);
			if (convertView == null) {
				LayoutInflater infalInflater = (LayoutInflater) this._context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = infalInflater.inflate(R.layout.list_group, null);
			}

			TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
			lblListHeader.setTypeface(null, Typeface.BOLD);
			lblListHeader.setText(headerTitle);

			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
}

