package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);
        if (currentEarthquake != null) {
            TextView magnitude = (TextView) listViewItem.findViewById(R.id.magnitude);
            magnitude.setText((new DecimalFormat("0.0")).format(currentEarthquake.getMagnitude()));
            GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
            magnitudeCircle.setColor(getMagnitudeColor(currentEarthquake.getMagnitude()));

            String[] locations = currentEarthquake.getLocation().split(" of ");
            TextView offset = (TextView) listViewItem.findViewById(R.id.offset);
            offset.setText(((locations.length == 1) ? "Near the" : locations[0] + " of"));
            TextView location = (TextView) listViewItem.findViewById(R.id.location);
            location.setText(locations[locations.length - 1]);

            TextView date = (TextView) listViewItem.findViewById(R.id.date);
            date.setText((new SimpleDateFormat("LLL dd, yyyy")).format(new Date(currentEarthquake.getDate())));
            TextView time = (TextView) listViewItem.findViewById(R.id.time);
            time.setText((new SimpleDateFormat("h:mm a")).format(new Date(currentEarthquake.getDate())));
        }

        return listViewItem;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
