
package com.example.googlemapsemenara;

import android.app.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.json.JSONObject;

import Logic.DownloadUploadDroboxFiles;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class UserMScreen extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	private static TextView textV;
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_mscreen);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	public void showLocationOnMap(View v) {
		Intent intent = new Intent(this, MapLocation.class);
		startActivity(intent);
	}

	public void callAndContact(View v) {
		Intent intent = new Intent(this, ContactGarden.class);
		startActivity(intent);
	}

	public void showMenueView(View v) {
		Intent intent = new Intent(this, MenueActivity.class);
		startActivity(intent);
	}

	public void showReservationView(View V) {
		Intent intent = new Intent(this, ReservationV.class);
		startActivity(intent);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
				.commit();
	}

	public void onSectionAttached(int number) {
		Intent intent = new Intent();
		switch (number) {

		case 1:
			mTitle = getString(R.string.title_section0);
			intent = new Intent(this, NavigationDrawerFragment.class);
			// startActivity(intent);
			break;
		case 2:
			mTitle = getString(R.string.title_section1);
			intent = new Intent(this, MapLocation.class);
			startActivity(intent);
			break;
		case 3:
			mTitle = getString(R.string.title_section2);
			intent = new Intent(this, ContactGarden.class);
			startActivity(intent);
			break;
		case 4:
			mTitle = getString(R.string.title_section3);
			intent = new Intent(this, ReservationV.class);
			startActivity(intent);
			break;
		case 5:
			mTitle = getString(R.string.title_section4);
			intent = new Intent(this, MenueActivity.class);
			startActivity(intent);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.user_mscreen, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_user_mscreen, container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((UserMScreen) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
	
	static  void readJasonFile() {
		try {
			File yourFile = new File(Environment.getExternalStorageDirectory(),
					"GardenApp/GardenAppjasonGarden.txt.txt");
			FileInputStream stream = new FileInputStream(yourFile);
			String jsonStr = null;
			try {
				FileChannel fc = stream.getChannel();
				MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

				jsonStr = Charset.defaultCharset().decode(bb).toString();
			} finally {
				stream.close();
			}

			JSONObject jsonObj = new JSONObject(jsonStr);

			// Getting data JSON Array nodes
			org.json.JSONArray data = jsonObj.getJSONArray("data");

			// looping through All nodes
			HashMap<String, String> parsedData = new HashMap<String, String>();
			for (int i = 0; i < data.length(); i++) {
				JSONObject c = data.getJSONObject(i);

				String id = c.getString("id");
				String title = c.getString("title");
				String duration = c.getString("duration");
				parsedData.put("id", id);
				parsedData.put("title", title);
				parsedData.put("duration", duration);
			}
			textV.setText(parsedData.get(0));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
