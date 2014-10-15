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

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import gr.telesto.MainApp;
import gr.telesto.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * This shows how to create a simple activity with streetview
 */
public class StreetViewActivity extends FragmentActivity {

    private StreetViewPanorama svp;
    private LatLng location;

    // George St, Sydney
    //private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        double lat = ((MainApp)getApplication()).selectedMarker.getLatitude();
        double lng = ((MainApp)getApplication()).selectedMarker.getLongitude();
        location = new LatLng(lat, lng);
        setContentView(R.layout.street_view_panorama_basic_demo);

        setUpStreetViewPanoramaIfNeeded(savedInstanceState);
    }

    private void setUpStreetViewPanoramaIfNeeded(Bundle savedInstanceState) {
        if (svp == null) {
            svp = ((SupportStreetViewPanoramaFragment)
                getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama))
                    .getStreetViewPanorama();
            if (svp != null) {
                if (savedInstanceState == null) {
                    svp.setPosition(location);
                }
            }
        }
    }
}
