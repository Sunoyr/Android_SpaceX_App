package android.eservices.spacex;

import android.app.Application;
import android.eservices.spacex.data.di.FakeDependencyInjection;

import com.facebook.stetho.Stetho;

public class SpaceXApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
