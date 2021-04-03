package android.eservices.spacex.presentation.adapter;

import android.eservices.spacex.R;
import android.eservices.spacex.data.api.model.Rocket;
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

public class RocketAdapter extends BaseAdapter<Rocket> {

    private static class RocketViewHolder extends MyViewHolder {
        private ImageView boardImageView;
        private TextView whitePlayerTextView;
        private TextView blackPlayerTextView;
        private TextView resultTextView;
        private TextView dateTextView;
        private TextView timecontrolTextView;
        private TextView terminationTextView;
        private View v;
        private Rocket rocket;

        public RocketViewHolder(View v) {
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
            Rocket rocket = (Rocket) obj;

            whitePlayerTextView.setText(String.format("%s (%s)", rocket.getWhite(), rocket.getWhiteelo()));
            blackPlayerTextView.setText(String.format("%s (%s)", rocket.getBlack(), rocket.getBlackelo()));
            resultTextView.setText(rocket.getResult());
            dateTextView.setText((new SimpleDateFormat("MM-dd-yyyy hh:ss", Locale.ENGLISH)).format(rocket.getDate()));
            timecontrolTextView.setText(rocket.getTimecontrol());
            terminationTextView.setText(rocket.getTermination());

            CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
            loader.start();

            Glide.with(v)
                    .load("http://www.fen-to-image.com/image/"+rocket.getFen())
                    .placeholder(loader)
                    .into(boardImageView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rocket, parent, false);
        return new RocketViewHolder(v);
    }
}