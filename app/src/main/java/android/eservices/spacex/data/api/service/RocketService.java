package android.eservices.spacex.data.api.service;


import android.eservices.spacex.data.api.model.rocket.Rocket;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RocketService {
    /**
     * Récupérer l'ensemble des rockets spaceX
     * @return la liste des rockets
     */
    @GET("rockets")
    Single<List<Rocket>> getAllRockets();


    /**
     * Récupérer une rocket spaceX
     * @param rocketId
     * @return une rocket
     */
    @GET("rockets/{rocketId}")
    Single<Rocket> getOneRocket(@Path("rocketId") int rocketId);
}
