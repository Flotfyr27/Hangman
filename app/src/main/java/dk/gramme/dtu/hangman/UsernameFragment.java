package dk.gramme.dtu.hangman;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UsernameFragment extends Fragment implements View.OnClickListener {
    Button btn;
    TextView tw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_username, container, false);
        btn = v.findViewById(R.id.user_input_ok);
        tw = v.findViewById(R.id.user_input);
        btn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        String name = tw.getText().toString();
        if(name.length() < 1){
            Toast.makeText(getActivity(), "Name is not valid", Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            prefs.edit().putString("username", name).apply();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new HomeFragment()).commit();
        }
    }
}
