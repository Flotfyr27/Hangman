package dk.gramme.dtu.hangman;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HighscoreFragment extends Fragment {
HighscoreLogic highscoreLogic;
    private ListView mListView;
    SharedPreferences prefs;
    private boolean startup = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_highscore, container, false);
        mListView = v.findViewById(R.id.playerList);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString("scoreboard", null);
        //Get type to convert from Json into
        Type type = new TypeToken<ArrayList<PlayerHighscore>>(){}.getType();
        //Read saved data if any exists
        if (json != null && startup) {
            startup = false;
            ArrayList<PlayerHighscore> playerList = gson.fromJson(json, type);
            PlayerListAdapter adapter = new PlayerListAdapter(getContext(), R.layout.player_highscore, playerList);
            mListView.setAdapter(adapter);
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        highscoreLogic = HighscoreLogic.getHighscoreLogic();
        ArrayList<PlayerHighscore> playerList;
        Gson gson = new Gson();
        //Pull data from SharedPreferences and convert from Json to PlayerHighscore objects
        String json = prefs.getString("scoreboard", "");
        Type type = new TypeToken<ArrayList<PlayerHighscore>>(){}.getType();
        //Check to see if data pulled exists
        if(json != null && json.length() > 0) {
            playerList = gson.fromJson(json, type);
            PlayerListAdapter adapter = new PlayerListAdapter(getContext(), R.layout.player_highscore, playerList);
            mListView.setAdapter(adapter);
        }

    }
}
