package me.yeojoy.bowlingscoreboard;

import org.junit.Test;

import java.util.List;

import me.yeojoy.bowlingscoreboard.exception.ScoreException;
import me.yeojoy.bowlingscoreboard.logic.BowlingManager;
import me.yeojoy.bowlingscoreboard.logic.ScoreCalculator;
import me.yeojoy.bowlingscoreboard.model.Frame;
import me.yeojoy.bowlingscoreboard.model.Player;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void setScore() throws ScoreException {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Player player = new Player();
        List<Frame> frames = player.getFrames();

        Frame frame = new Frame();
        frame.setFrameNumber(1);
        frames.add(frame.getFrameNumber() - 1, frame);

        scoreCalculator.setScore(frames, 1, 10);

        assertTrue(frame.isStrike());

        frame = new Frame();
        frame.setFrameNumber(2);
        frames.add(frame.getFrameNumber() - 1, frame);

        scoreCalculator.setScore(frames, 2, 5);
        assertTrue(!frame.isStrike());

        scoreCalculator.setScore(frames, 2, 5);

        frame = new Frame();
        frame.setFrameNumber(3);
        frames.add(frame.getFrameNumber() - 1, frame);

        scoreCalculator.setScore(frames, 3, 9);
        scoreCalculator.setScore(frames, 3, 0);

        assertEquals(20, scoreCalculator.getScoreAtFrame(frames, 1));
        assertEquals(39, scoreCalculator.getScoreAtFrame(frames, 2));
        assertEquals(48, scoreCalculator.getScoreAtFrame(frames, 3));

        frame = new Frame();
        frame.setFrameNumber(4);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, 4, 10);

        assertEquals(48, scoreCalculator.getScoreAtFrame(frames, 4));

        frame = new Frame();
        frame.setFrameNumber(5);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, 5, 10);
        assertEquals(48, scoreCalculator.getScoreAtFrame(frames, 5));


        frame = new Frame();
        frame.setFrameNumber(6);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, 6, 10);
        assertEquals(78, scoreCalculator.getScoreAtFrame(frames, 6));

        frame = new Frame();
        frame.setFrameNumber(7);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, 7, 10);
        assertEquals(108, scoreCalculator.getScoreAtFrame(frames, 7));

        frame = new Frame();
        frame.setFrameNumber(8);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, 8, 10);
        assertEquals(138, scoreCalculator.getScoreAtFrame(frames, 8));

        frame = new Frame();
        frame.setFrameNumber(9);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, 9, 10);
        assertEquals(168, scoreCalculator.getScoreAtFrame(frames, 9));

        frame = new Frame();
        frame.setFrameNumber(10);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, 10, 10);
        scoreCalculator.setScore(frames, 10, 10);
        scoreCalculator.setScore(frames, 10, 10);

        printAllFrames(player);
        assertEquals(198, scoreCalculator.getScoreAtFrame(frames, 8));
        assertEquals(228, scoreCalculator.getScoreAtFrame(frames, 9));
        assertEquals(258, scoreCalculator.getScoreAtFrame(frames, 10));

    }

    @Test
    public void testAll5Score() throws ScoreException {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Player player = new Player();
        List<Frame> frames = player.getFrames();

        Frame frame = new Frame();
        frame.setFrameNumber(1);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(2);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(3);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(4);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(5);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(6);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(7);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(8);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(9);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        frame = new Frame();
        frame.setFrameNumber(10);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 5);

        assertEquals(150, scoreCalculator.getScoreAtFrame(frames, 10));
    }

    @Test
    public void testPerfectGame() throws ScoreException {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Player player = new Player();
        List<Frame> frames = player.getFrames();

        Frame frame = new Frame();
        frame.setFrameNumber(1);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(2);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(3);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(4);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(5);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(6);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(7);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(8);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(9);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        frame = new Frame();
        frame.setFrameNumber(10);
        frames.add(frame.getFrameNumber() - 1, frame);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);
        scoreCalculator.setScore(frames, frame.getFrameNumber(), 10);

        assertEquals(300, scoreCalculator.getScoreAtFrame(frames, 10));
    }

    @Test
    public void testSinglePlayWithBowlingManager() throws ScoreException {
        BowlingManager manager = BowlingManager.getInstance();

        Player player = new Player("yeojoy");
        assertEquals(player.getName(), "yeojoy");
        manager.addPlayer(player);

        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);
        manager.setScore(player, 10);

        ScoreCalculator scoreCalculator = new ScoreCalculator();
        assertEquals(300, scoreCalculator.getScoreAtFrame(player.getFrames(), 10));
    }

    @Test
    public void testSinglePlayWithBowlingManagerAll5() throws ScoreException {
        BowlingManager manager = BowlingManager.getInstance();

        Player player = new Player("yeojoy");
        assertEquals(player.getName(), "yeojoy");
        manager.addPlayer(player);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 1);

        ScoreCalculator scoreCalculator = new ScoreCalculator();
        assertEquals(171, scoreCalculator.getScoreAtFrame(player.getFrames(), 10));
    }
    @Test
    public void testSinglePlayTwice() throws ScoreException {
        BowlingManager manager = BowlingManager.getInstance();

        Player player = new Player("yeojoy");
        assertEquals(player.getName(), "yeojoy");
        manager.addPlayer(player);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 2);

        manager.setScore(player, 8);
        manager.setScore(player, 1);

        ScoreCalculator scoreCalculator = new ScoreCalculator();
        assertEquals(171, scoreCalculator.getScoreAtFrame(player.getFrames(), 10));

        manager.setNewGame();

        assertNull(manager.getPlayer());

        try {
            manager.setScore(null, 10);
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(), "No players.");
        }

        try {
            Player player1 = new Player();
            manager.setScore(player1, 10);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The player doesn't play.");
        }
    }

    @Test
    public void testTwoPlayersWithBowlingManager8and2() throws ScoreException {
        BowlingManager manager = BowlingManager.getInstance();

        Player player1 = new Player("yeojoy");
        assertEquals(player1.getName(), "yeojoy");
        manager.addPlayer(player1);

        Player player2 = new Player("haetnim");
        assertEquals(player2.getName(), "haetnim");
        manager.addPlayer(player2);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 2);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);

        manager.setScore(player1, 8);
        manager.setScore(player1, 1);

        manager.setScore(player2, 5);
        manager.setScore(player2, 5);
        manager.setScore(player2, 10);

        int scoreOfPlayer1 = manager.getPlayersScore(player1, 10);
        int scoreOfPlayer2 = manager.getPlayersScore(player2, 10);

        assertEquals(171, scoreOfPlayer1);
        assertEquals((15 * 9 + 20), scoreOfPlayer2);
    }

    private void printAllFrames(Player player) {
        System.out.println("=================================================================================");
        for (Frame frame : player.getFrames()) {
            System.out.println(frame.toString());
        }
    }
}