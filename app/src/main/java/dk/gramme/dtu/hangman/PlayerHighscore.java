package dk.gramme.dtu.hangman;

public class PlayerHighscore implements Comparable<PlayerHighscore>{
    private String mPlacement, mName;
    private int mPoints;

    public PlayerHighscore(String placement, String name, int points){
        mPlacement = placement;
        mName = name;
        mPoints = points;
    }
    public PlayerHighscore(){

    }

    public void setPlacement(String mPlacement) {
        this.mPlacement = mPlacement;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setPoints(int mPoints) {
        this.mPoints = mPoints;
    }

    public String getPlacement() {
        return mPlacement;
    }

    public String getName() {
        return mName;
    }

    public int getPoints() {
        return mPoints;
    }

    @Override
    public int compareTo(PlayerHighscore player2){
        return this.getPoints() - player2.getPoints();
    }
}
