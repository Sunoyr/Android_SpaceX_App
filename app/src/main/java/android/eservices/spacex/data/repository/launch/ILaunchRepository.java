package android.eservices.spacex.data.repository.launch;

import android.eservices.spacex.data.api.model.Launch;

import java.util.List;

import io.reactivex.Single;

public interface ILaunchRepository {
    Single<List<Launch>> getAllLaunchs();

    Single<Launch> getOneLaunch(int launchId);

}
