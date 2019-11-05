package dk.gramme.dtu.hangman;

public class Highscore {
    private String placement;
    private String playerName;
    private String score;

    public Highscore(String placement, String playerName, String score){
        this.placement = placement;
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
