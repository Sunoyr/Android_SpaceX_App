package android.eservices.spacex.presentation.viewmodel;

import android.eservices.spacex.data.api.model.launch.Launch;
import android.eservices.spacex.data.repository.launch.ILaunchRepository;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LaunchViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable;
    private ILaunchRepository launchRepository;

    public LaunchViewModel(ILaunchRepository launchRepository) {
        this.launchRepository = launchRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    private MutableLiveData<List<Launch>> launches;
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public MutableLiveData<List<Launch>> getLaunches() {
        isDataLoading.postValue(true);
        if(this.launches == null){
            launches = new MutableLiveData<>();
            compositeDisposable.clear();
            compositeDisposable.add(launchRepository.getAllLaunches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Launch>>() {
                    @Override
                    public void onSuccess(@NonNull List<Launch> launchList) {
                        launches.setValue(launchList);
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("myTag","on passe dans le if du launchViewModel");
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }
                }));
        }
        return launches;
    }
}