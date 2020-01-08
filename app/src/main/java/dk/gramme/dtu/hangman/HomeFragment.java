package dk.gramme.dtu.hangman;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import static dk.gramme.dtu.hangman.MainActivity.getLogic;

public class HomeFragment extends Fragment implements View.OnClickListener{
    int lettersID[] = {R.id.letterA, R.id.letterB, R.id.letterC, R.id.letterC, R.id.letterD, R.id.letterE, R.id.letterF, R.id.letterG, R.id.letterH, R.id.letterI, R.id.letterJ, R.id.letterK,
    R.id.letterL, R.id.letterM, R.id.letterN, R.id.letterO, R.id.letterP, R.id.letterQ, R.id.letterR, R.id.letterS, R.id.letterT, R.id.letterU, R.id.letterV, R.id.letterW, R.id.letterX,
    R.id.letterY, R.id.letterZ, R.id.letterÆ, R.id.letterØ, R.id.letterÅ};
    Galgelogik logic;
    ImageView galgePic;
    TextView word;
    HighscoreLogic HS_logic;
    SharedPreferences prefs;
    LottieAnimationView lottieAnimationView;
    LinearLayout linearLayout;
    private String playerName;

    public HomeFragment(){
        //Required empty constructor
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initTextViewArray(v);
        word = v.findViewById(R.id.GuessWord);
        galgePic = v.findViewById(R.id.galgeView);
        logic = getLogic();
        HS_logic = HighscoreLogic.getHighscoreLogic();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        logic.nulstil();
        word.setText(logic.getSynligtOrd().toUpperCase());
        lottieAnimationView = v.findViewById(R.id.confettiAnimation);
        lottieAnimationView.setAnimation("confetti.json");
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Sets the players name from the saved username
        playerName = prefs.getString("username", "You");
        HS_logic.setPlayer(playerName);
    }

    //Implementation of the onClick method for the TextViews
    @Override
    public void onClick(View v) {
        TextView tw = (TextView) v;
        //Go through all of the views in the array to identify which button was pushed
        for(int i = 0; i < lettersID.length; i++){
            //If the button exists, has not been pressed, and the game is not finished; continue
            if(tw.getId() == lettersID[i] && (tw.getCurrentTextColor() != Color.GREEN && tw.getCurrentTextColor() != Color.RED )&& !logic.erSpilletSlut()){
                logic.gætBogstav(tw.getText().toString().toLowerCase());
                //If letter was correct, colour it green
                if(logic.erSidsteBogstavKorrekt()){
                    ((TextView)v.findViewById(lettersID[i])).setTextColor(Color.GREEN);
                }else {// if letter was wrong
                    //Change picture of gallows according to number of wrong letters, also colour text red
                    int wrong = logic.getAntalForkerteBogstaver();
                    tw.setTextColor(Color.RED);
                    switch (wrong){
                        case 0: galgePic.setImageResource(R.drawable.galge); break;
                        case 1: galgePic.setImageResource(R.drawable.forkert1); break;
                        case 2: galgePic.setImageResource(R.drawable.forkert2); break;
                        case 3: galgePic.setImageResource(R.drawable.forkert3); break;
                        case 4: galgePic.setImageResource(R.drawable.forkert4); break;
                        case 5: galgePic.setImageResource(R.drawable.forkert5); break;
                        case 6: galgePic.setImageResource(R.drawable.forkert6); break;
                    }
                }
            }
        }
        //Change the displayed word to match most current
        word.setText(logic.getSynligtOrd().toUpperCase());
        //If game is over create a dialogue box to replay
        if(logic.erSpilletSlut()){
            if(logic.erSpilletVundet()){
                MainActivity.winGamePlayer.start();
                retryBtn("Tillykke du har vundet!", "Ordet var: " + logic.getOrdet().toUpperCase(),"Next", getContext());
                galgePic.setVisibility(View.GONE);
                lottieAnimationView.setVisibility(View.VISIBLE);
                lottieAnimationView.playAnimation();
            }else{
                MainActivity.loseGamePlayer.start();
                retryBtn("Du har tabt!", "Ordet var: " + logic.getOrdet().toUpperCase(), "Prøv igen", getContext());
            }
        }
    }

    public void retryBtn(String title, String msg, String btnMsg, Context context){
        final int score = HS_logic.calcScore(logic.getOrdet().length(), logic.getAntalForkerteBogstaver());
        HS_logic.setScore(HS_logic.getScore() + score);
        //Create button content
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false).setTitle(title);
        //Create message for player
        if(logic.erSpilletVundet()){
            builder.setMessage(msg + HS_logic.generateHighscoreMessage(logic.getOrdet(), logic.getAntalForkerteBogstaver()));
        }else{
            builder.setMessage(msg + HS_logic.generateHighscoreMessage(logic.getOrdet(), logic.getAntalForkerteBogstaver()));
            HS_logic.addToLeaderboard(HS_logic.getPlayer(), HS_logic.getScore(), getActivity());
            Toast.makeText(getActivity(), "Leaderboard updated", Toast.LENGTH_SHORT).show();
            HS_logic.resetScore();
        }
        //Set buttons onClick method
        builder.setPositiveButton(btnMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialogue
                dialog.dismiss();
                //Change text to black
                for(int i = 0; i < lettersID.length; i++){
                    ((TextView) getView().findViewById(lettersID[i])).setTextColor(Color.BLACK);
                }
                //Reset image
                galgePic.setImageResource(R.drawable.galge);
                //Reset logic
                logic.nulstil();
                //Reset shown word
                word.setText(logic.getSynligtOrd().toUpperCase());
                //Hide confetti and show the gallows
                galgePic.setVisibility(View.VISIBLE);
                lottieAnimationView.pauseAnimation();
                lottieAnimationView.setVisibility(View.GONE);
            }
        });
        //Create the dialogue box
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    //Helper method to initialise an array containing all the textviews
    private void initTextViewArray(View v){
        v.findViewById(R.id.letterA).setOnClickListener(this);
        v.findViewById(R.id.letterB).setOnClickListener(this);
        v.findViewById(R.id.letterC).setOnClickListener(this);
        v.findViewById(R.id.letterD).setOnClickListener(this);
        v.findViewById(R.id.letterE).setOnClickListener(this);
        v.findViewById(R.id.letterF).setOnClickListener(this);
        v.findViewById(R.id.letterG).setOnClickListener(this);
        v.findViewById(R.id.letterH).setOnClickListener(this);
        v.findViewById(R.id.letterI).setOnClickListener(this);
        v.findViewById(R.id.letterJ).setOnClickListener(this);
        v.findViewById(R.id.letterK).setOnClickListener(this);
        v.findViewById(R.id.letterL).setOnClickListener(this);
        v.findViewById(R.id.letterM).setOnClickListener(this);
        v.findViewById(R.id.letterN).setOnClickListener(this);
        v.findViewById(R.id.letterO).setOnClickListener(this);
        v.findViewById(R.id.letterP).setOnClickListener(this);
        v.findViewById(R.id.letterQ).setOnClickListener(this);
        v.findViewById(R.id.letterR).setOnClickListener(this);
        v.findViewById(R.id.letterS).setOnClickListener(this);
        v.findViewById(R.id.letterT).setOnClickListener(this);
        v.findViewById(R.id.letterU).setOnClickListener(this);
        v.findViewById(R.id.letterV).setOnClickListener(this);
        v.findViewById(R.id.letterW).setOnClickListener(this);
        v.findViewById(R.id.letterX).setOnClickListener(this);
        v.findViewById(R.id.letterZ).setOnClickListener(this);
        v.findViewById(R.id.letterY).setOnClickListener(this);
        v.findViewById(R.id.letterÆ).setOnClickListener(this);
        v.findViewById(R.id.letterØ).setOnClickListener(this);
        v.findViewById(R.id.letterÅ).setOnClickListener(this);
    }
}