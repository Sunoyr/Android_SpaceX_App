package android.eservices.spacex.presentation;

import android.eservices.spacex.R;
import android.eservices.spacex.data.api.model.rocket.Rocket;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class RocketDetailsActivity extends AppCompatActivity {
    private static final String ROCKET = "rocket";
    protected Toolbar toolbar;
    public Rocket rocket;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rocket_details);
        rocket = (Rocket) getIntent().getSerializableExtra(ROCKET);
        updateToolBar();
        setupView();
    }

    private void setupView() {
        View v = findViewById(R.id.rocket_details);

        TextView name = v.findViewById(R.id.rocket_name_textview);
        name.setText(R.string.rocket_title+rocket.getName());

        ImageView imageView = v.findViewById(R.id.rocket_image_imageView);

        CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
        loader.start();

        Glide.with(v)
                .load(rocket.getFlickr_image())
                .placeholder(loader)
                .into(imageView);

        TextView description = v.findViewById(R.id.rocket_description_textView);
        description.setText(R.string.rocket_description+rocket.getDescription());

        TextView height = v.findViewById(R.id.rocket_height_textView);
        height.setText(R.string.rocket_height+rocket.getHeight()+"");

        TextView diameter = v.findViewById(R.id.rocket_diameter_textView);
        diameter.setText(R.string.rocket_diameter+rocket.getDiameter()+"");

        TextView mass = v.findViewById(R.id.rocket_mass_textView);
        mass.setText(R.string.rocket_mass+rocket.getMass()+"");

        TextView engines = v.findViewById(R.id.rocket_engines_textView);
        engines.setText(R.string.rocket_engines+rocket.getEngines()+"");

        TextView engine_type = v.findViewById(R.id.rocket_engines_type_textView);
        engine_type.setText(R.string.rocket_engines_type+rocket.getEnginesType());

        TextView cost_per_launch = v.findViewById(R.id.rocket_cost_per_launch_textView);
        cost_per_launch.setText(R.string.rocket_cost_per_launch+rocket.getCost_per_launch()+"");

        TextView sucess_rate = v.findViewById(R.id.rocket_success_rate_textView);
        sucess_rate.setText(R.string.rocket_success_rate+rocket.getSuccess_rate()+"");

        TextView first_flight = v.findViewById(R.id.rocket_first_flight_textView);
        first_flight.setText(R.string.rocket_first_flight+(new SimpleDateFormat("MM-dd-yyyy hh:ss", Locale.ENGLISH)).format(rocket.getFirst_flight()));
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
}
