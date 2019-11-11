package dk.gramme.dtu.hangman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlayerListAdapter extends ArrayAdapter<PlayerHighscore> {
    private static final String TAG = "PlayerListAdapter";
    private Context context;
    private int mResource;

    public PlayerListAdapter(Context context, int resource, ArrayList<PlayerHighscore> objects) {
        super(context, resource, objects);
        this.context = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Get information
        String placement = getItem(position).getPlacement();
        String name = getItem(position).getName();
        int points = getItem(position).getPoints();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource, parent, false);
        //Access views
        TextView twPlacement = convertView.findViewById(R.id.highscore_placement);
        TextView twName = convertView.findViewById(R.id.highscore_playerName);
        TextView twPoints = convertView.findViewById(R.id.highscore_points);
        //Save data to views
        String pts = Integer.toString(points);
        twPlacement.setText(placement);
        twName.setText(name);
        twPoints.setText(pts);

        return convertView;
    }
}
