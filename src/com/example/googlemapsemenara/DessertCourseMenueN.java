/**
 * this class represent the dessert menu 
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

public class DessertCourseMenueN extends Activity {

	ListView list;   // list to show menu for layout
	FirstAdapter adapter;  // adapter to create list
	ArrayList<First> dessertList; // to add the desert menu 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dessert_course_menue_n);
		dessertList = new ArrayList<First>();
		
		try {
			MenuUpdateJson.getInstance().readJasonDessertMenu(dessertList, this); // download and load json menu file
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListView listview = (ListView) findViewById(R.id.list1);  // find desert list xml
		adapter = new FirstAdapter(getApplicationContext(), R.layout.row, dessertList); // create list

		listview.setAdapter(adapter); // set adapter 

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				Toast.makeText(getApplicationContext(), dessertList.get(position).getName(), Toast.LENGTH_LONG).show();
			}
		});
	}

	


	

}
