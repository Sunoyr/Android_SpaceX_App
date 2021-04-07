package android.eservices.spacex.presentation.fragment;

import android.eservices.spacex.R;
import android.eservices.spacex.data.di.FakeDependencyInjection;
import android.eservices.spacex.presentation.adapter.RocketAdapter;
import android.eservices.spacex.presentation.viewmodel.RocketViewModel;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RocketFragment extends BaseFragment {

    public static final String TAB_NAME = "Rockets";
    private View rootView;
    private RocketViewModel rocketViewModel;
    private RocketAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    private RocketFragment() {}

    public static RocketFragment newInstance() {
        return new RocketFragment();
    }

    @Override
    public void updateLayout(Boolean isLinear) {
        if (isLinear) {
            layoutManager = new LinearLayoutManager(getContext());
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_rocket, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.rockets);
        adapter = new RocketAdapter();
        recyclerView.setAdapter(adapter);
        updateLayout(true);
    }

    private void registerViewModels() {
        rocketViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(RocketViewModel.class);
        getRockets();
    }

    private void getRockets() {
        rocketViewModel.getRockets().observe(getViewLifecycleOwner(), rockets -> {
            adapter.bindViewModels(rockets);
        });
    }
}
