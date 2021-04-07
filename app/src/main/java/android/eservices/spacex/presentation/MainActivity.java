package android.eservices.spacex.presentation;

import android.eservices.spacex.R;
import android.eservices.spacex.presentation.fragment.BaseFragment;
import android.eservices.spacex.presentation.fragment.LaunchFragment;
import android.eservices.spacex.presentation.fragment.RocketFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<BaseFragment> fragments = new ArrayList<>();
    private ImageButton imageBtn;
    private boolean isLinear = true;
    public static final String LAYOUT = "layout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();

        imageBtn = (ImageButton) findViewById(R.id.ic_display_mode);
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapDisplay(v);
            }
        });
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);
        Bundle bundle = new Bundle();
        bundle.putBoolean(LAYOUT, isLinear);
        fragments.add(LaunchFragment.newInstance());
        fragments.add(RocketFragment.newInstance());

        for(BaseFragment fragment:fragments) {
            fragment.setArguments(bundle);
        }

        viewPager.setAdapter(new FragmentPagerAdapter(
            getSupportFragmentManager(),
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return LaunchFragment.TAB_NAME;
                }
                return RocketFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

    public void swapDisplay(View v) {
        if (isLinear) {
            imageBtn.setImageResource(R.drawable.ic_list);
            isLinear = false;
        } else {
            imageBtn.setImageResource(R.drawable.ic_grid);
            isLinear = true;
        }
        updateFragments();
    }

    private void updateFragments() {
        for(BaseFragment fragment:fragments) {
            if(fragment.getView() != null){
                fragment.updateLayout(isLinear);
            }else {
                assert fragment.getArguments() != null;
                fragment.getArguments().putBoolean(LAYOUT, isLinear);
            }
        }
    }
}
