package dk.gramme.dtu.hangman;

import java.util.HashMap;

public class HighscoreLogic {
    private int score;
    private String player;
    private HashMap<String, Integer> leaderboard = new HashMap<>();
    private static final HighscoreLogic HIGHSCORE_LOGIC = new HighscoreLogic();

    private HighscoreLogic(){}

    public static HighscoreLogic getHighscoreLogic(){
        return HIGHSCORE_LOGIC;
    }
    public String generateHigscoreMessage(boolean gameFinished, String name, String word, int mistakes){
        String msg;
        int temp = calcScore(word.length(), mistakes);
        //if game lost + beat highscore
        if(gameFinished){
            if(score > getCurrentHighscore(name)){
                msg = "\nTotal score: " + score + "\nNy højeste score: " + score;
                //if game lost - no beat
            }else{
                msg = "\nTotal score: " + score + "\nNuværende highscore: " + getCurrentHighscore(name);
            }
            //if game won
        }else{
            msg = "\nDu fik: " + temp + " point med " + mistakes + " forsøg!" + "\nTotal score: " + score + "\nNuværende highscore: " + getCurrentHighscore(name);
        }

        return msg;
    }
    public void resetScore(){
        score = 0;
    }

    public void addToLeaderboard(String name, int score){
        if(leaderboard.containsKey(name)){
            if(leaderboard.get(name) < score){
                leaderboard.remove(name);
                leaderboard.put(name, score);
            }
        }else{
            leaderboard.put(name, score);
        }
    }

    public int getCurrentHighscore(String player){
        if(leaderboard.containsKey(player)){
            return leaderboard.get(player);
        }else{
            return 0;
        }
    }

    public int calcScore(int wordLength, int attempts){
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
