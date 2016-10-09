package com.example.googlemapsemenara;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import Location.GoogleLocationHolder;
import Location.GoogleLocationListener;
import RoutesGoogleMaps.Route;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;


public class MyMapView extends FrameLayout implements GoogleLocationListener{
	
	private Context ctx = null;
	private GoogleMap googlemap;
	private Marker currentLocationMarker = null;
	private MapView mMapView = null;
	private static int mode= GoogleMap.MAP_TYPE_NORMAL;
	private ImageButton target = null;
	private LatLng myLocation = null;
	private float defaultZoom = 17;
	private  GroundOverlay facilityGroundOverlay= null;
	private Polyline path = null;
	private final LatLng LOCATION_Israel = new LatLng(32.46, 35.00);

	public MyMapView(Context context, AttributeSet attrs) {
		
		super(context, attrs);
		ctx = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.activity_my_map_view, this, true);
		
	}

	public void init(Bundle savedInstanceState) {
		
		try {
			MapsInitializer.initialize(ctx);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		mMapView = (MapView) findViewById(R.id.googleMap);
		mMapView.onCreate(savedInstanceState);
	
	    
		googlemap = mMapView.getMap();
		googlemap.setMapType(mode);
		googlemap.getUiSettings().setZoomControlsEnabled(true);
		
		
		
		
		target = (ImageButton) findViewById(R.id.target);
		target.setOnClickListener(targetlistener);
		
		GoogleLocationHolder.getInstance().init(ctx);
		GoogleLocationHolder.getInstance().subscribeForLocation(this);
	}
	
	
	private void drawPlolyLine() {
		PolylineOptions polyoptions = new PolylineOptions().color(Color.BLUE);
		
		polyoptions.add(new LatLng(30,31), new LatLng(32,33));
	
		
		if (polyoptions != null) {
			path = googlemap.addPolyline(polyoptions);
		}
	}



	private OnClickListener targetlistener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (myLocation != null) {
				googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, defaultZoom));
			}
		}
	};
	
	
	public void onResume() {
		try {
			mMapView.onResume();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void onDestroy() {
		
		if (facilityGroundOverlay != null) {
			facilityGroundOverlay.remove();
			facilityGroundOverlay = null;
		}
		if(currentLocationMarker!=null){
			currentLocationMarker.remove();
		}
		
		if(path!=null){
			path.remove();
		}
		
		mMapView.onDestroy();
	}

	
	public void onPause() {
		mMapView.onPause();
	}

	
	public void onLowMemory() {
		mMapView.onLowMemory();
	}

	public void onSaveInstanceState(Bundle outState) {
		mMapView.onSaveInstanceState(outState);
	}

	@Override
	public void GoogleLocationChange(LatLng GLocation) {
		
		myLocation = GLocation;
		
		if (myLocation != null) {
			
			if(currentLocationMarker==null){
				CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLocation,defaultZoom);
				googlemap.animateCamera(update);
				currentLocationMarker = googlemap.addMarker(new MarkerOptions().position(myLocation).title("Your Location"));
			}
			else{
				currentLocationMarker.setPosition(myLocation);
			}
			
			
			
		}
		
	}

	
	
	//------------------get\set----------------------------------
	
	public Context getCtx() {
		return ctx;
	}

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}

	public GoogleMap getGooglemap() {
		return googlemap;
	}

	public void setGooglemap(GoogleMap googlemap) {
		this.googlemap = googlemap;
	}

	public Marker getCurrentLocationMarker() {
		return currentLocationMarker;
	}

	public void setCurrentLocationMarker(Marker currentLocationMarker) {
		this.currentLocationMarker = currentLocationMarker;
	}

	public LatLng getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(LatLng myLocation) {
		this.myLocation = myLocation;
	}


}
