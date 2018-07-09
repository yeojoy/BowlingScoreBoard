package me.yeojoy.bowlingscoreboard.logic;

import java.util.ArrayList;
import java.util.List;

import me.yeojoy.bowlingscoreboard.model.Frame;
import me.yeojoy.bowlingscoreboard.model.Player;

public class BowlingManager {
    private static final String TAG = BowlingManager.class.getSimpleName();

    private static BowlingManager bowlingManager;

    private List<Player> players;
    private static int frameNumber;

    public static BowlingManager getInstance() {
        if (bowlingManager == null) {
            bowlingManager = new BowlingManager();
        }

        return bowlingManager;
    }

    private BowlingManager() {
        init();
    }

    private void init() {
        if (players == null) {
            players = new ArrayList<>();
        }

        players.clear();
        frameNumber = 1;
    }

    public List<Frame> getCurrentFrames(Player player) {
        if (players == null) return null;

        return players.get(players.indexOf(player)).getFrames();
    }

    public void setNewGame() {
        init();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayer() {
        for (Player player : players) {
            List<Frame> frames = getCurrentFrames(player);
            if (frames.get(frameNumber - 1).getFrameState() == Frame.FrameState.NONE) {
                return player;
            }
        }

        for (Player player : players) {
            if (player.getFrames().size() < frameNumber) {
                return player;
            }
        }

        return null;
    }

    public Player getPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }

        return null;
    }

    public void setScore(Player player, int score) {

        if (player == null) {
            throw(new NullPointerException("No players."));
        }

        if (!players.contains(player)) {
            throw(new IllegalArgumentException("The player doesn't play."));
        }

        ScoreCalculator scoreCalculator = new ScoreCalculator();

        List<Frame> frames = player.getFrames();
        if (frames.size() < frameNumber) {
            Frame frame = new Frame(frameNumber);
            frames.add(frame);
        }

        scoreCalculator.setScore(frames, frameNumber, score);

        checkFrame();
    }

    private void checkFrame() {

        if (frameNumber == 10) { return; }

        for (Player player : players) {

            if (player.getFrames().size() != frameNumber) {
                // 한 사람이라도 Frame size가 frameNumber와 같지 않으면 증가하지 않음.
                return;
            }

            Frame.FrameState state = player.getFrames().get(frameNumber - 1).getFrameState();
            if (state == Frame.FrameState.NONE ||
                    state == Frame.FrameState.FIRST_SHOT_ENDED ||
                    (frameNumber == 10 && state == Frame.FrameState.SECOND_SHOT_ENDED)) {
                // 한 사람이라도 NONE 이거나 FIRST_SHOT_ENDED 라면 종료
                return;
            }
        }

        frameNumber++;
    }

    public int getPlayersScore(Player player, int frameNumber) {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        int score = scoreCalculator.getScoreAtFrame(player.getFrames(), frameNumber);
        return score;
    }

    public int getFrameNumber() {
        return frameNumber;
    }
}
