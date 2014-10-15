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
package gr.telesto.search;

import gr.telesto.MixView;
import gr.telesto.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	}	
	public void onclick(View v){
		Log.i("Search Activity","Search button pressed");
		String q = "q=";
		String since = "since=";
		String query ="?";
		boolean location = false;
		if (((EditText)findViewById(R.id.editText1)).getText().length()>0)
			q+= ((EditText)findViewById(R.id.editText1)).getText().toString();
		else q="";
		if (((EditText)findViewById(R.id.editText2)).getText().length()>0){			
			since += ((EditText)findViewById(R.id.editText2)).getText().toString();
			since = since.replace('/', '-');
		}
		else since="";
		if (((RadioButton)findViewById(R.id.radio0)).isSelected())
			location = false;
		else location = true;
		if ((q.length()==0)&&(since.length()==0))
			return;
		else{
			query += q;
			if ((since.length()>0)&&(q.length()>0))
				query += "&" + since;
			else 
				query +=  since;
			
		}
		Log.i("Search Activity",query);
		Intent intent = new Intent(this,MixView.class);
		intent.putExtra("query", query);
		intent.putExtra("lcation", location);
		startActivity(intent);
//		finish();		
		
	}
}

