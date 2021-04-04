package android.eservices.spacex.presentation;

import android.eservices.spacex.R;
import android.eservices.spacex.presentation.fragment.LaunchFragment;
import android.eservices.spacex.presentation.fragment.RocketFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }

    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);
        fragments.add(LaunchFragment.newInstance());
        fragments.add(RocketFragment.newInstance());
        
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
}
