package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;

import Constant.Constant;
import android.os.Environment;

public class DownloadUploadDroboxFiles {

	public static DownloadUploadDroboxFiles instance = null;

	public static DownloadUploadDroboxFiles getInstance() {
		if (instance == null) {
			instance = new DownloadUploadDroboxFiles();

		}
		return instance;
	}
	public String rFile() throws JSONException {
		String data = null;
		File Root = Environment.getExternalStorageDirectory();
		File Dir = new File(Root.getAbsolutePath() + "/GardenApp");
		File file = new File(Dir + Constant.fullMenu);
		String Message;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			StringBuffer stringbuffer = new StringBuffer();

			while ((Message = bufferReader.readLine()) != null) {
				stringbuffer.append(Message + "\n");

			}

			data = stringbuffer.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	
}
