package dk.gramme.dtu.hangman;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoreLogic {
    private int score;
    private String player;
    private ArrayList<PlayerHighscore> leaderboard = new ArrayList<>();
    private static final HighscoreLogic HIGHSCORE_LOGIC = new HighscoreLogic();

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

    public void addToLeaderboard(String name, int score){
            PlayerHighscore player = new PlayerHighscore();
            player.setName(name);
            player.setPoints(score);
            leaderboard.add(player);
    }
    public ArrayList<PlayerHighscore> getList(){
        ArrayList<PlayerHighscore> playerList = leaderboard;
        Collections.sort(playerList);

        return playerList;
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
