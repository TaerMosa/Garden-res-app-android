package Logic;

import java.util.ArrayList;

import com.example.googlemapsemenara.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstAdapter extends ArrayAdapter<First> {
	ArrayList<First> actorList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public FirstAdapter(Context context, int resource, ArrayList<First> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		actorList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.tvName = (TextView) v.findViewById(R.id.tvName);

			holder.tvDOB = (TextView) v.findViewById(R.id.tvPrice);

			holder.tvHeight = (TextView) v.findViewById(R.id.tvExp);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.tvName.setText(actorList.get(position).getName());
		holder.tvDOB.setText(actorList.get(position).getDob());
		holder.tvHeight.setText(actorList.get(position).getDescription());
		holder.tvHeight.setText("Meal Name: " + actorList.get(position).getName());
		holder.tvDOB.setText("Meal Price: " + actorList.get(position).getDob());
		holder.tvHeight.setText("Meal Content: " + actorList.get(position).getDescription());

		return v;

	}

	static class ViewHolder {
		public ImageView tvImg;
		public TextView tvName;
		public TextView tvDOB;
		public TextView tvHeight;
	}
}