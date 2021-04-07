package android.eservices.spacex.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public static final String LAYOUT = "layout";
    private boolean isLinear;

    public abstract void updateLayout(Boolean isLinear);

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.isLinear = getArguments().getBoolean(LAYOUT);
        return null;
    }
}
