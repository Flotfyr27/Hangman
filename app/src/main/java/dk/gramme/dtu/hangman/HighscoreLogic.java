package dk.gramme.dtu.hangman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public ArrayList<PlayerHighscore> getList(){
        ArrayList<PlayerHighscore> playerList = new ArrayList<>();
        List list = new LinkedList(leaderboard.entrySet());

        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry)(o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        PlayerHighscore player = new PlayerHighscore();
        for(Iterator it = list.iterator(); it.hasNext();){
            Map.Entry entry = (Map.Entry) it.next();
            player.setName(entry.getKey().toString());
            player.setPoints(Integer.parseInt(entry.getValue().toString()));
            String placement = (playerList.size()+1) + ".";
            player.setPlacement(placement);
        }
        return playerList;
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
