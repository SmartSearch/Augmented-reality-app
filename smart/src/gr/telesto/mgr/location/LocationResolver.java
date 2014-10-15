/*
 * Copyright (C) 2012- Peer internet solutions & Finalist IT Group
 * 
 * This file is part of mixare and is used in Smart AR app.
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
package gr.telesto.mgr.location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * This class will be used to start each location provider for 20 seconds
 * and they will then listen for locations. This class will check for updates for
 * the observer. 
 * Using this method: http://stackoverflow.com/questions/3145089/
 * @author A. Egal
 */
public class LocationResolver implements LocationListener{

	private String provider;
	private LocationMgrImpl locationMgrImpl;
	private LocationManager lm;
	
	public LocationResolver(LocationManager lm, String provider, LocationMgrImpl locationMgrImpl){
		this.lm = lm;
		this.provider = provider;
		this.locationMgrImpl = locationMgrImpl;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		lm.removeUpdates(this);
		locationMgrImpl.locationCallback(provider);
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	
}
