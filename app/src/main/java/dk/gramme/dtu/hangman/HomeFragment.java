package dk.gramme.dtu.hangman;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements View.OnClickListener{
    int lettersID[] = {R.id.letterA, R.id.letterB, R.id.letterC, R.id.letterC, R.id.letterD, R.id.letterE, R.id.letterF, R.id.letterG, R.id.letterH, R.id.letterI, R.id.letterJ, R.id.letterK,
    R.id.letterL, R.id.letterM, R.id.letterN, R.id.letterO, R.id.letterP, R.id.letterQ, R.id.letterR, R.id.letterS, R.id.letterT, R.id.letterU, R.id.letterV, R.id.letterW, R.id.letterX,
    R.id.letterY, R.id.letterZ, R.id.letterÆ, R.id.letterØ, R.id.letterÅ};
    Galgelogik logic;
    ImageView galgePic;
    TextView word;

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
        logic = ((MainActivity)getActivity()).getLogic();

        word.setText(logic.getSynligtOrd());
        return v;
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
        v.findViewById(R.id.letterY).setOnClickListener(this);
        v.findViewById(R.id.letterÆ).setOnClickListener(this);
        v.findViewById(R.id.letterØ).setOnClickListener(this);
        v.findViewById(R.id.letterÅ).setOnClickListener(this);
    }
}