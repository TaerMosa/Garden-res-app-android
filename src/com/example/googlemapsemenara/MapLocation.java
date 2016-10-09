/**
 * this class represent the map activity 
 * 1- show user location 
 * 2- find garden location
 * 3- active google navigation from user location to garden ben goruon haifa 
 * 4- show route on the map from user location to garden with steps markers  
 * 
 */
package com.example.googlemapsemenara;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import RoutesGoogleMaps.Route;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MapLocation extends Activity  {

	private final String TAG = "MapLocation";
	private MyMapView mMapView = null;
	
	private final LatLng LOCATION_Garden = new LatLng(32.819252, 34.990329);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Set content view
		setContentView(R.layout.activity_map_location);
		mMapView = (MyMapView) findViewById(R.id.mapview);
		mMapView.init(savedInstanceState);
		

	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.onResume();
		Log.i(TAG, "The activity is and has focus (it is now \"resumed\")");
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.onPause();
		Log.i(TAG, "Another activity is taking focus (this activity is about to be \"paused\")");
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		mMapView.onDestroy();
		Log.i(TAG, "The activity is about to be destroyed.");
	}
/**
 * open google navcation app 
 * and start navc to garden res 
 */
	public void navction() {

		Uri gmmIntentUri = Uri.parse("google.navigation:q=Ben+Gurion+Haifa");
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		mapIntent.setPackage("com.google.android.apps.maps");
		startActivity(mapIntent);
	}
/**
 * draw route on the map 
 * from user location to garden res 
 * with stations markers 
 * using drawroute method from class Route
 */
	public void showroud() {
		Route route = new Route();
		route.drawRoute(mMapView.getGooglemap(), mMapView.getCtx(), mMapView.getMyLocation(), LOCATION_Garden, true,
				"en");
		LatLngBounds bounds = new LatLngBounds.Builder()
				.include(new LatLng(LOCATION_Garden.latitude, LOCATION_Garden.longitude))
				.include(new LatLng(mMapView.getMyLocation().latitude, mMapView.getMyLocation().longitude)).build();

		Point displaySize = new Point();
		getWindowManager().getDefaultDisplay().getSize(displaySize);
		mMapView.getGooglemap().moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, displaySize.x, 400, 0));
	}
/**
 * show garden restaurant location with marker 
 */
	public void showGardenRes() {
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_Garden, 15);
		mMapView.getGooglemap().animateCamera(update);
		mMapView.getGooglemap().addMarker(new MarkerOptions().position(LOCATION_Garden).title("Gaden Resturant"));
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
		case R.id.Map_FindUs:  // show garden location
			showGardenRes();
			return true;
		case R.id.Map_Navigation:
			navction(); // open google navigation 
			return true;

		case R.id.Map_Route:  // draw route on the map 
			showroud();
			return true;
		default:
			return false;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.map_location, menu);
		return true;
	}
	
}
