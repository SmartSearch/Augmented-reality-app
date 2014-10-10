/*

 * Copyright (C) 2010- Peer internet solutions
 * 
 * This file is part of mixare.
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

package gr.telesto;

import gr.telesto.data.DataSourceList;
//import gr.telesto.poidetails.DialogDetails;

import gr.telesto.details.DetailActivity;
import gr.telesto.details.SocialDetails;
import gr.telesto.details.StreetViewActivity;
import gr.telesto.details.DetailsOptionInterface;

import java.util.ArrayList;
import java.util.List;

//import org.mixare.lib.MixUtils;




import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Canvas;
import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.Point;
//import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;
//import com.google.android.maps.ItemizedOverlay;
//import com.google.android.maps.MapView;
//import com.google.android.maps.Overlay;
//import com.google.android.maps.OverlayItem;
//import com.google.android.maps.Projection;

/**
 * This class creates the map view and its overlay. It also adds an overlay with
 * the markers to the map.
 */
public class MixMap extends FragmentActivity implements OnTouchListener,
		OnMarkerClickListener, OnInfoWindowClickListener, DetailsOptionInterface {

//	private static List<Overlay> mapOverlays;
	private GoogleMap mMap;
	private POIMarker initLocation;
	private ActionBar actionBar;

	private static List<LocalMarker> markerList;
	private static DataView dataView;
	private static List<GeoPoint> walkingPath = new ArrayList<GeoPoint>();

	public static final String PREFS_NAME = "MixMapPrefs";
	public LocalMarker selectedPOI;

	private MixContext mixContext;
//	private MapView mapView;
	private Dialog settingsDialog;

	private static Context thisContext;
	private static TextView searchNotificationTxt;
	public static List<LocalMarker> originalMarkerList;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basic_demo);
		dataView = MixView.getDataView();
		setMixContext(dataView.getContext());
		setMarkerList(dataView.getDataHandler().getMarkerList());
		actionBar = getActionBar();
		actionBar.show();
		setMapContext(this);
		setUpMapIfNeeded();
		
		addMarkerstoMap();
		
		if (dataView.isFrozen()) {
			searchNotificationTxt = new TextView(this);
			searchNotificationTxt.setWidth(MixView.getdWindow().getWidth());
			searchNotificationTxt.setPadding(10, 2, 0, 0);
			searchNotificationTxt.setText(getString(R.string.search_active_1)
					+ " " + DataSourceList.getDataSourcesStringList()
					+ getString(R.string.search_active_2));
			searchNotificationTxt.setBackgroundColor(Color.DKGRAY);
			searchNotificationTxt.setTextColor(Color.WHITE);

			searchNotificationTxt.setOnTouchListener(this);
//			addContentView(searchNotificationTxt, new LayoutParams(
//					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			addContentView(searchNotificationTxt, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		}
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map. 

		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {

		// mMap.setMyLocationEnabled(true);
		Location location = getMixContext().getLocationFinder()
				.getCurrentLocation();
		// Location location = locationManager.getLastKnownLocation(provider);
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		LatLng coordinate = new LatLng(lat, lng);
		CameraUpdate center = CameraUpdateFactory.newLatLng(coordinate);
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
		mMap.moveCamera(center);
		mMap.animateCamera(zoom);
		mMap.addMarker(new MarkerOptions().position(setStartPoint()).title(
				"My Position"));
		this.initLocation = new POIMarker("", "My Position", lat, lng, 0, "", 6, Color.WHITE);
		mMap.setOnMarkerClickListener(this);
		mMap.setOnInfoWindowClickListener(this);
	}

	/**
	 * Closes MapView Activity and returns that request to NOT refresh screen by
	 * default.
	 * 
	 * @param boolean do refresh? true or false
	 */
	private void closeMapViewActivity(boolean doRefreshScreen) {
		Intent closeMapView = new Intent();
		closeMapView.putExtra("RefreshScreen", doRefreshScreen);
		setResult(RESULT_OK, closeMapView);
		finish();
	}

	/**
	 * Closes MapView Activity and returns that request to NOT refresh screen.
	 * Default value is false
	 */
	private void closeMapViewActivity() {
		closeMapViewActivity(false);
	}
	
	private void startStreetView() {	
	Intent intent = new Intent(MixMap.this, StreetViewActivity.class);
	MainApp.getInstance().selectedMarker = this.initLocation;
	startActivityForResult(intent, 20);
//		Intent intent2 = new Intent(MixMap.this, StreetMap.class);
//		startActivityForResult(intent2, 20);
	}

	/* ********* Operators ********** */
	public LatLng setStartPoint() {

		Location location = getMixContext().getLocationFinder()
				.getCurrentLocation();
		LatLng start = new LatLng(location.getLatitude(),
				location.getLongitude());
		return start;
	}

//	public void createWalkingPath() {
//		if (isPathVisible()) {
//			mapOverlays = mapView.getOverlays();
//			Overlay item = new MixPath(walkingPath);
//			mapOverlays.add(item);
//		}
//	}

	

	/* ********* Operator - Menu ***** */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		int base = Menu.FIRST;
		/* define the first */

		MenuItem item1 = menu.add(base, base, base,getString(R.string.map_menu_cam_mode));
		item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		MenuItem item2 = menu.add(base, base + 1, base + 1,getString(R.string.map_menu_streetview_mode));
		item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		MenuItem item3 = menu.add(base, base + 2, base + 2,getString(R.string.map_my_location));
		item3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		/* assign icons to the menu items */
		item1.setIcon(android.R.drawable.ic_menu_camera);
		item2.setIcon(android.R.drawable.ic_menu_mapmode);
		item3.setIcon(android.R.drawable.ic_menu_mylocation);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		/* Camera View */
		case 1:
			closeMapViewActivity();
			break;
		/* street View */
		case 2:
			startStreetView();
			break;
		/* go to users location */
		case 3:
			setStartPoint();
			break;
		}
		return true;
	}

	public void startPointMsg() {
		Toast.makeText(getMapContext(), R.string.map_current_location_click,
				Toast.LENGTH_LONG).show();
	}

	/* ************ Handlers ************ */

	// private void handleIntent(Intent intent) {
	// if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	// String query = intent.getStringExtra(SearchManager.QUERY);
	// doMixSearch(query);
	// }
	// }

	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		return false;
	}

	/* ******* Getter and Setters ********** */

	/**
	 * @return the mixContext
	 */
	private MixContext getMixContext() {
		return mixContext;
	}

	/**
	 * @param mixContext
	 *            the mixContext to set
	 */
	private void setMixContext(MixContext mixContext) {
		this.mixContext = mixContext;
	}

//	/**
//	 * @return the mapView
//	 */
//	private MapView getMapView() {
//		return mapView;
//	}

//	/**
//	 * @param mapView
//	 *            the mapView to set
//	 */
//	private void setMapView(MapView mapView) {
//		this.mapView = mapView;
//	}

	public void setMarkerList(List<LocalMarker> maList) {
		markerList = maList;
	}

	public DataView getDataView() {
		return dataView;
	}

	// public List<Overlay> getMapOverlayList(){
	// return getMapOverlays();
	// }

	public void setMapContext(Context context) {
		thisContext = context;
	}

	public Context getMapContext() {
		return thisContext;
	}

	/**
	 * Adds a position to the walking route.(This route will be drawn on the
	 * map)
	 */
	public static void addWalkingPathPosition(GeoPoint geoPoint) {
		walkingPath.add(geoPoint);
	}
//
//	private boolean isPathVisible() {
//		final String property = "pathVisible";
//		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//		return settings.getBoolean(property, true);
//	}

	public void addMarkerstoMap() {
		int counter = 0;
		float colour;
		for (LocalMarker marker : markerList) {
			// if (marker.isActive()) {
			LatLng point = new LatLng(marker.getLatitude(),
					marker.getLongitude());
			colour = BitmapDescriptorFactory.HUE_BLUE;
			if (marker.extraData.getObservations() != null) {
				if (marker.extraData.getObservations().getTweets()!=null) {
					colour = BitmapDescriptorFactory.HUE_GREEN;
				}
			}

			mMap.addMarker(new MarkerOptions().position(point)
					.icon(BitmapDescriptorFactory.defaultMarker(colour))
					.title(marker.getTitle()));
			counter++;

		}
		Log.i("MixMap", "Numm of markers=" + counter);
	}

	@Override
	public boolean onMarkerClick(com.google.android.gms.maps.model.Marker arg0) {

		return false;
	}

	void showDialog() {
		settingsDialog = new Dialog(this);
		settingsDialog.setTitle("Event Options");
		View dialogView = getLayoutInflater().inflate(R.layout.details_dialog , null);
		settingsDialog.setContentView(dialogView); 
		settingsDialog.show(); 
//		DialogDetails newFragment = DialogDetails
//				.newInstance(R.string.alert_dialog_two_buttons_title);
//		newFragment.show(getFragmentManager(), "Select an option for Event");
	}

	public void doCancelClick(View v) {
		Log.i("Dialog MixMap", "Cancel click!");
		settingsDialog.dismiss();
	}
		public void doDetailsClick(View v) {
		Log.i("Dialog MixMap", "Details click!");
		Intent intent = new Intent(MixMap.this, DetailActivity.class);
		MainApp.getInstance().selectedMarker = this.selectedPOI;
		startActivityForResult(intent, 20);
		settingsDialog.dismiss();
	}

	public void doSocialClick(View v) {
//		Log.i("Dialog MixMap", "Social click!");
		Intent intent = new Intent(MixMap.this, SocialDetails.class);
		MainApp.getInstance().selectedMarker = this.selectedPOI;
		startActivityForResult(intent, 20);
		settingsDialog.dismiss();
	}
	
	public void doStreetViewClick(View v) {
//		Log.i("Dialog MixMap", "Street View click!");
		Intent intent = new Intent(MixMap.this, StreetViewActivity.class);
		MainApp.getInstance().selectedMarker = this.selectedPOI;
		startActivityForResult(intent, 20);
		settingsDialog.dismiss();
	}

	@Override
	public void onInfoWindowClick(com.google.android.gms.maps.model.Marker arg0) {
		this.selectedPOI = null;
//		Log.i("MixMap", "onMarkerClick ");
		if ((arg0.getTitle().equals(this.initLocation.title))){
			this.selectedPOI = this.initLocation;
			Log.i("MixMap", "onMarkerClick selected POI");
			showDialog();
		}
		for (LocalMarker marker : markerList) {
			if ((arg0.getTitle().equals(marker.title))) {
				this.selectedPOI = marker;
				Log.i("MixMap", "onMarkerClick selected POI");
				showDialog();
				break;
			}
		}

	}




}
//
///**
// * Draws Items on the map.
// */
//class MixOverlay extends ItemizedOverlay<OverlayItem> {
//
//	private ArrayList<OverlayItem> overlayItems = new ArrayList<OverlayItem>();
//	private MixMap mixMap;
//
//	public MixOverlay(MixMap mixMap, Drawable marker) {
//		super(boundCenterBottom(marker));
//		// need to call populate here. See
//		// http://code.google.com/p/android/issues/detail?id=2035
//		populate();
//		this.mixMap = mixMap;
//	}
//
//	@Override
//	protected OverlayItem createItem(int i) {
//		return overlayItems.get(i);
//	}
//
//	@Override
//	public int size() {
//		return overlayItems.size();
//	}
//
//	@Override
//	protected boolean onTap(int index) {
//		if (size() == 1)
//			mixMap.startPointMsg();
//		else if (mixMap.getDataView().getDataHandler().getMarker(index)
//				.getURL() != null) {
//			String url = mixMap.getDataView().getDataHandler().getMarker(index)
//					.getURL();
//			Log.d("MapView", "opern url: " + url);
//			try {
//				if (url != null && url.startsWith("webpage")) {
//					String newUrl = MixUtils.parseAction(url);
//					mixMap.getDataView().getContext().getWebContentManager()
//							.loadWebPage(newUrl, mixMap.getMapContext());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		return true;
//	}
//
//	public void addOverlay(OverlayItem overlay) {
//		overlayItems.add(overlay);
//		populate();
//	}
//}
//
///**
// * Draws a path(line) on the map.
// */
//class MixPath extends Overlay {
//
//	private List<GeoPoint> geoPoints;
//
//	public MixPath(List<GeoPoint> geoPoints) {
//		Log.i("MapActivity", geoPoints.toString());
//		this.geoPoints = geoPoints;
//	}
//
//	public void draw(Canvas canvas, MapView mapv, boolean shadow) {
//		super.draw(canvas, mapv, shadow);
//
//		if (geoPoints.size() <= 0) {
//			return;
//		}
//
//		Projection projection = mapv.getProjection();
//		Paint mPaint = new Paint();
//		mPaint.setDither(true);
//		mPaint.setColor(Color.BLUE);
//		mPaint.setStyle(Paint.Style.STROKE);
//		mPaint.setStrokeJoin(Paint.Join.ROUND);
//		mPaint.setStrokeCap(Paint.Cap.ROUND);
//		mPaint.setStrokeWidth(3);
//
//		Path path = new Path();
//
//		Point start = new Point();
//		projection.toPixels(geoPoints.get(0), start);
//		path.moveTo(start.x, start.y);
//
//		for (GeoPoint gp : geoPoints) {
//			Point p = new Point();
//			projection.toPixels(gp, p);
//			path.lineTo(p.x, p.y);
//		}
//
//		canvas.drawPath(path, mPaint);
//	}
//}