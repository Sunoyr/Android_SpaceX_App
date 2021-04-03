package android.eservices.spacex.presentation.viewmodel;

import android.eservices.spacex.data.repository.launch.ILaunchRepository;
import android.eservices.spacex.data.repository.rocket.IRocketRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.reactivex.annotations.NonNull;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ILaunchRepository launchRepository;
    private final IRocketRepository rocketRepository;

    public ViewModelFactory(ILaunchRepository launchRepository, IRocketRepository rocketRepository) {
        this.launchRepository = launchRepository;
        this.rocketRepository = rocketRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LaunchViewModel.class)) {
            return (T) new LaunchViewModel(launchRepository);
        }
        if (modelClass.isAssignableFrom(RocketViewModel.class)) {
            return (T) new RocketViewModel(rocketRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}