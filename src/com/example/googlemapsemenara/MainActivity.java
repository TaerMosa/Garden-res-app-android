/**
 * this class used for download dropbox data 
 * and save it just when the user enter to first 
 * screen in the up 
 */
package com.example.googlemapsemenara;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import Constant.Constant;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Reservation"; // path storage file
	public static File Root = Environment.getExternalStorageDirectory(); 
	public static File Dir = new File(Root.getAbsolutePath() + "/GardenApp");  
	public static File Dir2 = new File(Root.getAbsolutePath() + "/Reservation");
	public static Button button3;
	public static TextView textV;

	private Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		b1 = (Button) findViewById(R.id.button1);
		b1.setVisibility(ProgressBar.INVISIBLE);
		AndroidAuthSession session = buildSession();
		dropboxAPI = new DropboxAPI<AndroidAuthSession>(session);
		new JSONAsyncTask().execute();
		Dir.mkdirs();

	}
/**
 * go back to user screen activity 
 * @param v
 */
	public void goToMainScreen(View v) {
		Intent intent = new Intent(this, UserMScreen.class);
		startActivity(intent);
	}
	/**
	 * 
	 * @return this activity 
	 */
	public MainActivity getMain() {
		return this;
	}
	
	
//--------------------------- Dropbox open and download data ------------------------------------------------
	static DropboxAPI<AndroidAuthSession> dropboxAPI;
	private static final String APP_KEY = "72qy3mekhel2kev";
	private static final String APP_SECRET = "zzaahekroqjbcu0";
	private static final String ACCESSTOKEN = "HrJc050qCkAAAAAAAAAAFpnckj5USVWdjJv9aInRfZS93ClOsPACXB9xuv1Tbq8R";
	private DropboxAPI.UploadRequest request;

	private AndroidAuthSession buildSession() {
		AppKeyPair appKeyPair = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeyPair);
		session.setOAuth2AccessToken(ACCESSTOKEN);
		return session;
	}

	static final int UploadFromSelectApp = 9501;
	static final int UploadFromFilemanager = 9502;
	public static String DropboxUploadPathFrom = "";
	public static String DropboxUploadName = "";
	public static String DropboxDownloadPathFrom = "";
	public static String DropboxDownloadPathTo = "";

	private void DownloadFromDropboxFromPath(String downloadPathTo, String downloadPathFrom) {
		DropboxDownloadPathTo = downloadPathTo;
		DropboxDownloadPathFrom = downloadPathFrom;
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Thread th = new Thread(new Runnable() {
					public void run() {
						File file = new File(DropboxDownloadPathTo
								+ DropboxDownloadPathFrom.substring(DropboxDownloadPathFrom.lastIndexOf('.')));
						if (file.exists())
							file.delete();
						try {

							FileOutputStream outputStream = new FileOutputStream(file);
							MainActivity.dropboxAPI.getFile(DropboxDownloadPathFrom, null, outputStream, null);

							getMain().runOnUiThread(new Runnable() {
								@Override
								public void run() {
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				th.start();
			}
		});
	}

	
/**
 * upload to dropbox for reservation 
 * @throws FileNotFoundException
 */
	public void uploadToDropBoxNew() throws FileNotFoundException {
		Toast.makeText(getApplicationContext(), "Upload file ...", Toast.LENGTH_SHORT).show();
		final String uploadPathT = "/Reservation/Res.txt";
		Thread th = new Thread(new Runnable() {
			public void run() {
				File file = null;
				try {
					String fileName = "Reservation/MyReservation.txt";
					String path = Environment.getExternalStorageDirectory() + "/" + fileName;
					file = new File(path);

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
//-------------------------- Async task --------------------------------------------------------------
	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setMessage("Loading, please wait");
			dialog.setTitle("Connecting server");
			dialog.show();
			dialog.setCancelable(false);
		}

		@Override
		protected Boolean doInBackground(String... urls) {
			try {
				DownloadFromDropboxFromPath(Dir + Constant.fullMenu2, Constant.fullMenu);

				return true;
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			return false;
		}

		protected void onPostExecute(Boolean result) {
			if (result == true) {
				dialog.cancel();
				b1.setVisibility(ProgressBar.VISIBLE);
			} else if (result == false)
				Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

		}
	}

}
