package android.eservices.spacex.data.repository.launch;

import android.eservices.spacex.data.api.model.Launch;
import android.eservices.spacex.data.api.service.LaunchService;
import android.eservices.spacex.data.repository.launch.ILaunchRepository;

import java.util.List;

import io.reactivex.Single;

public class LaunchRepository implements ILaunchRepository {

    private LaunchService launchService;

    public LaunchRepository(LaunchService launchService) {
        this.launchService = launchService;
    }

    @Override
    public Single<List<Launch>> getAllLaunchs() {
        return launchService.getAllLaunchs();
    }

    @Override
    public Single<Launch> getOneLaunch(int launchId) {
        return launchService.getOneLaunch(launchId);
    }
}
