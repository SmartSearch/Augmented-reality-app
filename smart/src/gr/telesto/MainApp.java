package gr.telesto;

import android.app.Application;


public class MainApp extends Application{
	public LocalMarker selectedMarker;
	private static MainApp singleton;
	public static MainApp getInstance(){
		return singleton;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
	}	
}
