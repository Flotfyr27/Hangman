package dk.gramme.dtu.hangman;
/**
 * @author https://github.com/Flotfyr27
 * Software Engineering student @ DTU 2019
 */

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ImageView galgepic;
    private static final Galgelogik logic = new Galgelogik();
    TextView letters[] = new TextView[29];
    TextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new dk.gramme.dtu.hangman.HomeFragment()).commit();


    }
    public static Galgelogik getLogic(){
        return logic;
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

    //Helper method to create an alert dialog
    private void retryBtn(String title, String msg){
        //Create the buttons content
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(msg);
//Set the button and its onClick method
        builder.setPositiveButton("Prøv igen?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialogue
                dialog.dismiss();
            }
        });
        //Create the dialogue box
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}