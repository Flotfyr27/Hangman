package dk.gramme.dtu.hangman;
/**
 * @author https://github.com/Flotfyr27
 * Software Engineering student @ DTU 2019
 */

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new dk.gramme.dtu.hangman.HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.nav_home: selectedFragment = new dk.gramme.dtu.hangman.HomeFragment(); break;
                case R.id.nav_highscore: selectedFragment = new dk.gramme.dtu.hangman.HighscoreFragment(); break;
                case R.id.nav_settings: selectedFragment = new dk.gramme.dtu.hangman.SettingsFragment(); break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFragment).commit();

            return true;
        }
    };
}