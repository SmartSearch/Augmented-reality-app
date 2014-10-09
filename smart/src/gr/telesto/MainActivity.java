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