package gr.telesto.details;

import gr.telesto.MainApp;
import gr.telesto.R;
import gr.telesto.data.smart.Tweets;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;

public class SocialDetails extends Activity {
	ArrayList<Item> imageArry = new ArrayList<Item>();
	SocialAdapter adapter;
	public ArrayList<Tweets> tweets = new ArrayList<Tweets>(); 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.socialdetails);
		if ((((MainApp)getApplication()).selectedMarker!=null)&&(((MainApp)getApplication()).selectedMarker.getExtraData().getObservations().getTweets()!=null)){
		
			tweets = ((MainApp)getApplication()).selectedMarker.getExtraData().getObservations().getTweets();
			Log.i("SocialDetails", "teewt size=" +tweets.size());
			for(int i=0;i<tweets.size();i++){
				imageArry.add(new Item(R.drawable.twitter, tweets.get(i).getUser().getScreen_name(), tweets.get(i).getText()));
			}
		}
		else{
			imageArry.add(new Item(R.drawable.twitter, "", "no tweets"));
		}
		
		// add data in contact image adapter
		adapter = new SocialAdapter(this, R.layout.socialitem, imageArry);
		ListView dataList = (ListView) findViewById(R.id.socialDetailsList);
		dataList.setAdapter(adapter);

	}

	public class Item {
		int image;
		String user;
		String text;

		public Item(int image, String user, String text) {
			super();
			this.image = image;
			this.user = user;
			this.text = text;
		}
		public int getImage() {
			return image;
		}
		public void setImage(int image) {
			this.image = image;
		}
	}
	
	public class SocialAdapter extends ArrayAdapter<Item> {
		Context context;
		int layoutResourceId;
		RelativeLayout linearMain;
		ArrayList<Item> data = new ArrayList<Item>();

		public SocialAdapter(Context context, int layoutResourceId, ArrayList<Item> data) {
			super(context, layoutResourceId, data);
			this.layoutResourceId = layoutResourceId;
			this.context = context;
			this.data = data;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
//			ViewHolder holder = null;
			View row = convertView;

			if (row == null) {
				LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				row = inflater.inflate(layoutResourceId, parent, false);

				linearMain = (RelativeLayout) row.findViewById(R.id.socialItem);

				Item myImage = data.get(position);
//				ImageView image = new ImageView(context);
////				int outImage = myImage.image;
//				image.setImageResource( myImage.image);
//				linearMain.addView(image);
//				linearMain.findViewById(R.id.tuser).
				TextView label = (TextView) linearMain.findViewById(R.id.tuser);
				label.setText(myImage.user);
				TextView textlbl =(TextView) linearMain.findViewById(R.id.ttext);
				textlbl.setText(myImage.text);
//				linearMain.addView(label);
//				linearMain.addView(textlbl);
				}
			return row;
		}
	}
}

