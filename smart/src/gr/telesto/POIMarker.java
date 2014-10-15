/*
 * Copyright (C) 2010- Peer internet solutions
 * 
 * This file part of Smart AR app and is a modification of mixare POIMarker file.
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

import gr.telesto.LocalMarker;

import java.text.DecimalFormat;

import org.mixare.lib.MixUtils;
import org.mixare.lib.gui.PaintScreen;
import org.mixare.lib.gui.TextObj;

import android.graphics.Color;
import android.graphics.Path;
import android.location.Location;

/**
 * This markers represent the points of interest.
 * On the screen they appear as circles, since this
 * class inherits the draw method of the Marker.
 * 
 * @author hannes
 * 
 */
public class POIMarker extends LocalMarker {

	public static final int MAX_OBJECTS = 20;
	public static final int OSM_URL_MAX_OBJECTS = 5;

	public POIMarker(String id, String title, double latitude, double longitude,
			double altitude, String URL, int type, int color) {
		super(id, title, latitude, longitude, altitude, URL, type, color);

	}

	@Override
	public void update(Location curGPSFix) {
		super.update(curGPSFix);
	}

	@Override
	public int getMaxObjects() {
		return MAX_OBJECTS;
	}

	@Override
	public void drawCircle(PaintScreen dw) {
		if (isVisible) {
			float maxHeight = dw.getHeight();
			dw.setStrokeWidth(maxHeight / 100f);
			dw.setFill(false);

				dw.setColor(getColour());
			
			// draw circle with radius depending on distance
			// 0.44 is approx. vertical fov in radians
			double angle = 2.0 * Math.atan2(10, distance);
//			double radius = Math.max(Math.min(angle / 0.44 * maxHeight, maxHeight),	maxHeight / 25f);
			double radius = Math.max(Math.min(angle/2 * maxHeight, maxHeight),	maxHeight / 25f);
			float currentAngle = MixUtils.getAngle(cMarker.x, cMarker.y,signMarker.x, signMarker.y);
			

			/*
			 * distance 100 is the threshold to convert from circle to another
			 * shape
			 */
			if (distance < 100.0){
				otherShape(dw);
			}
			else{
				dw.paintCircle(cMarker.x, cMarker.y, (float) radius);
				Path tri = new Path();
				float x = 0;
				float y = (float)radius;
				tri.moveTo(x, y);
				tri.lineTo(0, 100);
				//tri.lineTo(x + radius, y - radius);

				tri.close();
				dw.paintPath(tri, cMarker.x, cMarker.y, 1f,1f,	currentAngle + 90, 1);
				//dw.paintLine(cMarker.x, cMarker.y+(float)radius, cMarker.x, cMarker.y+100+(float)radius);
			}

		}
	}

	@Override
	public void drawTextBlock(PaintScreen dw) {
		float maxHeight = Math.round(dw.getHeight() / 10f) + 1;
		// TODO: change textblock only when distance changes

		String textStr = "";

		double d = distance;
		DecimalFormat df = new DecimalFormat("@#");
		if (d < 100.0) {
			textStr = title + " (" + df.format(d) + "m)";
		} else {
			d = d / 100.0;
			textStr = title + " (" + df.format(d) + "km)";
		}

		textBlock = new TextObj(textStr, Math.round(maxHeight / 3f) + 1, 250,
				dw, underline);
		
		if (isVisible) {
			// based on the distance set the colour
			if (distance < 100.0) {
				textBlock.setBgColor(Color.argb(128, 52, 52, 52));
				textBlock.setBorderColor(Color.rgb(255, 104, 91));
			} else {
				textBlock.setBgColor(Color.argb(128, 0, 0, 0));
				textBlock.setBorderColor(Color.rgb(255, 255, 255));
			}
			if (this.extraData.getObservations()!=null)
				if (this.extraData.getObservations().getTweets()!=null)
					textBlock.setBorderColor(Color.GREEN);
			//dw.setColor(DataSource.getColor(type));

			float currentAngle = MixUtils.getAngle(cMarker.x, cMarker.y,signMarker.x, signMarker.y);
			txtLab.prepare(textBlock);
			dw.setStrokeWidth(1f);
			dw.setFill(true);
			//dw.paintObj(txtLab, cMarker.x - txtLab.getWidth() / 2, cMarker.y, currentAngle + 90, 1);
			float initX = signMarker.x- txtLab.getWidth() / 2;
			float initY = signMarker.y-txtLab.getHeight()/2;
			float extraX =0;// -200 * (float)Math.cos(currentAngle);	
			float extraY =0;// 200 * (float)Math.sin(currentAngle);
			//Log.v(MixView.TAG, "angle=" + currentAngle);
			
			dw.paintObj(txtLab, initX+extraX, initY+extraY, currentAngle + 90, 1);
			//dw.paintObj(txtLab, signMarker.x - txtLab.getWidth() / 2,signMarker.y + maxHeight+100, currentAngle + 90, 1);
//			dw.paintObj(txtLab, cMarker.x +100*(float)Math.cos(currentAngle), cMarker.y+100*(float)Math.sin(currentAngle),90+currentAngle, 1);

		}
	}

	public void otherShape(PaintScreen dw) {
		// This is to draw new shape, triangle
		float currentAngle = MixUtils.getAngle(cMarker.x, cMarker.y,
				signMarker.x, signMarker.y);
		float maxHeight = Math.round(dw.getHeight() / 10f) + 1;

		dw.setColor(getColour());
		float radius = maxHeight / 1.5f;
		dw.setStrokeWidth(dw.getHeight() / 100f);
		dw.setFill(false);

		Path tri = new Path();
		float x = 0;
		float y = 0;
		tri.moveTo(x, y);
		tri.lineTo(x - radius, y - radius);
		tri.lineTo(x + radius, y - radius);

		tri.close();
		dw.paintPath(tri, cMarker.x, cMarker.y, radius * 2, radius * 2,	currentAngle + 90, 1);
	}
	public void drawLine(PaintScreen dw, float x,float y){
		dw.paintLine(x, y, x, y+100);
		
	}

}
