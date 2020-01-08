package dk.gramme.dtu.hangman;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static dk.gramme.dtu.hangman.MainActivity.getLogic;
import static dk.gramme.dtu.hangman.MainActivity.wordList;

public class SettingsFragment extends Fragment{
    HighscoreLogic highscoreLogic;
    TextView tw;
    ListView listView;
    Galgelogik galgelogik;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        highscoreLogic = HighscoreLogic.getHighscoreLogic();
        tw = v.findViewById(R.id.setting_name);
        //Get name, saved from user input
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String username = prefs.getString("username", "Player1");
        tw.setText(username);
        highscoreLogic.setPlayer(username);
        listView = v.findViewById(R.id.setting_wordlist);
        galgelogik = getLogic();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        wordList = galgelogik.muligeOrd;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, wordList);
        listView.setAdapter(adapter);

    }
}
