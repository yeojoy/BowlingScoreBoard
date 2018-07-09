package me.yeojoy.bowlingscoreboard.model;

public class Frame {
    private Score score;
    private BonusScore bonusScore;
    private boolean isStrike;
    private boolean isSpare;
    private int frameNumber;

    private FrameState frameState;

    public enum FrameState {
        NONE, // no score
        FIRST_SHOT_ENDED, // first shot finished
        SECOND_SHOT_ENDED, // second shot finished. only on frame 10.
        HOLDING, // if strike or spare, waiting next shot to calculate score
        FINISHED // all process are finished.
    }

    public Frame() {
        this(0);
    }

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        score = new Score();
        isStrike = false;
        isSpare = false;
        frameState = FrameState.NONE;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public FrameState getFrameState() {
        return frameState;
    }

    public void setFrameState(FrameState frameState) {
        this.frameState = frameState;
    }

    public BonusScore getBonusScore() {
        return bonusScore;
    }

    public void setBonusScore(BonusScore bonusScore) {
        this.bonusScore = bonusScore;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "score=" + score +
                ", bonusScore=" + bonusScore +
                ", isStrike=" + isStrike +
                ", isSpare=" + isSpare +
                ", frameNumber=" + frameNumber +
                ", frameState=" + frameState +
                '}';
    }
}
