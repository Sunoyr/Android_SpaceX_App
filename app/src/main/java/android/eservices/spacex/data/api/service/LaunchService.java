package android.eservices.spacex.data.api.service;


import android.eservices.spacex.data.api.model.launch.Launch;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LaunchService {
    /**
     * Récupérer l'ensemble des launchs
     * @return la liste des launchs
     */
    @GET("launches")
    Single<List<Launch>> getAllLaunches();


    /**
     * Récupérer un launch .
     * @param launchId
     * @return la liste des launchs
     */
    @GET("launches/{launchId}")
    Single<Launch> getOneLaunch(@Path("launchId") int launchId);
}
