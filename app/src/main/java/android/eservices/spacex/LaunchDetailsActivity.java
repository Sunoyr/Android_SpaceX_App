package android.eservices.spacex;

import android.eservices.spacex.data.api.model.launch.Launch;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

public class LaunchDetailsActivity extends AppCompatActivity {

    private static final String LAUNCH = "launch";
    protected Launch launch;
    protected Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_details);
        launch = (Launch) getIntent().getSerializableExtra(LAUNCH) ;
        updateToolBar();
        setupView();
    }

    private void setupView() {
        View v = findViewById(R.id.launch_details);
        ImageView img = v.findViewById(R.id.launch_picture_imageView);

        CircularProgressDrawable loader = new CircularProgressDrawable(v.getContext());
        loader.start();

        Log.d("MyTag", launch.getOriginalPictures().get(0));
        Glide.with(v)
                .load(launch.getOriginalPictures().get(0))
                .placeholder(loader)
                .into(img);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateToolBar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
