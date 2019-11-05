package dk.gramme.dtu.hangman;

public class HighscoreLogic {
    private int score;
    private String player;
    private static final HighscoreLogic HIGHSCORE_LOGIC = new HighscoreLogic();

    private HighscoreLogic(){}

    public static HighscoreLogic getHighscoreLogic(){
        return HIGHSCORE_LOGIC;
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
