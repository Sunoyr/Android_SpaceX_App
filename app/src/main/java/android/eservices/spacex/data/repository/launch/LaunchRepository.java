package android.eservices.spacex.data.repository.launch;

import android.eservices.spacex.data.api.model.launch.Launch;
import android.eservices.spacex.data.api.service.LaunchService;

import java.util.List;

import io.reactivex.Single;

public class LaunchRepository implements ILaunchRepository {

    private LaunchService launchService;

    public LaunchRepository(LaunchService launchService) {
        this.launchService = launchService;
    }

    @Override
    public Single<List<Launch>> getAllLaunches() {
        return launchService.getAllLaunches();
    }

    @Override
    public Single<Launch> getOneLaunch(int launchId) {
        return launchService.getOneLaunch(launchId);
    }
}
