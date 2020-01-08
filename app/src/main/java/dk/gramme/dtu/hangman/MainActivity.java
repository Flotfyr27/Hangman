package dk.gramme.dtu.hangman;
/**
 * @author https://github.com/Flotfyr27
 * Software Engineering student @ DTU 2019
 */

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final Galgelogik logic = new Galgelogik();
    private HighscoreLogic HS_logic;
    private SharedPreferences prefs;
    public static ArrayList<String> wordList;
    public static MediaPlayer loseGamePlayer;
    public static MediaPlayer winGamePlayer;
    BottomNavigationView bottomNav;

    @Override
    public void onBackPressed() {
        bottomNav.setSelectedItemId(R.id.nav_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new dk.gramme.dtu.hangman.HomeFragment()).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        HS_logic = HighscoreLogic.getHighscoreLogic();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new dk.gramme.dtu.hangman.UsernameFragment(), "USER_FRAG").commit();
        wordList = new ArrayList<>();
        loseGamePlayer = MediaPlayer.create(this, R.raw.womp);
        winGamePlayer = MediaPlayer.create(this, R.raw.youwin);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Saves the scoreboard whenever this fragment is paused
        Gson gson = new Gson();
        String json = gson.toJson(HS_logic.getList());
        prefs.edit().putString("scoreboard", json).apply();
    }
    public static Galgelogik getLogic(){
        return logic;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String name = prefs.getString("username", null);
            //Makes sure the username has been entered before the bottom navbar is usable
            if(name == null){
                Toast.makeText(getApplicationContext(), "Indtast brugernavn", Toast.LENGTH_SHORT).show();
                return false;
            }
            //Switch to determine which navbar icon has been clicked on
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new dk.gramme.dtu.hangman.HomeFragment();
                        break;
                    case R.id.nav_highscore:
                        selectedFragment = new dk.gramme.dtu.hangman.HighscoreFragment();
                        break;
                    case R.id.nav_settings:
                        selectedFragment = new dk.gramme.dtu.hangman.SettingsFragment();
                        break;
                }
            //Change to selected fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFragment).commit();

                return true;

        }
    };

}