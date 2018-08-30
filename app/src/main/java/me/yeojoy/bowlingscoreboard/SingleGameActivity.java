package me.yeojoy.bowlingscoreboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import me.yeojoy.bowlingscoreboard.app.SingleBowlingScoreAdapter;
import me.yeojoy.bowlingscoreboard.exception.ScoreException;
import me.yeojoy.bowlingscoreboard.logic.BowlingManager;
import me.yeojoy.bowlingscoreboard.model.Frame;
import me.yeojoy.bowlingscoreboard.model.Player;
import me.yeojoy.bowlingscoreboard.view.SingleGameView;

public class SingleGameActivity extends AppCompatActivity implements SingleGameView {
    private static final String TAG = SingleGameActivity.class.getSimpleName();

    private Button mButtonScore1, mButtonScore2, mButtonScore3, mButtonScore4, mButtonScore5,
            mButtonScore6, mButtonScore7, mButtonScore8, mButtonScore9, mButtonScore10,
            mButtonScore0;

    private RecyclerView mRecyclerView;

    private Player mPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);

        initViews();
    }

    private void initViews() {
        mButtonScore0 = findViewById(R.id.button_score_0);
        mButtonScore1 = findViewById(R.id.button_score_1);
        mButtonScore2 = findViewById(R.id.button_score_2);
        mButtonScore3 = findViewById(R.id.button_score_3);
        mButtonScore4 = findViewById(R.id.button_score_4);
        mButtonScore5 = findViewById(R.id.button_score_5);
        mButtonScore6 = findViewById(R.id.button_score_6);
        mButtonScore7 = findViewById(R.id.button_score_7);
        mButtonScore8 = findViewById(R.id.button_score_8);
        mButtonScore9 = findViewById(R.id.button_score_9);
        mButtonScore10 = findViewById(R.id.button_score_10);

        findViewById(R.id.button_new_game).setOnClickListener(view -> setNewGame());
        mButtonScore0.setOnClickListener(view -> setScore(0));
        mButtonScore1.setOnClickListener(view -> setScore(1));
        mButtonScore2.setOnClickListener(view -> setScore(2));
        mButtonScore3.setOnClickListener(view -> setScore(3));
        mButtonScore4.setOnClickListener(view -> setScore(4));
        mButtonScore5.setOnClickListener(view -> setScore(5));
        mButtonScore6.setOnClickListener(view -> setScore(6));
        mButtonScore7.setOnClickListener(view -> setScore(7));
        mButtonScore8.setOnClickListener(view -> setScore(8));
        mButtonScore9.setOnClickListener(view -> setScore(9));
        mButtonScore10.setOnClickListener(view -> setScore(10));

        mRecyclerView = findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mPlayer = new Player("yeojoy");
        BowlingManager.getInstance().addPlayer(mPlayer);
        BowlingManager.getInstance().setOnAllowFrameScoreListener(this::onAllowedScore);
        BowlingManager.getInstance().setOnEndGameCallback(this::onEndGame);
        mRecyclerView.setAdapter(new SingleBowlingScoreAdapter(this, mPlayer));
    }

    private void onEndGame() {
        mButtonScore0.setEnabled(false);
        mButtonScore1.setEnabled(false);
        mButtonScore2.setEnabled(false);
        mButtonScore3.setEnabled(false);
        mButtonScore4.setEnabled(false);
        mButtonScore5.setEnabled(false);
        mButtonScore6.setEnabled(false);
        mButtonScore7.setEnabled(false);
        mButtonScore8.setEnabled(false);
        mButtonScore9.setEnabled(false);
        mButtonScore10.setEnabled(false);
    }

    private void onAllowedScore(int allowedScore) {
        Log.d(TAG, "onAllowedScore > allowedScore ::: " + allowedScore);
        setEnabledAllScoreButtons();

        switch (allowedScore) {
            case 1:
                mButtonScore2.setEnabled(false);
            case 2:
                mButtonScore3.setEnabled(false);
            case 3:
                mButtonScore4.setEnabled(false);
            case 4:
                mButtonScore5.setEnabled(false);
            case 5:
                mButtonScore6.setEnabled(false);
            case 6:
                mButtonScore7.setEnabled(false);
            case 7:
                mButtonScore8.setEnabled(false);
            case 8:
                mButtonScore9.setEnabled(false);
            case 9:
                mButtonScore10.setEnabled(false);
            default:
                break;
        }
    }

    private void setScore(int score) {
        BowlingManager manager = BowlingManager.getInstance();
        try {
            manager.setScore(mPlayer, score);
        } catch (ScoreException e) {

        }

        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void setNewGame() {
        BowlingManager.getInstance().setNewGame();
        BowlingManager.getInstance().addPlayer(mPlayer);
        mPlayer.removeFrames();
        mRecyclerView.getAdapter().notifyDataSetChanged();

       setEnabledAllScoreButtons();
    }

    private void setEnabledAllScoreButtons() {
        mButtonScore0.setEnabled(true);
        mButtonScore1.setEnabled(true);
        mButtonScore2.setEnabled(true);
        mButtonScore3.setEnabled(true);
        mButtonScore4.setEnabled(true);
        mButtonScore5.setEnabled(true);
        mButtonScore6.setEnabled(true);
        mButtonScore7.setEnabled(true);
        mButtonScore8.setEnabled(true);
        mButtonScore9.setEnabled(true);
        mButtonScore10.setEnabled(true);
    }

    @Override
    public void onRowClick(Frame frame) {

    }
}
