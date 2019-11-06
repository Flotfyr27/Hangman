package dk.gramme.dtu.hangman;
/**
 * @author https://github.com/Flotfyr27
 * Software Engineering student @ DTU 2019
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final Galgelogik logic = new Galgelogik();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new dk.gramme.dtu.hangman.UsernameFragment(), "USER_FRAG").commit();


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
            if(name == null){
                Toast.makeText(getApplicationContext(), "Indtast brugernavn", Toast.LENGTH_SHORT).show();
                return false;
            }
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
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFragment).commit();

                return true;

        }
    };

}