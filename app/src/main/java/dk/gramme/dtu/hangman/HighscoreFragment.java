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
        Type type = new TypeToken<ArrayList<PlayerHighscore>>(){}.getType();
        if (json != null && startup) {
            startup = false;
            ArrayList<PlayerHighscore> playerList = gson.fromJson(json, type);
            System.out.println("TEST JSON INPUT2 -----");
            System.out.println(json);
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
        String json = prefs.getString("scoreboard", "");
        Type type = new TypeToken<ArrayList<PlayerHighscore>>(){}.getType();
        playerList = gson.fromJson(json, type);
        System.out.println("TEST JSON INPUT -----");
        System.out.println(json);
        PlayerListAdapter adapter = new PlayerListAdapter(getContext(), R.layout.player_highscore, playerList);
        mListView.setAdapter(adapter);

    }
}
