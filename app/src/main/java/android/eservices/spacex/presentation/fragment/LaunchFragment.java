package android.eservices.spacex.presentation.fragment;

import android.eservices.spacex.R;
import android.eservices.spacex.data.di.FakeDependencyInjection;
import android.eservices.spacex.presentation.MainActivity;
import android.eservices.spacex.presentation.adapter.LaunchAdapter;
import android.eservices.spacex.presentation.viewmodel.LaunchViewModel;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LaunchFragment extends Fragment {

    public static final String TAB_NAME = "Launches";
    private View rootView;
    private LaunchViewModel launchViewModel;
    private LaunchAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private LaunchFragment() { }

    public static LaunchFragment newInstance() {
        return new LaunchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_launch, container, false);
        setupRecyclerView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerViewModels();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = rootView.findViewById(R.id.launches);
        recyclerView.setHasFixedSize(true);
        adapter = new LaunchAdapter();
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        /*ImageButton imageButton = (ImageButton) rootView.findViewById(R.id.ic_display_mode);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutManager instanceof GridLayoutManager) {
                    layoutManager = new LinearLayoutManager(getContext());
                } else {
                    layoutManager = new GridLayoutManager(getContext(), 2);
                }
                recyclerView.setLayoutManager(layoutManager);
            }
        });*/

        recyclerView.setNestedScrollingEnabled(false);
    }

    private void registerViewModels() {
        launchViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(LaunchViewModel.class);
        getLaunches();
    }

    private void getLaunches() {
        launchViewModel.getLaunches().observe(getViewLifecycleOwner(), launches -> {
            adapter.bindViewModels(launches);
        });
    }
}
