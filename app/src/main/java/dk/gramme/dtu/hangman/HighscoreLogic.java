package dk.gramme.dtu.hangman;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoreLogic {
    private int score;
    private String player;
    private ArrayList<PlayerHighscore> leaderboard = new ArrayList<>();
    private static final HighscoreLogic HIGHSCORE_LOGIC = new HighscoreLogic();
    SharedPreferences prefs;

    private HighscoreLogic(){}

    public static HighscoreLogic getHighscoreLogic(){
        return HIGHSCORE_LOGIC;
    }
    public String generateHighscoreMessage(String word, int mistakes){
        String msg;
        int temp = calcScore(word.length(), mistakes);
        msg = "\nDu fik: " + temp + " point med " + mistakes + " forkerte!" + "\nTotal score: " + score;

        return msg;
    }
    public void resetScore(){
        score = 0;
    }

    public void addToLeaderboard(String name, int score, Context context){
            PlayerHighscore player = new PlayerHighscore();
            player.setName(name);
            player.setPoints(score);
            leaderboard.add(player);
            saveDataToJSON(context);
    }
    public ArrayList<PlayerHighscore> getList(){
        leaderboard = sort(leaderboard);

        return leaderboard;
    }

    private void saveDataToJSON(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = gson.toJson(getList());
        prefs.edit().putString("scoreboard", json).apply();
        System.out.println("TEST JSON----");
        System.out.println(json);
    }

    public ArrayList<PlayerHighscore> sort(ArrayList<PlayerHighscore> highscores){
        int arrayListSize = highscores.size();
        PlayerHighscore tmp;
        boolean sorted;
        for(int i = 0; i < arrayListSize; i++){
            sorted = true;
            for (int j = 1; j < arrayListSize; j++) {
                if(highscores.get(j-1).getPoints() < highscores.get(j).getPoints()){
                    tmp = highscores.get(j-1);
                    highscores.set(j-1, highscores.get(j));
                    highscores.set(j, tmp);
                    sorted = false;
                }
            }
            if (sorted) break;
        }

        for(int i = 0; i < arrayListSize; i++){
            highscores.get(i).setPlacement(Integer.toString(i + 1));
        }

        return highscores;
    }


    public int calcScore(int wordLength, int attempts){
        if(wordLength > attempts && attempts > 6){
            return 0;
        }
        int x = wordLength - attempts;
        if(x <= 0) return 0;
        return x;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
