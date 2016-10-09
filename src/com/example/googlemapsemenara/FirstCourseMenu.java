/**
 * this class represent the First Course menu 
 * download data from dropbox json file 
 * using class MenuUpdateJson.java
 * and show data in a list 
 */
package com.example.googlemapsemenara;

import java.util.ArrayList;
import org.json.JSONException;
import Logic.First;
import Logic.FirstAdapter;
import Logic.MenuUpdateJson;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FirstCourseMenu extends Activity {
	ListView list;
	FirstAdapter adapter;
	ArrayList<First> firstList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_course_menu);
		firstList = new ArrayList<First>();
		try {
			MenuUpdateJson.getInstance().readJasonFisrtMenu(firstList, this); // load data using jsin methods in class menuupdatejson
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListView listview = (ListView) findViewById(R.id.list1);
		adapter = new FirstAdapter(getApplicationContext(), R.layout.row, firstList);

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), firstList.get(position).getName(), Toast.LENGTH_LONG).show();
			}
		});
	}

}
