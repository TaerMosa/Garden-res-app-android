package Logic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.widget.Toast;

public class MenuUpdateJson {

	public static MenuUpdateJson instance = null;

	public static MenuUpdateJson getInstance() {
		if (instance == null) {
			instance = new MenuUpdateJson();

		}
		return instance;
	}

	public void readJasonFisrtMenu(ArrayList<First> firstList,Context context) throws JSONException {
		String data =DownloadUploadDroboxFiles.getInstance().rFile();
		int placeD = 0;
		int placeDiss = 0;
		JSONObject jRealObject = null;
		if (data != null) {
			JSONTokener readFrom = new JSONTokener(data);

			JSONObject jObject = new JSONObject(readFrom);
			JSONArray jArray = jObject.getJSONArray("data");

			for (int i = 0; i < jArray.length(); i++) {
				jRealObject = jArray.getJSONObject(i);
				if (jRealObject.getString("id").equals("first")) {
					placeD = i;
				}

			}

			for (int i = placeD + 1; i < jArray.length(); i++) {

				First actor = new First();
				jRealObject = jArray.getJSONObject(i);
				actor.setName(jRealObject.getString("id"));
				actor.setDob(jRealObject.getString("title"));
				actor.setDescription(jRealObject.getString("duration"));
				firstList.add(actor);

			}

		} else {
			Toast.makeText(context.getApplicationContext(), "there is no data", Toast.LENGTH_SHORT).show();

		}
	}
	
	public void readJasonMainMenu(ArrayList<First> MainList,Context context) throws JSONException {
		String data =DownloadUploadDroboxFiles.getInstance().rFile();
		int placeM = 0;
		int placeD = 0;
		JSONObject jRealObject = null;
		if (data != null) {
			JSONTokener readFrom = new JSONTokener(data);

			JSONObject jObject = new JSONObject(readFrom);
			JSONArray jArray = jObject.getJSONArray("data");

			for (int i = 0; i < jArray.length(); i++) {
				jRealObject = jArray.getJSONObject(i);
				if (jRealObject.getString("id").equals("MainMeals")) {
					placeM = i;
				} else if (jRealObject.getString("id").equals("desserts")) {
					placeD = i;
				}

			}
			for (int i = placeM + 1; i < placeD; i++) {

				First actor = new First();
				jRealObject = jArray.getJSONObject(i);
				actor.setName(jRealObject.getString("id"));
				actor.setDob(jRealObject.getString("title"));
				actor.setDescription(jRealObject.getString("duration"));
				MainList.add(actor);

			}

		} else {
			Toast.makeText(context.getApplicationContext(), "there is no data", Toast.LENGTH_SHORT).show();

		}
	}
	
	public void readJasonDrinksMenu(ArrayList<First> drinksList,Context context) throws JSONException {
		String data =DownloadUploadDroboxFiles.getInstance().rFile();
		int placeD = 0;
		int placeDiss = 0;
		JSONObject jRealObject = null;
		if (data != null) {
			JSONTokener readFrom = new JSONTokener(data);

			JSONObject jObject = new JSONObject(readFrom);
			JSONArray jArray = jObject.getJSONArray("data");

			for (int i = 0; i < jArray.length(); i++) {
				jRealObject = jArray.getJSONObject(i);
				if (jRealObject.getString("id").equals("drinks")) {
					placeD = i;
				}

			}

			for (int i = placeD + 1; i < jArray.length(); i++) {

				First actor = new First();
				jRealObject = jArray.getJSONObject(i);
				actor.setName(jRealObject.getString("id"));
				actor.setDob(jRealObject.getString("title"));
				actor.setDescription(jRealObject.getString("duration"));
				drinksList.add(actor);

			}

		} else {
			Toast.makeText(context.getApplicationContext(), "there is no data", Toast.LENGTH_SHORT).show();

		}
	}
	
	public void readJasonDessertMenu(ArrayList<First> dessertList,Context context) throws JSONException {
		String data =DownloadUploadDroboxFiles.getInstance().rFile();
		int placeD = 0;
		int placeDrink = 0;
		JSONObject jRealObject = null;
		if (data != null) {
			JSONTokener readFrom = new JSONTokener(data);

			JSONObject jObject = new JSONObject(readFrom);
			JSONArray jArray = jObject.getJSONArray("data");

			for (int i = 0; i < jArray.length(); i++) {
				jRealObject = jArray.getJSONObject(i);
				if (jRealObject.getString("id").equals("desserts")) {
					placeD = i;
				} else if (jRealObject.getString("id").equals("drinks")) {
					placeDrink = i;
				}

			}

			for (int i = placeD + 1; i < placeDrink; i++) {

				First actor = new First();
				jRealObject = jArray.getJSONObject(i);
				actor.setName(jRealObject.getString("id"));
				actor.setDob(jRealObject.getString("title"));
				actor.setDescription(jRealObject.getString("duration"));
				dessertList.add(actor);

			}

		} else {
			Toast.makeText(context.getApplicationContext(), "there is no data", Toast.LENGTH_SHORT).show();

		}
	}
	
}
