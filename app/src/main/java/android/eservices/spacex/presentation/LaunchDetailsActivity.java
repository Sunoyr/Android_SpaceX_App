package android.eservices.spacex.presentation;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.eservices.spacex.R;
import android.eservices.spacex.data.api.model.launch.Launch;
import android.eservices.spacex.data.api.model.rocket.Rocket;
import android.eservices.spacex.data.di.FakeDependencyInjection;
import android.eservices.spacex.data.repository.rocket.RocketRepository;
import android.eservices.spacex.presentation.viewmodel.RocketViewModel;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LaunchDetailsActivity extends AppCompatActivity {

    private static final String LAUNCH = "launch";
    protected Launch launch;
    protected Toolbar toolbar;
    protected RocketViewModel rocketViewModel;
    private RocketRepository RocketRepository;
    protected View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_details);
        launch = (Launch) getIntent().getSerializableExtra(LAUNCH);
        updateToolBar();
        setupView();
        retrieveRocket();
    }

    private void setupView() {
        v = findViewById(R.id.launch_details);
        ImageView img = v.findViewById(R.id.launch_picture_imageView);

        CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
        loader.start();

        Glide.with(v)
                .load(launch.getOriginalPicture())
                .placeholder(loader)
                .into(img);

        TextView title = v.findViewById(R.id.launch_name_textView);
        title.setText(launch.getName());

        TextView launchDate = v.findViewById(R.id.launch_date_textView);
        launchDate.setText((new SimpleDateFormat("dd-MM-yyyy hh:ss", Locale.ENGLISH)).format(launch.getDate_utc())+"h");

        TextView details = v.findViewById(R.id.launch_details_textView);
        details.setText(launch.getDetails());

        ImageButton webcast = v.findViewById(R.id.launch_webcast_imageView);
        if (launch.getYoutubeId()==null) {
            webcast.setVisibility(View.INVISIBLE);
        } else {
            TextView no_video = v.findViewById(R.id.no_video);
            no_video.setVisibility(View.INVISIBLE);
            webcast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    watchVideo(v);
                }
            });
        }
    }

    private void retrieveRocket() {
        rocketViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(RocketViewModel.class);
        rocketViewModel.getOneRocket(launch.getRocket()).observe(this, new Observer<Rocket>() {
            @Override
            public void onChanged(final Rocket rocket) {
                TextView rocket_name = v.findViewById(R.id.launch_rocket_textView);
                rocket_name.setText(rocket.getName());
            }
        });
    }

    private void updateToolBar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        ImageButton imageButton = toolbar.findViewById(R.id.ic_display_mode);
        imageButton.setVisibility(View.INVISIBLE);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void watchVideo(View v) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + launch.getYoutubeId()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + launch.getYoutubeId()));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) { }
    }
}
