package dk.gramme.dtu.hangman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Galgelogik logic = new Galgelogik();
    ImageView galgePic;
    TextView letters[] = new TextView[29];
    TextView word;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Make sure image is correct
        galgePic = findViewById(R.id.galgeView);
        galgePic.setImageResource(R.drawable.galge);
        //Access word to be guessed
        word = findViewById(R.id.GuessWord);
        word.setText(logic.getSynligtOrd().toUpperCase());
        logic.logStatus();
        //Add onClickListeners
        initTextViewArray();
        for(int i = 0; i < letters.length; i++){
            letters[i].setTextColor(Color.BLACK);
            letters[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < letters.length; i++){
            if(v.equals(letters[i]) && letters[i].getCurrentTextColor() == Color.BLACK && !logic.erSpilletSlut()){
                logic.gætBogstav(letters[i].getText().toString().toLowerCase());
                if(logic.erSidsteBogstavKorrekt()){
                    letters[i].setTextColor(Color.GREEN);
                }else {
                    int wrong = logic.getAntalForkerteBogstaver();
                    letters[i].setTextColor(Color.RED);
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
        word.setText(logic.getSynligtOrd().toUpperCase());
        if(logic.erSpilletSlut()){
            if(logic.erSpilletVundet()){
                retryBtn("Tillykke du har vundet!", "Ordet var: " + logic.getOrdet().toUpperCase());
            }else{
                retryBtn("Du har tabt!", "Ordet var: " + logic.getOrdet().toUpperCase());
            }

        }
    }

    private void retryBtn(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);

            builder.setPositiveButton("Prøv igen?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    logic.nulstil();
                }
            });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private void initTextViewArray(){
        letters[0] = findViewById(R.id.letterA);
        letters[1] = findViewById(R.id.letterB);
        letters[2] = findViewById(R.id.letterC);
        letters[3] = findViewById(R.id.letterD);
        letters[4] = findViewById(R.id.letterE);
        letters[5] = findViewById(R.id.letterF);
        letters[6] = findViewById(R.id.letterG);
        letters[7] = findViewById(R.id.letterH);
        letters[8] = findViewById(R.id.letterI);
        letters[9] = findViewById(R.id.letterJ);
        letters[10] = findViewById(R.id.letterK);
        letters[11] = findViewById(R.id.letterL);
        letters[12] = findViewById(R.id.letterM);
        letters[13] = findViewById(R.id.letterN);
        letters[14] = findViewById(R.id.letterO);
        letters[15] = findViewById(R.id.letterP);
        letters[16] = findViewById(R.id.letterQ);
        letters[17] = findViewById(R.id.letterR);
        letters[18] = findViewById(R.id.letterS);
        letters[19] = findViewById(R.id.letterT);
        letters[20] = findViewById(R.id.letterU);
        letters[21] = findViewById(R.id.letterV);
        letters[22] = findViewById(R.id.letterW);
        letters[23] = findViewById(R.id.letterX);
        letters[24] = findViewById(R.id.letterY);
        letters[25] = findViewById(R.id.letterZ);
        letters[26] = findViewById(R.id.letterÆ);
        letters[27] = findViewById(R.id.letterØ);
        letters[28] = findViewById(R.id.letterÅ);
    }
}
