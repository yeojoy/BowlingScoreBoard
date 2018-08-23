package me.yeojoy.bowlingscoreboard.app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.yeojoy.bowlingscoreboard.R;
import me.yeojoy.bowlingscoreboard.model.Frame;
import me.yeojoy.bowlingscoreboard.model.Player;
import me.yeojoy.bowlingscoreboard.model.Score;
import me.yeojoy.bowlingscoreboard.view.SingleGameView;

public class SingleBowlingScoreAdapter extends RecyclerView.Adapter<SingleBowlingScoreAdapter.ScoreViewHolder> {

    private static final String TAG = SingleBowlingScoreAdapter.class.getSimpleName();
    private SingleGameView mSingleGameView;
    private Player mPlayer;

    public SingleBowlingScoreAdapter(SingleGameView singleGameView, Player player) {
        mSingleGameView = singleGameView;
        mPlayer = player;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_frame, null, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        holder.mTextViewFrameNumber.setText(String.valueOf(position + 1));
        Frame frame = mPlayer.getFrames().get(position);
        Score score = frame.getScore();
        holder.mTextViewFirstScore.setText(String.valueOf(score.getFirstShotScore()));
        holder.mTextViewSecondScore.setText(String.valueOf(score.getSecondShotScore()));
        holder.mTextViewThirdScore.setText(String.valueOf(score.getThirdShotScore()));

        holder.itemView.setOnClickListener(v -> mSingleGameView.onRowClick(frame));
        switch (frame.getFrameState()) {
            case FIRST_SHOT_ENDED:
            case HOLDING:
                holder.mTextViewFirstScore.setVisibility(View.VISIBLE);
                holder.mTextViewSecondScore.setVisibility(View.INVISIBLE);
                holder.mTextViewThirdScore.setVisibility(View.GONE);
                break;
            case SECOND_SHOT_ENDED:
                holder.mTextViewFirstScore.setVisibility(View.VISIBLE);
                holder.mTextViewSecondScore.setVisibility(View.VISIBLE);
                break;
            case FINISHED:
                holder.mTextViewFirstScore.setVisibility(View.VISIBLE);
                holder.mTextViewSecondScore.setVisibility(View.VISIBLE);
                holder.mTextViewThirdScore.setVisibility(frame.getFrameNumber() == 10 ?
                        View.VISIBLE : View.GONE);
                break;
            case NONE:
            default:
                holder.mTextViewFirstScore.setVisibility(View.INVISIBLE);
                holder.mTextViewSecondScore.setVisibility(View.INVISIBLE);
                holder.mTextViewThirdScore.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mPlayer == null) return 0;

        return mPlayer.getFrames().size();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewFrameNumber, mTextViewFirstScore;
        TextView mTextViewSecondScore, mTextViewThirdScore;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            mTextViewFirstScore = itemView.findViewById(R.id.text_view_first_score);
            mTextViewSecondScore = itemView.findViewById(R.id.text_view_second_score);
            mTextViewThirdScore = itemView.findViewById(R.id.text_view_third_score);
            mTextViewFrameNumber = itemView.findViewById(R.id.text_view_frame_number);
        }
    }
}
