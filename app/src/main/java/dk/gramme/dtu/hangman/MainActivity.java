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
    public void onClickKeyboard(View v){
    }

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

//    //Helper method to initialise an array containing all the textviews
//    private void initTextViewArray(){
//        letters[0] = findViewById(R.id.letterA);
//        letters[1] = findViewById(R.id.letterB);
//        letters[2] = findViewById(R.id.letterC);
//        letters[3] = findViewById(R.id.letterD);
//        letters[4] = findViewById(R.id.letterE);
//        letters[5] = findViewById(R.id.letterF);
//        letters[6] = findViewById(R.id.letterG);
//        letters[7] = findViewById(R.id.letterH);
//        letters[8] = findViewById(R.id.letterI);
//        letters[9] = findViewById(R.id.letterJ);
//        letters[10] = findViewById(R.id.letterK);
//        letters[11] = findViewById(R.id.letterL);
//        letters[12] = findViewById(R.id.letterM);
//        letters[13] = findViewById(R.id.letterN);
//        letters[14] = findViewById(R.id.letterO);
//        letters[15] = findViewById(R.id.letterP);
//        letters[16] = findViewById(R.id.letterQ);
//        letters[17] = findViewById(R.id.letterR);
//        letters[18] = findViewById(R.id.letterS);
//        letters[19] = findViewById(R.id.letterT);
//        letters[20] = findViewById(R.id.letterU);
//        letters[21] = findViewById(R.id.letterV);
//        letters[22] = findViewById(R.id.letterW);
//        letters[23] = findViewById(R.id.letterX);
//        letters[24] = findViewById(R.id.letterY);
//        letters[25] = findViewById(R.id.letterZ);
//        letters[26] = findViewById(R.id.letterÆ);
//        letters[27] = findViewById(R.id.letterØ);
//        letters[28] = findViewById(R.id.letterÅ);
//    }
}