package android.eservices.spacex.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.eservices.spacex.R;
import android.eservices.spacex.data.api.model.rocket.Rocket;
import android.eservices.spacex.presentation.RocketDetailsActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import io.reactivex.annotations.NonNull;

public class RocketAdapter extends BaseAdapter<Rocket> {

    private static class RocketViewHolder extends MyViewHolder implements View.OnClickListener {
        private ImageView iconImageView;
        private TextView titleTextView;
        private View v;
        private Rocket rocket;

        public RocketViewHolder(View v) {
            super(v);
            this.v = v;
            v.setOnClickListener(this);
            iconImageView = v.findViewById(R.id.icon_rocket_imageview);
            titleTextView = v.findViewById(R.id.title_rocket_textview);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, RocketDetailsActivity.class);
            intent.putExtra("rocket", rocket);
            context.startActivity(intent);
        }

        @Override
        protected void bind(Object obj) {
            Rocket rocket = (Rocket) obj;

            titleTextView.setText(rocket.getName());

            CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
            loader.start();

            Glide.with(v)
                    .load(rocket.getFlickr_image())
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