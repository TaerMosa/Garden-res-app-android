package com.example.googlemapsemenara;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenueActivity extends Activity {
	private TextView editText ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menue);
		editText = (TextView) findViewById(R.id.tv1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menue, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//---------------------- open menus activities (drink,dessert,first,main)------------------------------------
	public void showFirstCourseMenue(View v)
	{
		Intent intent = new Intent(this ,FirstCourseMenu.class);
		startActivity(intent);
	}
	public void showMainCourseMenue(View v)
	{
		Intent intent = new Intent(this ,MainCourseMenueN.class);
		startActivity(intent);
	}
	public void showDessertMenue(View v)
	{
		Intent intent = new Intent(this ,DessertCourseMenueN.class);
		startActivity(intent);
	}
	public void showDrinkstMenue(View v)
	{
		Intent intent = new Intent(this ,DrinksCourseMenueN.class);
		startActivity(intent);
	}


	
}
