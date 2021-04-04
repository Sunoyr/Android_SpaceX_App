package android.eservices.spacex.presentation.adapter;

import android.eservices.spacex.R;
import android.eservices.spacex.data.api.model.rocket.Rocket;
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
        private ImageView iconImageView;
        private TextView titleTextView;
        private View v;
        private Rocket rocket;

        public RocketViewHolder(View v) {
            super(v);
            this.v = v;
            iconImageView = v.findViewById(R.id.icon_rocket_imageview);
            titleTextView = v.findViewById(R.id.title_rocket_textview);
        }

        @Override
        protected void bind(Object obj) {
            Rocket rocket = (Rocket) obj;

            titleTextView.setText(rocket.getName());

            CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
            loader.start();

            Glide.with(v)
                    .load(rocket.getFlickr_images().get(1))
                    .placeholder(loader)
                    .into(iconImageView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rocket, parent, false);
        return new RocketViewHolder(v);
    }
}