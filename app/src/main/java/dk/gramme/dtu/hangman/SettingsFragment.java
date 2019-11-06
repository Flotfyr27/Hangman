package dk.gramme.dtu.hangman;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    Button btn;
    HighscoreLogic highscoreLogic;
    TextView tw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        highscoreLogic = HighscoreLogic.getHighscoreLogic();
        tw = v.findViewById(R.id.setting_name);
        btn = v.findViewById(R.id.setting_save);
        btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.setting_save) {
            tw.setHint(((TextView) getActivity().findViewById(R.id.setting_name)).getText());
            tw.setText("");
        }
    }
}
