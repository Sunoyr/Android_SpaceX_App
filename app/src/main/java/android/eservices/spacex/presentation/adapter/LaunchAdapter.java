package android.eservices.spacex.presentation.adapter;

import android.eservices.spacex.R;
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

    private static class LaunchViewHolder extends android.eservices.spacex.presentation.adapter.MyViewHolder {
        private ImageView smallLogoImageView;
        private TextView nameTextView;
        private TextView dateTextView;
        private View v;
        private Launch launch;

        public LaunchViewHolder(View v) {
            super(v);
            this.v = v;
            smallLogoImageView = v.findViewById(R.id.launch_logo);
            nameTextView = v.findViewById(R.id.launch_title);
            dateTextView = v.findViewById(R.id.launch_date);
        }

        @Override
        protected void bind(Object obj) {
            Launch launch = (Launch) obj;

            nameTextView.setText(launch.getName());
            dateTextView.setText((new SimpleDateFormat("MM-dd-yyyy hh:ss", Locale.ENGLISH)).format(launch.getDate_utc()));

            CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
            loader.start();

            Glide.with(v)
                    .load(launch.getSmallLogo())
                    .placeholder(loader)
                    .into(smallLogoImageView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_launch, parent, false);
        return new LaunchViewHolder(v);
    }
}