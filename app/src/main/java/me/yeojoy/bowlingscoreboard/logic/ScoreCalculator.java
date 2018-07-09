package me.yeojoy.bowlingscoreboard.logic;

import java.util.List;

import me.yeojoy.bowlingscoreboard.model.BonusScore;
import me.yeojoy.bowlingscoreboard.model.Frame;
import me.yeojoy.bowlingscoreboard.model.Score;

public class ScoreCalculator {
    private static final String TAG = ScoreCalculator.class.getSimpleName();

    public int getScoreAtFrame(List<Frame> frames, int frameNumber) {

        if (frames == null || frames.size() < 1) {
            return 0;
        }

        int frameScore = 0;
        for (int index = 0; index < frameNumber; index++) {
            Frame frame = frames.get(index);
            if (frame.getFrameState() == Frame.FrameState.HOLDING) {
                break;
            }

            Score score = frame.getScore();

            if (score != null && score.getFirstShotScore() > -1) {
                frameScore += score.getFirstShotScore();
                if (frame.getScore().getSecondShotScore() > -1) {
                    frameScore += frame.getScore().getSecondShotScore();
                    if (frame.getScore().getThirdShotScore() > -1) {
                        frameScore += frame.getScore().getThirdShotScore();
                    }
                }
            }

            BonusScore bonusScore = frame.getBonusScore();
            if (bonusScore != null && bonusScore.getFirstBonusScore() > -1) {
                frameScore += bonusScore.getFirstBonusScore();
                if (bonusScore.getSecondBonusScore() > -1) {
                    frameScore += bonusScore.getSecondBonusScore();
                }
            }
        }

        System.out.println("------------------------");
        System.out.println("Number : " + frameNumber + ", score : " + frameScore);
        System.out.println("------------------------");
        return frameScore;
    }

    public void setScore(List<Frame> frames, int frameNumber, int score) {

        if (frames == null) return;

        Frame frame = frames.get(frameNumber - 1);

        if (frame == null) {
            return;
        }

        checkHoldingFrames(frames, frameNumber, score);

        if (frame.getFrameState() == Frame.FrameState.FINISHED) {
            return;
        }

        if (frameNumber < 10) {
            switch (frame.getFrameState()) {
                case NONE: {
                    if (score == 0) {
                        frame.setFrameState(Frame.FrameState.FIRST_SHOT_ENDED);
                    } else if (score == 10) {
                        frame.setFrameState(Frame.FrameState.HOLDING);
                        frame.setStrike(true);
                    } else {
                        frame.setFrameState(Frame.FrameState.FIRST_SHOT_ENDED);
                    }

                    frame.getScore().setFirstShotScore(score);

                    break;
                }

                case FIRST_SHOT_ENDED: {
                    Score scoreInFrame = frame.getScore();
                    scoreInFrame.setSecondShotScore(score);

                    if (scoreInFrame.getFirstShotScore() + score == 10) {
                        frame.setSpare(true);
                        frame.setFrameState(Frame.FrameState.HOLDING);
                    } else {
                        frame.setSpare(false);
                        frame.setFrameState(Frame.FrameState.FINISHED);
                    }

                    break;
                }
                default:
                    break;
            }
        } else {
            switch (frame.getFrameState()) {
                case NONE:
                    frame.getScore().setFirstShotScore(score);
                    frame.setFrameState(Frame.FrameState.FIRST_SHOT_ENDED);
                    break;

                case FIRST_SHOT_ENDED:
                    frame.getScore().setSecondShotScore(score);

                    if (score == 10 || (score + frame.getScore().getFirstShotScore() == 10)) {
                        frame.setFrameState(Frame.FrameState.SECOND_SHOT_ENDED);
                    } else {
                        frame.setFrameState(Frame.FrameState.FINISHED);
                    }

                    break;

                case SECOND_SHOT_ENDED:
                    frame.getScore().setThirdShotScore(score);
                    frame.setFrameState(Frame.FrameState.FINISHED);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 전전 혹은 전 프레임이 strike이나 spare로 보너스 점수가 필요할 경우
     * 이를 설정하고 완료가 됐다면 FrameState까지 완료 처리 시킨다.
     * @param frames
     * @param frameNumber
     * @param score
     */
    private void checkHoldingFrames(List<Frame> frames, int frameNumber, int score) {
        if (frames == null) return;

        // double (strike two times)
        int index = frameNumber - 2;
        if (index > 0) {
            Frame preFrame = frames.get(index - 1);
            if (preFrame.isStrike()) {
                setStrikeBonusScore(preFrame, score);
            }
        }

        index = frameNumber - 1;
        if (index > 0) {
            Frame preFrame = frames.get(index - 1);
            if (preFrame.isStrike()) {
                setStrikeBonusScore(preFrame, score);
            } else if (preFrame.isSpare()) {
                setSpareBonusScore(preFrame, score);
            }
        }
    }

    /**
     * 현재 프레임 이전의 프레임 중 HOLDING 상태인 것에 보너스 점수를 설정하고
     * 완료 시킨다.
     * @param preFrame
     * @param score
     */
    private void setSpareBonusScore(Frame preFrame, int score) {
        if (preFrame != null && preFrame.getFrameState() == Frame.FrameState.HOLDING) {
            BonusScore bonusScore = preFrame.getBonusScore();
            if (bonusScore == null) {
                bonusScore = new BonusScore();
            }

            bonusScore.setStrikeBonusScore(false);
            bonusScore.setFirstBonusScore(score);
            preFrame.setFrameState(Frame.FrameState.FINISHED);

            preFrame.setBonusScore(bonusScore);
        }
    }

    private void setStrikeBonusScore(Frame preFrame, int score) {
        if (preFrame != null && preFrame.getFrameState() == Frame.FrameState.HOLDING) {
            BonusScore bonusScore = preFrame.getBonusScore();
            if (bonusScore == null) {
                bonusScore = new BonusScore();

                bonusScore.setStrikeBonusScore(preFrame.isStrike());
                bonusScore.setFirstBonusScore(score);
            } else {
                bonusScore.setSecondBonusScore(score);
                preFrame.setFrameState(Frame.FrameState.FINISHED);
            }

            preFrame.setBonusScore(bonusScore);
        }
    }
}
