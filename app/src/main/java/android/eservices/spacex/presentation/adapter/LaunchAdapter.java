package android.eservices.spacex.presentation.adapter;

import android.eservices.spacex.R;
import android.eservices.spacex.data.api.model.Game;
import android.eservices.spacex.presentation.adapter.BaseAdapter;
import android.eservices.spacex.presentation.adapter.MyViewHolder;
import android.eservices.spacex.data.api.model.Launch;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

public class LaunchAdapter extends BaseAdapter<Launch> {

    private static class GameViewHolder extends android.eservices.spacex.presentation.adapter.MyViewHolder {
        private ImageView boardImageView;
        private TextView whitePlayerTextView;
        private TextView blackPlayerTextView;
        private TextView resultTextView;
        private TextView dateTextView;
        private TextView timecontrolTextView;
        private TextView terminationTextView;
        private View v;
        private Game game;

        public GameViewHolder(View v) {
            super(v);
            this.v = v;
            boardImageView = v.findViewById(R.id.board);
            whitePlayerTextView = v.findViewById(R.id.white_player);
            blackPlayerTextView = v.findViewById(R.id.black_player);
            resultTextView = v.findViewById(R.id.result);
            dateTextView = v.findViewById(R.id.date);
            timecontrolTextView = v.findViewById(R.id.timecontrol);
            terminationTextView = v.findViewById(R.id.termination);
        }

        @Override
        protected void bind(Object obj) {
            Game game = (Game) obj;

            whitePlayerTextView.setText(String.format("%s (%s)", game.getWhite(), game.getWhiteelo()));
            blackPlayerTextView.setText(String.format("%s (%s)", game.getBlack(), game.getBlackelo()));
            resultTextView.setText(game.getResult());
            dateTextView.setText((new SimpleDateFormat("MM-dd-yyyy hh:ss", Locale.ENGLISH)).format(game.getDate()));
            timecontrolTextView.setText(game.getTimecontrol());
            terminationTextView.setText(game.getTermination());

            CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
            loader.start();

            Glide.with(v)
                    .load("http://www.fen-to-image.com/image/"+game.getFen())
                    .placeholder(loader)
                    .into(boardImageView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
           return super.onCreateViewHolder(parent, viewType);

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(v);
    }
}