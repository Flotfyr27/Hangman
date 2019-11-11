package dk.gramme.dtu.hangman;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HighscoreFragment extends Fragment {
HighscoreLogic highscoreLogic;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_highscore, container, false);
        mListView = v.findViewById(R.id.playerList);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        highscoreLogic = HighscoreLogic.getHighscoreLogic();
        ArrayList<PlayerHighscore> playerList;
        playerList = highscoreLogic.getList();

        PlayerListAdapter adapter = new PlayerListAdapter(getContext(), R.layout.player_highscore, playerList);
        mListView.setAdapter(adapter);

    }
}
