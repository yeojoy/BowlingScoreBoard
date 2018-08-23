package me.yeojoy.bowlingscoreboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import me.yeojoy.bowlingscoreboard.app.SingleBowlingScoreAdapter;
import me.yeojoy.bowlingscoreboard.logic.BowlingManager;
import me.yeojoy.bowlingscoreboard.model.Frame;
import me.yeojoy.bowlingscoreboard.model.Player;
import me.yeojoy.bowlingscoreboard.view.SingleGameView;

public class SingleGameActivity extends AppCompatActivity implements SingleGameView {

    private Button mButtonScore1, mButtonScore2, mButtonScore3, mButtonScore4, mButtonScore5,
            mButtonScore6, mButtonScore7, mButtonScore8, mButtonScore9, mButtonScore10,
            mButtonScore0, mButtonNewGame;

    private RecyclerView mRecyclerView;

    private Player mPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);

        initViews();
    }

    private void initViews() {
        mButtonNewGame = findViewById(R.id.button_new_game);
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

        mButtonNewGame.setOnClickListener(view -> setNewGame());
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
        mRecyclerView.setAdapter(new SingleBowlingScoreAdapter(this, mPlayer));
    }

    private void setScore(int score) {
        BowlingManager manager = BowlingManager.getInstance();
        manager.setScore(mPlayer, score);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void setNewGame() {
        BowlingManager.getInstance().setNewGame();
        mPlayer.removeFrames();
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onRowClick(Frame frame) {

    }
}
