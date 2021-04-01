package android.eservices.spacex.data.repository.rocket;

import android.eservices.spacex.data.api.model.Rocket;

import java.util.List;

import io.reactivex.Single;

public interface IRocketRepository {
    Single<List<Rocket>> getAllRockets();

    Single<Rocket> getOneRocket(int rocketId);

}
