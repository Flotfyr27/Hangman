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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_highscore, container, false);
        ListView mListView = (ListView) v.findViewById(R.id.playerList);
        ArrayList<PlayerHighscore> playerList = new ArrayList<>();
        playerList.add(new PlayerHighscore("1.", "Swagmeister420", 420));
        playerList.add(new PlayerHighscore("2.", "TheLegend27", 69));
        playerList.add(new PlayerHighscore("3.", "Bob", 3));

        PlayerListAdapter adapter = new PlayerListAdapter(getContext(), R.layout.player_highscore, playerList);
        mListView.setAdapter(adapter);

        return v;
    }
}
