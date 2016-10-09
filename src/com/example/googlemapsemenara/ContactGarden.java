/**
 * this class represent the contact garden 
 * Service page in the app with 4 options 
 * 1- call garden restaurant 
 * 2- sent mail to garden restaurant 
 * 3- trip advisor garden restaurant web page
 * 4- facebook  garden restaurant web page   
 */

package com.example.googlemapsemenara;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ContactGarden extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_garden);

	}

	
/**
 * open the call application from the user device 
 * and calling the garden number 
 * @param v
 */
	public void callGarden(View v) {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "04-8507061"));
		startActivity(intent);
	}

	/**
	 * this method open email application from the user device 
	 * with the garden email 
	 * @param v on send email  click button from xml file 
	 */
	public void sendMail(View v) {

		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		emailIntent.setType("vnd.android.cursor.item/email");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { "GardenApp@gmail.com" });
		startActivity(Intent.createChooser(emailIntent, "Send mail using..."));

	}
	/**
	 * this method open facebook Garden Restaurant on web page  
	 * @param v on facebook click button from xml file 
	 */
	public void goToFaceBook(View v) {
		Uri webpage = Uri.parse("https://www.facebook.com/Garden.rest/?fref=ts");

		Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
		startActivity(webIntent);

	}
/**
 * this method open trip advisor Garden Restaurant on web page  
 * @param v on tripadvisor click button from xml file 
 */
	public void goToTripAdvisor(View v) {
		Uri webpage = Uri.parse(
				"https://www.tripadvisor.co.il/Restaurant_Review-g293982-d3639050-Reviews-Garden_Restaurant-Haifa_Haifa_District.html");

		Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
		startActivity(webIntent);

	}


}
