/**
 * load main meals menu 
 * and save it in the list 
 * and show as a list view 
 */
package com.example.googlemapsemenara;

import java.util.ArrayList;
import org.json.JSONException;
import Logic.First;
import Logic.FirstAdapter;
import Logic.MenuUpdateJson;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainCourseMenueN extends Activity {
	ListView list; // layout 
	FirstAdapter adapter; // to set adapter
	ArrayList<First> MainList; // to add rows

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_course_menue_n);
		MainList = new ArrayList<First>();
		try {
			MenuUpdateJson.getInstance().readJasonMainMenu(MainList, this); // load json data 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListView listview = (ListView) findViewById(R.id.list1);
		adapter = new FirstAdapter(getApplicationContext(), R.layout.row, MainList); // create layout 

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(), MainList.get(position).getName(), Toast.LENGTH_LONG).show();
			}
		});
	}


	

}
