package me.yeojoy.bowlingscoreboard.model;

public class Score {
    private int firstShotScore;
    private int secondShotScore;
    private int thirdShotScore;

    public Score() {
        firstShotScore = -1;
        secondShotScore = -1;
        thirdShotScore = -1;
    }

    public void setFirstShotScore(int firstShotScore) {
        this.firstShotScore = firstShotScore;
    }

    public void setSecondShotScore(int secondShotScore) {
        this.secondShotScore = secondShotScore;
    }

    public void setThirdShotScore(int thirdShotScore) {
        this.thirdShotScore = thirdShotScore;
    }

    public int getFirstShotScore() {
        return firstShotScore;
    }

    public int getSecondShotScore() {
        return secondShotScore;
    }

    public int getThirdShotScore() {
        return thirdShotScore;
    }

    @Override
    public String toString() {
        return "Score{" +
                "firstShotScore=" + firstShotScore +
                ", secondShotScore=" + secondShotScore +
                ", thirdShotScore=" + thirdShotScore +
                '}';
    }
}
