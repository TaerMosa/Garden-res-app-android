/**
 * this class for reservation user 
 * used for input user and after reserv button click 
 * data upload to dropbox server  
 */
package com.example.googlemapsemenara;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.example.googlemapsemenara.NavigationDrawerFragment.NavigationDrawerCallbacks;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ReservationV extends Activity {

	private static final String APP_KEY = "72qy3mekhel2kev";
	private static final String APP_SECRET = "zzaahekroqjbcu0";
	private static final String ACCESSTOKEN = "HrJc050qCkAAAAAAAAAAFpnckj5USVWdjJv9aInRfZS93ClOsPACXB9xuv1Tbq8R";
	private DropboxAPI.UploadRequest request;
	
	static DropboxAPI<AndroidAuthSession> dropboxAPI; // Api Dropbox
	// fields for user input
	public EditText editText1; // first name
	public EditText editText2; // last name
	public EditText editText3; // id
	public EditText editText4; // num of people
	public DatePicker d1; // date
	public TimePicker t1; // time
	public Spinner spin; // somking yes\no

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// find fields on layout
		setContentView(R.layout.activity_reservation_v);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		spin = (Spinner) findViewById(R.id.tv8);
		t1 = (TimePicker) findViewById(R.id.t1);
		d1 = (DatePicker) findViewById(R.id.d1);
		dropboxAPI = new DropboxAPI<AndroidAuthSession>(buildSession());

	}

	public void WriteFileToEXS(View v) {
// check input 
		if ((editText1.getText().toString().equals("")) || editText2.getText().toString().equals(" ")
				|| editText3.getText().toString().equals(" ") || editText4.getText().toString().equals(" ")) {
			Toast.makeText(getApplicationContext(), "You Have To fill All the Fields", Toast.LENGTH_SHORT).show();

		} else {

			String state;
			state = Environment.getExternalStorageState();
			if (Environment.MEDIA_MOUNTED.equals(state)) {
				File Root = Environment.getExternalStorageDirectory();
				File Dir = new File(Root.getAbsolutePath() + "/Reservation");
				if (!Dir.exists()) {
					Dir.mkdir();
				} else {

					File file = new File(Dir, "MyReservation.txt"); // create reservation file
					int timeH = t1.getCurrentHour();
					String symbolTime = ":";
					int timeM = t1.getCurrentMinute();

					int dateD = d1.getDayOfMonth();
					int dateM = d1.getMonth();
					int dateY = d1.getYear();

					String T = Integer.toString(timeH);
					String T2S = Integer.toString(timeM);

					String symbolDate = "/";
					String DS = Integer.toString(dateD);
					String D2S = Integer.toString(dateM);
					String D3S = Integer.toString(dateY);

					String allDate = DS + symbolDate + D2S + symbolDate + D3S;
					String allTime = T + symbolTime + T2S;

					String spinner = spin.getSelectedItem().toString();
// save details 
					String Message = ("firstName : " + editText1.getText().toString()) + "\n"
							+ ("lastName : " + editText2.getText().toString()) + "\n"
							+ ("Phone Number : " + editText3.getText().toString()) + "\n"
							+ ("Persons : " + editText4.getText().toString()) + "\n" + ("Smoke? : " + spinner) + "\n"
							+ "Time : " + (allTime) + "\n" + "Date : " + (allDate);
					try {
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						fileOutputStream.write(Message.getBytes());
						fileOutputStream.close();
						editText1.setText("");
						editText2.setText("");
						editText3.setText("");
						editText4.setText("");

						Toast.makeText(getApplicationContext(), "Message Saved", Toast.LENGTH_SHORT).show();
						try {
							uploadToDropBoxNew();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else { // exception 
				Toast.makeText(getApplicationContext(), "SD card not found", Toast.LENGTH_SHORT).show();
			}
		}
	}
/**
 * upload to dropbox 
 * @throws FileNotFoundException
 */
	public void uploadToDropBoxNew() throws FileNotFoundException {

		Toast.makeText(getApplicationContext(), "Upload file ...", Toast.LENGTH_SHORT).show();
 // random number for a new reservation file 
		Random r = new Random();
		String x = String.valueOf(r.nextInt(100000));

		final String uploadPathT = "/Reservation/Res" + x + ".txt"; // path file 
		Thread th = new Thread(new Runnable() {
			public void run() {
				File file = null;
				try {
					String fileName = "Reservation/MyReservation.txt";
					String path = Environment.getExternalStorageDirectory() + "/" + fileName;
					file = new File(path);
					// tmpFile = new File(uploadPathF);
				} catch (Exception e) {
					e.printStackTrace();
				}
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					dropboxAPI.putFileOverwrite(uploadPathT, fis, file.length(), null);
				} catch (Exception e) {
				}
				getMain().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "File successfully uploaded.", Toast.LENGTH_SHORT)
								.show();
					}
				});
			}
		});
		th.start();

	}
/**
 * 
 * @return this activity 
 */
	public ReservationV getMain() {
		return this;
	}
	/**
	 * rebuld the session 
	 * @return
	 */
	private AndroidAuthSession buildSession() {
		AppKeyPair appKeyPair = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
		session.setOAuth2AccessToken(ACCESSTOKEN);
		return session;
	}

}
