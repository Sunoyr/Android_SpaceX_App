package android.eservices.spacex.data.di;

import android.content.Context;
import android.eservices.spacex.presentation.viewmodel.ViewModelFactory;
import android.eservices.spacex.data.api.service.LaunchService;
import android.eservices.spacex.data.api.service.RocketService;
import android.eservices.spacex.data.repository.launch.ILaunchRepository;
import android.eservices.spacex.data.repository.launch.LaunchRepository;
import android.eservices.spacex.data.repository.rocket.IRocketRepository;
import android.eservices.spacex.data.repository.rocket.RocketRepository;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Please never do that in a production app. Ever.
 * For the purpose of our course, this is the best option to cover interesting topics as
 * we don't have time to dig into Dependency Injection frameworks such as the famous Dagger.
 * Singleton are compulsory for some classes, such as the one here. If you don't know why, then ask me.
 * Note that this god object doesn't handle Scopes nor component lifecycles so this really shouldn't be
 * the way to go when you master the craft of your software.
 */
public class FakeDependencyInjection {

    private static LaunchService launchService;
    private static RocketService rocketService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static IRocketRepository rocketRepository;
    private static ILaunchRepository launchRepository;
    private static Context applicationContext;
    private static ViewModelFactory viewModelFactory;

    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getLaunchRepository(), getRocketRepository());
        }
        return viewModelFactory;
    }

    public static ILaunchRepository getLaunchRepository() {
        if (launchRepository == null) {
            launchRepository = new LaunchRepository(getLaunchService());
        }
        return launchRepository;
    }

    public static IRocketRepository getRocketRepository() {
        if (rocketRepository == null) {
            rocketRepository = new RocketRepository(getRocketService());
        }
        return rocketRepository;
    }

    public static LaunchService getLaunchService() {
        if (launchService == null) {
            launchService = getRetrofit().create(LaunchService.class);
        }
        return launchService;
    }

    private static RocketService getRocketService() {
        if (rocketService == null) {
            rocketService = getRetrofit().create(RocketService.class);
        }
        return rocketService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.spacexdata.com/v4/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }
}
