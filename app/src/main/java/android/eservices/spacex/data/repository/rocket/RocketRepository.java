package android.eservices.spacex.data.repository.rocket;

import android.eservices.spacex.data.api.model.rocket.Rocket;
import android.eservices.spacex.data.api.service.RocketService;

import java.util.List;

import io.reactivex.Single;

public class RocketRepository implements IRocketRepository {

    private RocketService rocketService;

    public RocketRepository(RocketService rocketService) {
        this.rocketService = rocketService;
    }

    @Override
    public Single<List<Rocket>> getAllRockets() {
        return rocketService.getAllRockets();
    }

    @Override
    public Single<Rocket> getOneRocket(int rocketId) {
        return rocketService.getOneRocket(rocketId);
    }
}
