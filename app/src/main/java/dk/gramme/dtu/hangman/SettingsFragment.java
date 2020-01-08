package dk.gramme.dtu.hangman;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static dk.gramme.dtu.hangman.MainActivity.getLogic;
import static dk.gramme.dtu.hangman.MainActivity.wordList;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    HighscoreLogic highscoreLogic;
    TextView tw;
    ListView listView;
    Galgelogik galgelogik;
    ProgressDialog progressDialog;
    ToggleButton toggleButton;
    Button saveBtn;
    ArrayAdapter<String> adapter;
    Button helpBtn;
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
        toggleButton = v.findViewById(R.id.setting_toggle);
        saveBtn = v.findViewById(R.id.setting_save);
        saveBtn.setOnClickListener(this);
        helpBtn = v.findViewById(R.id.setting_help);
        helpBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, galgelogik.muligeOrd);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.setting_save){
            if(toggleButton.isChecked()) {
                DRWordDownload wordDownload = new DRWordDownload();
                wordDownload.execute();
            }else{
                galgelogik.muligeOrd.clear();
                galgelogik.muligeOrd.add("bil");
                galgelogik.muligeOrd.add("computer");
                galgelogik.muligeOrd.add("programmering");
                galgelogik.muligeOrd.add("motorvej");
                galgelogik.muligeOrd.add("busrute");
                galgelogik.muligeOrd.add("gangsti");
                galgelogik.muligeOrd.add("skovsnegl");
                galgelogik.muligeOrd.add("solsort");
                galgelogik.muligeOrd.add("nitten");
                galgelogik.nulstil();
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, galgelogik.muligeOrd);
                listView.setAdapter(adapter);
            }
        }else if(v.getId() == R.id.setting_help){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new dk.gramme.dtu.hangman.HelpFragment()).commit();
        }
    }
    //Class to handle downloads from DR.dk with AsyncTask
    private class DRWordDownload extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
            try {
                galgelogik.hentOrdFraDr();
                return "Download fuldført!";
            } catch (Exception e) {
                e.printStackTrace();
                return "Download fejlet: " + e;
            }
        }
        @Override
        protected void onPostExecute(String string) {
            progressDialog.dismiss();
            for(int i = galgelogik.muligeOrd.size()-1; i > 0; i--){
                if(galgelogik.muligeOrd.get(i).length() < 3){
                    galgelogik.muligeOrd.remove(i);
                }
            }
            galgelogik.muligeOrd.remove(0);
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, galgelogik.muligeOrd);
            listView.setAdapter(adapter);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), "DR-Ordvalg", "Henter ord fra DR");
        }

    }
}


