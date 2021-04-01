package android.eservices.spacex.results.viewmodel;

import android.eservices.pogchamps.results.viewmodel.MatchViewModel;
import android.eservices.pogchamps.results.viewmodel.ParticipantViewModel;
import android.eservices.pogchamps.results.viewmodel.TournamentSelectViewModel;
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
        if (modelClass.isAssignableFrom(LaunchSelectViewModel.class)) {
            return (T) new LaunchSelectViewModel(launchRepository);
        }
        if (modelClass.isAssignableFrom(RocketSelectViewModel.class)) {
            return (T) new RocketSelectViewModel(rocketRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}