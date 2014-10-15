/*
 * Copyright (C) 2014 Telesto
 * 
 * This file is part of smart AR app and is a modification of mixare Main Activity  
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

import gr.telesto.search.SearchActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * This is the main activity of Smart search, that will be opened if smartsearch is
 * launched through the android.intent.action.MAIN and is based on Mixare main Activity
 * 
 * 
 * @author gkouzas
 */

public class MainActivity extends Activity {

	private Context ctx;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		startActivity(new Intent(ctx,SearchActivity.class));
		finish();
	}
}