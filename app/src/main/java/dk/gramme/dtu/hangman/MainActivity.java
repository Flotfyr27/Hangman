package dk.gramme.dtu.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView galgePic;
    TextView letters[] = new TextView[28];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Make sure image is correct
        galgePic = findViewById(R.id.galgeView);
        galgePic.setImageResource(R.drawable.galge);
        initTextViewArray();
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
