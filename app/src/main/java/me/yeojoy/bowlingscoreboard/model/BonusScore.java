package me.yeojoy.bowlingscoreboard.model;

public class BonusScore {
    private int firstBonusScore;
    private int secondBonusScore;

    private boolean isStrikeBonusScore;

    public BonusScore() {
        firstBonusScore = -1;
        secondBonusScore = -1;
        isStrikeBonusScore = false;
    }

    public int getFirstBonusScore() {
        return firstBonusScore;
    }

    public void setFirstBonusScore(int firstBonusScore) {
        this.firstBonusScore = firstBonusScore;
    }

    public int getSecondBonusScore() {
        return secondBonusScore;
    }

    public void setSecondBonusScore(int secondBonusScore) {
        this.secondBonusScore = secondBonusScore;
    }

    public boolean isStrikeBonusScore() {
        return isStrikeBonusScore;
    }

    public void setStrikeBonusScore(boolean strikeBonusScore) {
        isStrikeBonusScore = strikeBonusScore;
    }

    @Override
    public String toString() {
        return "BonusScore{" +
                "firstBonusScore=" + firstBonusScore +
                ", secondBonusScore=" + secondBonusScore +
                ", isStrikeBonusScore=" + isStrikeBonusScore +
                '}';
    }
}
